import com.pi4j.io.gpio.GpioController;

import java.util.concurrent.ExecutionException;

/**
 * Created by Timmy on 25/1/17.
 */
public class FanToggle implements Runnable {

    TemperatureReader temperatureReader;


    public FanToggle(TemperatureReader temperatureReader){
        this.temperatureReader = temperatureReader;
    }


    @Override
    public void run() {
        if(temperatureReader.getTemperature().isDone()){
            try {
                System.out.println(temperatureReader.getTemperature().get().doubleValue());
                if(temperatureReader.getTemperature().get() > 25){
                    GpioSingleton.getInstance().getFanController().turnOnFan1();
                    GpioSingleton.getInstance().getFanController().turnOnFan2();
                }
                else{
                    GpioSingleton.getInstance().getFanController().turnOffFan1();
                    GpioSingleton.getInstance().getFanController().turnOffFan2();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
