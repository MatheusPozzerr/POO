package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Trabalho1.*;

public class Main extends Application {

    public static Stage stage;
    public static Scene TelaInicial;
    public static Scene TelaAdiministrador;
    public static Portifolio portifolio=new Portifolio();
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage=primaryStage;
        primaryStage.setTitle("ACMEJobs");

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        TelaInicial= new Scene(root,400,305);

        Parent fxmlSegunda = FXMLLoader.load(getClass().getResource("TelaAdministrador.fxml"));
        TelaAdiministrador = new Scene (fxmlSegunda,640,480);

        primaryStage.setScene(TelaInicial);
        primaryStage.show();
    }

    public static void changeScreen(String scr){
        switch (scr){
            case "TelaInicial":
                stage.setScene(TelaInicial);
                break;
            case "TelaAdministrador":
                stage.setScene(TelaAdiministrador);
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
