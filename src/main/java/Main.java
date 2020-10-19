import plotty.components.LinePlot;
import plotty.Frame;

public class Main {
    public static void main(String[] args) {
        String root="C:\\Users\\bidemi\\Desktop\\javacodes\\";
        
        //LinePlot plot1 = LinePlot.getInstance("data.csv", 400, 300);
        LinePlot plot2 = LinePlot.getInstance(root+"examdata.csv");
        LinePlot plot3 = LinePlot.getInstance(root+"data2.csv");
        Frame frame = new Frame(1000, 900, "My plots");
        //frame.add(plot1);
        frame.add(plot2);
        frame.add(plot3);
       
    }
}
