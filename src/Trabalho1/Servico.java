package Trabalho1;

public class Servico extends Acordo {

    private final String descricao;


    public Servico(double valor, String observacao, String descricao) {
        super(valor, observacao);
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String toString(){
        return "" + prestadorArquivo() + ";" + descricao + ";" + valor +";"+ observacao;
    }

}

