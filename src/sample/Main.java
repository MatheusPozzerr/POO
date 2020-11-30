package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Trabalho1.*;

import java.time.LocalDateTime;

public class Main extends Application {

    public static Stage stage;
    public static Scene TelaInicial;
    public static Scene TelaAdiministrador;
    public static Scene TelaPrestador;
    public static Scene TelaAtendente;
    public static Scene TelaCadastrarPrestador;
    public static Scene TelaCadastrarServico;
    public static Scene TelaCadastrarCliente;
    public static Scene TelaCadastrarContrato;
    public static Scene TelaMostraPrestadores;
    public static Scene TelaConsultaClientes;
    public static Scene TelaConsultaServicos;
    public static Scene TelaConfirmarPagamento;
    public static Scene TelaConfirmarCancelarContrato;
    public static Scene TelaPrestadorConfirmarCancelarContrato;
    public static Scene TelaConsultaContratos;
    public static Scene TelaTerminoContrato;
    public static Scene TelaPrestadorConsultaContratos;
    public static Scene TelaPrestadorrrConfirmarTermino;

	// Variavel estatica, usada em toda a aplicacao
    public static enum login {
        usr_atendente,
        usr_prestador,
        usr_admin,
        usr_deslogado;
    }
    public static login usuario = login.usr_deslogado;

    public static Portifolio portifolio=new Portifolio();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Prestador prestador1 = new Prestador("Laura", 989054321, "laura@gmail.com", "12345678912", portifolio);
        portifolio.adicionaPrestador(prestador1);
        Servico servico1 = new Servico(89, "pintura de casas", "valor por m²");
        Servico servico2 = new Servico(18, "troca de tomadas antigas por novas", "valor por tomada");
        portifolio.adicionaServico(servico1, "12345678912");
        portifolio.adicionaServico(servico2, "12345678912");

        Prestador prestador2 = new Prestador("Matheus", 34856978, "matheus@gmail.com", "98765432198", portifolio);
        portifolio.adicionaPrestador(prestador2);
        Servico servico3 = new Servico(100, "segurança para eventos", "valor por hora");
        portifolio.adicionaServico(servico3, "98765432198");
        Servico servico4 = new Servico(20, "buffet para festas", "valor por pessoa, mínimo 100 pessoas");
        portifolio.adicionaServico(servico4, "98765432198");

        Prestador prestador3 = new Prestador("Giovanni", 34589786, "giovanni@gmail.com", "87654321098", portifolio);
        portifolio.adicionaPrestador(prestador3);
        Servico servico5 = new Servico(20, "docinhos para festas", "valor por cento");
        portifolio.adicionaServico(servico5, "87654321098");
        Servico servico6 = new Servico(150, "bolo de casamento", "bolo de 3 andares simples");
        portifolio.adicionaServico(servico6, "87654321098");

        Cliente clienteIndividual1 = new ClienteIndividual("Stella", 987747851, "stellamaris@uol.com.br",
                "Av. Cristóvao Colombo, 176 - Porto Alegre", "02478945602");
        portifolio.adicionaClienteIndividual(clienteIndividual1);
        Cliente clienteIndividual2 = new ClienteIndividual("Marcos", 34568974, "marcostel@bol.com.br",
                "Rua Maré, 654 - Cachoeirinha", "78945612378");
        portifolio.adicionaClienteIndividual(clienteIndividual2);

        ClienteEmpresarial clienteEmpresarial1 = new ClienteEmpresarial("Ceumar", "Ceumar Company" ,
                34568741, "contato@ceumar.com.br", "Av. Farrapos, 566 - Porto Alegre", "12345678901234");
        portifolio.adicionaClienteEmpresarial(clienteEmpresarial1);
        ClienteEmpresarial clienteEmpresarial2 = new ClienteEmpresarial("Ana Eventos", "Ana Eventos Company",
                36547891, "ana.eventos@gmail.com",  "Av Protásio Alves, 789 - Porto Alegre, ", "09876543210987");
        portifolio.adicionaClienteEmpresarial(clienteEmpresarial2);

        LocalDateTime horario1 = LocalDateTime.of(2020, 10, 12, 14, 30);
        Contrato contrato1 = new Contrato(89, "Devolução de apartamento, pintura de sala de 10 m²", horario1);
        contrato1.setDesconto(6.23);
        contrato1.setValor(contrato1.valor-6.23);
        portifolio.adicionaContrato(contrato1, servico1, "12345678901234");

        LocalDateTime horario2 = LocalDateTime.of(2021, 01, 01, 22, 00);
        Contrato contrato2 = new Contrato(20, "Festa de ano novo para 100 pessoas", horario2);
        contrato2.setDesconto(1.4);
        contrato2.setValor(contrato2.valor-1.4);
        portifolio.adicionaContrato(contrato2, servico4, "09876543210987");

        LocalDateTime horario3 = LocalDateTime.of(2021, 02, 15, 19, 30);
        Contrato contrato3 = new Contrato(20, "Evento empresarial", horario3);
        portifolio.adicionaContrato(contrato3, servico5, "78945612378");

        LocalDateTime horario4 = LocalDateTime.of(2020, 10, 30, 8, 30);
        Contrato contrato4 = new Contrato(18, "Troca de 1 tomada", horario4);
        portifolio.adicionaContrato(contrato4, servico2, "02478945602");

        //


        stage=primaryStage;
        primaryStage.setTitle("ACMEJobs");

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        TelaInicial= new Scene(root,400,305);

        Parent fxmlSegunda = FXMLLoader.load(getClass().getResource("TelaAdministrador.fxml"));
        TelaAdiministrador = new Scene (fxmlSegunda,500,345);

        Parent fxmlTelaPrestador= FXMLLoader.load(getClass().getResource("TelaPrestador.fxml"));
        TelaPrestador = new Scene (fxmlTelaPrestador,500,345);

        Parent fxmlTelaAtendente = FXMLLoader.load(getClass().getResource("TelaAtendente.fxml"));
        TelaAtendente = new Scene (fxmlTelaAtendente,500,345);

        Parent fxmlCadastroPrestador = FXMLLoader.load(getClass().getResource("TelaCadastrarPrestadores.fxml"));
        TelaCadastrarPrestador = new Scene(fxmlCadastroPrestador,640,480);

        Parent fxmlCadastroServico = FXMLLoader.load(getClass().getResource("TelaCadastrarServico.fxml"));
        TelaCadastrarServico = new Scene(fxmlCadastroServico,640,480);

        Parent fxmlCadastroCliente = FXMLLoader.load(getClass().getResource("TelaCadastrarCliente.fxml"));
        TelaCadastrarCliente = new Scene(fxmlCadastroCliente,640,480);

        Parent fxmlCadastroContrato = FXMLLoader.load(getClass().getResource("TelaCadastrarContrato.fxml"));
        TelaCadastrarContrato = new Scene(fxmlCadastroContrato,640,480);

        Parent fxmlMostraPrestadores = FXMLLoader.load(getClass().getResource("TelaMostraPrestadores.fxml"));
        TelaMostraPrestadores = new Scene(fxmlMostraPrestadores,640,480);

        Parent fxmlConsultaClientes= FXMLLoader.load(getClass().getResource("TelaConsultaClientes.fxml"));
        TelaConsultaClientes = new Scene(fxmlConsultaClientes,1075,480);

        Parent fxmlConsultaServicos = FXMLLoader.load(getClass().getResource("TelaConsultaServicos.fxml"));
        TelaConsultaServicos= new Scene(fxmlConsultaServicos,640,480);

        Parent fxmlConfirmarPagamento = FXMLLoader.load(getClass().getResource("TelaConfirmarPagamento.fxml"));
        TelaConfirmarPagamento = new Scene(fxmlConfirmarPagamento,640,480);

        Parent fxmlConfirmarCancelarContrato = FXMLLoader.load(getClass().getResource("TelaConfirmarCancelarContrato.fxml"));
        TelaConfirmarCancelarContrato = new Scene(fxmlConfirmarCancelarContrato,640,480);

        Parent fxmlPrestadorConfirmarCancelarContrato = FXMLLoader.load(getClass().getResource("TelaPrestadorConfirmarCancelarContrato.fxml"));
        TelaPrestadorConfirmarCancelarContrato=new Scene(fxmlPrestadorConfirmarCancelarContrato,640,480);

        Parent fxmlConsultarContrato= FXMLLoader.load(getClass().getResource("TelaConsultarContratos.fxml"));
        TelaConsultaContratos=new Scene(fxmlConsultarContrato,640,480);

        Parent fxmlConfirmarTermino= FXMLLoader.load(getClass().getResource("TelaConfirmaTermino.fxml"));
        TelaTerminoContrato =new Scene(fxmlConfirmarTermino,640,480);

        Parent fxmlPrestadorConsultaContratos= FXMLLoader.load(getClass().getResource("TelaPrestadoresConsultarContratos.fxml"));
        TelaPrestadorConsultaContratos =new Scene(fxmlPrestadorConsultaContratos,640,480);

        Parent fxmlPrestadorConfirmarTermino= FXMLLoader.load(getClass().getResource("TelaPrestadorConfirmarTermino.fxml"));
        TelaPrestadorrrConfirmarTermino =new Scene(fxmlPrestadorConfirmarTermino,640,480);

        primaryStage.setScene(TelaInicial);
        primaryStage.show();
    }

    public static void changeScreen(String scr){
        switch (scr){
            case "TelaInicial":
                stage.setScene(TelaInicial);
                break;
            case "TelaAdministrador":
                stage.setScene(TelaAdiministrador);
                break;
            case"TelaPrestador":
                stage.setScene(TelaPrestador);
                break;
            case"TelaAtendente":
                stage.setScene(TelaAtendente);
                break;
            case "TelaCadastrarPrestador":
                stage.setScene(TelaCadastrarPrestador);
                break;
            case"TelaCadastrarServico":
                stage.setScene(TelaCadastrarServico);
                break;
            case"TelaCadastrarCliente":
                stage.setScene(TelaCadastrarCliente);
                break;
            case"TelaCadastrarContrato":
                stage.setScene(TelaCadastrarContrato);
                break;
            case"TelaMostraPrestadores":
                stage.setScene(TelaMostraPrestadores);
                break;
            case"TelaConsultaClientes":
                stage.setScene(TelaConsultaClientes);
                break;
            case"TelaConsultaServicos":
                stage.setScene(TelaConsultaServicos);
                break;
            case"TelaConfirmarPagamento":
                stage.setScene(TelaConfirmarPagamento);
                break;
            case"TelaConfirmarCancelarContrato":
                stage.setScene(TelaConfirmarCancelarContrato);
                break;
            case"TelaPrestadorConfirmarCancelarContrato":
                stage.setScene(TelaPrestadorConfirmarCancelarContrato);
                break;
            case"TelaConsultarContratos":
                stage.setScene(TelaConsultaContratos);
                break;
            case"TelaConfirmarTermino":
                stage.setScene(TelaTerminoContrato);
                break;
            case "TelaPrestadorConsultaContratos":
                stage.setScene(TelaPrestadorConsultaContratos);
                break;
            case "TelaPrestadorConfirmarTermino":
                stage.setScene(TelaPrestadorrrConfirmarTermino);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
