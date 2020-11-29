package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import Trabalho1.*;

public class Controller {

    Portifolio portifolio= Main.portifolio;
    @FXML
    public Label LabelCpf;

    @FXML
    public TextField TextCPF;

    @FXML
    public Button BotaoSair;

    @FXML
    public Button BotaoConfirmar;

    @FXML
    public MenuButton Selecionar;

    @FXML
    public MenuItem BotaoAtendente;

    @FXML
    public MenuItem BotaoPrestador;

    @FXML
    public MenuItem BotaoAdministracao;

    @FXML
    public Label LabelAvisos;

    public void ClicaSair(){
        System.exit(0);
    }

    public void ClicaConfirmar() {
        boolean verificacpf=true;
        String cpf = TextCPF.getText();
        if (Selecionar.getText().equals("Selecionar")) {
            LabelAvisos.setText("Escolha alguma opcao no MenuButton.");
            LabelAvisos.setTextFill(Color.FIREBRICK);
        }
        if (Selecionar.getText().equals("Prestador")) {
            if(TextCPF.getText()!= null) {
                cpf = TextCPF.getText();
                if (!VerificaDigitosCpfOuCnpj(cpf) || cpf.length() != 11) {
                    LabelAvisos.setText("Erro: Cpf Invalido");
                    LabelAvisos.setTextFill(Color.FIREBRICK);
                } else {
                    verificacpf = false;
                    if (portifolio != null) {
                        for (Prestador prestadore : portifolio.getPrestadores()) {
                            if (cpf.equals(prestadore.getCpf())) {
                                Main.changeScreen("TelaPrestador");
                                verificacpf = false;
                                break;
                            }
                            verificacpf = false;
                        }
                    }
                    if (verificacpf == false) {
                        LabelAvisos.setText("Erro: Cpf não cadastrado.");
                        LabelAvisos.setTextFill(Color.FIREBRICK);
                    }
                }
            }
            else {
                LabelAvisos.setText("Preencha todos os campos.");
                LabelAvisos.setTextFill(Color.FIREBRICK);
            }
        }
        if (Selecionar.getText().equals("Atendente")){
            Main.changeScreen("TelaAtendente");
        }

        if (Selecionar.getText().equals("Administrador")){
            Main.changeScreen("TelaAdministrador");
        }
    }

    public void SelecionaAtendente(){
        Selecionar.setText("Atendente");
        LabelCpf.setVisible(false);
        TextCPF.setVisible(false);
    }

    public void SelecionaPrestador(){
        Selecionar.setText("Prestador");
        LabelCpf.setVisible(true);
        TextCPF.setVisible(true);
    }

    public void SelecionaAdminsitracao(){
        Selecionar.setText("Administrador");
        LabelCpf.setVisible(false);
        TextCPF.setVisible(false);
    }

            public static boolean VerificaDigitosCpfOuCnpj(String cpfOuCnpj){
                char[] c = cpfOuCnpj.toCharArray();
                boolean d = true;

                for (char value : c) {

                    if (!Character.isDigit(value)) {
                        System.out.println("Erro: não é um digito");
                        return false;
                    }
                }
                return true;
            }
}
