package plotty.components;



public final class Point2D {
    public final int x, y;
    public final String value;

    public Point2D(int x, int y) {
        this.x =  x;
        this.y =  y;
        this.value = null;
    }

    public Point2D(int x, int y, int value) {
        this.x =  x;
        this.y =  y;
        this.value = String.valueOf(value);
    }

    public Point2D(int x, int y, String value) {
        this.x =  x;
        this.y =  y;
        this.value = value;
    }

    public double getValue() {
        return (value==null)?0d:Double.parseDouble(value);       
    }

}