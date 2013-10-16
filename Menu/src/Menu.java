import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Menu {
	public static void main(String args[]) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = br.read();
		int n = br.read();
		int m = br.read();

		while (!(0 == k && 0 == n && 0 == m)) {
			int[][] dishes = new int[n][2];
			for (int i = 0; i < n; i++) {
				int c = br.read();
				int v = br.read();
				dishes[i][0] = c;
				dishes[i][1] = v;
			}
			Pair max = max(k, dishes, m, 0, 0);
			System.out.println(max);
		}
	}

	private static Pair max(int days, int[][] dishes, int budget,
			int previous_dish, int previous_previous_dish) {

		if (days < 1 || budget < 1)
			return new Pair();

		double max_value = 0;
		int max_dish = 0;
		ArrayList<Integer> max_dish_list = new ArrayList<Integer>();

		for (int i = 0; i < dishes.length; i++) {
			int cost = dishes[i][0];
			double value = dishes[i][1];
			if (i == previous_dish && i == previous_previous_dish) {
				value = 0;
			} else if (i == previous_dish) {
				value = value * 0.5;
			}

			Pair max = max(days - 1, dishes, budget - cost, i, previous_dish);
			double current_value = value + max.getMaxValue();
			if (current_value > max_value) {
				max_value = current_value;
				max_dish = i;
				max_dish_list = max.getMaxDishList();
			}
		}
		max_dish_list.add(max_dish);
		return new Pair(max_value, max_dish_list);

	}
}

class Pair {
	private double max_value_;
	private ArrayList<Integer> max_dish_list_;

	public Pair(double max_value, ArrayList<Integer> max_dish_list) {
		max_value_ = max_value;
		max_dish_list_ = max_dish_list;
	}

	public Pair() {
		max_value_ = 0.0;
		max_dish_list_ = new ArrayList<Integer>();
	}

	public double getMaxValue() {
		return max_value_;
	}

	public ArrayList<Integer> getMaxDishList() {
		return max_dish_list_;
	}
}