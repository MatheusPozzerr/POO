package sample;

import Trabalho1.Portifolio;
import Trabalho1.Prestador;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import Trabalho1.*;

public class ControllerCadastrarServico {

    private Portifolio portifolio = Main.portifolio;

    @FXML
    public Button buttonConfirma;
    @FXML
    public TextField valorTextField;
    @FXML
    public TextField descricaoTextField;
    @FXML
    public TextField cpfTextField;
    @FXML
    public TextField observacaoTextField;
    @FXML
    public Button buttonLimpa;
    @FXML
    public Label LabelAvisos;

    public void ClicaVoltar(){
        Main.changeScreen("TelaAdministrador");
    }

    public void LimpaBotao(){
        valorTextField.clear();
        descricaoTextField.clear();
        observacaoTextField.clear();
        cpfTextField.clear();
    }


    @FXML
    public void ApertaBotao(){
        if(observacaoTextField.getText()!= null && descricaoTextField.getText()!= null && cpfTextField.getText() != null && valorTextField!=null) {
            boolean verificacpf = false;
            boolean verificaTelefone = true;
            String cpf = cpfTextField.getText();
            String descricao = descricaoTextField.getText();
            String observacao = observacaoTextField.getText();
            if (!VerificaDigitosCpfOuCnpj(cpf) || cpf.length() != 11) {
                LabelAvisos.setText("Erro: Cpf Invalido");
                LabelAvisos.setTextFill(Color.FIREBRICK);
            } else {
                cpf = cpfTextField.getText();

                try {
                    int valor = Integer.parseInt(valorTextField.getText());
                } catch (Exception e) {
                    System.err.format("Erro: %s%n", e);
                    verificaTelefone = false;
                    LabelAvisos.setText("Erro: Valor invalido");
                    LabelAvisos.setTextFill(Color.FIREBRICK);
                }
                if (portifolio.getPrestadores() != null) {
                    for (Prestador prestadore : portifolio.getPrestadores()) {
                        if (cpf.equals(prestadore.getCpf())) {
                            verificacpf = true;
                            LabelAvisos.setText("Erro: CPF ja cadastrado.");
                            LabelAvisos.setTextFill(Color.FIREBRICK);
                            break;
                        }
                    }
                    if (!verificacpf) {
                        LabelAvisos.setText("Erro: CPF de prestador nao cadastrado.");
                        LabelAvisos.setTextFill(Color.FIREBRICK);
                    }
                }
                if (verificacpf && verificaTelefone) {
                    int valor = Integer.parseInt(valorTextField.getText());
                    Servico servico = new Servico(valor, observacao, descricao);
                    portifolio.adicionaServico(servico, cpf);
                    LabelAvisos.setText("Servico cadastrado: " + servico.toString());
                }
            }
        }
        else{
            LabelAvisos.setText("Preencha todos os campos.");
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
