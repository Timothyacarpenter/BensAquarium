/**
 * Created by Timmy on 24/1/17.
 */
import com.pi4j.io.gpio.GpioController;

import java.util.Calendar;

public class LightTimer implements Runnable {

    public void run() {

        //System.out.println("Checking current date");

        Calendar lightSet1 = Calendar.getInstance();
        lightSet1.set(Calendar.HOUR_OF_DAY, 10);
        lightSet1.set(Calendar.MINUTE, 0);
        lightSet1.set(Calendar.SECOND, 0);
        lightSet1.set(Calendar.MILLISECOND, 0);

        Calendar lightSet2 = Calendar.getInstance();
        lightSet2.set(Calendar.HOUR_OF_DAY, 17);
        lightSet2.set(Calendar.MINUTE, 0);
        lightSet2.set(Calendar.SECOND, 0);
        lightSet2.set(Calendar.MILLISECOND, 0);

        Calendar noLights = Calendar.getInstance();
        noLights.set(Calendar.HOUR_OF_DAY, 22);
        noLights.set(Calendar.MINUTE, 0);
        noLights.set(Calendar.SECOND, 0);
        noLights.set(Calendar.MILLISECOND, 0);

        Calendar currentTime = Calendar.getInstance();

//        System.out.println(lightSet1.getTime());
//        System.out.println(lightSet2.getTime());
//        System.out.println(noLights.getTime());
//        System.out.println(currentTime.getTime());

        if(currentTime.compareTo(lightSet1) >= 0 && currentTime.compareTo(lightSet2) <= 0){
            GpioSingleton.getInstance().getLightController().turnOnLED1();
            //System.out.println("Light 1 Should be on");
        }
        else if(currentTime.compareTo(lightSet2) >= 0 && currentTime.compareTo(noLights) <= 0){
            GpioSingleton.getInstance().getLightController().turnOffLED1();
            GpioSingleton.getInstance().getLightController().turnOnLED2();
            //System.out.println("Light 2 Should be on");
        }
        else{
            GpioSingleton.getInstance().getLightController().turnOffLED1();
            GpioSingleton.getInstance().getLightController().turnOffLED2();
            //System.out.println("No lights Should be on");
        }

        //System.out.println("Finished checking current date");
    }

}
