import java.awt.*;

public class Recktionsspiel {

    private String name;
    private int trys = 0;
    private long bestTime;
    private long badTime;
    private long averageTime;

    Recktionsspiel(String name){
        this.name = name;
    }
    EinUndAusgabe eo = new EinUndAusgabe();

    public void setBestTime(long bestTime){
        this.bestTime = bestTime;
    }
    public void setBadTime(long badTime){
        this.badTime = badTime;
    }

    public void setAverageTime(long averageTime) {
        this.averageTime = averageTime;
    }

    public void setTrys(int trys) {
        this.trys = trys + 1;
        if (getTrys() == 5){
            this.trys = 5;
        }
    }

    public long getBestTime() {
        return bestTime;
    }

    public long getBadTime() {
        return badTime;
    }

    public long getAverageTime() {
        return averageTime;
    }

    public int getTrys() {
        return trys;
    }

    @Override
    public String toString() {
        return  "Failed attempts: " + getTrys() + " out " + "5 \n" +
                "Best Time: " + conTimeToSec(getBestTime()) + " Sec \n" +
                "Baddest Time: "+ conTimeToSec(getBadTime()) + " Sec \n" +
                "Average Time: " + conTimeToSec(getAverageTime()) + " Sec";
    }

    public void start(){
        System.out.println("Ready?");
        System.out.println("Go!");
        int rdmNumRounds = betteRandomNumber(5,10);
        for( int i = 0; i < rdmNumRounds; i++){
            wait(betteRandomNumber(2, 5));
            long startTime = getMilliSeconds();
            ifLogik(startTime, rdmNumRounds);
        }

        System.out.println("End!");
        System.out.println(this.name + ", look at your stats!");
        System.out.println("");
    }

    private void ifLogik(long start, int rdmNumRounds){
        int rdmNum = betteRandomNumber(0, 9);
        System.out.println(rdmNum);
        if (eo.leseInteger() == rdmNum){
            long finishTime = getMilliSeconds();
            long time = finishTime - start;
            insTime(time, rdmNumRounds);
        }else  {
            System.out.println("Wrong!");
            setTrys(getTrys());
            if(getTrys() == 5){
                return;
            }
        }
    }

    private void insTime(long finishTime, int rdmNumRounds){

        if(finishTime < getBestTime() || getBestTime() == 0){
            this.setBestTime(finishTime);
        }
        if(finishTime > getBadTime()){
            setBadTime(finishTime);
        }
        setAverageTime((getAverageTime() + finishTime) / rdmNumRounds);
    }

    private int betteRandomNumber(int von, int bis){
        int rdmNum = getRandomNumber(bis);
        return Math.max(rdmNum, von);
    }

    private double conTimeToSec(long bestTime){
        double TimeDouble = Double.valueOf(bestTime);
        return TimeDouble / 1000.0;
    }





    // liefert eine Zahl, die die aktuelle Zeit in Millisek repräsentiert
    long getMilliSeconds() {
        return new java.util.Date().getTime();
    }
    // liefert eine Zufallszahl zwischen 0 und max (einschließlich)
    int getRandomNumber(int max) {
        return new java.util.Random().nextInt(max + 1);
    }
    // hält das Programm seconds Sekunden an
    void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception exc) {
        }
    }
    // liefert den größt-möglichen long-Wert
    long getMaxLongNumber() {
        return Long.MAX_VALUE;
    }
}
