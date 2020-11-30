package sample;

import Trabalho1.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class ControllerTelaPrestador {

    Portifolio portifolio= Main.portifolio;
    Arquivo arquivo = Main.arquivo;

    @FXML
    public Label LabelCpf;

    @FXML
    public TextField TextCPF;

    @FXML
    public Button BotaoVoltar;

    @FXML
    public Button BotaoSair;

    @FXML
    public Button BotaoConfirmar;

    @FXML
    public MenuButton Servicos;

    @FXML
    public Label LabelAvisos;


    public void ClicaConfirmar(){
        if (Servicos.getText().equals("Servicos")){
            LabelAvisos.setText("Selecione um servico.");
        }
        if (Servicos.getText().equals("Confirmar ou Cancelar Contrato")){
            Main.changeScreen("TelaPrestadorConfirmarCancelarContrato");
        }
        if (Servicos.getText().equals("Consultar Servicos Contratados")){
            Main.changeScreen("TelaPrestadorConsultaContratos");
        }
       if (Servicos.getText().equals("Confirmar Termino de Servico")) {
           Main.changeScreen("TelaPrestadorConfirmarTermino");
       }

    }


    public void ClicaSair(){

        arquivo.salvaDados(portifolio);
        System.exit(0);
    }

    public void ClicaVoltar(){
        Main.changeScreen("TelaInicial");
    }

    // MenuButton Itens

    public void ConsultarContratos(){
        Servicos.setText("Consultar Servicos Contratados");
    }

    public void ConfirmarCancelarServico(){
        Servicos.setText("Confirmar ou Cancelar Contrato");
    }

    public void ConfirmarTermino(){
        Servicos.setText("Confirmar Termino de Servico");
    }


}
