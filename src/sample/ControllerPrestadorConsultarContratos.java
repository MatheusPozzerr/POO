package sample;

import Trabalho1.Contrato;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import Trabalho1.*;
import java.util.ArrayList;

public class ControllerPrestadorConsultarContratos {
    @FXML
    public Label LabelAvisos;
    @FXML
    public Label labelCpf;
    @FXML
    public ComboBox<Contrato> contrato;
    @FXML
    public Button button;
    @FXML
    public TextField cpfTextField;

    public ArrayList<Contrato> contratos;
    Portifolio portifolio=Main.portifolio;


    public void ClicaVoltar(){
        LabelAvisos.setText("");
        Main.changeScreen("TelaPrestador");
    }

    public void ClicaVerifica() {
        boolean Verificando =true;
        if (cpfTextField.getText() != null) {
            String cpf = cpfTextField.getText();
            if (!VerificaDigitosCpfOuCnpj(cpf) || cpf.length() != 11) {
                LabelAvisos.setText("Erro: Cpf Invalido");
                LabelAvisos.setTextFill(Color.FIREBRICK);
            } else {
                if (portifolio.verificaCpfPrestador(cpf)) {
                    try {
                        String sb = portifolio.buscaCpfPrestador(cpf).consultarServicosContratados();
                        LabelAvisos.setText(sb);
                    }
                    catch (Exception e) {
                        Verificando=false;
                        LabelAvisos.setText("Prestador nao possui contratos Contratados");
                        LabelAvisos.setTextFill(Color.FIREBRICK);
                    }
                }
                else {
                    LabelAvisos.setText("Cpf nao cadastrado");
                    LabelAvisos.setTextFill(Color.FIREBRICK);
                }

            }
        }
        else {
            LabelAvisos.setText("Insira um cpf");
            LabelAvisos.setTextFill(Color.FIREBRICK);
        }
    }

    public static boolean VerificaDigitosCpfOuCnpj(String cpfOuCnpj){
        char[] c = cpfOuCnpj.toCharArray();
        for (char value : c) {
            if (!Character.isDigit(value)) {
                System.out.println("Erro: não é um digito");
                return false;
            }
        }
        return true;
    }
}
