package ACMEJobsTeste;


import Trabalho1.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class PortifolioTest {

    private Portifolio portifolio;
    private ClienteIndividual clienteIndividual;
    private String cpfCliente;
    private ClienteEmpresarial clienteEmpresarial;
    private String cnpjCliEmp;
    private Prestador prestador;
    private String cpfPrest;
    private Servico servico;
    private LocalDateTime horario;
    private Contrato contrato;

    @BeforeEach
    void setUp() {
        portifolio = new Portifolio();
        clienteIndividual = new ClienteIndividual("Fulano", 12345678, "email@email.com", "Av. Teste, 1", "12345678912");
        cpfCliente = ("12345678912");
        clienteEmpresarial = new ClienteEmpresarial("ClienteEmpresarial", "ClienteEmpresarialCompany" ,23149875,
                "empresa@empresa.com", "Rua Testando, 10", "12345678912345");
        cnpjCliEmp = ("12345678912345");
        prestador = new Prestador("Prestador", 87654321, "prestado@prestador.com", "78945612378");
        cpfPrest = ("78945612378");
        servico = new Servico(100, "Limpeza", "dia");
        horario = LocalDateTime.of(2020, 10, 14, 00, 00);
        contrato = new Contrato(100, "limpeza casa de 2 andares", horario);
    }

    @Test
    void adicionaGets(){
        portifolio.adicionaClienteEmpresarial(clienteEmpresarial);
        portifolio.adicionaClienteIndividual(clienteIndividual);
        portifolio.adicionaPrestador(prestador);
        portifolio.adicionaServico(servico, cpfPrest);

        Assertions.assertTrue(portifolio.getClientesEmpresariais().contains(clienteEmpresarial));
        Assertions.assertTrue(portifolio.getClientesIndividuais().contains(clienteIndividual));
        Assertions.assertTrue(portifolio.getPrestadores().contains(prestador));
        Assertions.assertTrue(portifolio.getServicos().contains(servico));
    }

    @Test
    void adicionaRemoveGetsContratos(){
        portifolio.adicionaClienteEmpresarial(clienteEmpresarial);
        portifolio.adicionaClienteIndividual(clienteIndividual);
        portifolio.adicionaPrestador(prestador);
        portifolio.adicionaServico(servico, cpfPrest);

        portifolio.adicionaContrato(contrato, servico, cpfCliente);
        Assertions.assertTrue(portifolio.getContratos().contains(contrato));
        Assertions.assertTrue(portifolio.getContratosContratados().contains(contrato));

        portifolio.removeContratoContratados(contrato);
        Assertions.assertTrue(portifolio.getContratosConfirmado().contains(contrato));

        portifolio.adicionaContrato(contrato, servico, cnpjCliEmp);
        portifolio.removeContratoContratadosCancelando(contrato);
        Assertions.assertTrue(portifolio.getContratosCancelados().contains(contrato));

        portifolio.removeContratoConfirmado(contrato);
        Assertions.assertTrue(portifolio.getContratosTerminados().contains(contrato));
    }

    @Test
    void verifica(){
        portifolio.adicionaClienteEmpresarial(clienteEmpresarial);
        portifolio.adicionaClienteIndividual(clienteIndividual);
        portifolio.adicionaPrestador(prestador);

        Assertions.assertTrue(portifolio.verificaCnpjCliente(cnpjCliEmp));
        Assertions.assertTrue(portifolio.verificaCpfCliente(cpfCliente));
        Assertions.assertTrue(portifolio.verificaCpfPrestador(cpfPrest));
    }

    @Test
    void busca(){
        portifolio.adicionaClienteEmpresarial(clienteEmpresarial);
        portifolio.adicionaClienteIndividual(clienteIndividual);
        portifolio.adicionaPrestador(prestador);

        Assertions.assertEquals(clienteEmpresarial, portifolio.buscaCnpjCliente(cnpjCliEmp));
        Assertions.assertEquals(clienteIndividual, portifolio.buscaCpfCliente(cpfCliente));
        Assertions.assertEquals(prestador, portifolio.buscaCpfPrestador(cpfPrest));
    }

}