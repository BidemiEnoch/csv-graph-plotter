import javax.swing.JPanel;
import plotty.components.LinePlot;
import plotty.Frame;

public class Main {
    public static void main(String[] args) {
        // long time=System.nanoTime();

        JPanel plot1 = LinePlot.getInstance("data.csv", 400, 300);
        JPanel plot2 = LinePlot.getInstance("examdata.csv", 400, 300);
        JPanel plot3 = LinePlot.getInstance("data2.csv", 400, 300);
        Frame frame = new Frame(1000, 900, "My plots");
        frame.add(plot1);
        frame.add(plot2);
        frame.add(plot3);
        /*
         * for(int i=0;i<10000;i++){ JPanel plot =
         * LinePlot.getInstance("data2.csv",500,300); }
         * 
         * double exectime=(double)(System.nanoTime()-time)/1000000000;
         * System.out.println(exectime);
         */
    }
}
