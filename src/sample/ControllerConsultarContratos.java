package sample;

import Trabalho1.Contrato;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class ControllerConsultarContratos {

    @FXML
    Label labelTexto;

    public void ClicaVoltar(){
        labelTexto.setText("");
        Main.changeScreen("TelaAdministrador");
    }

    public void ClicaMostrar() {
       try {
            labelTexto.setText(Main.portifolio.consultarContratos());
        }
       catch (Exception e){
           labelTexto.setText("Nao ha contratos cadastrados");
       }
    }
}

