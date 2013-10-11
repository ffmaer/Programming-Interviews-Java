import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithms {

	private final static boolean PRINT_LOG = false;
	private final static int AMOUNT_OF_NUMBERS = 100;

	public static void main(String args[]) {
		Random rnd = new Random();
		int[] array1 = new int[AMOUNT_OF_NUMBERS];
		for (int i = 0; i < AMOUNT_OF_NUMBERS; i++) {
			array1[i] = rnd.nextInt();
		}

		// int[] array1 = { 2, 1, 9, 6, 4, 7, 2 };

		int[] array2 = new int[array1.length];
		System.arraycopy(array1, 0, array2, 0, array1.length);
		// System.out.print("Start: ");
		// printArray(array1);

		long startTime1 = System.currentTimeMillis();
		quickSort(array1);
		long endTime1 = System.currentTimeMillis();
		long totalTime1 = endTime1 - startTime1;
		System.out.println(String.format("Tengchao time: %s milliseconds",
				totalTime1));

		// System.out.print("End: ");
		// printArray(array1);

		long startTime2 = System.currentTimeMillis();
		Arrays.sort(array2);
		long endTime2 = System.currentTimeMillis();
		long totalTime2 = endTime2 - startTime2;
		System.out.println(String.format("Oracle time: %s milliseconds",
				totalTime2));

		for (int i = 0; i < array1.length; i++) {
			if (array1[i] != array2[i]) {
				System.out.println(String.format("%s ~ %s", array1[i],
						array2[i]));
			}
		}

	}

	private static void quickSort(int[] unsortedArray) {
		quickSort(unsortedArray, 0, unsortedArray.length - 1);

	}

	private static void quickSort(int[] unsortedArray, int start_index,
			int end_index) {

		// nothing to sort
		if (end_index - start_index + 1 < 2)
			return;

		if (PRINT_LOG) {
			printArray(unsortedArray);

			System.out.println(String.format(" Start: %s, End: %s",
					start_index, end_index));
		}

		// when there are at least 3 elements

		// choose the middle element

		int pivot_index = (end_index + start_index) / 2;
		int pivot = unsortedArray[pivot_index];

		if (PRINT_LOG)

			System.out
					.println(String.format("The chosen number is %s.", pivot));

		// front and back indexes keep track of two pointers

		int front_index = start_index;
		int back_index = end_index;// out of bound

		while (front_index < back_index) {
			if (unsortedArray[front_index] > pivot) {
				// looking for an element that is smaller than the pivot
				while (back_index > front_index) {
					if (unsortedArray[back_index] <= pivot) {
						swap(unsortedArray, front_index, back_index);
						if (back_index == pivot_index) {
							pivot_index = front_index;
						}
						front_index++;
						back_index--;
						break;
					} else {
						back_index--;
					}
				}
			} else {
				front_index++;
			}
		}
		if (unsortedArray[front_index] > pivot) {
			swap(unsortedArray, pivot_index, front_index - 1);
		} else {
			swap(unsortedArray, pivot_index, front_index);
		}

		if (front_index > start_index) {
			quickSort(unsortedArray, start_index, front_index - 1);
		}
		if (back_index < end_index) {
			quickSort(unsortedArray, back_index, end_index);

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
