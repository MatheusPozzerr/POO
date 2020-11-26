package Trabalho1;

import java.time.LocalDateTime;

public class Contrato extends Acordo {

    private double desconto;
    private final LocalDateTime dataEHora;
    private Cliente cliente;
    private Servico servico;


    public Contrato(double valor, String observacao, LocalDateTime horaEData) {
        super(valor, observacao);
        this.dataEHora = horaEData;
        desconto = 0;
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


    public double getDesconto() {
        return desconto;
    }

    public Cliente getCliente() {
        return cliente;
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

}

