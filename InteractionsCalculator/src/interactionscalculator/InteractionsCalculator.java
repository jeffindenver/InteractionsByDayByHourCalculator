package interactionscalculator;

import FileOps.FileOps;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author JShepherd
 */
public class InteractionsCalculator {

    private final DateTimeFormatter dateFormatter;
    private final DateTimeFormatter timeFormatter;
    private List<String> sourceList;
    private ArrayList<HalfhourCallStat> callList;
    private int[][] hourByDayTotals;
    private String filename;

    public InteractionsCalculator() {
        dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
    }

    void calculateInteractions() {
        //sums interactions in a time frame for a day of the week
        int hoursInDay = 24;
        hourByDayTotals = new int[7][24];
        int j = 0;
        DayOfWeek[] daysOfWeek = {DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY
        };

        for (DayOfWeek day : daysOfWeek) {

            for (HalfhourCallStat item : callList) {

                if (item.getDate().getDayOfWeek().equals(day)) {

                    for (int i = 0; i < hoursInDay; i++) {

                        if (item.getTime().get(HOUR_OF_DAY) >= i && item.getTime().get(HOUR_OF_DAY) < i + 1) {

                            hourByDayTotals[j][i] += item.getInteractions();
                        }
                    }
                }
            }
            j++;
        }
    }

    void composeStats() {
        String line[];
        LocalDate date;
        LocalTime time;
        callList = new ArrayList<>();
        for (int i = 0; i < sourceList.size(); i++) {
            line = sourceList.get(i).split(",");

            if (line.length > 2 && !line[0].equals("")) {
                //setDate()
                date = LocalDate.parse(line[0], dateFormatter);

                //setHour()
                time = LocalTime.parse(line[1], timeFormatter);

                //setInteractions()
                int interactions = (Integer.parseInt(line[2]));
                HalfhourCallStat call = new HalfhourCallStat(date, time, interactions);
                callList.add(call);
            }
        }
    }

    void readFile() {
        if (!filename.equals("")) {
            FileOps fo = new FileOps(filename, true);
            try {
                sourceList = fo.readToList();
                System.out.println("File read to list.");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    void writeFile(String total) throws IOException {
        String directory = System.getProperty("user.home");
        String filepath = directory + "\\desktop\\sergeants_file.csv";
        FileOps fo = new FileOps(filepath, true);
        fo.writeToFile(total);
    }

    String writeTotals() {
        StringBuilder sb = new StringBuilder();
        //separate to writeHeader()
        sb.append("Monday,");
        sb.append("Tuesday,");
        sb.append("Wednesday,");
        sb.append("Thursday,");
        sb.append("Friday,");
        sb.append("Saturday,");
        sb.append("Sunday\n");

        int j = 0;
        while (j < hourByDayTotals[0].length) {
            for (int i = 0; i < hourByDayTotals.length; i++) {
                sb.append(hourByDayTotals[i][j]);
                sb.append(",");
                System.out.println("Total " + hourByDayTotals[i][j] + "appended to colmun " + i + " and row " + j);
            }
            sb.append("\n");
            j++;
        }
        return sb.toString();
    }

    String getUserInput(String prompt) {
        Scanner sc = new Scanner(System.in);
        String input = "";
        System.out.println(prompt);
        if (sc.hasNextLine()) {
            input = sc.nextLine();
        }
        return input;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
        System.out.println("Filename set to " + filename);
    }
}
