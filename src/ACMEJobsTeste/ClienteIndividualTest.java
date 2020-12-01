package ACMEJobsTeste;

import Trabalho1.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

class ClienteIndividualTest {

    private String clienteNome;
    private String clienteCpf;
    private ClienteIndividual clienteIndividual;
    private Contrato contrato1;
    private Contrato contrato2;
    private Contrato contrato3;

    @BeforeEach
    void beforeEach() {
        clienteNome = "Fulano";
        clienteCpf = "12345678912";
        clienteIndividual = new ClienteIndividual(clienteNome, 12345678, "email@email.com", "Av. Teste, 1", clienteCpf);

        LocalDateTime horario1 = LocalDateTime.of(2020, 10, 12, 14, 30);
        contrato1 = new Contrato(89, "Devolução de apartamento, pintura de sala de 10 m²", horario1);
        contrato1.setDesconto(6.23);

        LocalDateTime horario2 = LocalDateTime.of(2021, Month.JANUARY, 1, 22, 0);
        contrato2 = new Contrato(20, "Festa de ano novo para 100 pessoas", horario2);
        contrato2.setDesconto(1.4);

        LocalDateTime horario3 = LocalDateTime.of(2021, Month.FEBRUARY, 15, 19, 30);
        contrato3 = new Contrato(20, "Evento empresarial", horario3);
    }

    @Test
    void clienteGetContratos () {
        clienteIndividual.adicionaContrato(contrato1);
        clienteIndividual.adicionaContrato(contrato2);
        clienteIndividual.adicionaContrato(contrato3);

        Assertions.assertEquals(contrato1, clienteIndividual.getContratos().get(0));
        Assertions.assertEquals(contrato2, clienteIndividual.getContratos().get(1));
        Assertions.assertEquals(contrato3, clienteIndividual.getContratos().get(2));
    }

    @Test
    void clienteRemoveContratos() {
        clienteIndividual.adicionaContrato(contrato1);
        clienteIndividual.adicionaContrato(contrato2);

        clienteIndividual.removeContratoCancelando(contrato1);
        Assertions.assertTrue(clienteIndividual.getContratoCancelado().contains(contrato1));
        Assertions.assertFalse(clienteIndividual.getContratos().contains(contrato1));

        clienteIndividual.removeContratoConfirmando(contrato2);
        Assertions.assertTrue(clienteIndividual.getContratosConfirmados().contains(contrato2));
        Assertions.assertFalse(clienteIndividual.getContratos().contains(contrato2));

        clienteIndividual.removeContratoConfirmados(contrato2);
        Assertions.assertTrue(clienteIndividual.getContratosTerminado().contains(contrato2));
        Assertions.assertFalse(clienteIndividual.getContratosConfirmados().contains(contrato2));

        clienteIndividual.removeContratoTerminado(contrato2);
        Assertions.assertTrue(clienteIndividual.getContratosPagos().contains(contrato2));
        Assertions.assertFalse(clienteIndividual.getContratosTerminado().contains(contrato2));
    }

    @Test
    void clienteGetNome() {
        Assertions.assertEquals(clienteNome , clienteIndividual.getNome());
    }


    @Test
    void getCpfOuCnpj () {
        Assertions.assertEquals(clienteCpf , clienteIndividual.getCpfOuCnpj());
    }

    @Test
    void testToString() {
        String printCliIndiv = "12345678912;Fulano;12345678;email@email.com;Av. Teste, 1";
        Assertions.assertEquals(printCliIndiv, clienteIndividual.toString());
    }

}