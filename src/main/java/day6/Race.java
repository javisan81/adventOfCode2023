package day6;

import java.math.BigDecimal;

public class Race {
    private final int time;
    private final BigDecimal distance;

    public Race(int time, BigDecimal distance) {
        this.time = time;
        this.distance = distance;
    }

    public int numberOfOptionsToWin() {
        int numberOfWaysToBeatTheRecord = 0;
        System.out.println(Integer.MAX_VALUE);
        for (int timeToCharge = 0; timeToCharge < time; timeToCharge++) {
            var velocity = timeToCharge;
            var timeRemaining = time - timeToCharge;
            var distanceDone = new BigDecimal(velocity).multiply(new BigDecimal(timeRemaining));
            if (distanceDone.compareTo(distance) >0) {
                numberOfWaysToBeatTheRecord++;
            }
        }
        return numberOfWaysToBeatTheRecord;
    }
}
