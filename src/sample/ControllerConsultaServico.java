package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import Trabalho1.*;
import java.util.ArrayList;

public class ControllerConsultaServico {

    @FXML
    Label labelTexto;

    public void ClicaVoltar(){
        labelTexto.setText("");
        Main.changeScreen("TelaAdministrador");
    }

    public void ClicaMostrar() {
        ArrayList<Servico> servicos;
        servicos = Main.portifolio.getServicos();
        StringBuilder sb = new StringBuilder();
        int num = 1;
        for (Servico servico : servicos) {
            sb.append(num + "." + servico.toString() + "\n");
            num++;
        }
        labelTexto.setText(sb.toString());
    }
}
