package Trabalho1;

import java.time.LocalDateTime;

public class Contrato extends Acordo {

    private double desconto;
    private final LocalDateTime dataEHora;
    private Cliente cliente;
    private Servico servico;
    private double qtdDesconto;
    private final double valorinicial;


    public Contrato(double valor, String observacao, LocalDateTime horaEData) {
        super(valor, observacao);
        this.dataEHora = horaEData;
        desconto = 0;
        valorinicial=valor;
    }

    public double getValorInicial(){
        return valorinicial;
    }

    public void setDesconto(double desconto) {
        this.desconto += desconto;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public void setQtdDesconto(double qtd){
        this.qtdDesconto=qtd;
    }
    public double getQtdDesconto() {
        return qtdDesconto;
    }

    public double getDesconto() {
        return desconto;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public String getCPFCliente() {
        return cliente.getCpfOuCnpj();
    }

    public Servico getServico() {
        return servico;
    }

    public LocalDateTime getDataEHora() {
        return dataEHora;
    }


    public String toString(){
        return "Valor: " + String.format("%.2f", valor) + " ; Desconto: " + String.format("%.2f", desconto) + " ; Observação: " + observacao + " ; Data e horário: " + dataEHora +  ".";
    }
    public String toStringArquivo(){
        return "" +dataEHora + ";" +valor+";"+ observacao ;
    }
}

