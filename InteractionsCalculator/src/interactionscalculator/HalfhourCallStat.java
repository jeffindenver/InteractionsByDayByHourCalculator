package interactionscalculator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author JShepherd
 */
public class HalfhourCallStat {

    private LocalDate date;
    private LocalTime time;
    private String timeString;

    private int interactions;

    public HalfhourCallStat(LocalDate date, LocalTime time, int interactions) {
        setDate(date);
        setTime(time);
        setInteractions(interactions);
    }

    public int[] convertTimeToInt() {
        timeString = time.format(DateTimeFormatter.ISO_LOCAL_TIME);
        String[] hourString = timeString.split(":");
        int[] hour = {Integer.parseInt(hourString[0]), Integer.parseInt(hourString[1])};
        return hour;
    }

    public LocalDate getDate() {
        return date;
    }

    public final void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public final void setTime(LocalTime time) {
        this.time = time;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public int getInteractions() {
        return interactions;
    }

    public final void setInteractions(int interactions) {
        this.interactions = interactions;
    }

}
