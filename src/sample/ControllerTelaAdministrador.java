package sample;


import javafx.fxml.FXML;
import javafx.scene.control.*;


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
        if (Servicos.getText().equals("Consultar Prestadores Cadastrados")){
            Main.changeScreen("TelaMostraPrestadores");
        }
        if (Servicos.getText().equals("Consultar Clientes Cadastrados")){
            Main.changeScreen("TelaConsultaClientes");
        }
        if (Servicos.getText().equals("Consultar Servicos Cadastrados")){
            Main.changeScreen("TelaConsultaServicos");
        }
        if (Servicos.getText().equals("Confirmar Pagamento")){
            Main.changeScreen("TelaConfirmarPagamento");
        }
        if (Servicos.getText().equals("Confirmar ou Cancelar Servico")){
            Main.changeScreen("TelaConfirmarCancelarServico");
        }
        if (Servicos.getText().equals("Confirmar ou Cancelar Contrato")){
            Main.changeScreen("TelaConfirmarCancelarContrato");
        }
        if (Servicos.getText().equals("Consultar Contratos")){
            Main.changeScreen("TelaConsultarContratos");
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
        Servicos.setText("Confirmar ou Cancelar Contrato");
    }

    public void ConfirmarTermino(){
        Servicos.setText("Confirmar Termino de Servico");
    }
    public void Simulacao(){
        Servicos.setText("Realizar Simulacao");
    }


}
