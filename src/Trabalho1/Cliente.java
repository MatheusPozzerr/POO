package Trabalho1;

import java.util.ArrayList;

public abstract class Cliente {

    protected final String nome;
    protected int telefone;
    protected String email;
    protected String endereco;
    protected ArrayList<Contrato> contratos = new ArrayList<>();
    private ArrayList<Contrato> contratosPagos = new ArrayList<>();
    private ArrayList<Contrato> contratosTerminado = new ArrayList<>();
    private ArrayList<Contrato> contratosConfirmado = new ArrayList<>();
    private ArrayList<Contrato> contratoCancelado = new ArrayList<>();


    public Cliente(String nome, int telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco= endereco;
    }

    public void adicionaContrato(Contrato contrato){
        contratos.add(contrato);
    }


    public void removeContratoCancelando(Contrato contrato){
        for(int i = 0; i<contratos.size(); i++){
            if (contrato.getObservacao().equals(contratos.get(i).getObservacao())){
                contratoCancelado.add(contratos.get(i));
                contratos.remove(contratos.get(i));
                return;
            }
        }
    }

    public void removeContratoConfirmando(Contrato contrato){
        for(int i = 0; i<contratos.size(); i++){
            if (contrato.getObservacao().equals(contratos.get(i).getObservacao())){
                contratosConfirmado.add(contratos.get(i));
                contratos.remove(contratos.get(i));
                return;
            }
        }
    }

    public void removeContratoConfirmados(Contrato contrato){
        for(int i = 0; i<contratosConfirmado.size(); i++){
            if (contrato.getObservacao().equals(contratosConfirmado.get(i).getObservacao())){
                contratosTerminado.add(contratosConfirmado.get(i));
                contratosConfirmado.remove(contratosConfirmado.get(i));
                return;
            }
        }
    }

    public void removeContratoTerminado(Contrato contrato){
        for(int i = 0; i<contratosTerminado.size(); i++){
            if (contrato.getObservacao().equals(contratosTerminado.get(i).getObservacao())){
                contratosPagos.add(contratosTerminado.get(i));
                contratosTerminado.remove(contratosTerminado.get(i));
            }
        }
    }


    public ArrayList<Contrato> getContratos() {
        return contratos;
    }

    public ArrayList<Contrato> getContratoCancelado() {
        return contratoCancelado;
    }

    public ArrayList<Contrato> getContratosConfirmados(){
        return contratosConfirmado;
    }

    public ArrayList<Contrato> getContratosTerminado() {
        return contratosTerminado;
    }

    public ArrayList<Contrato> getContratosPagos() {
        return contratosPagos;
    }


    public String getNome() {
        return nome;
    }

    public abstract String getCpfOuCnpj();

    public String toString(){
        return "nome: " + nome + " ; telefone: " + telefone + " ; e-mail: " + email + " ; endereco: " + endereco;
    }

}


