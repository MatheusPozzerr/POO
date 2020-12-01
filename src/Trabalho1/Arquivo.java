package Trabalho1;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Stack;

public class Arquivo {
    private Stack <Contrato> traceContratos = new Stack<>();
    private Prestador prestador;
    private ClienteIndividual clienteIndividual;
    private ClienteEmpresarial clienteEmpresarial;
    private Servico servico;
    private Contrato contrato;
    Portifolio portifolio = Main.portifolio;
    private Dictionary<Integer, Servico> catalogo = new Hashtable<Integer, Servico>();
    private Path filePath = Paths.get("Acme_Jobs.txt");
    private Dictionary <Servico, Integer> addSer;
    private ArrayList<Contrato> addCon;
    private ArrayList <Contrato> addConConfirmado;
    private ArrayList <Contrato> addConTerminado;
    private ArrayList <Contrato> addConCancelado;
    private ArrayList <Contrato> addConPago;

    public void salvaDados(Portifolio portifolio) {
        try (PrintWriter writer = new PrintWriter
                (Files.newBufferedWriter(filePath, Charset.defaultCharset()))) {

            writer.println(portifolio.toString());
            writer.println("\n[SERVICO]");
            addSer = new Hashtable<>();
            int cont;
            for (cont =0; cont < portifolio.getServicos().size() ; cont++){
                addSer.put( portifolio.getServicos().get(cont), (cont +1));
            }
            writer.println(addSer.toString().replace("{", "").replace("}", ""). replace("=", ";").replace(", ", "\n"));
            writer.println("\n[CONTRATO]");
            addCon = new ArrayList<>();
            addCon = portifolio.getContratosContratados();
            int numero2 = 1;
            for (Contrato contrato :addCon) {
                int nro_servico = addSer.get(contrato.getServico());
                writer.println(numero2 + ";"+ contrato.getCPFCliente() + ";" + nro_servico + ";"+ contrato.toStringArquivo());
                numero2++;
            }
            writer.println("\n[CONTRATOCONFIRMADO]");
            addConConfirmado = new ArrayList<>();
            addConConfirmado = portifolio.getContratosConfirmado();
            int numero3 = 1;
            for (Contrato contrato :addConConfirmado) {
                int nro_servico = addSer.get(contrato.getServico());
                writer.println(numero3 + ";"+ contrato.getCPFCliente() + ";" + nro_servico + ";"+ contrato.toStringArquivo());
                numero3++;
            }
            writer.println("\n[CONTRATOTERMINADO]");
            addConTerminado = new ArrayList<>();
            addConTerminado = portifolio.getContratosTerminados();
            int numero4 = 1;
            for (Contrato contrato :addConTerminado) {
                int nro_servico = addSer.get(contrato.getServico());
                writer.println(numero4 + ";"+ contrato.getCPFCliente() + ";" + nro_servico + ";"+ contrato.toStringArquivo());
                numero4++;
            }
            writer.println("\n[CONTRATOCANCELADO]");
            addConCancelado = new ArrayList<>();
            addConCancelado = portifolio.getContratosCancelados();
            int numero5 = 1;
            for (Contrato contrato :addConCancelado) {
                int nro_servico = addSer.get(contrato.getServico());
                writer.println(numero5 + ";"+ contrato.getCPFCliente() + ";" + nro_servico + ";"+ contrato.toStringArquivo());
                numero5++;
            }
            writer.println("\n[CONTRATOPAGO]");
            addConPago = new ArrayList<>();
            addConPago = portifolio.getContratosPagos();
            int numero6 = 1;
            for (Contrato contrato :addConPago) {
                int nro_servico = addSer.get(contrato.getServico());
                writer.println(numero6 + ";"+ contrato.getCPFCliente() + ";" + nro_servico + ";"+ contrato.toStringArquivo());
                numero6++;
            }
        } catch (IOException e) {
            System.err.println("Caminho inválido, tente novamente");
        }
    }

    public String leDados(String text){


        Path ADMpath = Paths.get(text);
        try (BufferedReader reader = java.nio.file.Files.newBufferedReader(ADMpath, Charset.defaultCharset())) {
            String line = reader.readLine();
            while((line = reader.readLine()) != null){
                if (line.equals("[PRESTADOR]")) {
                    line = reader.readLine();
                    while(line.length() >0){

                        String[] stringEditada = line.trim().split(";");
                        prestador = new Prestador(stringEditada[1], //nome
                                Integer.parseInt((stringEditada[2].replace("-", ""))), //
                                stringEditada[3],
                                stringEditada[0].replace(".", "").replace("-", "")); // email
                        portifolio.adicionaPrestador(prestador);
                        line = reader.readLine();
                        if (line == null){
                            break;
                        }
                    }
                }
                else if (line.equals("[CLIENTEINDIVIDUAL]")){
                    line = reader.readLine();
                    while(line.length() >0){

                        String[] stringEditada = line.trim().split(";");
                        clienteIndividual = new ClienteIndividual(stringEditada[1], //nome
                                Integer.parseInt((stringEditada[2].replace("-", ""))), //telefone
                                stringEditada[3], //email
                                stringEditada[4], // endereco
                                stringEditada[0].replace(".", "").replace("-", ""));// cpf
                        portifolio.adicionaClienteIndividual(clienteIndividual);
                        line = reader.readLine();
                        if (line == null){
                            break;
                        }
                    }
                }
                else if (line.equals("[CLIENTEEMPRESARIAL]")){
                    line = reader.readLine();
                    while(line.length() >0){

                        String[] stringEditada = line.trim().split(";");
                        clienteEmpresarial = new ClienteEmpresarial(stringEditada[5], //nome fantasia
                                stringEditada[1], //nome
                                Integer.parseInt((stringEditada[2].replace("-", ""))), //telefone
                                stringEditada[3], //email
                                stringEditada[4], // endereco
                                stringEditada[0].replace(".", "").replace("-", ""));// cnpj
                        portifolio.adicionaClienteEmpresarial(clienteEmpresarial);
                        line = reader.readLine();
                        if (line == null){
                            break;
                        }
                    }
                }
                else if (line.equals("[SERVICO]")){
                    line = reader.readLine();

                    while(line.length() >0){

                        String[] stringEditada = line.trim().split(";");
                        double valor = Double.parseDouble(stringEditada[3].replace(",", "."));
                        servico = new Servico(valor, stringEditada[4],stringEditada[2]);
                        portifolio.adicionaServico(servico, stringEditada[0].replace(".", "").replace("-", ""));
                        catalogo.put(Integer.parseInt(stringEditada[0]), servico);
                        line = reader.readLine();
                        if (line == null){
                            break;
                        }
                    }
                }
                else if (line.equals("[CONTRATO]")){
                    line = reader.readLine();
                    int index = 0;
                    while(line.length() >0){
                        String[] stringEditada = line.trim().split(";");
                        int identificador = Integer.parseInt(stringEditada[3]);
                        String cpfOuCnpj = stringEditada[2].replace(".", "").replace("-", "");
                        String[] data = stringEditada[4].trim().split("/");
                        String[] horario = stringEditada[5].trim().split(":");
                        double valor = Double.parseDouble((stringEditada[6].replace(",", ".")));
                        LocalDateTime agendamento = LocalDateTime.of(Integer.parseInt(data[2]), //ano
                                Integer.parseInt(data[1]), //mes
                                Integer.parseInt(data[0]), // dia
                                Integer.parseInt(horario[0]), //hora
                                Integer.parseInt(horario[1])
                        );

                        contrato = new Contrato(valor, //valor
                                stringEditada[7], //observacao
                                agendamento);// cnpj
                        traceContratos.push(contrato);
                        portifolio.adicionaContrato(traceContratos.get(index), catalogo.get(identificador),cpfOuCnpj );
                        line = reader.readLine();
                        index++;
                        if (line == null){
                            break;
                        }
                    }

                }

            }
            reader.close();
            return "OK";
        }
        catch (IOException e) {
            return "N";
        }

    }

    public void leArquivo(Portifolio portifolio){


        try (BufferedReader reader2 = java.nio.file.Files.newBufferedReader(filePath, Charset.defaultCharset())) {
            String line;
            while((line = reader2.readLine()) != null){
                if (line.equals("[PRESTADOR]")) {
                    line = reader2.readLine();
                    while (line.length() > 0) {
                        String[] stringEditada = line.trim().split(";");
                        prestador = new Prestador(stringEditada[1], //nome
                                Integer.parseInt((stringEditada[2].replace("-", ""))), //telefone
                                stringEditada[3],// email
                                stringEditada[0].replace(".", "").replace("-", "")); // cpf
                        portifolio.adicionaPrestador(prestador);
                        line = reader2.readLine();
                        if (line == null) {
                            break;
                        }
                    }
                }
                else if (line.equals("[CLIENTEINDIVIDUAL]")){
                    line = reader2.readLine();
                    while(line.length() >0){

                        String[] stringEditada = line.trim().split(";");
                        clienteIndividual = new ClienteIndividual(stringEditada[1], //nome
                                Integer.parseInt((stringEditada[2].replace("-", ""))), //telefone
                                stringEditada[3], //email
                                stringEditada[4], // endereco
                                stringEditada[0].replace(".", "").replace("-", ""));// cpf
                        portifolio.adicionaClienteIndividual(clienteIndividual);
                        line = reader2.readLine();
                        if (line == null){
                            break;
                        }
                    }
                }
                else if (line.equals("[CLIENTEEMPRESARIAL]")){
                    line = reader2.readLine();
                    while(line.length() >0){

                        String[] stringEditada = line.trim().split(";");
                        clienteEmpresarial = new ClienteEmpresarial(stringEditada[5], //nome fantasia
                                stringEditada[1], //nome
                                Integer.parseInt((stringEditada[2].replace("-", ""))), //telefone
                                stringEditada[3], //email
                                stringEditada[4], // endereco
                                stringEditada[0].replace(".", "").replace("-", ""));// cnpj
                        portifolio.adicionaClienteEmpresarial(clienteEmpresarial);
                        line = reader2.readLine();
                        if (line == null){
                            break;
                        }
                    }
                }
                else if (line.equals("[SERVICO]")){
                    line = reader2.readLine();

                    while(line.length() >0){

                        String[] stringEditada = line.trim().split(";");
                        double valor = Double.parseDouble(stringEditada[2].replace(",", "."));
                        servico = new Servico(valor, //valor
                                stringEditada[1],
                                stringEditada[3]);// cnpj
                        portifolio.adicionaServico(servico, stringEditada[0].replace(".", "").replace("-", ""));
                        catalogo.put(Integer.parseInt(stringEditada[4]), servico);
                        line = reader2.readLine();
                        if (line == null){
                            break;
                        }
                    }
                }
                else if (line.equals("[CONTRATO]")){
                    line = reader2.readLine();

                    while(line.length() >0){
                        String[] stringEditada = line.trim().split(";");
                        int identificador = Integer.parseInt(stringEditada[2]);
                        String cpfOuCnpj = stringEditada[1].replace(".", "").replace("-", "");
                        String data = stringEditada[3].replace("-", "/").replace("T", ";");

                        double valor = Double.parseDouble((stringEditada[4].replace(",", ".")));
                        LocalDateTime agendamento = LocalDateTime.of(Integer.parseInt(data.substring(0,4)), //ano
                                Integer.parseInt(data.substring(5,7)), //mes
                                Integer.parseInt(data.substring(8,10)), // dia
                                Integer.parseInt(data.substring(11,13)), //hora
                                Integer.parseInt(data.substring(14,16))
                        );
                        contrato = new Contrato(valor, //valor
                                stringEditada[5], //observacao
                                agendamento);// cnpj

                        portifolio.adicionaContrato(contrato, catalogo.get(identificador),cpfOuCnpj );

                        line = reader2.readLine();
                        if (line == null){
                            break;
                        }
                    }

                }
                else if (line.equals("[CONTRATOCONFIRMADO]")){
                    line = reader2.readLine();

                    while(line.length() >0){
                        String[] stringEditada = line.trim().split(";");
                        int identificador = Integer.parseInt(stringEditada[2]);
                        String cpfOuCnpj = stringEditada[1].replace(".", "").replace("-", "");
                        String data = stringEditada[3].replace("-", "/").replace("T", ";");

                        double valor = Double.parseDouble((stringEditada[4].replace(",", ".")));
                        LocalDateTime agendamento = LocalDateTime.of(Integer.parseInt(data.substring(0,4)), //ano
                                Integer.parseInt(data.substring(5,7)), //mes
                                Integer.parseInt(data.substring(8,10)), // dia
                                Integer.parseInt(data.substring(11,13)), //hora
                                Integer.parseInt(data.substring(14,16))
                        );
                        contrato = new Contrato(valor, //valor
                                stringEditada[5], //observacao
                                agendamento);// cnpj

                        portifolio.adicionaContrato(contrato, catalogo.get(identificador),cpfOuCnpj);
                        portifolio.removeContratoContratados(contrato);

                        line = reader2.readLine();
                        if (line == null){
                            break;
                        }
                    }

                }
                else if (line.equals("[CONTRATOCANCELADO]")){
                    line = reader2.readLine();

                    while(line.length() >0){
                        String[] stringEditada = line.trim().split(";");
                        int identificador = Integer.parseInt(stringEditada[2]);
                        String cpfOuCnpj = stringEditada[1].replace(".", "").replace("-", "");
                        String data = stringEditada[3].replace("-", "/").replace("T", ";");

                        double valor = Double.parseDouble((stringEditada[4].replace(",", ".")));
                        LocalDateTime agendamento = LocalDateTime.of(Integer.parseInt(data.substring(0,4)), //ano
                                Integer.parseInt(data.substring(5,7)), //mes
                                Integer.parseInt(data.substring(8,10)), // dia
                                Integer.parseInt(data.substring(11,13)), //hora
                                Integer.parseInt(data.substring(14,16))
                        );
                        contrato = new Contrato(valor, //valor
                                stringEditada[5], //observacao
                                agendamento);// cnpj

                        portifolio.adicionaContrato(contrato, catalogo.get(identificador),cpfOuCnpj);
                        portifolio.removeContratoContratadosCancelando(contrato);

                        line = reader2.readLine();
                        if (line == null){
                            break;
                        }
                    }

                }
                else if (line.equals("[CONTRATOTERMINADO]")){
                    line = reader2.readLine();

                    while(line.length() >0){
                        String[] stringEditada = line.trim().split(";");
                        int identificador = Integer.parseInt(stringEditada[2]);
                        String cpfOuCnpj = stringEditada[1].replace(".", "").replace("-", "");
                        String data = stringEditada[3].replace("-", "/").replace("T", ";");

                        double valor = Double.parseDouble((stringEditada[4].replace(",", ".")));
                        LocalDateTime agendamento = LocalDateTime.of(Integer.parseInt(data.substring(0,4)), //ano
                                Integer.parseInt(data.substring(5,7)), //mes
                                Integer.parseInt(data.substring(8,10)), // dia
                                Integer.parseInt(data.substring(11,13)), //hora
                                Integer.parseInt(data.substring(14,16))
                        );
                        contrato = new Contrato(valor, //valor
                                stringEditada[5], //observacao
                                agendamento);// cnpj

                        portifolio.adicionaContrato(contrato, catalogo.get(identificador),cpfOuCnpj);
                        portifolio.removeContratoConfirmado(contrato);

                        line = reader2.readLine();
                        if (line == null){
                            break;
                        }
                    }

                }
                else if (line.equals("[CONTRATOPAGO]")){
                    line = reader2.readLine();
                    if (line == null){
                        break;
                    }
                    while(line.length() >0){
                        String[] stringEditada = line.trim().split(";");
                        int identificador = Integer.parseInt(stringEditada[2]);
                        String cpfOuCnpj = stringEditada[1].replace(".", "").replace("-", "");
                        String data = stringEditada[3].replace("-", "/").replace("T", ";");

                        double valor = Double.parseDouble((stringEditada[4].replace(",", ".")));
                        LocalDateTime agendamento = LocalDateTime.of(Integer.parseInt(data.substring(0,4)), //ano
                                Integer.parseInt(data.substring(5,7)), //mes
                                Integer.parseInt(data.substring(8,10)), // dia
                                Integer.parseInt(data.substring(11,13)), //hora
                                Integer.parseInt(data.substring(14,16))
                        );
                        contrato = new Contrato(valor, //valor
                                stringEditada[5], //observacao
                                agendamento);// cnpj

                        portifolio.adicionaContrato(contrato, catalogo.get(identificador),cpfOuCnpj);
                        portifolio.removeContratoTerminado(contrato);

                        line = reader2.readLine();
                    }

                }
            }
            reader2.close();
        }catch (IOException e){

        }
    }
}
