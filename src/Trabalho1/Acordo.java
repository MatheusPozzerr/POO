package Trabalho1;

public abstract class Acordo {

    public double valor;
    protected String observacao;
    private Prestador prestador;


    public Acordo(double valor, String observacao) {
        if(valor < 1) {
            valor = 1;
        }
        this.valor = valor;
        this.observacao = observacao;
    }


    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }


    public double getValor() {
        return valor;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public String prestadorArquivo() {
        return prestador.getCpf();
    }

    public String getObservacao() {
        return observacao;
    }

}
