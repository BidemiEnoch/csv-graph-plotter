package plotty.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Dimension;
//import java.awt.BasicStroke;
import java.awt.geom.Line2D;
import java.awt.Font;
import java.awt.geom.AffineTransform;
//import plotty.components.Axis;
//import plotty.components.PlotComponent;
//import plotty.components.Point2D;

public final class LinePlot extends PlotComponent {

	private LinePlot(String path, int w, int h, Color c) {
		super(path, w, h, c);

		setBackground(Color.green);
		setPreferredSize(new Dimension(width + 20, height + 20));
	}

	@Override
	protected void drawPlot(Graphics2D ctx) {
		ctx.setStroke(THICK_STROKE);
		// System.out.println(color);
		ctx.setColor(color);

		for (int i = 0; i < graphPoints.size() - 1; i++) {
			Point2D point1 = graphPoints.get(i);
			Point2D point2 = graphPoints.get(i + 1);

			ctx.draw(new Line2D.Double(point1.x, point1.y, point2.x, point2.y));
			ctx.translate(-3, -3);
			ctx.fillOval((int) point1.x, (int) point1.y, 6, 6);
			ctx.translate(3, 3);
		}
	}

	@Override
	protected void drawAxis(Graphics2D ctx) {

		ctx.setColor(axis.color);
		// System.out.println(axis.color);
		ctx.draw(new Line2D.Float(0, 0, axis.width, 0)); // draws the x-axis
		ctx.draw(new Line2D.Float(0, 0, 0, axis.height)); // draws the y-axis
		drawYLabels(ctx);
		drawXLabels(ctx);
	}

	@Override
	protected void drawYLabels(Graphics2D ctx) {
		ctx.setFont(new Font("monospace", Font.BOLD, 13));
		
		for (Point2D point : axis.yLabels) {
			ctx.setStroke(THIN_STROKE);
			ctx.draw(new Line2D.Float(point.x, point.y, point.x + 5, point.y));
			ctx.setStroke(DASHED_STROKE);
			ctx.draw(new Line2D.Float(point.x + 5, point.y, point.x + axis.width, point.y));
			AffineTransform t = ctx.getTransform();
			ctx.translate(-30, point.y);
			ctx.scale(1, -1);
			ctx.drawString(point.value, 0, 0);
			ctx.setTransform(t);
		}

	}

	protected void drawXLabels(Graphics2D ctx) {
		ctx.setFont(new Font("Arial", Font.ITALIC, 10));
		for (Point2D point : axis.xLabels) {
			ctx.setStroke(THIN_STROKE);
			ctx.draw(new Line2D.Float(point.x, point.y, point.x, point.y - 5));
			AffineTransform t = ctx.getTransform();
			ctx.translate(point.x - 10, -20);
			ctx.scale(1, -1);
			ctx.drawString(point.value, 0, 0);
			ctx.setTransform(t);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D ctx = (Graphics2D) g;
		ctx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ctx.translate(0, height);
		ctx.scale(1, -1);
		ctx.translate(axis.translationX, axis.translationY - 10);
		drawAxis(ctx);
		drawPlot(ctx);
	}

	public static LinePlot getInstance(String path) {
		return new LinePlot(path, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR);
	}

	public static LinePlot getInstance(String path, Color c) {
		return new LinePlot(path, DEFAULT_WIDTH, DEFAULT_HEIGHT, c);
	}

	public static LinePlot getInstance(String path, int w, int h) {
		return new LinePlot(path, w, h, DEFAULT_COLOR);
	}

}
