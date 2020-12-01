package ACMEJobsTeste;

import Trabalho1.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServicoTest {

    private Servico servico;
    private Prestador prestador;

    @BeforeEach
    void setUp() {
        servico = new Servico(100, "Limpeza", "dia");
        prestador = new Prestador("Prestador", 87654321, "prestado@prestador.com", "78945612378");
    }

    @Test
    public void GetSetAcordo(){
        int valor = 50;
        servico.setValor(valor);
        servico.setPrestador(prestador);

        Assertions.assertEquals(valor, servico.getValor());
        Assertions.assertEquals(prestador, servico.getPrestador());

        Assertions.assertEquals("Limpeza", servico.getObservacao());
    }

    @Test
    void getDescricao() {
        Assertions.assertEquals("dia", servico.getDescricao());
    }

    @Test
    void testToString() {
        String printServico = "dia;100.0;Limpeza";
        Assertions.assertEquals(printServico, servico.toString());
    }
}