package codegame;

/*
 * Problem 3 – Pattern Matching (100 Marks)
 All of us must have used regular expression for pattern matching. This problem is related to pattern matching.

 We have been given strings comprised of only numbers. For e.g. “3446225”

 We can use only two characters for regular expression, either “$” or “%”. “$” denotes that odd number of digits are present and “%” denotes that even number ( > 0) of digits are present.

 So a pattern of “3$2” will find a match in the example string.
 A pattern of “3$6” will not be matched.
 “3%6” will have a match.
 “3%5” will not have a match.

 Input
 First line number of test cases – N. Followed by test cases
 Each test case has two lines; 1st line is the pattern and 2nd line is string

 Output
 Case #1: Match found
 Case #2: Match found
 Case #3: Match not found

 Sample Input
 4
 3$5
 34322215446
 1%2
 44554333221
 %12
 1112222
 $1
 1112222

 Sample Output
 Case #1: Match not found
 Case #2: Match not found
 Case #3: Match found
 Case #4: Match found
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatternMatching {

	private static class TestCase {
		String pattern;
		String string;

		public TestCase(String pattern, String string) {
			this.pattern = pattern;
			this.string = string;
		}

	}

	public static void main(String[] args) {
		PatternMatching pm = new PatternMatching();
		List<TestCase> testCases = pm.readCode();

		for (int i = 0; i < testCases.size(); i++) {
			TestCase c = testCases.get(i);
			if (pm.isMatch(c)) {
				System.out.println("Case #" + (i + 1) + ":Match found");
			} else {
				System.out.println("Case #" + (i + 1) + ":Match not found");
			}
		}

	}

	private boolean isMatch(TestCase c) {

		int i = c.pattern.indexOf("$");
		if (i > -1) {
			String[] split = c.pattern.split("\\$");
			if (split.length == 2) {
				return isMatchOdd(split[0], split[1], c.string);
			} else {
				if (i == 0) {
					return isMatchOdd(null, split[0], c.string);
				} else {
					return isMatchOdd(split[0], null, c.string);
				}
			}
		} else {
			String[] split = c.pattern.split("#");
			i = c.pattern.indexOf("%");
			if (split.length == 2) {
				return isMatchEven(split[0], split[1], c.string);
			} else {
				if (i == 0) {
					return isMatchEven(null, split[0], c.string);
				} else {
					return isMatchEven(split[0], null, c.string);
				}
			}
		}

	}

	private boolean isMatchEven(String start, String end, String string) {
		int sI = 1;
		int eI = string.length();
		if (start != null && !start.isEmpty())
			sI = string.indexOf(start) + 1;
		if (end != null && !end.isEmpty())
			eI = string.lastIndexOf(end) + 1;
		if (sI < 1 || eI < 1 || sI >= eI) {
			return false;
		}
		if (eI > sI && ((eI - sI) % 2) == 1) {
			return true;
		}

		return isMatchEven(start, end, string.substring(sI))
				|| isMatchEven(start, end, string.substring(sI - 1, eI - 1));
	}

	private boolean isMatchOdd(String start, String end, String string) {
		int sI = 1;
		int eI = string.length();
		if (start != null && !start.isEmpty())
			sI = string.indexOf(start) + 1;
		if (end != null && !end.isEmpty())
			eI = string.lastIndexOf(end) + 1;
		if (sI < 1 || eI < 1 || sI >= eI) {
			return false;
		}
		if (eI > sI && ((eI - sI) % 2) == 0) {
			return true;
		}

		return isMatchOdd(start, end, string.substring(sI))
				|| isMatchOdd(start, end, string.substring(sI - 1, eI - 1));
	}

	private List<TestCase> readCode() {

		ArrayList<TestCase> list = new ArrayList<TestCase>();
		Scanner scanner = new Scanner(System.in);
		int numOfTestCases = scanner.nextInt();
		while (numOfTestCases > 0) {

			list.add(new TestCase(scanner.next(), scanner.next()));

			numOfTestCases--;
		}
		// list.add(new TestCase("3$5", "34322215446"));
		// list.add(new TestCase("1#2", "44554333221"));
		// list.add(new TestCase("#12", "1112222"));
		// list.add(new TestCase("$1", "1112222"));
		scanner.close();
		return list;
	}
}
