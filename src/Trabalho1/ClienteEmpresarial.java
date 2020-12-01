package Trabalho1;

public class ClienteEmpresarial extends Cliente {

    private final String cnpj;
    private final String nomeFantasia;

    public ClienteEmpresarial(String nomeFantasia, String nome, int telefone, String email, String endereco,String cnpj) {
        super(nome,telefone,email,endereco);
        this.cnpj=cnpj;
        this.nomeFantasia=nomeFantasia;
    }


    public String getNomeFantasia() {
        return nomeFantasia;
    }

    @Override
    public String getCpfOuCnpj() {
        return cnpj;
    }

    public String toString(){
        return "" + cnpj +";" + nome + ";" + telefone + ";" + email + ";" + endereco + ";" + nomeFantasia+"\n";
    }

}

