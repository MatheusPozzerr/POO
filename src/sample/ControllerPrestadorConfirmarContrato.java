package sample;

import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import Trabalho1.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerPrestadorConfirmarContrato implements Initializable {
    @FXML
    public TextField descontoTextField;
    @FXML
    public Label LabelAvisos;
    @FXML
    public Label labeldesconto;
    @FXML
    public Label labelServicos;
    @FXML
    public Label labelCpf;
    @FXML
    public ComboBox<Contrato> contrato;
    @FXML
    public Button button;
    @FXML
    public Button buttonCancelar;
    @FXML
    public TextField cpfTextField;

    private final Portifolio portifolio=Main.portifolio;
    private ObservableList<Contrato> obsContrato;
    private List<Contrato> contratos = new ArrayList<>();

    public void ClicaVoltar(){
        LabelAvisos.setText("");
        Main.changeScreen("TelaPrestador");
    }

    public void Confirmar() {
        boolean verificacpf = false;
        boolean verificaDesconto = true;
        String cpf = cpfTextField.getText();
        double desconto;
        if (!VerificaDigitosCpfOuCnpj(cpf) || cpf.length() != 11) {
            LabelAvisos.setText("Erro: Cpf Invalido");
            LabelAvisos.setTextFill(Color.FIREBRICK);
        } else {
            try {
                desconto = Integer.parseInt(descontoTextField.getText());
            } catch (Exception e) {
                System.err.format("Erro: %s%n", e);
                verificaDesconto = false;
                LabelAvisos.setText("Erro: Desconto invalido");
                LabelAvisos.setTextFill(Color.FIREBRICK);
            }

            if (portifolio.verificaCpfPrestador(cpf)) {
                verificacpf = true;
            }
            else {
                LabelAvisos.setText("Erro: CPF não cadastrado.");
                LabelAvisos.setTextFill(Color.FIREBRICK);
            }
            if (verificacpf && verificaDesconto && contrato.getValue()!= null) {
                desconto = Integer.parseInt(descontoTextField.getText());
                double descontoo= contrato.getValue().getQtdDesconto();
                desconto = desconto + 7 + descontoo;
                if (desconto < 0 || desconto > 100) {
                    LabelAvisos.setText("Erro: Desconto invalido");
                    LabelAvisos.setTextFill(Color.FIREBRICK);
                }
                else{
                    desconto = Integer.parseInt(descontoTextField.getText());
                    double descontoTeste=desconto+contrato.getValue().getQtdDesconto();
                    if (0<= descontoTeste && descontoTeste <= 100) {
                        double valorDeDesconto = (contrato.getValue().getValorInicial() * desconto) / 100;
                        contrato.getValue().setDesconto(valorDeDesconto);
                        double valor = contrato.getValue().getValor() - valorDeDesconto;
                        contrato.getValue().setValor(valor);
                        contrato.getValue().getCliente().removeContratoConfirmando(contrato.getValue());
                        contrato.getValue().getPrestador().removeServicoContratadoAdicionaConfirmado(contrato.getValue());
                        LabelAvisos.setText("Contrato confirmado: " + contrato.getValue().toString());
                        portifolio.removeContratoContratados(contrato.getValue());
                        button.setDisable(true);
                        buttonCancelar.setDisable(true);
                    }
                }
            }
            else {
                LabelAvisos.setText("Erro: Selecione um contrato");
                LabelAvisos.setTextFill(Color.FIREBRICK);
            }
        }
    }

    public void ClicaCancelar() {
        boolean verificacpf=false;
        String cpf=cpfTextField.getText();
        if (!VerificaDigitosCpfOuCnpj(cpf) || cpf.length() != 11) {
            LabelAvisos.setText("Erro: Cpf Invalido");
            LabelAvisos.setTextFill(Color.FIREBRICK);
        }
        else {
            if (portifolio.verificaCpfPrestador(cpf)) {
                verificacpf = true;
            }
            else {
                LabelAvisos.setText("Erro: CPF não cadastrado.");
                LabelAvisos.setTextFill(Color.FIREBRICK);
            }
            if (verificacpf && contrato.getValue()!= null) {
                LabelAvisos.setText("Contrato cancelado: " + contrato.getValue().toString());
                contrato.getValue().getCliente().removeContratoCancelando(contrato.getValue());
                contrato.getValue().getPrestador().removeServicoContratado(contrato.getValue());
                portifolio.removeContratoContratadosCancelando(contrato.getValue());
                contratos = portifolio.buscaCpfPrestador(cpf).getServicosContratados();
                obsContrato = FXCollections.observableArrayList(contratos);
                contrato.setItems(obsContrato);
                button.setDisable(true);
                buttonCancelar.setDisable(true);
            } else {
                LabelAvisos.setText("Preencha todos os campos");
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

    public void ClicaVerifica() {
        boolean Verificando =false;
            if (cpfTextField.getText() != null) {
                String cpf = cpfTextField.getText();
                if (!VerificaDigitosCpfOuCnpj(cpf) || cpf.length() != 11) {
                    LabelAvisos.setText("Erro: Cpf Invalido");
                    LabelAvisos.setTextFill(Color.FIREBRICK);
                } else {
                    if (portifolio.verificaCpfPrestador(cpf)) {
                        Verificando=true;
                        contrato.setDisable(false);
                        labelServicos.setDisable(false);
                        try {
                            contratos = portifolio.buscaCpfPrestador(cpf).getServicosContratados();
                            obsContrato = FXCollections.observableArrayList(contratos);
                            contrato.setItems(obsContrato);
                        } catch (Exception e) {
                            Verificando=false;
                            LabelAvisos.setText("Cliente nao possui contratos Contratados");
                            LabelAvisos.setTextFill(Color.FIREBRICK);
                        }
                    } else {
                        LabelAvisos.setText("Cpf nao cadastrado");
                        LabelAvisos.setTextFill(Color.FIREBRICK);
                    }
                    if (Verificando){
                        descontoTextField.setDisable(false);
                        contrato.setDisable(false);
                        labeldesconto.setDisable(false);
                        labeldesconto.setDisable(false);
                        button.setDisable(false);
                        buttonCancelar.setDisable(false);
                    }
                }
            }
            else {
                LabelAvisos.setText("Preencha todos os campos");
                LabelAvisos.setTextFill(Color.FIREBRICK);
            }
    }



    public void carregaContratos(){
        try{
            contratos = portifolio.getContratosContratados();
            obsContrato = FXCollections.observableArrayList(contratos);
            contrato.setItems(obsContrato);
        }
        catch(Exception e){
            System.err.format("Erro: %s%n", e);
            LabelAvisos.setText("Nao ha contratos a serem confirmados");
            LabelAvisos.setTextFill(Color.FIREBRICK);
        }
    }

        public void Atualiza(){
            if (cpfTextField.getText() != null) {
                String cpf = cpfTextField.getText();
                if (!VerificaDigitosCpfOuCnpj(cpf) || cpf.length() != 11) {
                }
                else {
                    if (portifolio.verificaCpfPrestador(cpf)) {
                        try {
                            contratos = portifolio.buscaCpfPrestador(cpf).getServicosContratados();
                            obsContrato = FXCollections.observableArrayList(contratos);
                            contrato.setItems(obsContrato);
                        } catch (Exception e) {
                            System.err.format("Erro: %s%n", e);
                            LabelAvisos.setText("Nao ha contratos a serem confirmados ou cancelados");
                            LabelAvisos.setTextFill(Color.FIREBRICK);
                        }
                    }
                }
            }
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


