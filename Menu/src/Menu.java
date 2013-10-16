import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

class Menu {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {

			String[] s_array = br.readLine().split("\\s++");

			int days_left = Integer.parseInt(s_array[0]);
			int n = Integer.parseInt(s_array[1]);
			int budget_left = Integer.parseInt(s_array[2]);

			if (days_left == 0 && n == 0 && budget_left == 0)
				break;

			int[][] dishes = new int[n][2];
			for (int i = 0; i < n; i++) {
				s_array = br.readLine().split(" ");
				dishes[i][0] = Integer.parseInt(s_array[0]);
				dishes[i][1] = Integer.parseInt(s_array[1]);
			}

			// [days_left][budget_left][previous_day][previous_previous_day]
			Pair[][][][] answer_table = new Pair[days_left + 1][budget_left + 1][n + 1][n + 1];

			Pair max = max(days_left, dishes, budget_left, 0, 0, answer_table);
			if (max.isCompleted()) {
				System.out.println(max.getMaxValue());
				ArrayList<Integer> max_dish_list = max.getMaxDishList();
				Iterator<Integer> it = max_dish_list.iterator();
				while (it.hasNext()) {
					System.out.print(it.next() + " ");
				}
				System.out.println();
			} else {
				System.out.println("0.0");
				System.out.println();

			}
		}
	}

	private static Pair max(int days_left, int[][] dishes, int budget_left,
			int previous_dish, int previous_previous_dish,
			Pair[][][][] answer_table) {

		if (days_left > 0 && budget_left < 1)
			return new Pair(false);

		double max_value = 0;
		int max_dish = 0;
		int min_cost = 1000;
		ArrayList<Integer> max_dish_list = new ArrayList<Integer>();

		for (int i = 0; i < dishes.length; i++) {
			int cost = dishes[i][0];
			double value = dishes[i][1];

			int current_dish = i + 1;
			if (current_dish == previous_dish
					&& current_dish == previous_previous_dish) {
				value = 0;
			} else if (current_dish == previous_dish) {
				value = value * 0.5;
			}
			if (days_left == 1) {
				if (value > max_value
						&& cost < budget_left
						|| (value == max_value && cost < budget_left && cost < min_cost)) {
					min_cost = cost;
					max_value = value;
					max_dish = current_dish;
				}

			} else {
				if (days_left - 1 > 0 && budget_left - cost > 0) {
					Pair max;
					if (answer_table[days_left - 1][budget_left - cost][current_dish][previous_dish] != null) {
						max = answer_table[days_left - 1][budget_left - cost][current_dish][previous_dish];
					} else {
						max = max(days_left - 1, dishes, budget_left - cost,
								current_dish, previous_dish, answer_table);
						answer_table[days_left - 1][budget_left - cost][current_dish][previous_dish] = max;
					}
					if (max.isCompleted()) {
						double current_value = value + max.getMaxValue();
						int current_cost = cost + max.getMinCost();
						if (current_value > max_value
								|| (current_value == max_value && current_cost < min_cost)) {
							min_cost = current_cost;
							max_value = current_value;
							max_dish = current_dish;
							max_dish_list = max.getMaxDishList();
						}
					}
				}
			}

		}
		if (max_dish != 0 && max_value != 0 && min_cost != 1000) {
			ArrayList<Integer> new_max_dish_list = new ArrayList<Integer>(
					max_dish_list);
			new_max_dish_list.add(max_dish);
			return new Pair(max_value, min_cost, new_max_dish_list, true);
		} else {
			return new Pair(false);
		}
	}
}

class Pair {
	private double max_value_;
	private int min_cost_;
	private ArrayList<Integer> max_dish_list_;
	private boolean completed_;

	public Pair(double max_value, int min_cost,
			ArrayList<Integer> max_dish_list, boolean completed) {
		max_value_ = max_value;
		min_cost_ = min_cost;
		max_dish_list_ = max_dish_list;
		completed_ = completed;
	}

	public Pair(boolean completed) {
		max_value_ = 0.0;
		min_cost_ = 1000;
		max_dish_list_ = new ArrayList<Integer>();
		completed_ = completed;
	}

	public double getMaxValue() {
		return max_value_;
	}

	public ArrayList<Integer> getMaxDishList() {
		return max_dish_list_;
	}

	public boolean isCompleted() {
		return completed_;
	}

	public int getMinCost() {
		return min_cost_;
	}

}