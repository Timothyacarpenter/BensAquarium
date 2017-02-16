import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.component.temperature.impl.TmpDS18B20DeviceType;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.w1.W1Device;
import com.pi4j.io.w1.W1Master;
import java.util.List;

/**
 * Created by Timmy on 29/1/17.
 */
class GpioSingleton {

    private static GpioSingleton gpioSingleton;
    private GpioController gpioController = GpioFactory.getInstance();
    private FanController fanController = new FanController(gpioController);
    private LightController lightController = new LightController(gpioController);
    private TemperatureReader temperatureReader = new TemperatureReader();
    private FanToggle fanToggle = new FanToggle(temperatureReader);
    private LightTimer lightTimer = new LightTimer();
    private W1Master master = new W1Master();
    private List<W1Device> w1Devices = master.getDevices(TmpDS18B20DeviceType.FAMILY_CODE);
    private TemperatureSensor temperatureSensor = null;




    private GpioSingleton(){}

    public static synchronized GpioSingleton getInstance(){
        if(gpioSingleton == null){
            gpioSingleton = new GpioSingleton();
        }
        return gpioSingleton;
    }

    public FanController getFanController(){
        return fanController;
    }

    public TemperatureReader getTemperatureReader(){
        return temperatureReader;
    }

    public FanToggle getFanToggle(){
        return fanToggle;
    }

    public LightTimer getLightTimer(){
        return lightTimer;
    }

    public void shutDownGpioController(){
        gpioController.shutdown();
    }


    public LightController getLightController() {
        return lightController;
    }

    public double getTempSensor(){

        for (W1Device device : w1Devices) {
            temperatureSensor = (TemperatureSensor)device;
        }

        return temperatureSensor.getTemperature();
    }
}
