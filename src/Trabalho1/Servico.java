package Trabalho1;

public class Servico extends Acordo {

    private final String descricao;


    public Servico(int valor, String observacao, String descricao) {
        super(valor, observacao);
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String toString(){
        return "Valor: " + String.format("%.2f", valor) + " ; Descricao: " + descricao + " ; Observacao: " + observacao + ".";
    }

}

