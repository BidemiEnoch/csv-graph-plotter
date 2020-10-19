package plotty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class CsvData {
    public final LinkedHashMap<String, Integer> value = new LinkedHashMap<>();
    public final int minY, maxY;

    public CsvData(String path) {
        ArrayList<String[]> data = new ArrayList<>();
        String line;
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(path));
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                value.put(arr[0], Integer.parseInt(arr[1]));
                data.add(arr);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace(); 
            System.exit(0);
        }
        
        Set<String> issues = checkForIssues(data);
        if (issues.size() != 0) {
            issues.stream().forEach(System.out::println);
            System.exit(0);
        }

        maxY = Collections.max(value.values());
        minY = Collections.min(value.values());
    }

    public static Set<String> checkForIssues(ArrayList<String[]> data) {
        Set<String> issues = new HashSet<>();
        int firstRowLength = data.get(0).length;

        for (String[] row : data) {
            if (row.length > 2) {
                issues.add(
                        "This plotter cannot accomodate more than 3 graphs in a single plot so your csv data must have at most 3 values and should b in the order key->value...");
            }

            if (row.length != firstRowLength) {
                issues.add("The length of some rows in the csv file do not match");
            }
            /*
             * gets a substring(the csv values) of the array from the 2nd element to the
             * last, this line removes the key
             */
            String[] values = Arrays.copyOfRange(row, 1, row.length);
            // checks if those values contains only integers
            boolean containsOnlyInts = Arrays.stream(values).allMatch((String val) -> val.matches("\\d+"));
            if (!containsOnlyInts) {
                issues.add(
                        "This plotter does not support non-integer or negative values in csv files yet, also make sure your items are in the order key->value...");
            }

            boolean containsLargeValues = Arrays.stream(values).anyMatch((String val) -> val.length() > 6);
            if (containsLargeValues) {
                issues.add("This plotter does not support large values and values must only be integers");
            }

        }
        return issues;

    }

    public Integer[] getYCords() {
        return value.values().toArray(new Integer[value.size()]);
    }
}
