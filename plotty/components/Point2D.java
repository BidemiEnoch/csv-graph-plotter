package plotty.components;

public final class Point2D {
    public final int x, y;
    public final String value;

    public Point2D(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
        this.value = "";
    }

    public Point2D(double x, double y, double value) {
        this.x = (int) x;
        this.y = (int) y;
        this.value = (int)value+"";
    }

    public Point2D(double x, double y, String value) {
        this.x = (int) x;
        this.y = (int) y;
        this.value = value;
    }

    public double getValue() {
        return Double.parseDouble(value);
    }

}
