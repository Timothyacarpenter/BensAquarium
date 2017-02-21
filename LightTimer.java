/**
 * Created by Timmy on 24/1/17.
 */
import java.util.Calendar;

public class LightTimer implements Runnable {

    Settings settings;

    public LightTimer(Settings settings) {
        this.settings = settings;
    }

    public void run() {

        //System.out.println("Checking current date");

        Calendar lightSet1 = Calendar.getInstance();
        lightSet1.set(Calendar.HOUR_OF_DAY, settings.getMorning()[0]);
        lightSet1.set(Calendar.MINUTE, settings.getMorning()[1]);
        lightSet1.set(Calendar.SECOND, 0);
        lightSet1.set(Calendar.MILLISECOND, 0);

        Calendar lightSet2 = Calendar.getInstance();
        lightSet2.set(Calendar.HOUR_OF_DAY, settings.getDay()[0]);
        lightSet2.set(Calendar.MINUTE, settings.getDay()[1]);
        lightSet2.set(Calendar.SECOND, 0);
        lightSet2.set(Calendar.MILLISECOND, 0);

        Calendar lightSet3 = Calendar.getInstance();
        lightSet3.set(Calendar.HOUR_OF_DAY, settings.getNight()[0]);
        lightSet3.set(Calendar.MINUTE, settings.getNight()[1]);
        lightSet3.set(Calendar.SECOND, 0);
        lightSet3.set(Calendar.MILLISECOND, 0);

        Calendar noLights = Calendar.getInstance();
        noLights.set(Calendar.HOUR_OF_DAY, settings.getOff()[0]);
        noLights.set(Calendar.MINUTE, settings.getOff()[1]);
        noLights.set(Calendar.SECOND, 0);
        noLights.set(Calendar.MILLISECOND, 0);

        Calendar currentTime = Calendar.getInstance();

        if (currentTime.compareTo(lightSet1) >= 0 && currentTime.compareTo(lightSet2) <= 0){
            GpioSingleton.getInstance().getLightController().turnOnLED2();
        }
        else if(currentTime.compareTo(lightSet2) >= 0 && currentTime.compareTo(lightSet3) <= 0){
            GpioSingleton.getInstance().getLightController().turnOffLED2();
            GpioSingleton.getInstance().getLightController().turnOnLED1();
            //System.out.println("Light 1 Should be on");
        }
        else if(currentTime.compareTo(lightSet3) >= 0 && currentTime.compareTo(noLights) <= 0){
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
