/**
 * Created by Timothy Carpenter on 23/1/17.
 * Fan Controller that will give the ability to turn on and off fans
 * PinState HIGH means that the relay is open and fan is not on
 * PinState LOW means that the relay is closed and the fan is spinning
 */

import com.pi4j.io.gpio.*;

public class FanController {

    private GpioPinDigitalOutput fan1;
    private GpioPinDigitalOutput fan2;

    public FanController(GpioController gpioController){


        fan1 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Fan1", PinState.HIGH);
        fan1.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);


        fan2 = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_08, "Fan2", PinState.HIGH);
        fan2.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);

    }

    public String turnOnFan1(){
        fan1.low();
        return fan1.getState().toString();
    }


    public String turnOnFan2(){
        fan2.low();
        return fan2.getState().toString();
    }


    public String turnOffFan1(){
        fan1.high();
        return fan1.getState().toString();
    }


    public String turnOffFan2(){
        fan2.high();
        return fan2.getState().toString();
    }








}
