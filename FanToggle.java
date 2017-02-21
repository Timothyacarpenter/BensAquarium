import java.util.concurrent.ExecutionException;

/**
 * Created by Timmy on 25/1/17.
 */
public class FanToggle implements Runnable {

    TemperatureReader temperatureReader;
    Settings settings;


    public FanToggle(TemperatureReader temperatureReader, Settings settings){
        this.temperatureReader = temperatureReader;
        System.out.println("water temp limit" + settings.getWaterTempLimit());
        this.settings = settings;
    }


    @Override
    public void run() {
        if(temperatureReader.getTemperature().isDone()){
            try {
                System.out.println(temperatureReader.getTemperature().get().doubleValue());
                if(temperatureReader.getTemperature().get() > settings.getWaterTempLimit()){
                    GpioSingleton.getInstance().getFanController().turnOnFan1();
                }
                else{
                    GpioSingleton.getInstance().getFanController().turnOffFan1();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
