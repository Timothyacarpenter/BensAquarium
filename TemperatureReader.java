import java.util.concurrent.*;

/**
 * Created by Timmy on 25/1/17.
 */
public class TemperatureReader implements Runnable{


    Future<Double> future = CompletableFuture.completedFuture(25.0);

    public void checkTemperature(){

        //System.out.println("Checking Temp");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Double> callable = new Callable<Double>() {
            @Override
            public Double call() throws InterruptedException {
                return GpioSingleton.getInstance().getTempSensor();
            }
        };
        future = executor.submit(callable);
        // future.get() returns 2 or raises an exception if the thread dies, so safer
        executor.shutdown();

    }

    public boolean isRunning(){
        return future.isDone();
    }

    public Future<Double> getTemperature(){
        return future;
    }

    @Override
    public void run() {

        checkTemperature();

    }
}
