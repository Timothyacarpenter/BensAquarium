import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Timmy on 16/2/17.
 */
public class Settings {

    String settings = "settings.dat";


    private int[] morning = new int[2];
    private int[] day = new int[2];
    private int[] night = new int[2];
    private int[] off = new int[2];
    private int waterTempLimit = 0;


    public Settings() {


        Path path = Paths.get("settings.dat");
        if(!Files.exists(path)){
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(settings);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                //morning light default

                morning[0] = 10;
                morning[1] = 0;
                bufferedWriter.write("10");
                bufferedWriter.newLine();
                bufferedWriter.write("0");
                bufferedWriter.newLine();
                //day light default
                day[0] = 12;
                day[1] = 0;
                bufferedWriter.write("12");
                bufferedWriter.newLine();
                bufferedWriter.write("0");
                bufferedWriter.newLine();
                //night light default
                night[0] = 17;
                night[1] = 0;
                bufferedWriter.write("17");
                bufferedWriter.newLine();
                bufferedWriter.write("0");
                bufferedWriter.newLine();
                //off light default
                off[0] = 22;
                off[1] = 0;
                bufferedWriter.write("22");
                bufferedWriter.newLine();
                bufferedWriter.write("0");
                bufferedWriter.newLine();
                //water temp limit
                waterTempLimit = 25;
                bufferedWriter.write("25");
                bufferedWriter.newLine();


                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else{

            FileReader fileReader = null;
            try {
                fileReader = new FileReader(settings);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String currentLine;
                int counter = 0;

                while((currentLine = bufferedReader.readLine()) != null) {

                    switch (counter){
                        case 0: morning[0] = Integer.parseInt(currentLine);
                            break;
                        case 1: morning[1] = Integer.parseInt(currentLine);
                            break;
                        case 2: day[0] = Integer.parseInt(currentLine);
                            break;
                        case 3: day[1] = Integer.parseInt(currentLine);
                            break;
                        case 4: night[0] = Integer.parseInt(currentLine);
                            break;
                        case 5: night[1] = Integer.parseInt(currentLine);
                            break;
                        case 6: off[0] = Integer.parseInt(currentLine);
                            break;
                        case 7: off[1] = Integer.parseInt(currentLine);
                            break;
                        case 8: waterTempLimit = Integer.parseInt(currentLine);
                            break;
                    }
                    counter++;

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    public void saveAllSettings(){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(settings);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //morning light default
            bufferedWriter.write(String.valueOf(morning[0]));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(morning[1]));
            bufferedWriter.newLine();
            //day light default
            bufferedWriter.write(String.valueOf(day[0]));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(day[1]));
            bufferedWriter.newLine();
            //night light default


            bufferedWriter.write(String.valueOf(night[0]));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(night[1]));
            bufferedWriter.newLine();
            //off light default
            bufferedWriter.write(String.valueOf(off[0]));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(off[1]));
            bufferedWriter.newLine();
            //water temp limit
            bufferedWriter.write(String.valueOf(waterTempLimit));
            bufferedWriter.newLine();


            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[] getMorning() {
        return morning;
    }

    public void setMorning(int[] morning) {
        this.morning = morning;
    }

    public int[] getDay() {
        return day;
    }

    public void setDay(int[] day) {
        this.day = day;
    }

    public int[] getNight() {
        return night;
    }

    public void setNight(int[] night) {
        this.night = night;
    }

    public int[] getOff() {
        return off;
    }

    public void setOff(int[] off) {
        this.off = off;
    }

    public int getWaterTempLimit() {
        return waterTempLimit;
    }

    public void setWaterTempLimit(int waterTempLimit) {
        this.waterTempLimit = waterTempLimit;
    }
}
