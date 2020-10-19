package plotty.components;

import plotty.CsvData;
//import plotty.components.Point2D;
import java.util.ArrayList;
import java.awt.Color;

public class Axis {
    public static final Color DEFAULT_COLOR = new Color(50, 50, 50);
    public static final int MAX_WIDTH = 500, MAX_HEIGHT = 400, MIN_WIDTH = 200, MIN_HEIGHT = 200;
    protected final ArrayList<Point2D> xLabels = new ArrayList<>();
    protected final ArrayList<Point2D> yLabels = new ArrayList<>();
    public final int translationX = 50, translationY = 40, xInterval, ySegments = 6, width, height;
    public final Color color;

    private Axis(int panelWidth, int panelHeight, CsvData data, Color c) {
        color = c;
        width = panelWidth - translationX;
        height = panelHeight - translationY - 20;
        xInterval = width / data.value.size();

        generateYLabels(data);
        generateXLabels(data);
    }

    private int getMaxYValue(CsvData data) {
        int value = 0;
        for (int i = 1; i < 1000; i++) {
            value = ySegments * i;
            if (value > data.maxY)
                break;
        }
        return value;
    }

    private int getMinYValue(CsvData data) {
        int value = 0;
        if (data.minY >= 0)
            return 0;
        for (int i = 0; i > -1000; i--) {
            value = ySegments * i;
            if (value < data.minY)
                break;
        }
        return value;
    }

    private void generateYLabels(CsvData data) {

        int y = 10;
        int maxVal = getMaxYValue(data);
        //int value = getMinYValue(data);
        int value=0;
        for (int i = 0; i <= ySegments; i++) {
            yLabels.add(new Point2D(-5, y, value));
            value += maxVal / ySegments;
            y += (int) ((height / ySegments) * 0.95);
        }
    }

    protected void generateXLabels(CsvData data) {
        int x = 10;

        for (String key : data.value.keySet()) {
            xLabels.add(new Point2D(x, -5, key));
            x += xInterval;
        }
    }

    public static Axis getInstance(int w, int h, CsvData data) {
        return new Axis(w, h, data, DEFAULT_COLOR);
    }

    public static Axis getInstance(int w, int h, CsvData data, Color c) {
        return new Axis(w, h, data, c);
    }

}
