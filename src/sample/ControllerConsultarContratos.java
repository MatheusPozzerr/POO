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
        if(Main.usuario.equals(Main.login.usr_admin)) {
            Main.changeScreen("TelaAdministrador");
        } else if(Main.usuario.equals(Main.login.usr_atendente)){
            Main.changeScreen("TelaAtendente");
        } else {
            Main.changeScreen("TelaPrestador");
        }
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

