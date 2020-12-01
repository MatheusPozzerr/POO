package ACMEJobsTeste;


import Trabalho1.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class PrestadorTest {

    private Portifolio portifolio;
    private Servico servico;
    private LocalDateTime horario;
    private Contrato contrato;
    private Prestador prestador;
    private String cpfPrest;
    private String nome;

    @BeforeEach
    void setUp() {
        portifolio = new Portifolio();
        servico = new Servico(100, "Limpeza", "dia");
        horario = LocalDateTime.of(2020, 10, 14, 00, 00);
        contrato = new Contrato(100, "limpeza casa de 2 andares", horario);
        prestador = new Prestador("Prestador", 87654321, "prestado@prestador.com", "78945612378");
        cpfPrest = ("78945612378");
        nome = ("Prestador");
    }

    @Test
    void adicionaRemoveGets(){
        prestador.adicionaServico(servico);
        Assertions.assertEquals(servico, prestador.getServicos().get(0));

        prestador.adicionaServicoContratados(contrato);
        Assertions.assertEquals(contrato, prestador.getServicosContratados().get(0));

        prestador.removeServicoContratado(contrato);
        Assertions.assertTrue(prestador.getServicosContratados().isEmpty());

        prestador.adicionaServicoContratados(contrato);
        prestador.removeServicoContratadoAdicionaConfirmado(contrato);
        Assertions.assertEquals(contrato, prestador.getServicosConfirmados().get(0));

        prestador.removeServicoConfirmado(contrato);
        Assertions.assertTrue(prestador.getServicosConfirmados().isEmpty());

        Assertions.assertEquals(cpfPrest, prestador.getCpf());
        Assertions.assertEquals(nome, prestador.getNome());
    }

}