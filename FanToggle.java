import javafx.application.Platform;

import java.util.concurrent.ExecutionException;

/**
 * Created by Timmy on 25/1/17.
 */
public class FanToggle implements Runnable {

    TemperatureReader temperatureReader;
    Settings settings;


    public FanToggle(TemperatureReader temperatureReader, Settings settings){
        this.temperatureReader = temperatureReader;
        this.settings = settings;
    }


    @Override
    public void run() {
        if(temperatureReader.getTemperature().isDone()){
            try {
                System.out.println(temperatureReader.getTemperature().get().doubleValue());
                if(temperatureReader.getTemperature().get() > settings.getWaterTempLimit()){
                    GpioSingleton.getInstance().getFanController().turnOnFan1();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GpioSingleton.getInstance().getFanStatus().setValue("On");
                        }
                    });

                }
                else{
                    GpioSingleton.getInstance().getFanController().turnOffFan1();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GpioSingleton.getInstance().getFanStatus().setValue("Off");
                        }
                    });
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
