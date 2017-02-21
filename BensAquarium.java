import java.util.concurrent.ExecutionException;

/**
 * Created by Timmy on 24/1/17.
 */


public class BensAquarium {

    static ServiceController serviceController = new ServiceController();

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println(GpioSingleton.getInstance().getTempSensor());
        serviceController.startAllServices();
        Thread.sleep(10000);
        GpioSingleton.getSettings().setWaterTempLimit(25);
        Thread.sleep(10000);

        serviceController.stopAllServices();
        System.out.println("Stopping all");
        GpioSingleton.getInstance().shutDownGpioController();
        GpioSingleton.getSettings().saveAllSettings();


    }

}
