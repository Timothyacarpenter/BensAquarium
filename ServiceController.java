import com.pi4j.wiringpi.Gpio;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Timmy on 29/1/17.
 */
public class ServiceController {

    ScheduledExecutorService fanService;
    ScheduledExecutorService tempExecutor;
    ScheduledExecutorService lightExecutor;
    GpioSingleton gpioSingleton = GpioSingleton.getInstance();

    public void startFanService(){

        fanService = Executors.newSingleThreadScheduledExecutor();
        fanService.scheduleAtFixedRate(gpioSingleton.getFanToggle(), 0, 3, TimeUnit.SECONDS);
    }

    public void startTempService(){

        tempExecutor = Executors.newSingleThreadScheduledExecutor();
        tempExecutor.scheduleAtFixedRate(gpioSingleton.getTemperatureReader(), 0, 5, TimeUnit.SECONDS);

    }

    public void startLightService(){

        lightExecutor = Executors.newSingleThreadScheduledExecutor();
        lightExecutor.scheduleAtFixedRate(gpioSingleton.getLightTimer(), 0, 10, TimeUnit.SECONDS);

    }


    public void shutdownFanService(){
        fanService.shutdown();
    }

    public void shutdownTempService(){
        tempExecutor.shutdown();
    }

    public void shutdownLightService(){
        lightExecutor.shutdown();
    }




}
