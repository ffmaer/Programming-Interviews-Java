public class SortingAlgorithms {

	private final static boolean PRINT_LOG = false;

	public static void main(String args[]) {
		int[] array = { 2, 0, 2, 8, 6, 4, 1, 5, 9 };
		for (int num : array) {
			System.out.print(num);
		}
		System.out.println();
		quickSort(array, 0, array.length - 1);
		System.out.print("The end result: ");
		for (int num : array) {
			System.out.print(num);
		}
		System.out.println();
	}

	private static void quickSort(int[] unsortedArray, int start_index,
			int end_index) {
		if (PRINT_LOG) {

			for (int num : unsortedArray) {
				System.out.print(num);
			}
			System.out.println();

			System.out.println(String.format(" Start: %s, End: %s",
					start_index, end_index));
		}
		if (start_index + 1 == end_index) {
			if (unsortedArray[start_index] > unsortedArray[end_index]) {
				swap(unsortedArray, start_index, end_index);
			}
		} else if (start_index + 1 < end_index) {

			int chosen_index = (end_index + start_index) / 2;
			int chosen_number = unsortedArray[chosen_index];
			if (PRINT_LOG)

				System.out.println(String.format("The chosen number is %s.",
						chosen_number));
			int front_index = start_index;
			int back_index = end_index;

			while (front_index + 1 != back_index) {

				while (unsortedArray[front_index] <= chosen_number
						&& front_index + 1 != back_index) {
					front_index++;
				}

				while (unsortedArray[back_index] >= chosen_number
						&& front_index + 1 != back_index) {
					back_index--;
				}

				if (front_index + 1 != back_index) {
					swap(unsortedArray, front_index, back_index);
				}

			}

			if (unsortedArray[front_index] > unsortedArray[back_index]) {
				swap(unsortedArray, front_index, back_index);
			} else {
				if (chosen_index < front_index) {
					swap(unsortedArray, front_index, chosen_index);
					front_index--;
				} else if (chosen_index > back_index) {
					swap(unsortedArray, back_index, chosen_index);
					back_index++;
				}
			}

			if (front_index > start_index) {
				quickSort(unsortedArray, start_index, front_index);

			}
			if (back_index < end_index) {
				quickSort(unsortedArray, back_index, end_index);

			}
		}
	}

	private static void swap(int array[], int index_a, int index_b) {

		if (PRINT_LOG)
			System.out.println(String.format("%s and %s are swaped.",
					array[index_a], array[index_b]));
		int temp = array[index_a];
		array[index_a] = array[index_b];
		array[index_b] = temp;
	}
}
