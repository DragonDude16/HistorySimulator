
public class Button {
	
	double x;
	double y;
	double length;
	double height;
	String name;
	long timeLastClicked;
	
	public Button(double X, double Y, double l, double h, String n)
	{
		x = X;
		y = Y;
		length = l;
		height = h;
		name = n;
		timeLastClicked = 0;
	}
	
	public void display()
	{
		StdDraw.rectangle(x, y, length/2, height/2);
		StdDraw.text(x, y, name);
	}
	
	public boolean clicked(double mx, double my)
	{
		if(x + length/2 > mx && x - length/2 < mx)
		{
			if(y + height/2 > my && y - height/2 < my)
			{
				if(System.currentTimeMillis() - timeLastClicked > 100)
				{
					timeLastClicked = System.currentTimeMillis();
					return true;
				}
			}
		}
		return false;
	}
}
