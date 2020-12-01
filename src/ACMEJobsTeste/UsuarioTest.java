package ACMEJobsTeste;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Trabalho1.*;

class UsuarioTest {

    private String cpfPrest;
    private String nomePrest;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        cpfPrest = "78945612378";
        nomePrest = "Prestador Teste";
        usuario = new Usuario(nomePrest, 87654321, "prestado@prestador.com", cpfPrest);
    }

    @Test
    void verificaDigitosCpfOuCnpj() {
        Assertions.assertTrue(Usuario.VerificaDigitosCpfOuCnpj("1234567809"));
        Assertions.assertFalse(Usuario.VerificaDigitosCpfOuCnpj("abcdefgh"));
        Assertions.assertFalse(Usuario.VerificaDigitosCpfOuCnpj("a1c2e3g4"));
    }

    @Test
    void gets() {
        Assertions.assertEquals(nomePrest, usuario.getNome());
        Assertions.assertEquals(cpfPrest, usuario.getCpf());
    }
}