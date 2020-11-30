package sample;

import Trabalho1.Contrato;
import Trabalho1.Portifolio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ControllerPrestadorConfirmarTermino {


    @FXML
    public Label LabelAvisos;
    @FXML
    public Label labelServicos;
    @FXML
    public Label labelCpf;
    @FXML
    public ComboBox<Contrato> contrato;
    @FXML
    public Button button;
    @FXML
    public TextField cpfTextField;

    private final Portifolio portifolio=Main.portifolio;
    private ObservableList<Contrato> obsContrato;
    private List<Contrato> contratos = new ArrayList<>();

    public void ClicaVoltar(){
        Main.changeScreen("TelaPrestador");
    }

    public void Confirmar() {
        boolean verificacpf = false;

        String cpf = cpfTextField.getText();
        double desconto;
        if (!VerificaDigitosCpfOuCnpj(cpf) || cpf.length() != 11) {
            LabelAvisos.setText("Erro: Cpf Invalido");
            LabelAvisos.setTextFill(Color.FIREBRICK);
        } else {

            if (portifolio.verificaCpfPrestador(cpf)) {
                verificacpf = true;
            } else {
                LabelAvisos.setText("Erro: CPF não cadastrado.");
                LabelAvisos.setTextFill(Color.FIREBRICK);
            }
            try {
                contrato.getValue().getCliente().removeContratoConfirmados(contrato.getValue());
                contrato.getValue().getPrestador().removeServicoConfirmado(contrato.getValue());
                LabelAvisos.setText("Contrato terminado: " + contrato.getValue().toString());
                portifolio.removeContratoContratados(contrato.getValue());
                button.setDisable(true);
                contrato.setDisable(true);
                labelServicos.setDisable(true);
            } catch (Exception e) {
                LabelAvisos.setText("Erro: Selecione um contrato");
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
                        contratos = portifolio.buscaCpfPrestador(cpf).getServicosConfirmados();
                        obsContrato = FXCollections.observableArrayList(contratos);
                        contrato.setItems(obsContrato);
                    } catch (Exception e) {
                        Verificando=false;
                        LabelAvisos.setText("Prestador nao possui contratos confirmados");
                        LabelAvisos.setTextFill(Color.FIREBRICK);
                    }
                }
                else {
                    LabelAvisos.setText("Cpf nao cadastrado");
                    LabelAvisos.setTextFill(Color.FIREBRICK);
                }
                if (Verificando){
                    contrato.setDisable(false);
                    button.setDisable(false);
                    labelServicos.setDisable(false);
                }
            }
        }
        else {
            LabelAvisos.setText("Preencha todos os campos");
            LabelAvisos.setTextFill(Color.FIREBRICK);
        }
    }

    }

