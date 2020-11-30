package Trabalho1;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Usuario {

    private String nome;
    private int telefone;
    private String email;
    private String cpf;
    protected Portifolio portifolio;
    protected final Scanner in = new Scanner(System.in);

    public int getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }
    public Usuario(Portifolio portifolio) {
        this.portifolio = portifolio;
    }

    public Usuario(String nome, int telefone, String email, String cpf,Portifolio portifolio){
        this.nome=nome;
        this.telefone=telefone;
        this.email=email;
        this.cpf=cpf;
        this.portifolio=portifolio;
    }

    public Usuario(String nome, int telefone, String email, String cpf){
        this.nome=nome;
        this.telefone=telefone;
        this.email=email;
        this.cpf=cpf;
    }


    public void cadastrarCliente() {
        System.out.println("Cadastramento de clientes, digite:");
        boolean teste = false;
        while (teste == false) {
            System.out.println("Digite 1: Para cadastrar um cliente fisico \nDigite 2: Para cadastrar um cliente empresarial\nDigite 3: Para sair do cadastramento");
            int decisao = this.in.nextInt();
            if (decisao == 1) {
                System.out.println("Primeiro digite o nome do cliente:");
                String nomeCliente = this.in.next().trim();
                System.out.println("Agora insira o seu telefone/celular:");
                int numeroCelular = this.in.nextInt();
                System.out.println("E-mail:");
                this.in.nextLine();
                String email = this.in.nextLine().trim();
                System.out.println("endereco:");
                String endereco = this.in.nextLine().trim();
                System.out.println("Agora digite o cpf(11 digitos sem os caracteres especiais):");
                String cpf = this.in.nextLine().trim();
                boolean ehDigitos = VerificaDigitosCpfOuCnpj(cpf);
                if (ehDigitos == true && cpf.length() == 11) {
                    Cliente cliente = new ClienteIndividual(nomeCliente, numeroCelular, email, endereco, cpf);
                    boolean verificando = portifolio.adicionaClienteIndividual(cliente);
                    if (verificando == false) {
                        System.out.println("Cpf já cadastrado");
                    } else {
                        System.out.println("Usuario cadastrado com sucesso");
                    }
                } else {
                    System.out.println("Insira um cpf valido");
                }
            }


            if (decisao == 2) {
                System.out.println("Primeiro digite o nomeFantasia do cliente empresarial:");
                String nomeFantasia = this.in.next().trim();
                System.out.println("Agora digite o nome do cliente empresarial:");
                String nome = this.in.next().trim();
                System.out.println("Insira o seu telefone/celular:");
                int numeroCelular = this.in.nextInt();
                System.out.println("E-mail:");
                this.in.nextLine();
                String email = this.in.nextLine().trim();
                System.out.println("endereco:");
                String endereco = this.in.nextLine().trim();
                System.out.println("Agora digite o cnpj(14 digitos sem os caracteres especias):");
                String cnpj = this.in.nextLine();
                boolean ehDigitos = VerificaDigitosCpfOuCnpj(cnpj);
                if (ehDigitos == true && cnpj.length() == 14) {
                    ClienteEmpresarial clienteEmpresa = new ClienteEmpresarial(nomeFantasia,nome, numeroCelular, email, endereco, cnpj);
                    boolean verificando = portifolio.adicionaClienteEmpresarial(clienteEmpresa);
                    if (verificando == false) {
                        System.out.println("Cnpj já cadastrado");
                    } else {
                        System.out.println("Usuário cadastrado com sucesso");
                    }
                } else {
                    System.out.println("Insira um cnpj válido");
                }
            }
            if (decisao == 3) {
                System.out.println("Saindo do cadastramento de clientes...\n");
                teste = true;
            }
            if (decisao > 3 || decisao <= 0) {
                System.out.println("Insira um numero valido");
            }
        }
    }

    public void cadastrarPrestador() {
        System.out.println("Cadastramento de prestadores, digite:");
        boolean teste = false;
        while (teste == false) {
            System.out.println("Digite 1: Para cadastrar um prestador \nDigite 2: Para sair do cadastramento de prestadores.");
            int decisao = in.nextInt();
            if (decisao == 1) {
                System.out.println("Primeiro digite o nome do prestador:");
                String correcao = in.nextLine();
                String nomeCliente = in.nextLine().trim();
                System.out.println("Agora insira o seu telefone/celular:");
                int numeroCelular = in.nextInt();
                System.out.println("E-mail:");
                in.nextLine();
                String email = in.nextLine().trim();
                System.out.println("Agora digite o cpf(11 digitos sem os caracteres especiais):");
                String cpf = in.nextLine().trim();
                boolean ehDigitos = VerificaDigitosCpfOuCnpj(cpf);
                if (ehDigitos == true && cpf.length() == 11) {
                    Prestador prestador = new Prestador(nomeCliente, numeroCelular, email, cpf,portifolio);
                    boolean verificando = portifolio.adicionaPrestador(prestador);
                    if (verificando == true) {
                        System.out.println("Prestador cadastrado com sucesso\n");
                    }
                } else {
                    System.out.println("Erro: Insira um cpf valido");
                }
            }
            if (decisao == 2) {
                teste = true;
                System.out.println("Saindo do cadastramednto de prestadores...\n");
            }
            if (decisao > 2 || decisao <= 0) {
                System.out.println("Insira um numero valido");
            }
        }
    }

    public void cadastrarServico() {
        System.out.println("Cadastramento de servico, digite:");
        boolean teste = false;
        while (teste == false) {
            System.out.println("Digite 1:Cadastrar servico.\nDigite 2:Sair da tela de cadastramento de servico.");
            int decisao = in.nextInt();
            if (decisao == 1) {
                System.out.println("Insira o cpf do prestador que deseja cadastrar seu servico:");
                String correcao = in.nextLine();
                String cpf = in.nextLine().trim();
                boolean testandoCpf = portifolio.verificaCpfPrestador(cpf);
                if (testandoCpf == true) {
                    System.out.println("Agora insira a descricao do trabalho");
                    String descricao = in.nextLine().trim();
                    System.out.println("O valor do servico");
                    int valor = in.nextInt();
                    System.out.println("Observacao");
                    correcao = in.nextLine();
                    String observacao = in.nextLine().trim();
                    Servico servico = new Servico(valor, observacao, descricao);
                    servico.setPrestador(portifolio.buscaCpfPrestador(cpf));
                    boolean testandoDescricao = portifolio.adicionaServico(servico, cpf);
                    if (testandoDescricao == true) {
                        System.out.println("Servico cadastrado \n");
                    } else {
                        System.out.println("Erro: Servico ja cadastrado pelo prestador");
                    }
                } else {
                    System.out.println("Erro: Cpf não cadastrado em prestadores.");
                }
            }
            if (decisao == 2) {
                System.out.println("Saindo do cadastramento de servico... \n");
                teste = true;
            }
            if (decisao > 2 || decisao <= 0) {
                System.out.println("Insira um numero valido");

            }
        }
    }

    public void cadastrarContrato() {
        System.out.println("Cadastramento de contratos, digite:");
        boolean teste = false;
        while (teste == false) {
            System.out.println("Digite 1:Cadastrar contrato.\nDigite 2:Sair da tela de cadastramento de contratos.");
            int decisao = in.nextInt();
            if (decisao == 1) {
                System.out.println("Digite 1:Cadastrar contrato de cliente individual.\nDigite 2:Cadastrar contrato de cliente empresarial");
                int escolha = in.nextInt();
                if (escolha == 1) {
                    System.out.println("Digite o cpf do cliente");
                    String correcao = in.nextLine();
                    String cpf = in.nextLine();
                    boolean verificandoCpf = portifolio.verificaCpfCliente(cpf);
                    if (verificandoCpf == true) {
                        ArrayList<Servico> catalogo;
                        catalogo = portifolio.getServicos();
                        int num = 1;
                        System.out.println("Lista de servicos cadastrados:");
                        for (Servico servico : catalogo) {
                            System.out.println(num + ":" + servico + "\n");
                            num++;
                        }
                        System.out.println("Qual servico desejado, insira o numero correspondente ao servico");
                        int numero = in.nextInt();
                        numero = numero - 1;
                        System.out.println("Insira a data a qual gostaria do servico, primeiro coloque o ano:");
                        int ano = in.nextInt();
                        System.out.println("Agora o mes, em número:");
                        int mes = in.nextInt();
                        System.out.println("O dia:");
                        int dia = in.nextInt();
                        System.out.println("O horario, colocando primeiro as horas:");
                        int hora = in.nextInt();
                        System.out.println("Agora os minutos:");
                        int minutos = in.nextInt();
                        LocalDateTime horario = LocalDateTime.of(ano, mes, dia, hora, minutos);
                        System.out.println("A observacao sobre o servico");
                        correcao = in.nextLine();
                        String observacao = in.nextLine();
                        System.out.println("Quantos porcento de desconto a ACMEjobs gostaria de conceder, caso nao queira digite 0");
                        double porcentagem = in.nextDouble();
                        while (0>porcentagem || porcentagem > 100) {
                            System.out.println("Insira uma porcentagem de desconto válido");
                            porcentagem = in.nextDouble();
                        }
                        double valorInicial = catalogo.get(numero).getValor();
                        double quantidadeDeDesconto = 0;
                        if (porcentagem > 0) {
                            quantidadeDeDesconto = (valorInicial * porcentagem) / 100;
                        }
                        double valorFinal = valorInicial - quantidadeDeDesconto;
                        Contrato novoContrato = new Contrato(valorFinal, observacao, horario);
                        novoContrato.setDesconto(quantidadeDeDesconto);
                        novoContrato.setCliente(portifolio.buscaCpfCliente(cpf));
                        novoContrato.setPrestador(catalogo.get(numero).getPrestador());
                        novoContrato.setServico(catalogo.get(numero));
                        portifolio.buscaCpfCliente(cpf).adicionaContrato(novoContrato);
                        System.out.println("Contrato cadastrado. Dados:");
                        System.out.println("Cliente: " + portifolio.buscaCpfCliente(cpf).toString());
                        System.out.print("Servico: " + catalogo.get(numero).getDescricao() + " ; valor: " + String.format("%.2f", catalogo.get(numero).getValor()) + " ; data: " + horario + ".");
                        System.out.println(" ; desconto: " + String.format("%.2f", quantidadeDeDesconto) + " ; valor final: " + String.format("%.2f", valorFinal) + " ; observacao: " + observacao + ".");
                        System.out.println("Prestador: " + catalogo.get(numero).getPrestador().toString());
                        portifolio.adicionaContrato(novoContrato, catalogo.get(numero), cpf);
                    }
                    if (verificandoCpf == false) {
                        System.out.println("ERRO:Cpf nao cadastrado");
                    }

                }
                if (escolha == 2) {
                    boolean verificandoCnpj;
                    System.out.println("Digite o cnpj do cliente");
                    String correcao = in.nextLine();
                    String cnpj = in.nextLine();
                    verificandoCnpj = portifolio.verificaCnpjCliente(cnpj);
                    if (verificandoCnpj == true) {
                        ArrayList<Servico> catalogo;
                        catalogo = portifolio.getServicos();
                        int num = 1;
                        System.out.println("Lista de servicos cadastrados:");
                        for (Servico servico : catalogo) {
                            System.out.println(num + ":" + servico + "\n");
                            num++;
                        }
                        System.out.println("Qual servico desejado, insira o numero correspondente ao servico");
                        int numero = in.nextInt();
                        numero = numero - 1;
                        System.out.println("Insira a data a qual gostaria do servico, primeiro coloque o ano:");
                        int ano = in.nextInt();
                        System.out.println("Agora o mes, em número:");
                        int mes = in.nextInt();
                        System.out.println("O dia:");
                        int dia = in.nextInt();
                        System.out.println("O horario, colocando primeiro as horas:");
                        int hora = in.nextInt();
                        System.out.println("Agora os minutos:");
                        int minutos = in.nextInt();
                        LocalDateTime horario = LocalDateTime.of(ano, mes, dia, hora, minutos);
                        System.out.println("A observacao sobre o servico");
                        correcao = in.nextLine();
                        String observacao = in.nextLine();
                        System.out.println("Quanto de desconto a ACMEjobs gostaria de conceder para clientes empresariais (7% ja considerado), caso nao queira, digite 0");
                        double porcentagem = in.nextDouble();
                        while (0 > porcentagem || porcentagem > 100) {
                            System.out.println("Insira uma porcentagem de desconto válido");
                            porcentagem = in.nextDouble();
                        }
                        if(porcentagem > 93) {
                            porcentagem = 100;
                        } else {
                            porcentagem += 7;
                        }
                        double valorInicial = catalogo.get(numero).getValor();
                        double quantidadeDeDesconto = (valorInicial * porcentagem) / 100.0;
                        double valorFinal = valorInicial - quantidadeDeDesconto;
                        Contrato novoContrato = new Contrato(valorFinal, observacao, horario);
                        novoContrato.setDesconto(quantidadeDeDesconto);
                        novoContrato.setCliente(portifolio.buscaCpfCliente(cnpj));
                        novoContrato.setPrestador(catalogo.get(numero).getPrestador());
                        novoContrato.setServico(catalogo.get(numero));
                        portifolio.buscaCnpjCliente(cnpj).adicionaContrato(novoContrato);
                        System.out.println("Contrato cadastrado. Dados:");
                        System.out.println("Cliente: " + portifolio.buscaCnpjCliente(cnpj).toString());
                        System.out.print("Servico: " + catalogo.get(numero).getDescricao() + " ; valor: " + String.format("%.2f", catalogo.get(numero).getValor()) + " ; data: " + horario + ".");
                        System.out.println(" ; desconto: " + String.format("%.2f", quantidadeDeDesconto) + " ; valor final: " + String.format("%.2f", valorFinal) + " ; observacao: " + observacao + ".");
                        System.out.println("Prestador: " + catalogo.get(numero).getPrestador().toString());
                        portifolio.adicionaContrato(novoContrato, catalogo.get(numero), cnpj);
                    }
                    if (verificandoCnpj == false) {
                        System.out.println("ERRO:Cnpj nao cadastrado \n");
                    }
                }

            }
            if (decisao == 2) {
                System.out.println("Saindo do cadastramento de contratos... \n");
                teste = true;
            }
            if (decisao > 2 || decisao <= 0) {
                System.out.println("Insira um número valido");
            }
        }
    }

    // Para poder confirmar o pagamento, primeiro e necessario confirmar o termino do contrato.
    public void confirmarPagamento() {
        String correcao;
        System.out.println("Confirmar pagamentos, digite:");
        boolean teste = false;
        while (teste == false) {
            System.out.println("Digite 1:Confirmar pagamentos.\nDigite 2:Sair da tela de pagamentos.");
            int decisao = in.nextInt();
            if (decisao == 1) {
                System.out.println("Digite 1:confirmar pagamento de cliente individual.\nDigite 2:confirmar pagamento de cliente empresarial");
                int escolha = in.nextInt();
                if (escolha == 1) {
                    System.out.println("Digite o cpf do cliente");
                    correcao = in.nextLine();
                    String cpf = in.nextLine();
                    boolean verificandoCpf = portifolio.verificaCpfCliente(cpf);
                    if (verificandoCpf == true) {
                        ArrayList<Contrato> contratos;
                        contratos = portifolio.buscaCpfCliente(cpf).getContratosTerminado();
                        if (contratos.size() == 0) {
                            System.out.println("Nao ha contratos terminados para confirmar pagamento");
                        }
                        int numero2 = 1;
                        if (contratos.size() > 0) {
                            System.out.println("Servicos contratados terminados:");
                            for (Contrato contrato : contratos) {
                                System.out.println(numero2 + ":" + contrato.toString());
                                numero2++;
                            }
                            System.out.println("Insira o numero do contrato que deseja confirmar pagamento:");
                            int numero = in.nextInt();
                            numero=numero-1;
                            System.out.println("Cliente: " + portifolio.buscaCpfCliente(cpf).toString());
                            String valorServico = String.format("%.2f", contratos.get(numero).getServico().getValor());
                            System.out.println("Servico: " + contratos.get(numero).getServico().getDescricao() + " ; valor: " + valorServico + " ; data: " + contratos.get(numero).getDataEHora() + ".");
                            valorServico = String.format("%.2f", contratos.get(numero).getValor());
                            String descontoRecebido = String.format("%.2f", contratos.get(numero).getDesconto());
                            System.out.print(" ; desconto: " + descontoRecebido + " ; valor final: " + valorServico + " ; observacao: " + contratos.get(numero).getObservacao() + ".");
                            System.out.println("Prestador: " + contratos.get(numero).getPrestador().toString());
                            System.out.println("Se deseja realmente confirmar o pagamento, digite 1");
                            int decidindo = in.nextInt();

                            if (decidindo == 1) {
                                portifolio.buscaCpfCliente(cpf).removeContratoTerminado(contratos.get(numero));
                                System.out.println("Pagamento confirmado.");
                            } else {
                                System.out.println("Pagamento não confirmado.");
                            }
                        }
                    }
                    if (verificandoCpf==false){
                        System.out.println("Cpf nao cadastrado");
                    }
                }
                if (escolha==2){
                    System.out.println("Digite o cnpj do cliente");
                    correcao = in.nextLine();
                    String cnpj = in.nextLine();
                    boolean verificandocnpj = portifolio.verificaCnpjCliente(cnpj);
                    if (verificandocnpj == true) {
                        ArrayList<Contrato> contratos;
                        contratos = portifolio.buscaCnpjCliente(cnpj).getContratosConfirmados();
                        if (contratos.size() == 0) {
                            System.out.println("Nao ha contratos confirmados para confirmar pagamento");
                        }
                        int numero2 = 1;
                        if (contratos.size() > 0) {
                            System.out.println("Servicos contratados:");
                            for (Contrato contrato : contratos) {
                                System.out.println(numero2 + ":" + contrato.toString());
                                numero2++;
                            }
                            System.out.println("Insira o numero do contrato que deseja confirmar pagamento:");
                            int numero = in.nextInt();
                            numero=numero-1;
                            System.out.println("Cliente: " + portifolio.buscaCnpjCliente(cnpj).toString());
                            String valorContrato = String.format("%.2f", contratos.get(numero).getServico().getValor());
                            System.out.print("Servico: " + contratos.get(numero).getServico().getDescricao() + " ; valor: " + valorContrato + " ; data: " + contratos.get(numero).getDataEHora() + ".");
                            valorContrato = String.format("%.2f", contratos.get(numero).getValor());
                            String descontoContrato = String.format("%.2f", contratos.get(numero).getDesconto());
                            System.out.println(" ; desconto: " + descontoContrato + " ; valor final: " + valorContrato + " ; observacao: " + contratos.get(numero).getObservacao() + ".");
                            System.out.println("Prestador: " + contratos.get(numero).getPrestador().toString());
                            System.out.println("Se deseja realmente confirmar o pagamento, digite 1");
                            int decidindo = in.nextInt();

                            if (decidindo == 1) {
                                portifolio.buscaCnpjCliente(cnpj).removeContratoTerminado(contratos.get(numero));
                                System.out.println("Pagamento confirmado.");
                            } else {
                                System.out.println("Pagamento não confirmado.");
                            }
                        }
                    }
                    if (verificandocnpj==false){
                        System.out.println("Cpf nao cadastrado");
                    }

                }
            }
            if (decisao == 2) {
                System.out.println("Saindo da tela de pagamento... \n");
                teste = true;
            }
            if (decisao > 2 || decisao <= 0) {
                System.out.println("Insira um número valido");
            }
        }
    }

    public void confirmarTermino() {
        ArrayList<Contrato> servicosConfirmados;
        servicosConfirmados = portifolio.getContratosConfirmado();
        if (servicosConfirmados.size() > 0) {
            System.out.println("Servicos confirmados:");
            int numero = 1;
            for (Contrato servicosConfirmado : servicosConfirmados) {
                System.out.println(numero + ":" + "Servico contratado: " + servicosConfirmado.getServico().toString() + "\n" + numero + ":" + "Contrato:" + servicosConfirmado.toString() + "\n");
                numero++;
            }
            System.out.println("Digite o numero do servico que voce gostaria de reportar termino");
            int escolha = in.nextInt();
            escolha = escolha - 1;
            servicosConfirmados.get(escolha).getPrestador().removeServicoConfirmado(servicosConfirmados.get(escolha));
            servicosConfirmados.get(escolha).getCliente().removeContratoConfirmados(servicosConfirmados.get(escolha));
            portifolio.removeContratoConfirmado(servicosConfirmados.get(escolha));
        }
        if (servicosConfirmados.size() == 0) {
            System.out.println("Nao possui servicos contratados");
        }

    }

    public void cancelarServico() {
        ArrayList<Contrato> servicosContratados;
        servicosContratados = portifolio.getContratosContratados();
        if (servicosContratados.size() > 0) {
            System.out.println("Servicos contratados:");
            int numero = 1;
            for (Contrato servicosContratado : servicosContratados) {
                System.out.println(numero + ":" + "Servico contratado: " + servicosContratado.getServico().toString() + "\n" + numero + ":" + "Contrato:" + servicosContratado.toString() + "\n");
                numero++;
            }
            System.out.println("Digite o numero do servico que voce gostaria de cancelar");
            int escolha = in.nextInt();
            escolha = escolha - 1;
            System.out.println("O contrato: " + servicosContratados.get(escolha).toString() + " foi cancelado");
            portifolio.removeContratoContratadosCancelando(servicosContratados.get(escolha));
            servicosContratados.get(escolha).getCliente().removeContratoCancelando(servicosContratados.get(escolha));
            servicosContratados.get(escolha).getPrestador().removeServicoContratado(servicosContratados.get(escolha));
        }
        if (servicosContratados.size() == 0) {
            System.out.println("Nao possui servicos confirmado");
        }
    }

    public void confirmarServico() {
        ArrayList<Contrato> servicosContratados2;
        servicosContratados2 = portifolio.getContratosContratados();

        if (servicosContratados2.size() > 0) {
            System.out.println("Servicos contratados:");
            int numero = 1;
            for (Contrato contrato : servicosContratados2) {
                System.out.println(numero + ":" + "Servico contratado: " + contrato.getServico().toString() + "\n" + numero + ":" + "Contrato:" + contrato.toString() + "\n");
                numero++;
            }
            System.out.println("Digite o numero do servico que voce gostaria de confirmar");
            int escolha = in.nextInt();
            escolha = escolha - 1;
            System.out.println("Digite a porcentagem de desconto desejado (0 = sem desconto)");
            double desconto = in.nextDouble();
            while (0>desconto || desconto > 100) {
                System.out.println("Insira uma porcentagem de desconto válido");
                desconto = in.nextDouble();
            }
            double valorDeDesconto = (servicosContratados2.get(escolha).getValor() * desconto) / 100;
            servicosContratados2.get(escolha).setDesconto(valorDeDesconto);
            double valor = servicosContratados2.get(escolha).getValor() - valorDeDesconto;
            servicosContratados2.get(escolha).getServico().setValor(valor);
            System.out.println("O valor final ficou:" + String.format("%.2f", valor));
            servicosContratados2.get(escolha).setValor(valor);
            servicosContratados2.get(escolha).getCliente().removeContratoConfirmando(servicosContratados2.get(escolha));
            servicosContratados2.get(escolha).getPrestador().removeServicoContratadoAdicionaConfirmado(servicosContratados2.get(escolha));
            System.out.println("O contrato: " + servicosContratados2.get(escolha).toString() + " foi confirmado");
            portifolio.removeContratoContratados(servicosContratados2.get(escolha));
        }
        if (servicosContratados2.size() == 0) {
            System.out.println("Nao possui servicos contratados");
        }
    }


    public static boolean VerificaDigitosCpfOuCnpj(String cpfOuCnpj){
        char[] c = cpfOuCnpj.toCharArray();

        for ( int i = 0; i < c.length; i++ ) {
            if (!Character.isDigit(c[i])) {
                System.out.println("Erro: não é um digito");
                return false;
            }
        }
        return true;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void consultarContratos() {
        portifolio.consultarContratos();
    }

    public void consultarClientes() {
        portifolio.consultarClientes();
    }

    public void consultarServico() {
        portifolio.consultarServicos();
    }

    public void consultarPrestadores() {
        portifolio.consultarPrestador();
    }

    public String toString() {
        return "nome: " + nome + " ; telefone: " + telefone + " ; e-mail: " + email + " ; cpf: " + cpf + ".";
    }

}

