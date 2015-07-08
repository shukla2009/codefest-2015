package codegame;

/*
 * Problem 2 – Leading Digit (100 Marks)
 This is a very simple problem.  You have the find the lead first digit of B^N (B raised to N). For e.g. 2^4 = 16 so leading first digit is 1.
 Input
 First line number of test cases – N. Followed by test cases
 Each test case has a line with two numbers B N
 Output
 Case #1: a
 Case #2: b
 Case #3: c
 Sample Input
 4
 2 3
 4 2
 4 4
 5 3
 Sample Output
 Case #1: 8
 Case #2: 1
 Case #3: 2
 Case #4: 1
 */

import java.util.ArrayList;

import java.util.Scanner;

public class LeadingDigit {

	private long base = 0L;
	private long power = 0L;

	public LeadingDigit(long base, long power) {
		this.base = base;
		this.power = power;

	}

	public short getFirstDigit() {

		double n = power * Math.log10(base);

		double floor = Math.floor(n);

		Double approx = Math.pow(10, n - floor);

		return (short) (Math.round(10 * approx) / 10);
	}

	public static void main(String[] args) {

		ArrayList<LeadingDigit> list = new ArrayList<LeadingDigit>();
		Scanner scanner = new Scanner(System.in);
		int numOfTestCases = scanner.nextInt();

		while (numOfTestCases > 0) {
			list.add(new LeadingDigit(new Long(scanner.next()), new Long(
					scanner.next())));
			numOfTestCases--;
		}
		scanner.close();

		for (LeadingDigit obj : list) {
			int counter = 1;
			System.out.println("Case #" + counter + ": " + obj.getFirstDigit());
		}

	}

}
