import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.component.temperature.impl.TmpDS18B20DeviceType;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.w1.W1Device;
import com.pi4j.io.w1.W1Master;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

/**
 * Created by Timmy on 29/1/17.
 */
public class GpioSingleton {

    private static GpioSingleton gpioSingleton;
    static Settings settings = new Settings();
    GpioController gpioController = GpioFactory.getInstance();
    FanController fanController = new FanController(gpioController);
    LightController lightController = new LightController(gpioController);
    TemperatureReader temperatureReader = new TemperatureReader();
    FanToggle fanToggle = new FanToggle(temperatureReader, settings);
    LightTimer lightTimer = new LightTimer(settings);
    W1Master master = new W1Master();
    List<W1Device> w1Devices = master.getDevices(TmpDS18B20DeviceType.FAMILY_CODE);
    TemperatureSensor temperatureSensor = null;

    StringProperty fanStatus = new SimpleStringProperty("");




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

    public StringProperty getFanStatus() {
        return fanStatus;
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

    public static Settings getSettings() {
        return settings;
    }
}
