import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    Stage primaryStage;
    Scene mainMenu;
    Scene settings;
    TextField tempSetting;
    TextField morningMinutes;
    TextField dayMinutes;
    TextField nightMinutes;
    TextField offMinutes;
    TextField morningHours;
    TextField dayHours;
    TextField nightHours;
    TextField offHours;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        System.out.println(GpioSingleton.getInstance().getTempSensor());
        serviceController.startAllServices();
        this.primaryStage = primaryStage;

        primaryStage.setTitle("Ben's Aquarium");
        Button exitButton = new Button("Exit");
        Button settingsButton = new Button("Settings");
        exitButton.setOnAction(new ExitButtonListener());
        settingsButton.setOnAction(new settingsButtonListener());
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


        Button save = new Button("Save");
        save.setOnAction(new saveButtonListener());
        Button back = new Button("Back");
        back.setOnAction(new backButtonListener());
        Label currentTempSetting = new Label("Temp Setting : ");
        tempSetting = new TextField(String.valueOf(GpioSingleton.getSettings().getWaterTempLimit()));
        Label morningHour = new Label("Morning - Hour : ");
        Label morningMinute = new Label("Minutes : ");
        Label dayHour = new Label("Day - Hour : ");
        Label dayMinute = new Label("Minutes : ");
        Label nightHour = new Label("Night - Hour : ");
        Label nightMinute = new Label("Minutes : ");
        Label offHour = new Label("Off - Hour : ");
        Label offMinute = new Label("Minutes : ");
        morningHours  = new TextField(String.valueOf(GpioSingleton.getSettings().getMorning()[0]));
        morningHours.setPrefWidth(60);
        morningMinutes = new TextField(String.valueOf(GpioSingleton.getSettings().getMorning()[1]));
        morningMinutes.setPrefWidth(60);
        dayHours  = new TextField(String.valueOf(GpioSingleton.getSettings().getDay()[0]));
        dayHours.setPrefWidth(60);
        dayMinutes = new TextField(String.valueOf(GpioSingleton.getSettings().getDay()[1]));
        dayMinutes.setPrefWidth(60);
        nightHours  = new TextField(String.valueOf(GpioSingleton.getSettings().getNight()[0]));
        nightHours.setPrefWidth(60);
        nightMinutes = new TextField(String.valueOf(GpioSingleton.getSettings().getNight()[1]));
        nightMinutes.setPrefWidth(60);
        offHours  = new TextField(String.valueOf(GpioSingleton.getSettings().getOff()[0]));
        offHours.setPrefWidth(60);
        offMinutes = new TextField(String.valueOf(GpioSingleton.getSettings().getOff()[1]));
        offMinutes.setPrefWidth(60);

        VBox settingsMenu = new VBox();
        settingsMenu.setPadding(new Insets(10));
        settingsMenu.setSpacing(8);

        HBox tempSettings = new HBox();
        tempSettings.setPadding(new Insets(15, 12, 15, 12));
        tempSettings.setSpacing(10);
        tempSettings.setStyle("-fx-background-color: #336699;");
        tempSettings.getChildren().add(currentTempSetting);
        tempSettings.getChildren().add(tempSetting);

        HBox morningSettings = new HBox();
        morningSettings.setPadding(new Insets(15, 12, 15, 12));
        morningSettings.setSpacing(10);
        morningSettings.setStyle("-fx-background-color: #336699;");
        morningSettings.getChildren().add(morningHour);
        morningSettings.getChildren().add(morningHours);
        morningSettings.getChildren().add(morningMinute);
        morningSettings.getChildren().add(morningMinutes);

        HBox daySettings = new HBox();
        daySettings.setPadding(new Insets(15, 12, 15, 12));
        daySettings.setSpacing(10);
        daySettings.setStyle("-fx-background-color: #336699;");
        daySettings.getChildren().add(dayHour);
        daySettings.getChildren().add(dayHours);
        daySettings.getChildren().add(dayMinute);
        daySettings.getChildren().add(dayMinutes);

        HBox nightSettings = new HBox();
        nightSettings.setPadding(new Insets(15, 12, 15, 12));
        nightSettings.setSpacing(10);
        nightSettings.setStyle("-fx-background-color: #336699;");
        nightSettings.getChildren().add(nightHour);
        nightSettings.getChildren().add(nightHours);
        nightSettings.getChildren().add(nightMinute);
        nightSettings.getChildren().add(nightMinutes);


        HBox offSettings = new HBox();
        offSettings.setPadding(new Insets(15, 12, 15, 12));
        offSettings.setSpacing(10);
        offSettings.setStyle("-fx-background-color: #336699;");
        offSettings.getChildren().add(offHour);
        offSettings.getChildren().add(offHours);
        offSettings.getChildren().add(offMinute);
        offSettings.getChildren().add(offMinutes);

        HBox backSave = new HBox();
        backSave.setPadding(new Insets(15, 12, 15, 12));
        backSave.setSpacing(10);
        backSave.setStyle("-fx-background-color: #336699;");
        backSave.getChildren().add(save);
        backSave.getChildren().add(back);

        settingsMenu.getChildren().add(tempSettings);
        settingsMenu.getChildren().add(morningSettings);
        settingsMenu.getChildren().add(daySettings);
        settingsMenu.getChildren().add(nightSettings);
        settingsMenu.getChildren().add(offSettings);
        settingsMenu.getChildren().add(backSave);






        mainMenu = new Scene(menu, 500,500);
        settings = new Scene(settingsMenu, 500,500);

        primaryStage.setScene(mainMenu);
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

    public class backButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent arg0) {
            primaryStage.setScene(mainMenu);
        }
    }

    public class saveButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent arg0) {
            GpioSingleton.getSettings().setWaterTempLimit(Integer.valueOf(tempSetting.getText()));
            int [] morning =  new int[] {Integer.valueOf(morningHours.getText()),Integer.valueOf(morningMinutes.getText())};
            GpioSingleton.getSettings().setMorning(morning);

            int [] day =  new int[] {Integer.valueOf(dayHours.getText()),Integer.valueOf(dayMinutes.getText())};
            GpioSingleton.getSettings().setDay(day);

            int [] night =  new int[] {Integer.valueOf(nightHours.getText()),Integer.valueOf(nightMinutes.getText())};
            GpioSingleton.getSettings().setNight(night);

            int [] off =  new int[] {Integer.valueOf(offHours.getText()),Integer.valueOf(offMinutes.getText())};
            GpioSingleton.getSettings().setOff(off);

            GpioSingleton.getSettings().saveAllSettings();
            primaryStage.setScene(mainMenu);
        }
    }

    public class settingsButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent arg0) {
            primaryStage.setScene(settings);
        }
    }


}


