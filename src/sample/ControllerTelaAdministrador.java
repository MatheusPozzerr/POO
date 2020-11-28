package sample;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import Trabalho1.*;

public class ControllerTelaAdministrador {

    Portifolio portifolio= Main.portifolio;

    @FXML
    public Label LabelCpf;

    @FXML
    public TextField TextCPF;

    @FXML
    public Button BotaoVoltar;

    @FXML
    public Button BotaoSair;

    @FXML
    public Button BotaoConfirmar;

    @FXML
    public MenuButton Servicos;

    @FXML
    public MenuItem ItemConsultarClientes;

    @FXML
    public MenuItem ItemPrestadoresCadastrados;

    @FXML
    public Label LabelAvisos;

    @FXML
    public CheckBox CheckClienteEmpresarial;

    @FXML
    public CheckBox CheckCliente;

    public void ClicaConfirmar(){
        if (Servicos.getText().equals("Servicos")){
            LabelAvisos.setText("Selecione um servico.");
        }
        if (Servicos.getText().equals("Cadastrar Prestador")){
            Main.changeScreen("TelaCadastrarPrestador");
        }
        if(Servicos.getText().equals("Cadastrar Servico")){
            Main.changeScreen("TelaCadastrarServico");
        }
        if(Servicos.getText().equals("Cadastrar Cliente")){
            Main.changeScreen("TelaCadastrarCliente");
        }
        if(Servicos.getText().equals("Cadastrar Contrato")){
            Main.changeScreen("TelaCadastrarContrato");
        }
        if (Servicos.getText().equals("Consultar Prestadores Cadastrados")){
            Main.changeScreen("TelaMostraPrestadores");
        }
        if (Servicos.getText().equals("Consultar Clientes Cadastrados")){
            Main.changeScreen("TelaConsultaClientes");
        }
        if (Servicos.getText().equals("Consultar Servicos Cadastrados")){
            Main.changeScreen("TelaConsultaServicos");
        }

    }

    public void ClicaSair(){
        System.exit(0);
    }

    public void ClicaVoltar(){
    Main.changeScreen("TelaInicial");
    }



    // MenuButton Itens

    public void ConsultarClientes(){
        Servicos.setText("Consultar Clientes Cadastrados");
    }

    public void ConsultarPrestadores(){
        Servicos.setText("Consultar Prestadores Cadastrados");
    }

    public void ConsultarServicos(){
       Servicos.setText("Consultar Servicos Cadastrados");
    }

    public void CadastrarPrestador(){
        Servicos.setText("Cadastrar Prestador");
    }

    public void CadastrarServico(){
        Servicos.setText("Cadastrar Servico");
    }

    public void CadastrarCliente(){
        Servicos.setText("Cadastrar Cliente");
    }

    public void CadastrarContrato(){
        Servicos.setText("Cadastrar Contrato");
    }

    public void ConfirmarPagamento(){
        Servicos.setText("Confirmar Pagamento");
    }

    public void ConsultarContratos(){
        Servicos.setText("Consultar Contratos");
    }

    public void ConfirmarCancelarServico(){
        Servicos.setText("Confirmar ou Cancelar Servico");
    }

    public void ConfirmarTermino(){
        Servicos.setText("Confirmar Termino de Servico");
    }
    public void Simulacao(){
        Servicos.setText("Realizar Simulacao");
    }

}
