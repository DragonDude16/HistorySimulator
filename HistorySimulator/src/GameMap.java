
public class GameMap {
	
	Tile[][] map;
	int length;
	
	public GameMap(int x, int y)
	{
		map = new Tile[x][y];
		length = x;
	}
	
	public void runYear(int year)
	{
		for(int y = 0; y < map.length; y++)
		{
			for(int x = 0; x < map.length; x++)
			{
				map[x][y].runYear(year);
			}
		}
	}
	
	public void plainFill()
	{
		for(int y = 0; y < map.length; y++)
		{
			for(int x = 0; x < map.length; x++)
			{
				map[x][y] = new Tile(x, y, (0.04 + x * 0.04), (0.92 - 0.08 * y));
			}
		}
		
		map[5][5].addBasicVillage();
	}
	
	public void drawMap()
	{
		StdDraw.setPenColor(StdDraw.BLACK);
		for(int y = 0; y < map.length; y++)
		{
			for(int x = 0; x < map.length; x++)
			{
				if(map[x][y].fertility == 1)
				{
					StdDraw.setPenColor(178, 255, 102);
					StdDraw.filledRectangle(map[x][y].xCoord, map[x][y].yCoord, 0.02, 0.04);
				}
				if(map[x][y].fertility == 2)
				{
					StdDraw.setPenColor(158, 255, 102);
					StdDraw.filledRectangle(map[x][y].xCoord, map[x][y].yCoord, 0.02, 0.04);
				}
				
				StdDraw.setPenColor(StdDraw.BLACK);
				//StdDraw.square(map[x][y].xCoord + 0.02, map[x][y].yCoord, 0.04);
				StdDraw.rectangle(map[x][y].xCoord, map[x][y].yCoord, 0.02, 0.04);
				
				map[x][y].displayObjects();
				StdDraw.setPenColor(StdDraw.BLACK);
			}
		}
	}
	
	
	public Tile getTile(int x, int y)
	{
		return map[x][y];
	}
}
