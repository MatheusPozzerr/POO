package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Trabalho1.*;

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
    public static Scene TelaTraceAdm;

    // Variavel estatica, usada em toda a aplicacao
    public static enum login {
        usr_atendente,
        usr_prestador,
        usr_admin,
        usr_deslogado;
    }
    public static login usuario = login.usr_deslogado;

    public static Portifolio portifolio=new Portifolio();
    public static Arquivo arquivo=new Arquivo();
    @Override
    public void start(Stage primaryStage) throws Exception{
       //

        arquivo.leArquivo(portifolio);

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

        Parent fxmlTrace = FXMLLoader.load(getClass().getResource("TelaTrace.fxml"));
        TelaTraceAdm = new Scene(fxmlTrace,640,480);

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
                break;
            case "TelaTrace":
                stage.setScene(TelaTraceAdm);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
