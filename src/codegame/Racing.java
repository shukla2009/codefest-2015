package codegame;

/*
 * Problem 4 - Racing Season (100 Marks)
 Synerzip “Need For Speed” Season has started!! There are teams participating in this season and the rules for the season are as follows

 ·      Every team will have a few players in it
 ·      There will be X number of races in this season
 ·      In every race, multiple participants from each team can participate
 ·      The top 6 players in each race will be given scores of 10, 8, 6, 4, 2, 1
 ·      The score gained in a race will be added to the team score
 ·      After every race, the players might change the team. So PlayerA is in TeamA during Race #1 but after that he can move to TeamB
 ·      At the end of the season, the team with highest score wins the season
 ·      If two players or teams have same scores then we use alphabetic sorting (ascending)

 The input to this problem will be results of each race and the output desired is players standing and team standings after the season (along with the points earned)

 Input
 First line is number of races in the season - N
 Next 6 lines are ranking, player names and team names for Race #1
 Next 6 lines are ranking, player names and team names for Race #2
 Next 6 lines are ranking, player names and team names for Race #3
 …
 …

 Output
 Drivers Standing
 PlayerA Points
 PlayerB Points
 PlayerC Points

 Teams Standing
 TeamA Points
 TeamB Points
 TeamC Points

 Sample Input
 2
 1 AA Team1
 2 BB Team2
 3 CC Team3
 4 DD Team1
 5 EE Team1
 6 FF Team3
 1 DD Team1
 2 AA Team2
 3 CC Team4
 4 BB Team2
 5 EE Team1
 6 FF Team3

 Sample Output

 Drivers Standing
 AA 18
 DD 14
 BB 12
 CC 12
 EE 4
 FF 2

 Teams Standing
 Team1 28
 Team2 20
 Team3 8
 Team4 6
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by synerzip on 8/7/15.
 */

class ValueComparator implements Comparator<String> {

	private Map<String, Integer> base;

	public ValueComparator(Map<String, Integer> map) {
		base = map;
	}

	@Override
	public int compare(String a, String b) {
		int p1 = base.get(a);
		int p2 = base.get(b);

		if (p1 > p2)
			return -1;
		else if (p1 < p2)
			return 1;
		else
			return a.compareTo(b);
	}
}

public class Racing {

	private Map<Integer, Integer> rankPointsMap = new TreeMap<Integer, Integer>();
	private Map<String, Integer> playerPoints = new HashMap<String, Integer>();
	private Map<String, Integer> teamPoints = new HashMap<String, Integer>();

	public Racing() {
		rankPointsMap.put(1, 10);
		rankPointsMap.put(2, 8);
		rankPointsMap.put(3, 6);
		rankPointsMap.put(4, 4);
		rankPointsMap.put(5, 2);
		rankPointsMap.put(6, 1);
	}

	public static void main(String[] args) {

		int races = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("\nEnter Num Races\n");
		String arg = in.nextLine();
		races = Integer.parseInt(arg);

		int inputLines = races * 6;
		List<String> inputs = new ArrayList<String>(inputLines);

		System.out.println("Enter input, Required lines " + inputLines);

		while (--inputLines >= 0) {
			String inputArg = in.nextLine();
			inputs.add(inputArg.trim());
		}

		Racing racing = new Racing();

		for (String s : inputs) {
			racing.parseLine(s);
		}

		Map<String, Integer> sortedPlayerMap = new TreeMap<String, Integer>(
				new ValueComparator(racing.playerPoints));
		Map<String, Integer> sortedTeamMap = new TreeMap<String, Integer>(
				new ValueComparator(racing.teamPoints));

		sortedPlayerMap.putAll(racing.playerPoints);
		sortedTeamMap.putAll(racing.teamPoints);

		System.out.println("\n\n");
		System.out.println("Driver Standing");

		for (Map.Entry<String, Integer> entry : sortedPlayerMap.entrySet()) {
			String player = entry.getKey();
			int points = entry.getValue();
			System.out.println(player + "  " + points);
		}

		System.out.println("\n\n");
		System.out.println("Team Standing");

		for (Map.Entry<String, Integer> entry : sortedTeamMap.entrySet()) {
			String team = entry.getKey();
			int points = entry.getValue();
			System.out.println(team + "  " + points);
		}

	}

	void parseLine(String line) {

		final String[] split = line.split(" ");
		if (split.length == 3) {

			int pos = Integer.parseInt(split[0]);
			int points = rankPointsMap.get(pos);
			String player = split[1];
			String team = split[2];

			if (playerPoints.containsKey(player)) {
				Integer previousPoints = playerPoints.get(player);
				playerPoints.put(player, previousPoints + points);
			} else {
				playerPoints.put(player, points);
			}

			if (teamPoints.containsKey(team)) {
				Integer previousPoints = teamPoints.get(team);
				teamPoints.put(team, previousPoints + points);
			} else {
				teamPoints.put(team, points);
			}
		}
	}

}
