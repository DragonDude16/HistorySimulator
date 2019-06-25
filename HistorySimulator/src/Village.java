import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Village {

	DataMachine dm;
	DecimalFormat df;
	int x;
	int y;
	int currentHP;
	int maxHP;
	
	double growthChance;
	int pop;
	int farmers;
	int lumberjacks;
	int stoneminers;
	
	ArrayList<Double> population;
	ArrayList<Double> years;
	
	int food; 
	int foodGain; 	//10 + fertility per farmer
	int foodLoss; 	//10 per person
	double netFood;
	String netFoodS;
	
	double wood;
	int woodGain;	//5 * abundance per lumberjack
	double woodLoss;	//1 per 2 people
	double netWood;
	String netWoodS;
	
	double stone;
	int stoneGain; //5 * abundance per miner
	double stoneLoss; //1 per 2 people
	double netStone;
	String netStoneS;
	
	public Village(int X, int Y)
	{
		dm = new DataMachine();
		population = new ArrayList<Double>();
		years = new ArrayList<Double>();
		df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.FLOOR);
		
		x = X;
		y = Y;
		
		maxHP = 2;
		currentHP = 2;
		
		growthChance = 0;
		pop = 0;
		farmers = 0;
		lumberjacks = 0;
		stoneminers = 0;
		
		food = 200;
		foodGain = 0;
		foodLoss = 0;
		netFood = 0;
		
		wood = 100;
		woodGain = 0;
		woodLoss = 0;
		netWood = 0;
		
		stone = 0;
		stoneGain = 0;
		stoneLoss = 0;
		netStone = 0;
		
		pop += 10;
		farmers += 10;
	}
	
	public void runYear(Tile t, int year)
	{		
		population.add((double) pop);
		years.add((double) year);
		
		this.runResources(t);

		
		//birth
		this.runBirths();
		
		//death
		
		
		netFood = foodGain - foodLoss;
		if(food != 0) { netFood = netFood/food; }
		food = food + foodGain - foodLoss;
		
		netWood = woodGain - woodLoss;
		if(wood != 0) { netWood = netWood/wood; }
		wood += (woodGain - woodLoss);
		
		netStone = stoneGain - stoneLoss;
		if(stone != 0) { netStone = netStone/stone; }
		stone += (stoneGain - stoneLoss);
	}
	
	public void runBirths()
	{
		double birthChance = 5 - Math.log(pop + 1);
		if(birthChance < 0) { birthChance = 0.001; }
		
		for(int i = 0; i < pop; i++)
		{
			if(this.randomChance(birthChance))
			{
				this.newPerson();
			}
		}
	}
	
	public void newPerson()
	{
		pop++;
		if(this.randomChance(90))
		{
			this.changeRandomJobNumberProportionally(1);
		}
		else
		{
			this.changeRandomJobNumberEqually(1);
		}
		
	}
	
	public void killRandomPerson()
	{
		double deathChance = 1;
		for(int i = 0; i < pop; i++)
		{
			if(this.randomChance(deathChance))
			{
				this.killPerson();
			}
		}
	}
	
	public void killPerson()
	{
		pop--;
		this.changeRandomJobNumberProportionally(-1);
	}
	
	public void changeRandomJobNumberProportionally(int delta) //3 jobs rn
	{
		ArrayList<String> jobs = new ArrayList<>();
		for(int f = 0; f < (100 * farmers/pop) +1; f++)
		{
			jobs.add("farmer");
		}
		for(int l = 0; l < (100 * lumberjacks/pop) +1; l++)
		{
			jobs.add("lumberjack");
		}
		for(int s = 0; s < (100 * stoneminers/pop) +1; s++)
		{
			jobs.add("stoneminer");
		}
		
		String selected = jobs.get(this.diceRoll(99));
		
		switch(selected)
		{
			case("farmer"):
				farmers += delta;
			break;
			case("lumberjack"):
				lumberjacks += delta;
			break;
			case("stoneminers"):
				stoneminers += delta;
			break;
		}
	}
	
	public void changeRandomJobNumberEqually(int delta) //3 jobs rn
	{
		int roll = this.diceRoll(3);
		
		if(roll == 1) { farmers += delta; }
		else if(roll == 2) { lumberjacks += delta; }
		else if(roll == 3){ stoneminers += delta; }
		
		System.out.println(roll);
	}
	
	public void runResources(Tile t)
	{
		foodGain = farmers * (10 + t.fertility);
		foodLoss = pop *  10;
		
		woodGain = lumberjacks * (5 * t.woodLevel);
		woodLoss = pop * 0.5;
		
		stoneGain = stoneminers * (5 * t.stoneLevel);
		stoneLoss = pop * 0.5;
	}
	
	public void displayInfo()//decrease y by 0.04 each line
	{
		StdDraw.textLeft(0.45, 0.9, "Village");
		StdDraw.textLeft(0.45, 0.86, "Population: " + pop);
		
		StdDraw.textLeft(0.45, 0.78, "Food: " + food);
		StdDraw.textLeft(0.45, 0.74, "Wood: " + wood);
		StdDraw.textLeft(0.45, 0.70, "Stone: " + stone);
		
		StdDraw.textLeft(0.57, 0.78, "Farmers: " + farmers);
		StdDraw.textLeft(0.57, 0.74, "Lumberjacks: " + lumberjacks);
		StdDraw.textLeft(0.57, 0.70, "Stone Miners: " + stoneminers);
		
		dm.scatterPlotArrayList(years, population, 0.67, 0.1, 0.9, 0.8, "Years", "Population");
	}
	
	public void toDisplay(double X, double Y)
	{
		StdDraw.setPenColor(204, 142, 0);
		StdDraw.filledRectangle(X, Y, 0.01, 0.02);
	}
	
	public boolean randomChance(double p)
	{
		Random rand = new Random();
		return(rand.nextInt(1000) < p*10);
	}
	
	public boolean minMaxRandomChance(double min, double max)
	{
		Random rand = new Random();
		int r = rand.nextInt(1000);
		return (r > min*10 && r < max*10);
	}
	
	public boolean getRandomBoolean(float p)
	{
		Random rand = new Random();
		return rand.nextFloat() < p;
	}
	
	public int diceRoll(int sides)
	{
		Random rand = new Random();
		return rand.nextInt(sides) + 1;
	}
	
	
}

//System.out.println((5 - Math.log(year+1)));
		/*
		for(int i = 0; i < pop; i++)//jobs
		{
			if((5 - Math.log(year+1)) < 0) //birth
			{
				if(this.randomChance(0.0027))
				{
					people.add(new Person(this.assignJob(people.get(i).job)));
				}
			}
			else
			{
				if((this.randomChance( (5 - Math.log(year+1)) )))
				{
					people.add(new Person(this.assignJob(people.get(i).job)));
				}
			}
			
			if(people.get(i).age > 40) //death
			{
				if(this.randomChance(people.get(i).age - 40))
				{
					//System.out.println(people.get(i).age);
					//people.remove(i);
				}
			}

			switch(people.get(i).job)
			{
				case("farmer"):
					foodGain = foodGain + 10 + 5 * t.fertility;
					break;
				case("lumberjack"):
					woodGain += 5 * t.woodLevel;
					break;
				case("stoneminer"):
					stoneGain += 5 * t.stoneLevel;
					break;
			}
					
		}
		*/
