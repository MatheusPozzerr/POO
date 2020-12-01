package sample;
import Trabalho1.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.paint.Color;

public class ControllerConfirmarCancelarContrato implements Initializable{
    private final Portifolio portifolio=Main.portifolio;
    private ObservableList<Contrato> obsContrato;
    private List<Contrato> contratos = new ArrayList<>();

    @FXML
    public TextField descontoTextField;
    @FXML
    public MenuButton Selecionar;
    @FXML
    public Label LabelAvisos;
    @FXML
    public Label labeldesconto;
    @FXML
    public Label labelServicos;
    @FXML
    public ComboBox<Contrato> contrato;
    @FXML
    public Button button;

    @FXML
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

    public void ClicaVoltar(){
        LabelAvisos.setText("");
        Main.changeScreen("TelaAdministrador");
    }

    public void Atualiza(){
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

    public void ClicaConfirmar() {
        boolean verificaDesconto=true;
        if (descontoTextField != null && contrato.getValue()!=null) {
            try {
                int desconto = Integer.parseInt(descontoTextField.getText());
            }
            catch (Exception e) {
                System.err.format("Erro: %s%n", e);
                verificaDesconto = false;
                LabelAvisos.setText("Erro: Desconto invalido");
                LabelAvisos.setTextFill(Color.FIREBRICK);
            }
            if (verificaDesconto && contrato.getValue()!= null){
                double desconto = Integer.parseInt(descontoTextField.getText());
                double descontoTeste=desconto+contrato.getValue().getQtdDesconto();
                if (0<= descontoTeste && descontoTeste <= 100){
                    double valorDeDesconto = (contrato.getValue().getValorInicial() * desconto) / 100;
                    contrato.getValue().setDesconto(valorDeDesconto);
                    double valor = contrato.getValue().getValor() - valorDeDesconto;
                    contrato.getValue().setValor(valor);
                    contrato.getValue().getCliente().removeContratoConfirmando(contrato.getValue());
                    contrato.getValue().getPrestador().removeServicoContratadoAdicionaConfirmado(contrato.getValue());
                    LabelAvisos.setText("Contrato confirmado: "+ contrato.getValue().toString());
                    portifolio.removeContratoContratados(contrato.getValue());
                    contratos = portifolio.getContratosContratados();
                    obsContrato = FXCollections.observableArrayList(contratos);
                    contrato.setItems(obsContrato);
                }
                else {
                    LabelAvisos.setText("Erro: Desconto invalido");
                    LabelAvisos.setTextFill(Color.FIREBRICK);
                }
            }
        }
        else {
            LabelAvisos.setText("Preencha todos os campos");
            LabelAvisos.setTextFill(Color.FIREBRICK);
        }
    }

    public void ClicaCancelar() {
        boolean verificaDesconto=true;
        if (contrato.getValue()!=null) {
            LabelAvisos.setText("Contrato cancelado: " + contrato.getValue().toString());
            contrato.getValue().getCliente().removeContratoCancelando(contrato.getValue());
            contrato.getValue().getPrestador().removeServicoContratado(contrato.getValue());
            portifolio.removeContratoContratadosCancelando(contrato.getValue());
        }
        else {
            LabelAvisos.setText("Preencha todos os campos");
            LabelAvisos.setTextFill(Color.FIREBRICK);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregaContratos();
    }
}
