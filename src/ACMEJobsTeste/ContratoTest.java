package ACMEJobsTeste;

import Trabalho1.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ContratoTest {

    private LocalDateTime horario;
    private Contrato contrato;
    private Prestador prestador;
    private Cliente cliente;
    private Servico servico;

    @BeforeEach
    void setUp() {
        horario = LocalDateTime.of(2020, 10, 14, 00, 00);
        contrato = new Contrato(100, "limpeza casa de 2 andares", horario);
        prestador = new Prestador("Prestador", 87654321, "prestado@prestador.com", "78945612378");
        cliente = new ClienteIndividual("Fulano", 12345678, "email@email.com", "Av. Teste, 1", "12345678912");
        servico = new Servico(100, "Limpeza", "dia");
    }

    @Test
    public void GetSetAcordo(){
        int valor = 50;
        contrato.setValor(valor);
        contrato.setPrestador(prestador);

        Assertions.assertEquals(valor, contrato.getValor());
        Assertions.assertEquals(prestador, contrato.getPrestador());

        Assertions.assertEquals("limpeza casa de 2 andares", contrato.getObservacao());
    }

    @Test
    public void GetSetContrato(){
        double desconto = 6.0;
        contrato.setDesconto(desconto);
        contrato.setCliente(cliente);
        contrato.setServico(servico);

        Assertions.assertEquals(desconto, contrato.getDesconto());
        Assertions.assertEquals(cliente, contrato.getCliente());
        Assertions.assertEquals(servico, contrato.getServico());

        Assertions.assertEquals(horario, contrato.getDataEHora());
    }

    @Test
    public void testToString() {
        String printContrato = "Valor: 100,00 ; Desconto: 0,00" + " ; Observação: limpeza casa de 2 andares" + " ; Data e horário: 2020-10-14T00:00.";
        Assertions.assertEquals(printContrato, contrato.toString());
    }
}