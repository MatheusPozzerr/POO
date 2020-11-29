package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import Trabalho1.*;
import java.util.ArrayList;

public class ControllerConsultaClientes {

    @FXML
    Label labelTexto;

    public void ClicaVoltar(){
        labelTexto.setText("");
        Main.changeScreen("TelaAdministrador");
    }

    public void ClicaMostrar(){
        ArrayList<Cliente> clientes;
        clientes=Main.portifolio.getClientesIndividuais();
        StringBuilder sb = new StringBuilder();
        sb.append("Clientes Individuais: \n");
        int num=1;
        for (Cliente cliente : clientes) {
            sb.append(num + "." + cliente.toString() + "\n");
            num++;
        }
        ArrayList<Cliente> Empresarial;
        Empresarial=Main.portifolio.getClientesEmpresariais();
        sb.append("Clientes Empresariais:\n");
        for (Cliente cliente : Empresarial) {
            sb.append(num + "." + cliente.toString() + "\n");
            num++;
        }
        labelTexto.setText(sb.toString());
    }

}
