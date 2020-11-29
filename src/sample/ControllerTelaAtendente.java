package sample;

import Trabalho1.Portifolio;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class ControllerTelaAtendente {
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
    public Label LabelAvisos;

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
        if (Servicos.getText().equals("Consultar Contratos")){
            Main.changeScreen("TelaConsultarContratos");
        }
        if (Servicos.getText().equals("Confirmar Pagamento")){
            Main.changeScreen("TelaConfirmarPagamento");
        }

    }

    public void ClicaSair(){
        System.exit(0);
    }

    public void ClicaVoltar(){
        Main.changeScreen("TelaInicial");
    }

    // MenuButton Itens

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


}
