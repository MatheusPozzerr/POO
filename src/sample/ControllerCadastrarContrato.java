package sample;

import Trabalho1.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ControllerCadastrarContrato {
    private final Portifolio portifolio = Main.portifolio;
    ArrayList<Servico> Catalogo;
    private ObservableList<Servico> obsServicos;

    private final List<Servico> listaServicos = new ArrayList<>();
    @FXML
    public ComboBox<Servico> servicos;
    @FXML
    public Button buttonConfirma;
    @FXML
    public MenuButton Selecionar;
    @FXML
    public TextField descontoTextField;
    @FXML
    public TextField observacaoTextField;
    @FXML
    public DatePicker dataTextField;
    @FXML
    public TextField cpfTextField;
    @FXML
    public Button buttonLimpa;
    @FXML
    public Label LabelAvisos;
    @FXML
    public Label labelServicos;
    @FXML
    public Label labelCpf;

    public void ClicaVoltar(){
        Main.changeScreen("TelaAdministrador");
    }

    public void LimpaBotao(){
        observacaoTextField.clear();
        descontoTextField.clear();
        cpfTextField.clear();
    }

    public void Cliente(){
        Selecionar.setText("Cliente Individual");
        labelCpf.setText("Cpf:");
        Catalogo=portifolio.getServicos();
        obsServicos = FXCollections.observableArrayList(Catalogo);
        servicos.setItems(obsServicos);

    }

    public void ClienteEmpresarial(){
        Selecionar.setText("Cliente Empresarial");
        labelCpf.setText("Cnpj:");
        Catalogo=portifolio.getServicos();
        obsServicos = FXCollections.observableArrayList(Catalogo);
        servicos.setItems(obsServicos);
    }

    public void clicaConfirmar(){
        if (Selecionar.getText().equals("Selecionar")){
            LabelAvisos.setText("Selecione um tipo de Cliente");
            LabelAvisos.setTextFill(Color.FIREBRICK);
        }
        else {
            if (observacaoTextField.getText() != null && descontoTextField.getText() != null && cpfTextField.getText() != null && dataTextField != null && servicos.getValue() != null) {
                if (Selecionar.getText().equals("Cliente Individual")) {
                    boolean verificacpf = false;
                    boolean verificaDesconto = true;
                    boolean verificaData = true;
                    String cpf = cpfTextField.getText();
                    String observacao = observacaoTextField.getText();
                    int desconto;
                    if (!VerificaDigitosCpfOuCnpj(cpf) || cpf.length() != 11) {
                        LabelAvisos.setText("Erro: Cpf Invalido");
                        LabelAvisos.setTextFill(Color.FIREBRICK);
                    } else {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDate data = dataTextField.getValue();
                            LocalDateTime dataInicial = LocalDateTime.parse(data.toString() + " 23:59", formatter);
                        } catch (Exception e) {
                            verificaData = false;
                            LabelAvisos.setText("Erro: Data invalida");
                            LabelAvisos.setTextFill(Color.FIREBRICK);
                        }
                        try {
                            desconto = Integer.parseInt(descontoTextField.getText());
                        } catch (Exception e) {
                            System.err.format("Erro: %s%n", e);
                            verificaDesconto = false;
                            LabelAvisos.setText("Erro: Desconto invalido");
                            LabelAvisos.setTextFill(Color.FIREBRICK);
                        }
                        if (portifolio.getClientesIndividuais() != null) {
                            if (portifolio.verificaCpfCliente(cpf)) {
                                verificacpf = true;
                            } else {
                                LabelAvisos.setText("Erro: CPF não cadastrado.");
                                LabelAvisos.setTextFill(Color.FIREBRICK);
                            }
                        }

                        if (verificacpf && verificaDesconto && verificaData) {
                            desconto = Integer.parseInt(descontoTextField.getText());
                            if (desconto < 0 || desconto > 100) {
                                LabelAvisos.setText("Erro: Desconto invalido");
                                LabelAvisos.setTextFill(Color.FIREBRICK);
                            } else {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                LocalDate data = dataTextField.getValue();
                                LocalDateTime dataServico = LocalDateTime.parse(data.toString() + " 23:59", formatter);
                                Catalogo = portifolio.getServicos();
                                Servico servicoEscolhido = servicos.getValue();
                                double valorInicial = servicoEscolhido.getValor();
                                double quantidadeDeDesconto = 0;
                                if (desconto > 0) {
                                    quantidadeDeDesconto = (valorInicial * desconto) / 100;
                                }
                                double valorFinal = valorInicial - quantidadeDeDesconto;
                                Contrato novoContrato = new Contrato(valorFinal, observacao, dataServico);
                                novoContrato.setDesconto(quantidadeDeDesconto);
                                novoContrato.setCliente(portifolio.buscaCpfCliente(cpf));
                                novoContrato.setPrestador(servicoEscolhido.getPrestador());
                                novoContrato.setServico(servicoEscolhido);
                                novoContrato.setQtdDesconto(desconto);
                                portifolio.buscaCpfCliente(cpf).adicionaContrato(novoContrato);
                                portifolio.adicionaContrato(novoContrato, servicoEscolhido, cpf);
                                LabelAvisos.setText("Contrato cadastrado: " + novoContrato.toString());
                            }
                        }
                    }
                }

                if (Selecionar.getText().equals("Cliente Empresarial")) {
                    boolean verificacpf = false;
                    boolean verificaDesconto = true;
                    boolean verificaData = true;
                    String cnpj = cpfTextField.getText();
                    String observacao = observacaoTextField.getText();
                    int desconto;
                    if (!VerificaDigitosCpfOuCnpj(cnpj) || cnpj.length() != 14) {
                        LabelAvisos.setText("Erro: Cnpj Invalido");
                        LabelAvisos.setTextFill(Color.FIREBRICK);
                    } else {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDate data = dataTextField.getValue();
                            LocalDateTime dataInicial = LocalDateTime.parse(data.toString() + " 23:59", formatter);
                        } catch (Exception e) {
                            verificaData = false;
                            LabelAvisos.setText("Erro: Data invalida");
                            LabelAvisos.setTextFill(Color.FIREBRICK);
                        }
                        if (descontoTextField == null) {
                            desconto = 0;
                        }
                        try {
                            desconto = Integer.parseInt(descontoTextField.getText());
                        } catch (Exception e) {
                            System.err.format("Erro: %s%n", e);
                            verificaDesconto = false;
                            LabelAvisos.setText("Erro: Desconto invalido");
                            LabelAvisos.setTextFill(Color.FIREBRICK);
                        }
                        if (portifolio.getClientesIndividuais() != null) {
                            if (portifolio.verificaCnpjCliente(cnpj)) {
                                verificacpf = true;
                            } else {
                                LabelAvisos.setText("Erro: CPF não cadastrado.");
                                LabelAvisos.setTextFill(Color.FIREBRICK);
                            }
                        }

                        if (verificacpf && verificaDesconto && verificaData) {
                            desconto = Integer.parseInt(descontoTextField.getText());
                            desconto=desconto+7;
                            if (desconto < 7 || desconto > 100) {
                                LabelAvisos.setText("Erro: Desconto invalido");
                                LabelAvisos.setTextFill(Color.FIREBRICK);
                            }
                            else {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                LocalDate data = dataTextField.getValue();
                                LocalDateTime dataServico = LocalDateTime.parse(data.toString() + " 23:59", formatter);
                                Catalogo = portifolio.getServicos();
                                Servico servicoEscolhido = servicos.getValue();
                                double valorInicial = servicoEscolhido.getValor();
                                double quantidadeDeDesconto = 0;
                                if (desconto >= 7) {
                                    quantidadeDeDesconto = (valorInicial * desconto) / 100;
                                }
                                double valorFinal = valorInicial - quantidadeDeDesconto;
                                Contrato novoContrato = new Contrato(valorFinal, observacao, dataServico);
                                novoContrato.setDesconto(quantidadeDeDesconto);
                                novoContrato.setCliente(portifolio.buscaCnpjCliente(cnpj));
                                novoContrato.setPrestador(servicoEscolhido.getPrestador());
                                novoContrato.setServico(servicoEscolhido);
                                novoContrato.setQtdDesconto(desconto);
                                portifolio.buscaCnpjCliente(cnpj).adicionaContrato(novoContrato);
                                portifolio.adicionaContrato(novoContrato, servicoEscolhido, cnpj);
                                LabelAvisos.setText("Contrato cadastrado: " + novoContrato.toString());
                            }
                        }
                    }
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
