package application;

import java.io.IOException;
import java.util.Random;


public class Arrays_Enemy_Teams extends enemyTeam {

	int challengeRating = 6; // Hardcoded Challenge Rating for now
	int numOfTeams = 15; // Make 15 Random teams. (Can change if we want)
	Random rand = new Random(); // Random for selection
	enemyTeam[] teams; // Array to store the teams
	
	public Arrays_Enemy_Teams(int pos, int size, int numOfTeams) {
		super(pos, size);
		// TODO Auto-generated constructor stub
		this.positions = new int[pos];
		this.team = new Enemies[size];
		this.teams = new enemyTeam[numOfTeams];
	}
		
	void addToArray(int index, enemyTeam enemyTeam) {
			
		this.teams[index] = enemyTeam;
	}
		
	int getCR() {
		return challengeRating;
	}
		
	int getNumOfTeams() {
		return numOfTeams;
	}
	
	Object getTeam(int index) { // Get Chosen Team out of array
		return this.teams[index];
	}
	
public Enemies[] createEnemies() throws IOException {
	
	// Create Instances of Enemies
	goblinScout gS = new goblinScout(java.util.Optional.empty());
	goblinAxeman gA = new goblinAxeman(java.util.Optional.empty());
	goblinArcher gAr = new goblinArcher(java.util.Optional.empty());
	goblinShaman gSh = new goblinShaman(java.util.Optional.empty());
	skeletonSwordsman sSw = new skeletonSwordsman(java.util.Optional.empty());
	skeletonCrossbowman sCb = new skeletonCrossbowman(java.util.Optional.empty());
	skeletonDefender sD = new skeletonDefender(java.util.Optional.empty());
	necromancer N = new necromancer(java.util.Optional.empty());
	//bodyPile bP = new bodyPile(java.util.Optional.empty());
	giantPummeler gPu = new giantPummeler(java.util.Optional.empty());
	giantSlammer gSl = new giantSlammer(java.util.Optional.empty());
	zombiePeasant zP = new zombiePeasant(java.util.Optional.empty());
	zombieKnight zK = new zombieKnight(java.util.Optional.empty());
	minotaur M = new minotaur(java.util.Optional.empty());
	boar B = new boar(java.util.Optional.empty());

	// Array to store them in and pull from
	Enemies[] enemies = new Enemies[15]; // We can change the size as/if we make more enemies

	// Put Them In the Array
	enemies[0] = gS;
	enemies[1] = gA;
	enemies[2] = gAr;
	enemies[3] = gSh;
	enemies[4] = sSw;
	enemies[5] = sCb;
	enemies[6] = sD;
	enemies[7] = N;
	enemies[8] = gPu;
	enemies[9] = gSl;
	enemies[10] = zP;
	enemies[11] = zK;
	enemies[12] = M;
	enemies[13] = B;
	//enemies[8] = bP;
	
	return enemies;
	
}
	
public enemyTeam createTeams(int challengeRating) throws IOException {
		
		Random rand = new Random(); // Random for selection
		enemyTeam teamComp = new enemyTeam(4, 4);
		
		
		int nextIndex = 0; // Counter to Check if the positions are full
		while (/*teamComp.getTotalChallenge() < challengeRating &&   Can uncomment this to customize difficulty*/ teamComp.getPosFull(3) && nextIndex < 4) { // Check if teams challenge level exceeds
			Enemies[] enemies = createEnemies();
			int rand1 = rand.nextInt(14);												  				    // current max or team is full
			Enemies tempEnemy = enemies[rand1];
			
			teamComp.addToTeam(nextIndex, tempEnemy); // Add the random enemy
			nextIndex++;
		}
		
		return teamComp;
	
	}

	
	public Arrays_Enemy_Teams createSelection() throws IOException {
		
		Arrays_Enemy_Teams enemyTeamArray = new Arrays_Enemy_Teams(4, 4, 15);
		int i = 0;
		
		while (i < enemyTeamArray.getNumOfTeams()) {
			enemyTeam tempTeamComp = createTeams(enemyTeamArray.getCR());
			enemyTeamArray.addToArray(i, tempTeamComp);
			i++;
		}
		
		return enemyTeamArray;
	}
	
}
