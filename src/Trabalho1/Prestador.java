package Trabalho1;

import java.util.ArrayList;

public class Prestador extends Usuario {

    private ArrayList<Servico> servicos = new ArrayList<>();
    private ArrayList<Contrato> servicosContratados = new ArrayList<>();
    private ArrayList<Contrato> servicosConfirmados = new ArrayList<>();


    public Prestador(String nome, int telefone, String email, String cpf, Portifolio portifolio) {
        super(nome,telefone,email,cpf,portifolio);
    }

    public Prestador(String nome, int telefone, String email, String cpf) {
        super(nome,telefone,email,cpf);
    }


    public boolean adicionaServico(Servico Servico) {
        for (Trabalho1.Servico servico : servicos) {
            if (servico.getDescricao().toLowerCase().equals(Servico.getDescricao().toLowerCase())) {
                return false;
            }
        }
        servicos.add(Servico);
        return true;

    }

    public void adicionaServicoContratados(Contrato contrato){
        servicosContratados.add(contrato);
    }

    public void removeServicoContratado(Contrato contrato) {
        for (int i = 0; i < servicosContratados.size(); i++) {
            if (contrato.getObservacao().toLowerCase().equals(servicosContratados.get(i).getObservacao().toLowerCase())) {
                servicosContratados.remove(servicosContratados.get(i));
            }

        }
    }

    public void removeServicoContratadoAdicionaConfirmado(Contrato contrato){
        for (int i = 0; i < servicosContratados.size(); i++) {
            if (contrato.getObservacao().toLowerCase().equals(servicosContratados.get(i).getObservacao().toLowerCase())) {
                servicosConfirmados.add(servicosContratados.get(i));
                servicosContratados.remove(servicosContratados.get(i));
            }

        }
    }

    public void removeServicoConfirmado(Contrato contrato){
        for (int i = 0; i < servicosConfirmados.size(); i++) {
            if (contrato.getObservacao().toLowerCase().equals(servicosConfirmados.get(i).getObservacao().toLowerCase()))  {
                servicosConfirmados.remove(servicosConfirmados.get(i));
            }

        }
    }


    public void cancelarServico(){
        if (servicosContratados.size()>0) {
            System.out.println("Servicos contratados:");
            int numero = 1;
            for (Contrato servicosContratado : servicosContratados) {
                System.out.println(numero + ":" + "Servico contratado: " + servicosContratado.getServico().toString() + "\n" + numero + ":" + "Contrato:" + servicosContratado.toString() + "\n");
                numero++;
            }
            System.out.println("Digite o numero do servico que voce gostaria de cancelar");
            int escolha = in.nextInt();
            escolha = escolha - 1;
            portifolio.removeContratoContratadosCancelando(servicosContratados.get(escolha));
            System.out.println("O contrato: " + servicosContratados.get(escolha).toString() + "foi cancelado");
            servicosContratados.get(escolha).getCliente().removeContratoCancelando(servicosContratados.get(escolha));
            servicosContratados.remove(escolha);
        }
        if(servicosContratados.size()==0){
            System.out.println("Nao possui servicos confirmado");
        }


    }

    public void confirmarServico() {
        if (servicosContratados.size()>0) {
            System.out.println("Servicos contratados:");
            int numero = 1;
            for (Contrato servicosContratado : servicosContratados) {
                System.out.println(numero + ":" + "Servico contratado: " + servicosContratado.getServico().toString() + "\n" + numero + ":" + "Contrato:" + servicosContratado.toString() + "\n");
                numero++;
            }
            System.out.println("Digite o numero do servico que voce gostaria de confirmar");
            int escolha = in.nextInt();
            escolha = escolha - 1;
            System.out.println("Digite a porcentagem de desconto desejado (0 = sem desconto) Clientes empresariais já tem 7%");
            double porcentagem = in.nextDouble();
            while (0>porcentagem || porcentagem > 100) {
                System.out.println("Insira uma porcentagem de desconto válido");
                porcentagem = in.nextDouble();
            }
            double valorDeDesconto = (servicosContratados.get(escolha).getValor() * porcentagem) / 100;
            servicosContratados.get(escolha).setDesconto(valorDeDesconto);
            double valor = servicosContratados.get(escolha).getValor() - valorDeDesconto;
            servicosContratados.get(escolha).setValor(valor);
            System.out.println("O valor final ficou:" + String.format("%.2f", servicosContratados.get(escolha).getValor()));
            portifolio.removeContratoConfirmado(servicosContratados.get(escolha));
            servicosContratados.get(escolha).getCliente().removeContratoConfirmando(servicosContratados.get(escolha));
            System.out.println("O contrato: " + servicosContratados.get(escolha).toString() + " foi confirmado");
            servicosConfirmados.add(servicosContratados.get(escolha));
            servicosContratados.remove(escolha);
        }
        if(servicosContratados.size()==0){
            System.out.println("Nao possui servicos contratados");
        }

    }

    public void confirmarTermino() {

        int numero = 1;
        if (servicosConfirmados.size() == 0) {
            System.out.println("Nao possui contratos/servicos confirmados");
        }
        if (servicosConfirmados.size() > 0) {
            System.out.println("Servicos confirmados:");
            for (Contrato servicosConfirmado : servicosConfirmados) {
                System.out.println(numero + ":" + "Servico contratado: " + servicosConfirmado.getServico().toString() + "\n" + numero + ":" + "Contrato:" + servicosConfirmado.toString() + "\n");
                numero++;
            }
            System.out.println("Digite o numero do servico que voce gostaria de reportar termino");
            int escolha = in.nextInt();
            escolha = escolha - 1;
            servicosConfirmados.get(escolha).getCliente().removeContratoConfirmados(servicosConfirmados.get(escolha));
            portifolio.removeContratoConfirmado(servicosConfirmados.get(escolha));
            servicosConfirmados.remove(escolha);
        }

    }


    public ArrayList<Servico> getServicos() {
        return servicos;
    }

    public ArrayList<Contrato> getServicosContratados() {
        return servicosContratados;
    }

    public ArrayList<Contrato> getServicosConfirmados() {
        return servicosConfirmados;
    }

    public String consultarServicosContratados() {
        StringBuilder contratados = new StringBuilder();
        for (Contrato contrato : servicosContratados) {
            contratados.append("Contrato: ").append(contrato.toString()).append("\n")
                    .append("Servico contratado: ").append(contrato.getServico().toString()).append("\n")
                    .append("Contratante: ").append(contrato.getCliente().toString()).append("\n");
        }
        if(contratados.length() == 0) {
            return "Nenhum serviço contratado.";
        }
        return contratados.toString();
    }

}