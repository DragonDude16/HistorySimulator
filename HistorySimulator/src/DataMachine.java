import java.util.ArrayList;

public class DataMachine {
	public DataMachine()
	{
		
	}
	
	public void scatterPlot(double[] x, double[] y, double xo, double yo, double xf, double yf, String xAxis, String yAxis)
	{
		double maxX = this.findMax(x);
		double maxY = this.findMax(y);
		
		
		
		double[] scaledX = new double[x.length];
		double[] scaledY = new double[x.length];
		
		double coordXO = xo * 1;
		double coordYO = yo * 1.25;
		
		double xRange = xf - xo;
		double yRange = yf - yo; 	
		
		for(int i = 0; i < x.length; i++)
		{
			scaledX[i] = (x[i] / maxX) * xRange + coordXO;
			scaledY[i] = (y[i] / maxY) * yRange + coordYO;
		}
		
		StdDraw.setCanvasSize(500, 500);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);
		
		for(int j = 0; j < x.length; j++)
		{
			StdDraw.point(scaledX[j], scaledY[j]);
		}
		
		StdDraw.setPenRadius(0.005);
		StdDraw.line(coordXO, coordYO, this.findMax(scaledX), coordYO);
		StdDraw.line(coordXO, coordYO, coordXO, this.findMax(scaledY));
		
		StdDraw.textRight(xo, yo, "0");
		StdDraw.text(this.findMax(scaledX), yo, "" + maxX);
		StdDraw.text(xRange/2 + xo, yo, "" + (maxX/2));
		StdDraw.textRight(xo, this.findMax(scaledY), "" + maxY);
		StdDraw.textRight(xo, yRange/2 + yo, "" + (maxY/2));
		
	}
	
	public void scatterPlotArrayList(ArrayList<Double> x, ArrayList<Double> y, double xo, double yo, double xf, double yf, String xAxis, String yAxis)
	{
		if(x.size() == 0 || y.size() == 0) { return; }
		
		double maxX = this.findMax(x);
		double maxY = this.findMax(y);
		
		
		
		double[] scaledX = new double[x.size()];
		double[] scaledY = new double[x.size()];
		
		
		
		double coordXO = xo;
		double coordYO = yo;
		
		double xRange = xf - xo;
		double yRange = yf - yo; 	
		
		for(int i = 0; i < x.size(); i++)
		{
			scaledX[i] = (x.get(i) / maxX) * xRange + coordXO;
			scaledY[i] = (y.get(i) / maxY) * yRange + coordYO;
		}
		
		//StdDraw.setCanvasSize(500, 500);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);
		
		for(int j = 0; j < x.size(); j++)
		{
			StdDraw.point(scaledX[j], scaledY[j]);
		}
		
		StdDraw.setPenRadius(0.005);
		StdDraw.line(coordXO, coordYO, this.findMax(scaledX), coordYO);
		StdDraw.line(coordXO, coordYO, coordXO, this.findMax(scaledY));
		StdDraw.setPenRadius(0.001);
		
		StdDraw.textRight(xo, yo, "0");
		StdDraw.text(this.findMax(scaledX), yo, "" + maxX);
		//StdDraw.text(xRange/2 + xo, yo, "" + (maxX/2));
		StdDraw.textRight(xo, this.findMax(scaledY), "" + maxY);
		//StdDraw.textRight(xo, yRange/2 + yo, "" + (maxY/2));
		
	}
	
	public double findMax(double[] x)
	{
		double result = x[0];
		
		for(int i = 0; i < x.length; i++)
		{
			if(x[i] > result) { result = x[i]; }
		}
		
		return result;
	}
	
	public double findMax(ArrayList<Double> x)
	{
		double result = x.get(0);
		
		for(int i = 0; i < x.size(); i++)
		{
			if(x.get(i) > result) { result = x.get(i); }
		}
		
		return result;
	}
	
	public double findMin(ArrayList<Double> x)
	{
		double result = x.get(0);
		
		for(int i = 0; i < x.size(); i++)
		{
			if(x.get(i) < result) { result = x.get(i); }
		}
		
		return result;
	}
	
	public double findMin(double[] x)
	{
		double result = x[0];
		
		for(int i = 0; i < x.length; i++)
		{
			if(x[i] < result) { result = x[i]; }
		}
		
		return result;
	}
}
