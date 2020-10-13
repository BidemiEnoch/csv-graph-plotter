package plotty.components;

//import java.util.LinkedHashMap;
import plotty.CsvData;
//import plotty.components.Point2D;
import java.util.ArrayList;
import java.awt.Color;

public class Axis {
    protected final ArrayList<Point2D> xLabels = new ArrayList<>();
    protected final ArrayList<Point2D> yLabels = new ArrayList<>();
    public static final Color DEFAULT_COLOR = new Color(50, 50, 50);
    public final int translationX = 50, translationY = 40, xInterval, ySegments = 7, width, height;
    public final Color color;

    private Axis(int panelWidth, int panelHeight, CsvData data, Color c) {
        color = c;
        width = panelWidth - translationX;
        height = panelHeight>200?200:(panelHeight-translationY-20);
        xInterval = width / data.value.size();

        generateYLabels(data);
        generateXLabels(data);
    }

    private int getMaxYLabel(CsvData data) {
        int result = 0;
        for (int i = 1; i < 1000; i++) {
            result = ySegments * i;
            if (result > data.maxY)
                break;
        }
        return result;
    }

    private void generateYLabels(CsvData data) {

        double value = 0, y = 10;
        int maxVal = getMaxYLabel(data);
        for (int i = 0; i <= ySegments; i++) {
            yLabels.add(new Point2D(-5, y, value));
            value += (double) maxVal / ySegments;
            y += (height / ((double) ySegments)) * 0.95;
        }
    }

    protected void generateXLabels(CsvData data){
        double x=10;
        //for(int i=0;i<data)
        for(String key:data.value.keySet()){
            xLabels.add(new Point2D(x,-5,key));
            x+=xInterval;
        }
    }

    public static Axis getInstance(int w, int h, CsvData data) {
        return new Axis(w, h, data, DEFAULT_COLOR);
    }

    public static Axis getInstance(int w, int h, CsvData data, Color c) {
        return new Axis(w, h, data, c);
    }

}
