package interactionscalculator;

import fileops.FileOps;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author JShepherd
 */
public class InteractionsCalculator {

    private final DateTimeFormatter dateFormatter;
    private final DateTimeFormatter timeFormatter;
    private List<String> sourceList;
    private List<HalfhourCallStat> callList;
    private int[][] hourByDayTotals;

    public InteractionsCalculator() {
        dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
    }

    void composeStats() {
        LocalDate date;
        LocalTime time;
        callList = new ArrayList<>();

        //@Todo -- pull out a new function, "filter list" maybe, and pass
        //the filtering criteria to the function and returning a filtered list.
        //May call for a new class, "ListFilter." Or use a Stream.        
        
        String line[];
        for (int i = 0; i < sourceList.size(); i++) {
            line = sourceList.get(i).split(",");

            if (line.length < 1) {
                continue;
            }

            if (isValidStr(line[0])) {

                date = LocalDate.parse(line[0], dateFormatter);
                time = LocalTime.parse(line[1], timeFormatter);
                int interactions = (Integer.parseInt(line[2]));

                HalfhourCallStat call = new HalfhourCallStat(date, time, interactions);
                callList.add(call);
                //debugging statement
                System.out.println("Added--\n" + call.toString());
            }
        }
    }

    private boolean isValidStr(String aString) {
        /**
         * The exported files have excess at the top including a header that
         * should be skipped. regex "\d" resolves to any digit.
         */
        boolean isValid = false;
        Pattern p = Pattern.compile("\\d");
        Matcher m = p.matcher(aString.substring(0, 1));
        if (m.find()) {
            isValid = true;
        }
        return isValid;
    }

    void calculateInteractions() {
        final int daysInWeek = 7;
        final int hoursInDay = 24;
        hourByDayTotals = new int[daysInWeek][hoursInDay];
        final DayOfWeek[] daysOfWeek = {DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY
        };

        int j = 0;

        for (DayOfWeek day : daysOfWeek) {

            for (HalfhourCallStat stat : callList) {

                if (stat.getDate().getDayOfWeek().equals(day)) {

                    for (int i = 0; i < hoursInDay; i++) {

                        if (stat.getTime().get(HOUR_OF_DAY) >= i && stat.getTime().get(HOUR_OF_DAY) < i + 1) {

                            hourByDayTotals[j][i] += stat.getInteractions();
                        }
                    }
                }
            }
            j++;
        }
    }

    void readFile(String filename) throws IOException {
        if (!filename.isEmpty()) {
            FileOps fo = new FileOps(filename, true);

            sourceList = fo.readToList();
            System.out.println("File read to list.");
        }
    }

    void writeFile(String total) throws IOException {
        String directory = System.getProperty("user.home");
        String filepath = directory + "\\desktop\\interactions_calculated.csv";
        FileOps fo = new FileOps(filepath, true);
        fo.writeToFile(total);
    }

    String writeTotals() {
        StringBuilder sb = new StringBuilder();
        writeHeader(sb);

        int j = 0;
        while (j < hourByDayTotals[0].length) {
            for (int i = 0; i < hourByDayTotals.length; i++) {
                sb.append(hourByDayTotals[i][j]);
                sb.append(",");
            }
            sb.append("\n");
            j++;
        }
        return sb.toString();
    }

    void writeHeader(StringBuilder sb) {
        sb.append("Monday,");
        sb.append("Tuesday,");
        sb.append("Wednesday,");
        sb.append("Thursday,");
        sb.append("Friday,");
        sb.append("Saturday,");
        sb.append("Sunday\n");
    }

    public DateTimeFormatter getDateFormatter() {
        return dateFormatter;
    }
}
