package Trabalho1;

import sample.Main;

import java.util.ArrayList;

public class Portifolio {

    private ArrayList<Cliente> clientesEmpresariais;
    private ArrayList<Cliente> clientesIndividuais;
    private ArrayList<Prestador> prestadores;
    private ArrayList<Servico> servicos;
    private ArrayList<Contrato> contratos;
    private ArrayList<Contrato> contratosConfirmado;
    private ArrayList<Contrato> contratosContratados;
    private ArrayList<Contrato> ContratosCancelados;
    private ArrayList<Contrato> ContratosTerminados;
    private ArrayList<Contrato> ContratosPagos;

    public ArrayList<Contrato> getContratosPagos() {
        return ContratosPagos;
    }
    @Override
    public String toString() {
        String listaPrestadores = prestadores.toString().replace("[", "").replace("]", "").replace(", ", "\n");
        String listaClientesEmpresariais = clientesEmpresariais.toString().replace("[", "").replace("]", "").replace(", ", "\n");
        String listaClientesIndividuais = clientesIndividuais.toString().replace("[", "").replace("]", "").replace(", ", "\n");
        String listaServicos = servicos.toString().replace("[", "").replace("]", "").replace(", ", "\n");
        String listaContratosPendentes = contratos.toString().replace("[", "").replace("]", "").replace(", ", "\n");
        String listaContratosConfirmados = contratosConfirmado.toString().replace("[", "").replace("]", "").replace(", ", "\n");
        String listaContratosCancelados = ContratosCancelados.toString().replace("[", "").replace("]", "").replace(", ", "\n");
        String listaContratosTerminados = ContratosTerminados.toString().replace("[", "").replace("]", "").replace(", ", "\n");
        return "[CLIENTEEMPRESARIAL]\n" + listaClientesEmpresariais +
                "\n\n[CLIENTEINDIVIDUAL]\n" + listaClientesIndividuais +
                "\n\n[PRESTADOR]\n" + listaPrestadores ;
        //"\n[SERVICO]\n" + listaServicos;
        }
    public Portifolio() {
        this.clientesEmpresariais = new ArrayList<>();
        this.clientesIndividuais = new ArrayList<>();
        this.prestadores = new ArrayList<>();
        this.servicos = new ArrayList<>();
        this.contratos = new ArrayList<>();
        this.contratosConfirmado = new ArrayList<>();
        this.contratosContratados = new ArrayList<>();
        this.ContratosPagos = new ArrayList<>();
        ContratosCancelados = new ArrayList<>();
        ContratosTerminados = new ArrayList<>();
    }

    public boolean adicionaClienteEmpresarial(ClienteEmpresarial clienteEmpresarial) {
        for (Cliente value : this.clientesEmpresariais) {
            if (value.getCpfOuCnpj().equals(clienteEmpresarial.getCpfOuCnpj())) {
                return false;
            }
        }
        this.clientesEmpresariais.add(clienteEmpresarial);
        return true;
    }

    public boolean adicionaClienteIndividual(Cliente clienteIndividual) {
        for (Cliente cliente : clientesIndividuais) {
            if (cliente.getCpfOuCnpj().equals(clienteIndividual.getCpfOuCnpj())) {
                return false;
            }
        }
        clientesIndividuais.add(clienteIndividual);
        return true;

    }

    public boolean adicionaPrestador(Prestador Prestador) {
        for (Trabalho1.Prestador value : prestadores) {
            if (value.getCpf().equals(Prestador.getCpf())) {
                return false;
            }
        }
        prestadores.add(Prestador);
        return true;
    }

    public boolean adicionaServico(Servico Servico, String cpf) {
        for (Prestador prestador : prestadores) {

            if (prestador.getCpf().equals(cpf)) {
                boolean testandoDescicao = prestador.adicionaServico(Servico);

                if (testandoDescicao == true) {
                    Servico.setPrestador(prestador);
                    servicos.add(Servico);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public void adicionaContrato(Contrato contrato, Servico servico, String cpfOuCnpj) {
        if (cpfOuCnpj.length() == 11) {
            contrato.setCliente(buscaCpfCliente(cpfOuCnpj));
            contrato.setPrestador(servico.getPrestador());
            contrato.setServico(servico);
            contrato.getCliente().adicionaContrato(contrato);
            servico.getPrestador().adicionaServicoContratados(contrato);
            contratosContratados.add(contrato);
            contratos.add(contrato);
        }
        if (cpfOuCnpj.length() == 14) {
            contrato.setCliente(buscaCnpjCliente(cpfOuCnpj));
            contrato.setPrestador(servico.getPrestador());
            contrato.setServico(servico);
            servico.getPrestador().adicionaServicoContratados(contrato);
            contrato.getCliente().adicionaContrato(contrato);
            contratosContratados.add(contrato);
            contratos.add(contrato);
        }

    }

    public void removeContratoContratados(Contrato contrato) {
        for (int i = 0; i < contratosContratados.size(); i++) {
            if (contrato.getDataEHora() == contratosContratados.get(i).getDataEHora()) {
                contratosConfirmado.add(contratosContratados.get(i));
                contratosContratados.remove(contratosContratados.get(i));
            }
        }
    }

    public void removeContratoContratadosCancelando(Contrato contrato) {
        for (int i = 0; i < contratosContratados.size(); i++) {
            if (contrato.getDataEHora() == contratosContratados.get(i).getDataEHora()) {
                ContratosCancelados.add(contratosContratados.get(i));
                contratosContratados.remove(contratosContratados.get(i));
            }
        }
    }

    public void removeContratoConfirmado(Contrato contrato) {
        for (int i = 0; i < contratosConfirmado.size(); i++) {
            if (contrato.getDataEHora() == contratosConfirmado.get(i).getDataEHora()) {
                ContratosTerminados.add(contratosConfirmado.get(i));
                contratosConfirmado.remove(contratosConfirmado.get(i));
            }
        }
    }

    public void removeContratoTerminado(Contrato contrato) {
        for (int i = 0; i < ContratosTerminados.size(); i++) {
            if (contrato.getDataEHora() == ContratosTerminados.get(i).getDataEHora()) {
                ContratosPagos.add(contrato);
                ContratosTerminados.remove(ContratosTerminados.get(i));
            }
        }
    }

    public boolean verificaCnpjCliente(String cnpj) {

        for (Cliente value : clientesEmpresariais) {
            if (value.getCpfOuCnpj().equals(cnpj)) {
                System.out.println("Cliente: " + value.getCpfOuCnpj());
                return true;
            }
        }
        return false;
    }

    public boolean verificaCpfCliente(String cpf) {

        for (Cliente cliente : clientesIndividuais) {
            if (cliente.getCpfOuCnpj().equals(cpf)) {
                System.out.println("Cliente: " + cliente.getNome());
                return true;
            }
        }
        return false;
    }

    public boolean verificaCpfPrestador(String cpf) {

        for (Prestador value : prestadores) {
            if (value.getCpf().equals(cpf)) {
                System.out.println("Prestador: " + value.getNome());
                return true;
            }
        }
        return false;


    }

    public Cliente buscaCnpjCliente(String cnpj) {

        for (Cliente value : clientesEmpresariais) {
            if (value.getCpfOuCnpj().equals(cnpj)) {
                return value;
            }
        }
        return null;
    }

    public Cliente buscaCpfCliente(String cpf) {

        for (Cliente cliente : clientesIndividuais) {
            if (cliente.getCpfOuCnpj().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public Prestador buscaCpfPrestador(String cpf) {

        for (Prestador value : prestadores) {
            if (value.getCpf().equals(cpf)) {
                return value;
            }
        }
        return null;
    }

    public String consultarContratos() {

        StringBuilder sb = new StringBuilder();
        int num = 1;
        for (Contrato contrato : contratos) {
            sb.append(num+"."+contrato.toString() +"\n");
            num++;
        }
        return sb.toString();
    }

    public void consultarClientes() {
        System.out.println("Clientes individuais cadastrados:");
        int numero = 1;
        for (Cliente cliente : clientesIndividuais) {
            System.out.println(numero + ":" + cliente.toString());
            numero++;
        }
        System.out.println("\nClientes empresariais cadastrados:");
        int numero2 = 1;
        for (Cliente value : clientesEmpresariais) {
            System.out.println(numero2 + ":" + value.toString());
            numero2++;
        }
    }

    public void consultarPrestador() {
        System.out.println("Prestadores cadastrados:");
        int numero = 1;
        for (Prestador value : prestadores) {
            System.out.println(numero + ":" + value.toString());
            numero++;
        }
    }

    public void consultarServicos() {
        System.out.println("Servicos cadastrados:");
        int numero = 1;
        for (Servico servico : servicos) {
            System.out.println(numero + ":" + servico.toString());
            System.out.println("Prestador:" + servico.getPrestador().toString());
            numero++;
        }
    }

    public ArrayList<Cliente> getClientesEmpresariais() {
        return clientesEmpresariais;
    }

    public ArrayList<Cliente> getClientesIndividuais() {
        return clientesIndividuais;
    }

    public ArrayList<Prestador> getPrestadores() {
        return prestadores;
    }

    public ArrayList<Servico> getServicos() {
        return new ArrayList<>(servicos);
    }

    public ArrayList<Contrato> getContratos() {
        return contratos;
    }

    public ArrayList<Contrato> getContratosConfirmado() {
        return contratosConfirmado;
    }

    public ArrayList<Contrato> getContratosContratados() {
        return contratosContratados;
    }

    public ArrayList<Contrato> getContratosCancelados() {
        return ContratosCancelados;
    }

    public ArrayList<Contrato> getContratosTerminados() {
        return ContratosTerminados;
    }

}






