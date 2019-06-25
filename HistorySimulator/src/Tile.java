import java.util.ArrayList;

public class Tile {
	
	public int xNum;
	public int yNum;
	public double xCoord;
	public double yCoord;
	int fertility;
	int woodLevel;
	int stoneLevel;
	Village v;
	
	public Tile(int X, int Y, double x, double y)
	{
		xNum = X;
		yNum = Y;
		
		xCoord = x;
		yCoord = y;
		
		v = null;
		fertility = 1;
		woodLevel = 1;
		stoneLevel = 1;
	}
	
	public void runYear(int year)
	{
		if(v != null)
		{
			v.runYear(this, year);
		}
	}
	
	public void addBasicVillage()
	{
		v = new Village(xNum, yNum);
	}
	
	public void displayObjects()
	{
		if(v != null) { v.toDisplay(xCoord, yCoord); }
	}
	
	public void displayInfo()
	{
		if(v != null)
		{
			v.displayInfo();
		}
		
	}
	
	public boolean withinRangeOf(double x, double y)
	{
		if((xCoord + 0.02) > x && (xCoord - 0.02) < x) //x axis
		{
			if((yCoord + 0.04) > y && (yCoord - 0.04) < y)
			{
				return true;
			}
		}
		
		return false;
	}
}
