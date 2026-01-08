
import java.util.Scanner;
public class Last10 {
	
	public static void main(String[] args) {
		int[] list = new int[10];
		//Allows for the user to input numbers for an array
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Input a number for an array. ");
		int input = keyboard.nextInt();
		System.out.println(input);
		//Sets the initial values of index and oldest
		int index = 0;
		int oldest = 0;
		list[index] = input;
		index++;
		while (input != -1) {
			System.out.println("Input a new number.");
			input = keyboard.nextInt();
			System.out.println(input);
			//If the input is not -1, then it stores the input at the end of the array
			if (input != -1) {
				list[index] = input;
				//oldest tracks the index after index to print the oldest value first
				oldest = index + 1;
				index++;
				//%= ensures that the array does not go past 10 indexes
				index %= 10;
			}
		}
		//If the input is -1, then it closes the input 
		keyboard.close();
		System.out.println("Here is your array.");
		for (int counter = 0; counter <= 9; counter++) {
			//printing the array from the oldest given value to the newest
			System.out.print(list[(oldest + counter) % 10] + " ");
		}
	}
}