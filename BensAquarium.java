import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.concurrent.ExecutionException;

/**
 * Created by Timmy on 24/1/17.
 */
public class BensAquarium extends Application{

    private static ServiceController serviceController = new ServiceController();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        System.out.println(GpioSingleton.getInstance().getTempSensor());
        serviceController.startAllServices();

        primaryStage.setTitle("Ben's Aquarium");
        Button exitButton = new Button("Exit");
        Button settingsButton = new Button("Settings");
        exitButton.setOnAction(new ExitButtonListener());
        Label tempLabel = new Label("Water temp : ");
        Label temp = new Label("Label");
        Label blueLight = new Label("Blue lights : ");
        Label blueLightStatus = new Label("On or Off");
        Label normalLight = new Label("Normal lights : ");
        Label normalLightStatus = new Label("On or Off");
        Label fan = new Label("Fan : ");
        Label fanStatus = new Label("");
        temp.textProperty().bind(GpioSingleton.getInstance().getTemperatureReader().getTemp());
        fanStatus.textProperty().bind(GpioSingleton.getInstance().getFanStatus());
        normalLightStatus.textProperty().bind(GpioSingleton.getInstance().getNormalLightStatus());
        blueLightStatus.textProperty().bind(GpioSingleton.getInstance().getBlueLightStatus());

        VBox menu = new VBox();
        menu.setPadding(new Insets(10));
        menu.setSpacing(8);

        HBox tempStatus = new HBox();
        tempStatus.setPadding(new Insets(15, 12, 15, 12));
        tempStatus.setSpacing(10);
        tempStatus.setStyle("-fx-background-color: #336699;");
        tempStatus.getChildren().add(tempLabel);
        tempStatus.getChildren().add(temp);

        HBox exitSettings = new HBox();
        exitSettings.setPadding(new Insets(15, 12, 15, 12));
        exitSettings.setSpacing(10);
        exitSettings.setStyle("-fx-background-color: #336699;");
        exitSettings.getChildren().add(exitButton);
        exitSettings.getChildren().add(settingsButton);

        HBox blueLights = new HBox();
        blueLights.setPadding(new Insets(15, 12, 15, 12));
        blueLights.setSpacing(10);
        blueLights.setStyle("-fx-background-color: #336699;");
        blueLights.getChildren().add(blueLight);
        blueLights.getChildren().add(blueLightStatus);

        HBox normalLights = new HBox();
        normalLights.setPadding(new Insets(15, 12, 15, 12));
        normalLights.setSpacing(10);
        normalLights.setStyle("-fx-background-color: #336699;");
        normalLights.getChildren().add(normalLight);
        normalLights.getChildren().add(normalLightStatus);

        HBox fans = new HBox();
        fans.setPadding(new Insets(15, 12, 15, 12));
        fans.setSpacing(10);
        fans.setStyle("-fx-background-color: #336699;");
        fans.getChildren().add(fan);
        fans.getChildren().add(fanStatus);


        menu.getChildren().add(tempStatus);
        menu.getChildren().add(normalLights);
        menu.getChildren().add(blueLights);
        menu.getChildren().add(fans);
        menu.getChildren().add(exitSettings);





        Scene scene = new Scene(menu, 500,500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    @Override
    public void stop(){
        System.out.println("Stage is closing");
        serviceController.stopAllServices();
        GpioSingleton.getInstance().shutDownGpioController();
        GpioSingleton.getSettings().saveAllSettings();
    }

    public class ExitButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent arg0) {
            Platform.exit();
        }
    }


}


