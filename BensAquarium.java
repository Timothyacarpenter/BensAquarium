import java.util.concurrent.ExecutionException;

/**
 * Created by Timmy on 24/1/17.
 */


public class BensAquarium {

    static ServiceController serviceController = new ServiceController();

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println(GpioSingleton.getInstance().getTempSensor());

        startAllServices();
        Thread.sleep(50000);

        stopAllServices();
        System.out.println("Stopping all");
        GpioSingleton.getInstance().shutDownGpioController();


    }


    public static void stopAllServices(){
        serviceController.shutdownFanService();
        serviceController.shutdownTempService();
        serviceController.shutdownLightService();
    }

    public static void startAllServices(){
        serviceController.startLightService();
        serviceController.startTempService();
        serviceController.startFanService();
    }
}
