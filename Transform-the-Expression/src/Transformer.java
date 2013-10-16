import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Transformer {
	public static void main(String args[]) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			String s = br.readLine();
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
				continue;
			} else {
				System.out.print(c);
			}
		}
		System.out.println();
	}
}
