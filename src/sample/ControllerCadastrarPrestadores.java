package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.paint.Color;
import Trabalho1.*;

public class ControllerCadastrarPrestadores {
    private int telefone;
    private String nome;
    private String email;
    private String cpf;
    Portifolio portifolio=Main.portifolio;

    @FXML
    public Button buttonConfirma;
    @FXML
    public TextField nomeTextField;
    @FXML
    public TextField emailTextField;
    @FXML
    public TextField cpfTextField;
    @FXML
    public TextField telefoneTextField;
    @FXML
    public Button buttonLimpa;
    @FXML
    public Label labelErros;


    @FXML
    public void ClicaVoltar(){
        Main.changeScreen("TelaAdministrador");
    }

    @FXML
    public void ApertaBotao(){
        if(nomeTextField.getText()!= null && telefoneTextField.getText()!= null && cpfTextField.getText() != null && emailTextField != null) {
            boolean verificacpf = true;
            boolean verificaTelefone = true;
            String cpf = cpfTextField.getText();

            if (!VerificaDigitosCpfOuCnpj(cpf) || cpf.length() != 11) {
                labelErros.setText("Erro: Cpf Invalido");
                labelErros.setTextFill(Color.FIREBRICK);
            } else {
                cpf = cpfTextField.getText();
                nome = nomeTextField.getText();
                try {
                    telefone = Integer.parseInt(telefoneTextField.getText());
                } catch (Exception e) {
                    System.err.format("Erro: %s%n", e);
                    verificaTelefone = false;
                    labelErros.setText("Erro: Numero de telefone invalido");
                    labelErros.setTextFill(Color.FIREBRICK);
                }
                if (portifolio.getPrestadores() != null) {
                    for (Prestador prestadore : portifolio.getPrestadores()) {
                        if (cpf.equals(prestadore.getCpf())) {
                            verificacpf = false;
                            labelErros.setText("Erro: CPF ja cadastrado.");
                            labelErros.setTextFill(Color.FIREBRICK);
                            break;
                        }
                    }
                    cpf = cpfTextField.getText();
                    email = emailTextField.getText();
                    nome = nomeTextField.getText();
                }
                if (verificacpf && verificaTelefone) {
                    Prestador prestador = new Prestador(nome, telefone, email, cpf, portifolio);
                    portifolio.adicionaPrestador(prestador);
                    labelErros.setText("Prestador cadastrado: " + prestador.toString());
                }
            }
        }
        else {
            labelErros.setText("Preencha todos os campos.");
            labelErros.setTextFill(Color.FIREBRICK);
        }
    }

    @FXML
    public void LimpaBotao(){
        nomeTextField.clear();
        telefoneTextField.clear();
        emailTextField.clear();
        cpfTextField.clear();
    }

    @FXML
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
