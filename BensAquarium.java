import com.pi4j.wiringpi.Gpio;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.application.Application;
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
        exitButton.setOnAction(new ExitButtonListener());
        Label label = new Label("Label");
        label.textProperty().bind(GpioSingleton.getInstance().getTemperatureReader().getTemp());

        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(5, 0, 5, 0));
        flow.setVgap(4);
        flow.setHgap(4);
        flow.setPrefWrapLength(170); // preferred width allows for two columns
        flow.setStyle("-fx-background-color: DAE6F3;");


        flow.getChildren().add(label);
        flow.getChildren().add(exitButton);
        Scene scene = new Scene(flow, 500,500);
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


