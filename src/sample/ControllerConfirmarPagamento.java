package sample;
import Trabalho1.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ControllerConfirmarPagamento {
    private final Portifolio portifolio=Main.portifolio;
    private ObservableList<Contrato> obsContrato;
    private List<Contrato> contratos = new ArrayList<>();

    @FXML
    public TextField cpfTextField;
    @FXML
    public MenuButton Selecionar;
    @FXML
    public Label LabelAvisos;
    @FXML
    public Label labelCpf;
    @FXML
    public Label labelServicos;
    @FXML
    public ComboBox<Contrato> contrato;
    @FXML
    public Button button;


    public void ClicaVoltar(){
        cpfTextField.setText("");
        if(Main.usuario.equals(Main.login.usr_admin)) {
            Main.changeScreen("TelaAdministrador");
        } else if(Main.usuario.equals(Main.login.usr_atendente)){
            Main.changeScreen("TelaAtendente");
        } else {
            Main.changeScreen("TelaPrestador");
        }
    }

    public void Cliente(){
        Selecionar.setText("Cliente Individual");
        String cpf=cpfTextField.getText();
        cpfTextField.setDisable(false);
        labelCpf.setDisable(false);
        button.setDisable(false);
        labelCpf.setText("Cpf:");
    }

    public void Atualiza() {
        if (Selecionar.getText().equals("Cliente Individual")) {
            if (cpfTextField.getText() != null) {
                String cpf = cpfTextField.getText();
                if (!VerificaDigitosCpfOuCnpj(cpf) || cpf.length() != 11) {
                    LabelAvisos.setText("Erro: Cpf Invalido");
                    LabelAvisos.setTextFill(Color.FIREBRICK);
                } else {
                    if (portifolio.verificaCpfCliente(cpf)) {
                        contrato.setDisable(false);
                        labelServicos.setDisable(false);
                        try {
                            contratos = portifolio.buscaCpfCliente(cpf).getContratosTerminado();
                            obsContrato = FXCollections.observableArrayList(contratos);
                            contrato.setItems(obsContrato);
                        } catch (Exception e) {
                            LabelAvisos.setText("Cliente nao possui contratos terminados");
                            LabelAvisos.setTextFill(Color.FIREBRICK);
                        }
                    }
                }
            }
        }
        if (Selecionar.getText().equals("Cliente Empresarial")) {
            if (cpfTextField.getText() != null) {
                String cnpj = cpfTextField.getText();
                if (!VerificaDigitosCpfOuCnpj(cnpj) || cnpj.length() != 14) {
                    LabelAvisos.setText("Erro: Cnpj Invalido");
                    LabelAvisos.setTextFill(Color.FIREBRICK);
                } else {
                    if (portifolio.verificaCnpjCliente(cnpj)) {
                        contrato.setDisable(false);
                        labelServicos.setDisable(false);
                        try {
                            contratos = portifolio.buscaCnpjCliente(cnpj).getContratosTerminado();
                            obsContrato = FXCollections.observableArrayList(contratos);
                            contrato.setItems(obsContrato);
                        } catch (Exception e) {
                            LabelAvisos.setText("Cliente nao possui contratos terminados");
                            LabelAvisos.setTextFill(Color.FIREBRICK);
                        }
                    }
                }
            }
        }
    }


    public void ClicaVerifica(){
        if (Selecionar.getText().equals("Cliente Individual")) {
            if (cpfTextField.getText() != null) {
                String cpf = cpfTextField.getText();
                if (!VerificaDigitosCpfOuCnpj(cpf) || cpf.length() != 11) {
                    LabelAvisos.setText("Erro: Cpf Invalido");
                    LabelAvisos.setTextFill(Color.FIREBRICK);
                }
                else {
                    if (portifolio.verificaCpfCliente(cpf)) {
                        contrato.setDisable(false);
                        labelServicos.setDisable(false);
                        try{
                            contratos = portifolio.buscaCpfCliente(cpf).getContratosTerminado();
                            obsContrato = FXCollections.observableArrayList(contratos);
                            contrato.setItems(obsContrato);
                        }
                        catch(Exception e){
                            LabelAvisos.setText("Cliente nao possui contratos terminados");
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
                LabelAvisos.setText("Preencha todos os campos");
                LabelAvisos.setTextFill(Color.FIREBRICK);
            }
        }
        if (Selecionar.getText().equals("Cliente Empresarial")) {
            if (cpfTextField.getText() != null) {
                String cnpj = cpfTextField.getText();
                if (!VerificaDigitosCpfOuCnpj(cnpj) || cnpj.length() != 14) {
                    LabelAvisos.setText("Erro: Cnpj Invalido");
                    LabelAvisos.setTextFill(Color.FIREBRICK);
                }
                else {
                    if (portifolio.verificaCnpjCliente(cnpj)) {
                        contrato.setDisable(false);
                        labelServicos.setDisable(false);
                        try{
                            contratos = portifolio.buscaCnpjCliente(cnpj).getContratosTerminado();
                        obsContrato = FXCollections.observableArrayList(contratos);
                        contrato.setItems(obsContrato);
                        }
                        catch(Exception e){
                            LabelAvisos.setText("Cliente nao possui contratos terminados");
                            LabelAvisos.setTextFill(Color.FIREBRICK);
                        }
                    }
                    else {
                        LabelAvisos.setText("Cnpj nao cadastrado");
                        LabelAvisos.setTextFill(Color.FIREBRICK);
                    }
                }
            }
            else {
                LabelAvisos.setText("Preencha todos os campos");
                LabelAvisos.setTextFill(Color.FIREBRICK);
            }
        }
    }

    public void ClienteEmpresarial(){
        Selecionar.setText("Cliente Empresarial");
        cpfTextField.setDisable(false);
        labelCpf.setDisable(false);
        button.setDisable(false);
        labelCpf.setText("Cnpj:");
    }

    public void ClicaConfirmar() {
        String cpf =cpfTextField.getText();
        String cnpj =cpfTextField.getText();
        if (Selecionar.getText().equals("Cliente Individual")) {
            if (cpfTextField.getText() != null && contrato.getValue() != null) {
                if (!VerificaDigitosCpfOuCnpj(cpf) || cpf.length() != 11) {
                    LabelAvisos.setText("Erro: Cpf Invalido");
                    LabelAvisos.setTextFill(Color.FIREBRICK);
                } else {
                    portifolio.buscaCpfCliente(cpf).removeContratoTerminado(contrato.getValue());
                    LabelAvisos.setText("Confirmado pagamento do contrato: " + contrato.getValue().toString());
                }
            }
            else {
                LabelAvisos.setText("Preencha todos os campos.");
                LabelAvisos.setTextFill(Color.FIREBRICK);
            }
        }
        if (Selecionar.getText().equals("Cliente Empresarial")) {
            if (cpfTextField.getText() != null && contrato.getValue() != null) {
                if (!VerificaDigitosCpfOuCnpj(cnpj) || cpf.length() != 14) {
                    LabelAvisos.setText("Erro: Cnpj Invalido");
                    LabelAvisos.setTextFill(Color.FIREBRICK);
                }
                else {
                    portifolio.buscaCnpjCliente(cnpj).removeContratoTerminado(contrato.getValue());
                    portifolio.removeContratoTerminado(contrato.getValue());
                    LabelAvisos.setText("Confirmado pagamento do contrato: " + contrato.getValue().toString());
                }
            }
            else {
                LabelAvisos.setText("Preencha todos os campos.");
                LabelAvisos.setTextFill(Color.FIREBRICK);
            }
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
