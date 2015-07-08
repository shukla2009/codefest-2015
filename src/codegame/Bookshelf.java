package codegame;

/*
 * Problem 1 - Bookshelf (100 Marks)
 I have recently moved to a new house and have to setup my bookshelf. Now I have this habit of arranging the books in the shelf in ascending order of book’s height.

 For e.g. If I have got books with height of 3, 4 and 6 inches, I will place 3 on left side then 4 on right to it and 5 on right to 4.

 Now there are few rules, which I have to follow while setting up a shelf
 I have to pick the books from the box in the order they are placed i.e. the top one first then the 2nd one and so on and so forth
 Once I pick a book, I can either place it in the shelf or keep it aside. Once I keep it aside I can’t put this book in the shelf
 A book can either be placed on leftmost side of the shelf or rightmost side of the shelf if a book is already not present there
 A book can be placed adjacent to an existing book on left side or right side if space is available. If books are place in this [1 2 3….. ] then I can’t place a book on left side of book 3.
 The shelf has unlimited capacity i.e. Unlimited number of books can be placed on it.
 No two books will have same height
 Input
 First line is number of test cases (N) then follows N test cases

 Each test case has one row specifying the order of the books in the box (with each book represented using its height). The first book is the topmost book in the box.
 Output
 For each test case print the maximum number of books that can be stacked in the shelf
 Case #1: a
 Case #2: b
 Case #3: c

 Sample Input
 2
 2 1 3 4 6 7
 1 2 5 3 4 8
 Sample Output
 Case #1: 5
 Case #2: 5
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Bookshelf {

	public static void main(String[] args) {
		Bookshelf bs = new Bookshelf();
		List<TestCase> books = bs.readCode();
		for (int i = 0; i < books.size(); i++) {
			TestCase c = books.get(i);
			System.out.println("Case #" + (i + 1) + ":" + bs.getMaxBook(c));
		}

	}

	private int getMaxBook(TestCase c) {
		int[] books = c.books;

		Stack<Integer> leftShelf = new Stack<>();
		Stack<Integer> rightShelf = new Stack<>();
		placebook(c, books, 0, leftShelf, rightShelf);

		return c.count;
	}

	private void placebook(TestCase c, int[] books, int index,
			Stack<Integer> leftShelf, Stack<Integer> rightShelf) {
		if (index == books.length) {
			int len = leftShelf.size() + rightShelf.size();
			c.count = c.count < len ? len : c.count;
			return;
		}
		if (canPlaceToleft(books, index, leftShelf, rightShelf)) {
			leftShelf.push(books[index]);
			placebook(c, books, index + 1, leftShelf, rightShelf);
			leftShelf.pop();
		}
		if (canPlaceToright(books, index, leftShelf, rightShelf)) {
			rightShelf.push(books[index]);
			placebook(c, books, index + 1, leftShelf, rightShelf);
			rightShelf.pop();
		}

		placebook(c, books, index + 1, leftShelf, rightShelf);

	}

	private boolean canPlaceToleft(int[] books, int index,
			Stack<Integer> leftShelf, Stack<Integer> rightShelf) {
		if (rightShelf.isEmpty()) {
			if (leftShelf.isEmpty()) {
				return true;
			} else if (leftShelf.peek() < books[index]) {
				return true;
			}
			return false;

		} else {
			if (leftShelf.isEmpty()) {
				if (rightShelf.peek() > books[index]) {
					return true;
				}
				return false;
			} else if (rightShelf.peek() > books[index]
					&& leftShelf.peek() < books[index]) {
				return true;
			}
			return false;
		}
	}

	private boolean canPlaceToright(int[] books, int index,
			Stack<Integer> leftShelf, Stack<Integer> rightShelf) {
		if (leftShelf.isEmpty()) {
			if (rightShelf.isEmpty()) {
				return true;
			} else if (rightShelf.peek() > books[index]) {
				return true;
			}
			return false;

		} else {
			if (rightShelf.isEmpty()) {
				if (leftShelf.peek() < books[index]) {
					return true;
				}
				return false;
			} else if (rightShelf.peek() > books[index]
					&& leftShelf.peek() < books[index]) {
				return true;
			}
			return false;
		}
	}

	private static class TestCase {
		int[] books;
		int count;

		public TestCase(String array) {
			String[] split = array.split(" ");
			books = new int[split.length];
			for (int i = 0; i < split.length; i++) {
				String string = split[i];
				books[i] = Integer.parseInt(string);
			}
		}

	}

	private List<TestCase> readCode() {

		ArrayList<TestCase> list = new ArrayList<TestCase>();
		Scanner scanner = new Scanner(System.in);
		int numOfTestCases = scanner.nextInt();
		while (numOfTestCases > 0) {

			list.add(new TestCase(scanner.next()));

			numOfTestCases--;
		}
		// list.add(new TestCase("2 1 3 4 6 7"));
		// list.add(new TestCase("1 2 5 3 4 8"));
		scanner.close();
		return list;
	}
}
