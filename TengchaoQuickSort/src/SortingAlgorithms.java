import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithms {

	private final static boolean PRINT_LOG = true;
	private final static int AMOUNT_OF_NUMBERS = 1000000;

	public static void main(String args[]) {
		// Random rnd = new Random();
		// int[] array1 = new int[AMOUNT_OF_NUMBERS];
		// for (int i = 0; i < AMOUNT_OF_NUMBERS; i++) {
		// array1[i] = rnd.nextInt();
		// }

		int[] array1 = { 2, 1, 9, 6, 4, 7, 2 };

		// int[] array2 = new int[array1.length];
		// System.arraycopy(array1, 0, array2, 0, array1.length);
		System.out.print("Start: ");
		printArray(array1);

		long startTime1 = System.currentTimeMillis();
		quickSort(array1, 0, array1.length - 1);
		long endTime1 = System.currentTimeMillis();
		long totalTime1 = endTime1 - startTime1;
		System.out.println(String.format("Total time: %s milliseconds",
				totalTime1));

		System.out.print("End: ");
		printArray(array1);

		// long startTime2 = System.currentTimeMillis();
		// Arrays.sort(array2);
		// long endTime2 = System.currentTimeMillis();
		// long totalTime2 = endTime2 - startTime2;
		// System.out.println(String.format("Total time: %s milliseconds",
		// totalTime2));

		// for (int i = 0; i < array1.length; i++) {
		// if (array1[i] != array2[i]) {
		// System.out.println(String.format("%s ~ %s", array1[i],
		// array2[i]));
		// }
		// }

	}

	private static void quickSort(int[] unsortedArray, int start_index,
			int end_index) {

		if (PRINT_LOG) {
			printArray(unsortedArray);

			System.out.println(String.format(" Start: %s, End: %s",
					start_index, end_index));
		}

		// when there are only two elements

		if (start_index + 1 == end_index) {

			if (unsortedArray[start_index] > unsortedArray[end_index]) {
				swap(unsortedArray, start_index, end_index);
			}

		}

		// when there are at least 3 elements

		else if (start_index + 1 < end_index) {

			// choose the middle element

			int chosen_index = (end_index + start_index) / 2;
			int chosen_number = unsortedArray[chosen_index];

			if (PRINT_LOG)

				System.out.println(String.format("The chosen number is %s.",
						chosen_number));

			// front and back indexes keep track of two pointers

			int front_index = start_index;
			int back_index = end_index;

			// proceed until front and back are next to each other

			while (front_index < back_index) {

				if (unsortedArray[front_index] <= chosen_number) {
					front_index++;
				}
				if (unsortedArray[back_index] >= chosen_number) {
					back_index--;
				}

				if (front_index < back_index
						&& unsortedArray[front_index] > chosen_number
						&& unsortedArray[back_index] < chosen_number) {
					swap(unsortedArray, front_index, back_index);
				}

			}

			// put the chosen number at the right place

			if (chosen_index < front_index) {
				swap(unsortedArray, front_index, chosen_index);
				front_index--;
			} else if (chosen_index > back_index) {
				swap(unsortedArray, back_index, chosen_index);
				back_index++;
			}

			// sort only when there are more than 1 element

			if (front_index > start_index) {
				quickSort(unsortedArray, start_index, front_index);

			}

			// sort only when there are more than 1 element

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

	private static void printArray(int array[]) {
		for (int num : array) {
			System.out.print(num);
		}
		System.out.println();
	}

}
