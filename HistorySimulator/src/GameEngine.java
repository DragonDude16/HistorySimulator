import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameEngine {
	
	GameMap map;
	boolean playing;
	Tile toDisplay;
	ArrayList<Button> buttons;
	
	double xDisplay;
	double yDisplay;
	
	int year;
	
	public GameEngine()
	{
		map = new GameMap(10, 10);
		map.plainFill();
		playing = true;
		toDisplay = null;
		buttons = new ArrayList<>();
		buttons.add(new Button(0.06, 1, 0.08, 0.04, "Next Turn"));
		
		xDisplay = 0;
		yDisplay = 0;
		
		year = 0;
	}
	
	public void play()
	{
		this.drawCanvas();
		map.drawMap();
		
		while(playing) //animation loop
		{			
			StdDraw.clear();
			map.drawMap();
			
			this.detectTileClicks();
			this.detectButtonPresses();
			
			if(toDisplay != null){ toDisplay.displayInfo(); }
			this.displayButtons();
			this.displayResources();
			this.displayTileFeatures();
			
			StdDraw.show();
			
		}
	}
	
	public void nextFiveTurns()
	{
		year++;
		map.runYear(year);
		
		year++;
		map.runYear(year);
		year++;
		map.runYear(year);
		year++;
		map.runYear(year);
		year++;
		map.runYear(year);
	}
	
	public void nextTurn()
	{
		year++;
		map.runYear(year);
	}
	
	public void _tenthousand_TestTurn()
	{
		for(int i = 0; i < 10000; i++)
		{
			year++;
			map.runYear(year);
		}
		System.out.println("Population after 10000 years: " + map.getTile(5, 5).v.pop);
	}
	
	public void oneThousandYears()
	{
		for(int i = 0; i < 1000; i++)
		{
			year++;
			map.runYear(year);
		}
		
		System.out.println("Population after 1000 years: " + map.getTile(5, 5).v.pop);
		this.createGame();
	}
	
	public void oneThousandYearsOfWork()
	{
		for(int i = 0; i < 1000; i++)
		{
			year++;
			map.runYear(year);
		}
		
		System.out.println("Lumberjacks after 1000 years: " + map.getTile(5, 5).v.lumberjacks);
		System.out.println("Stoneminers after 1000 years: " + map.getTile(5, 5).v.stoneminers);
		this.createGame();
	}
	
	public void createGame()
	{
		map = new GameMap(10, 10);
		map.plainFill();
		playing = true;
		toDisplay = null;
		buttons = new ArrayList<>();
		buttons.add(new Button(0.06, 1, 0.08, 0.04, "Next Turn"));
		
		xDisplay = 0;
		yDisplay = 0;
		
		year = 0;
	}
	
	public void tf_tenthousand_tests()
	{
		for(int i = 0; i < 1; i++)
		{
			this._tenthousand_TestTurn();
			this.createGame();
		}
	}
	
	public void detectButtonPresses()
	{
		if(StdDraw.isMousePressed())
		{
			for(int i = 0; i < buttons.size(); i++)
			{
				if(buttons.get(i).clicked(StdDraw.mouseX(), StdDraw.mouseY()))
				{
					if(buttons.get(i).name.equals("Next Turn"))
					{
						//this.nextFiveTurns();
						//this._tenthousand_TestTurn();
						//this.tf_tenthousand_tests();
						this.oneThousandYearsOfWork();
						
						
						for(int d = 0; d < 25; d++)
						{
							//this.oneThousandYearsOfWork();
						}
						
					}
				}
			}
		}
	}
	
	public void displayResources()
	{
		StdDraw.textLeft(0.11, 1, "Year: " + year);
	}
	
	public void displayTileFeatures()
	{
		if(toDisplay != null)
		{
			StdDraw.textLeft(0.02, 0.1, "Fertility: " + toDisplay.fertility);
			
			
			StdDraw.setPenColor(StdDraw.YELLOW);
			StdDraw.rectangle(toDisplay.xCoord, toDisplay.yCoord, 0.02, 0.04);
			StdDraw.setPenColor(StdDraw.BLACK);

		}
	}
	
	public void displayButtons()
	{
		for(int i = 0; i < buttons.size(); i++)
		{
			buttons.get(i).display();
		}
	}
	
	public void detectTileClicks()
	{
		if(StdDraw.isMousePressed())
		{
			for(int y = 0; y < map.length; y++)
			{
				for(int x = 0; x < map.length; x++)
				{
					if(map.getTile(x, y).withinRangeOf(StdDraw.mouseX(), StdDraw.mouseY()))
					{
						toDisplay = map.getTile(x, y);
						return;
					}
				}
			}
			//toDisplay = null;
		}
	}
	
	public void drawCanvas()
	{
		StdDraw.setCanvasSize(1600, 800);
		StdDraw.setScale(-0.1, 1.1);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.enableDoubleBuffering();
	}
	
	public void drawMap()
	{
		for(int y = 0; y < map.length; y++)
		{
			for(int x = 0; x < map.length-1; x++)
			{
				StdDraw.square(map.getTile(x, y).xCoord + 0.02, map.getTile(x, y).yCoord, 0.04);
			}
		}
		
		
	}
}
