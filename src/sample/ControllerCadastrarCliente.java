package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import Trabalho1.*;
import javafx.scene.paint.Color;

public class ControllerCadastrarCliente {

    private final Portifolio portifolio = Main.portifolio;

    @FXML
    public MenuButton Selecionar;
    @FXML
    public TextField nomeFantasiaTextField;
    @FXML
    public TextField telefoneTextField;
    @FXML
    public TextField enderecoTextField;
    @FXML
    public TextField cpfTextField;
    @FXML
    public TextField emailTextField;
    @FXML
    public TextField nomeTextField;
    @FXML
    public Label LabelAvisos;
    @FXML
    public Label labelNomeFantasia;
    @FXML
    public Label labelEndereco;
    @FXML
    public Label labelEmail;
    @FXML
    public Label labelCpf;
    @FXML
    public Label labelNome;
    @FXML
    public Label labelTelefone;


    public void ClicaVoltar(){
        LimpaBotao();
        if(Main.usuario.equals(Main.login.usr_admin)) {
            Main.changeScreen("TelaAdministrador");
        } else if(Main.usuario.equals(Main.login.usr_atendente)){
            Main.changeScreen("TelaAtendente");
        } else {
            Main.changeScreen("TelaPrestador");
        }
    }

    public void LimpaBotao(){
        enderecoTextField.clear();
        telefoneTextField.clear();
        nomeFantasiaTextField.clear();
        cpfTextField.clear();
        nomeTextField.clear();
        emailTextField.clear();
    }

    public void Cliente(){
        Selecionar.setText("Cliente Individual");
        nomeFantasiaTextField.clear();
        labelNomeFantasia.setDisable(true);
        nomeFantasiaTextField.setDisable(true);
        nomeTextField.setDisable(false);
        labelNome.setDisable(false);
        cpfTextField.setDisable(false);
        labelCpf.setDisable(false);
        labelCpf.setText("Cpf:");
        emailTextField.setDisable(false);
        labelEmail.setDisable(false);
        enderecoTextField.setDisable(false);
        labelEndereco.setDisable(false);
        telefoneTextField.setDisable(false);
        labelTelefone.setDisable(false);
    }

    public void ClienteEmpresarial(){
        Selecionar.setText("Cliente Empresarial");
        labelNomeFantasia.setDisable(false);
        nomeFantasiaTextField.setDisable(false);
        nomeTextField.setDisable(false);
        labelNome.setDisable(false);
        cpfTextField.setDisable(false);
        labelCpf.setDisable(false);
        labelCpf.setText("Cnpj:");
        emailTextField.setDisable(false);
        labelEmail.setDisable(false);
        enderecoTextField.setDisable(false);
        labelEndereco.setDisable(false);
        telefoneTextField.setDisable(false);
        labelTelefone.setDisable(false);
    }

    public void clicaConfirmar(){
        if(Selecionar.getText().equals("Cliente Individual")) {
            if(nomeTextField.getText()!= null && enderecoTextField.getText()!= null && cpfTextField.getText() != null && telefoneTextField !=null && emailTextField != null) {
                boolean verificacpf = true;
                boolean verificaTelefone = true;
                String cpf = cpfTextField.getText();
                String nome = nomeTextField.getText();
                String endereco = enderecoTextField.getText();
                if (!VerificaDigitosCpfOuCnpj(cpf) || cpf.length() != 11) {
                    LabelAvisos.setText("Erro: Cpf Invalido");
                    LabelAvisos.setTextFill(Color.FIREBRICK);
                } else {
                    cpf = cpfTextField.getText();

                    try {
                        int telefone = Integer.parseInt(telefoneTextField.getText());
                    } catch (Exception e) {
                        System.err.format("Erro: %s%n", e);
                        verificaTelefone = false;
                        LabelAvisos.setText("Erro: Telefone invalido");
                        LabelAvisos.setTextFill(Color.FIREBRICK);
                    }
                    if (portifolio.getClientesIndividuais() != null) {
                        if (portifolio.verificaCpfCliente(cpf)) {
                            verificacpf = false;
                            LabelAvisos.setText("Erro: CPF ja cadastrado.");
                            LabelAvisos.setTextFill(Color.FIREBRICK);
                        }
                    }
                    if (verificacpf && verificaTelefone) {
                        int telefone = Integer.parseInt(telefoneTextField.getText());
                        String email = emailTextField.getText();
                        Cliente cliente = new ClienteIndividual(nome, telefone, email, endereco, cpf);
                        portifolio.adicionaClienteIndividual(cliente);
                        LabelAvisos.setText("Cliente cadastrado: " + cliente.toString());
                    }
                }
            }
            else {
                LabelAvisos.setText("Preencha todos os campos.");
                LabelAvisos.setTextFill(Color.FIREBRICK);
            }
        }

        if(Selecionar.getText().equals("Cliente Empresarial")) {
            if(nomeTextField.getText()!= null && enderecoTextField.getText()!= null && cpfTextField.getText() != null && telefoneTextField !=null && emailTextField != null && nomeFantasiaTextField!=null) {
                boolean verificacpf = true;
                boolean verificaTelefone = true;
                String cnpj = cpfTextField.getText();
                String nome = nomeTextField.getText();
                String endereco = enderecoTextField.getText();
                String nomeFantasia = nomeFantasiaTextField.getText();
                if (!VerificaDigitosCpfOuCnpj(cnpj) || cnpj.length() != 14) {
                    LabelAvisos.setText("Erro: Cnpj Invalido");
                    LabelAvisos.setTextFill(Color.FIREBRICK);
                }
                else {
                    cnpj = cpfTextField.getText();

                    try {
                        int telefone = Integer.parseInt(telefoneTextField.getText());
                    } catch (Exception e) {
                        System.err.format("Erro: %s%n", e);
                        verificaTelefone = false;
                        LabelAvisos.setText("Erro: Telefone invalido");
                        LabelAvisos.setTextFill(Color.FIREBRICK);
                    }
                    if (portifolio.getClientesEmpresariais() != null) {
                        if (portifolio.verificaCnpjCliente(cnpj)) {
                            verificacpf = false;
                        }
                    }
                    else {
                        LabelAvisos.setText("Erro: Cnpj ja cadastrado.");
                        LabelAvisos.setTextFill(Color.FIREBRICK);
                    }
                    if (verificacpf && verificaTelefone) {
                        int telefone = Integer.parseInt(telefoneTextField.getText());
                        String email = emailTextField.getText();
                        ClienteEmpresarial cliente = new ClienteEmpresarial(nomeFantasia, nome, telefone, email, endereco, cnpj);
                        portifolio.adicionaClienteEmpresarial(cliente);
                        LabelAvisos.setText("Cliente cadastrado: " + cliente.toString());
                    }
                }
            }
            else {
                LabelAvisos.setText("Preencha todos os campos.");
                LabelAvisos.setTextFill(Color.FIREBRICK);
            }
        }
        if (Selecionar.getText().equals("Selecionar")){
            LabelAvisos.setText("Selecione um tipo de Cliente");
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
