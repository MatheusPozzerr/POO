package sample;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import Trabalho1.*;

import java.util.ArrayList;

public class ContollerMostraPrestadores {

    @FXML
    Label labelTexto;

    public void ClicaVoltar(){
        labelTexto.setText("");
        Main.changeScreen("TelaAdministrador");
    }

    public void ClicaMostrar() {
        try {
            ArrayList<Prestador> prestadores;
            prestadores = Main.portifolio.getPrestadores();
            StringBuilder sb = new StringBuilder();
            int num = 1;
            for (Prestador prestadore : prestadores) {
                sb.append(num + "." + prestadore.toString() + "\n");
                num++;
            }
            labelTexto.setText(sb.toString());
        }
        catch (Exception e){

        }
    }
}
