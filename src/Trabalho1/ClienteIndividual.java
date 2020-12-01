package Trabalho1;

public class ClienteIndividual extends Cliente {

    private final String cpf;

    public ClienteIndividual(String nome, int telefone, String email, String endereco, String cpf) {
        super(nome,telefone, email, endereco);
        this.cpf=cpf;
    }

    @Override
    public String getCpfOuCnpj() {
        return cpf;
    }

    public String toString(){
        return "" + cpf + ";"+ nome +";" + telefone + ";" + email+ ";" + endereco +"\n";
    }

}
