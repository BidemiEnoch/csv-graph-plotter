package plotty;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;

public class CsvData {
    public final LinkedHashMap<String, Integer> value = new LinkedHashMap<>();
    public final int minY, maxY;

    public CsvData(String path) {
        String line;
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("C:\\Users\\bidemi\\Desktop\\javacodes\\" + path));
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                value.put(arr[0], Integer.parseInt(arr[1]));
            }
            reader.close();

        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("An error occured while trying to access the file");
            System.exit(0);
        }

        maxY = Collections.max(value.values());
        minY = Collections.min(value.values());
    }

    public Integer[] getYCords() {
        return value.values().toArray(new Integer[value.size()]);
    }
}
