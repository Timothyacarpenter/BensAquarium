import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import java.util.concurrent.*;

/**
 * Created by Timmy on 25/1/17.
 */
public class TemperatureReader implements Runnable{


    Future<Double> future = CompletableFuture.completedFuture(25.0);

    StringProperty temp;

    public TemperatureReader() {

        try {
            temp = new SimpleStringProperty(String.valueOf(future.get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



    }



    public void checkTemperature(){


        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Double> callable = new Callable<Double>() {
            @Override
            public Double call() throws InterruptedException {
                return GpioSingleton.getInstance().getTempSensor();
            }
        };
        future = executor.submit(callable);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        temp.setValue(String.valueOf(future.get()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });

        executor.shutdown();

    }

    public boolean isRunning(){
        return future.isDone();
    }

    public Future<Double> getTemperature(){
        return future;
    }

    public ObservableValue<String> getTemp(){
        return temp;
    }



    @Override
    public void run() {

        checkTemperature();

    }
}
