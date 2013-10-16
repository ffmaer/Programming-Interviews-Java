import java.util.Scanner;
import java.util.Stack;

public class Transformer {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			String s = sc.nextLine();
			convert(s);
		}
	}

	private static void convert(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
				stack.push(c);
			} else if (c == ')') {
				System.out.print(stack.pop());
			} else if (c == '(') {
				// do nothing
			} else {
				System.out.print(c);
			}
		}
		System.out.println();
	}
}
