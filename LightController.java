import com.pi4j.io.gpio.*;

/**
 * Created by Timmy on 24/1/17.
 */
public class LightController {

    private GpioPinDigitalOutput LED1;
    private GpioPinDigitalOutput LED2;

    public LightController(GpioController gpioController){

        LED1 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, "LED1", PinState.HIGH);
        LED1.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);

        LED2 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_09, "LED2", PinState.HIGH);
        LED2.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
    }

    public String turnOnLED1(){
        LED1.low();
        return LED1.getState().toString();
    }


    public String turnOnLED2(){
        LED2.low();
        return LED2.getState().toString();
    }


    public String turnOffLED1(){
        LED1.high();
        return LED1.getState().toString();
    }


    public String turnOffLED2(){
        LED2.high();
        return LED2.getState().toString();
    }

}
