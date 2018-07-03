package interactionscalculator;

import java.time.LocalDate;
import java.time.LocalTime;

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

    public LocalDate getDate() {
        return date;
    }

    final void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    final void setTime(LocalTime time) {
        this.time = time;
    }

    public String getTimeString() {
        return timeString;
    }

    void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public int getInteractions() {
        return interactions;
    }

    final void setInteractions(int interactions) {
        this.interactions = interactions;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Date: ");
        sb.append(date.toString());
        sb.append("\n");
        sb.append("Time: ");
        sb.append(time.toString());
        sb.append("\n");
        sb.append("Interactions: ");
        sb.append(interactions);
        
        return sb.toString();
    }

}
