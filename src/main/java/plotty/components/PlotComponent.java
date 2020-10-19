package plotty.components;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import plotty.CsvData;

public abstract class PlotComponent extends JPanel {
	protected final CsvData csvdata;
	protected final ArrayList<Point2D> graphPoints = new ArrayList<>();
	protected final int width, height;
	protected final static int DEFAULT_WIDTH = 400, DEFAULT_HEIGHT = 350;
	protected final static Color DEFAULT_COLOR = new Color(250, 0, 0);
	protected final static BasicStroke THIN_STROKE = new BasicStroke(1), THICK_STROKE = new BasicStroke(2),
			DASHED_STROKE = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f,
					new float[] { 1f, 0f, 1f }, 2f);
	protected final Axis axis;
	protected final Color color;

	abstract protected void drawPlot(Graphics2D ctx);

	abstract protected void drawAxis(Graphics2D ctx);

	abstract protected void drawYLabels(Graphics2D ctx);

	abstract protected void drawXLabels(Graphics2D ctx);

	protected PlotComponent(String path, int w, int h, Color c) {

		csvdata = new CsvData(path);
		color = c;
		axis = Axis.getInstance(width = w, height = h, csvdata);

		generatePlotPoints();
	}

	private void generatePlotPoints() {

		int x = 10;
		Point2D maxYLabel = axis.yLabels.get(axis.yLabels.size() - 1);
		Point2D minYLabel = axis.yLabels.get(0);
		
		for (Integer val : csvdata.getYCords()) {
			double scale = (maxYLabel.getValue()) / val;
			int yRange = maxYLabel.y - minYLabel.y;
			int y = (int)(yRange / scale) + minYLabel.y;
			graphPoints.add(new Point2D(x, y));
			x += axis.xInterval;

		}
	}


}
