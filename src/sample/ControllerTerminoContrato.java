package sample;

import Trabalho1.Contrato;
import Trabalho1.Portifolio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerTerminoContrato implements Initializable {

    private final Portifolio portifolio=Main.portifolio;
    private ObservableList<Contrato> obsContrato;
    private List<Contrato> contratos = new ArrayList<>();

    @FXML
    public TextField descontoTextField;
    @FXML
    public Label LabelTexto;
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
            contratos = portifolio.getContratosConfirmado();
            obsContrato = FXCollections.observableArrayList(contratos);
            contrato.setItems(obsContrato);
        }
        catch(Exception e){
            System.err.format("Erro: %s%n", e);
            LabelTexto.setText("Nao ha contratos a serem confirmados");
            LabelTexto.setTextFill(Color.FIREBRICK);
        }
    }


    public void ClicaVoltar(){
        Main.changeScreen("TelaAdministrador");
    }

    public void Atualiza(){
        try{
            contratos = portifolio.getContratosConfirmado();
            obsContrato = FXCollections.observableArrayList(contratos);
            contrato.setItems(obsContrato);
        }
        catch(Exception e){
            System.err.format("Erro: %s%n", e);
            LabelTexto.setText("Nao ha contratos a serem Terminados");
            LabelTexto.setTextFill(Color.FIREBRICK);
        }
    }

        public void ClicaConfirmar() {
                    try{
                    contrato.getValue().getPrestador().removeServicoConfirmado(contrato.getValue());
                    contrato.getValue().getCliente().removeContratoConfirmados(contrato.getValue());
                    LabelTexto.setText("Contrato Terminado: " + contrato.getValue().toString());
                    portifolio.removeContratoConfirmado(contrato.getValue());
                    contratos = portifolio.getContratosConfirmado();
                    obsContrato = FXCollections.observableArrayList(contratos);
                    contrato.setItems(obsContrato);
                }
           catch (Exception e){
                LabelTexto.setText("Selecione um contrato confirmado a ser terminado");
                LabelTexto.setTextFill(Color.FIREBRICK);
            }
        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregaContratos();
    }
}

