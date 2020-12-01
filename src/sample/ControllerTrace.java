package sample;

import Trabalho1.Arquivo;
import Trabalho1.Portifolio;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ControllerTrace {
    private Portifolio portifolio = Main.portifolio;
    private Arquivo arquivo = Main.arquivo;

    @FXML
    public Button BotaoVoltar;

    @FXML
    public Button BotaoConfirmar;

    @FXML
    public Label LabelAvisos;

    @FXML
    public Label Titulo;

    @FXML
    public Label EntradaArquivo;
    @FXML
    public ScrollBar scrollBar;
    @FXML
    public TextField TextPath;

    public void ClicaConfirmar(){
        if (!TextPath.getText().equals("")){
            String path = TextPath.getText();
            if (arquivo.leDados(path).equals("OK")){

                LabelAvisos.setText("Arquivo lido...\n" +
                        "Dados adicionados...\n" +
                        "Contratos empilhados...\n"+
                        "Contratos Confirmados\n"+
                        portifolio.toString() +
                        portifolio.retornaTrace());

            }
            else{
                LabelAvisos.setText("Caminho inválido");
            }

        }
        else {
            LabelAvisos.setText("Campo vazio, preencha novamente");
 }
    }
    public void ClicaVoltar(){
        Main.changeScreen("TelaAdministrador");
    }
}
