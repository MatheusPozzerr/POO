package ACMEJobsTeste;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Trabalho1.*;

import java.time.LocalDateTime;
import java.time.Month;

public class ClienteEmpresarialTest {

    private String clienteNome;
    private String clienteCnpj;
    private String clienteNomeFantasia;
    private ClienteEmpresarial clienteEmpresarial;
    private Contrato contrato1;
    private Contrato contrato2;
    private Contrato contrato3;

    @BeforeEach
    void beforeEach() {
        clienteNome = "Empresa Teste";
        clienteNomeFantasia = "ET";
        clienteCnpj = "12345678987654";
        clienteEmpresarial = new ClienteEmpresarial(clienteNomeFantasia, clienteNome,
                12345678, "email@email.com.br", "Av. Teste, 123", clienteCnpj);

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
        clienteEmpresarial.adicionaContrato(contrato1);
        clienteEmpresarial.adicionaContrato(contrato2);
        clienteEmpresarial.adicionaContrato(contrato3);

        Assertions.assertEquals(contrato1, clienteEmpresarial.getContratos().get(0));
        Assertions.assertEquals(contrato2, clienteEmpresarial.getContratos().get(1));
        Assertions.assertEquals(contrato3, clienteEmpresarial.getContratos().get(2));
    }

    @Test
    void clienteRemoveContratos() {
        clienteEmpresarial.adicionaContrato(contrato1);
        clienteEmpresarial.adicionaContrato(contrato2);

        clienteEmpresarial.removeContratoCancelando(contrato1);
        Assertions.assertTrue(clienteEmpresarial.getContratoCancelado().contains(contrato1));
        Assertions.assertFalse(clienteEmpresarial.getContratos().contains(contrato1));

        clienteEmpresarial.removeContratoConfirmando(contrato2);
        Assertions.assertTrue(clienteEmpresarial.getContratosConfirmados().contains(contrato2));
        Assertions.assertFalse(clienteEmpresarial.getContratos().contains(contrato2));

        clienteEmpresarial.removeContratoConfirmados(contrato2);
        Assertions.assertTrue(clienteEmpresarial.getContratosTerminado().contains(contrato2));
        Assertions.assertFalse(clienteEmpresarial.getContratosConfirmados().contains(contrato2));

        clienteEmpresarial.removeContratoTerminado(contrato2);
        Assertions.assertTrue(clienteEmpresarial.getContratosPagos().contains(contrato2));
        Assertions.assertFalse(clienteEmpresarial.getContratosTerminado().contains(contrato2));
    }

    @Test
    void clienteGetNome() {
        Assertions.assertEquals(clienteNome , clienteEmpresarial.getNome());
    }

    @Test
    void getNomeFantasia () {
        Assertions.assertEquals(clienteNomeFantasia, clienteEmpresarial.getNomeFantasia());
    }

    @Test
    void getCpfOuCnpj () {
        Assertions.assertEquals(clienteCnpj , clienteEmpresarial.getCpfOuCnpj());
    }

    @Test
    void testToString() {
        String printCliEmpre = "Nome fantasia: ET ; nome: Empresa Teste ; telefone: 12345678 ; e-mail: email@email.com.br ; endereco: Av. Teste, 123 ; cnpj: 12345678987654.";
        Assertions.assertEquals(printCliEmpre, clienteEmpresarial.toString());
    }

}
