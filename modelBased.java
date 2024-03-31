package Work;
import javax.swing.JOptionPane;

public class modelBased {

	/*The main method uses "JOptionPane" to prompt a user to enter a binary number
	 *Next, it executes the twosComplement method by taking that input, converting it
	 *into twos complement form, and then giving the output to the user
	 */

	public static void main(String[] args) {
		// Ask user to give an input for a binary number
		String input = JOptionPane.showInputDialog(null, "Enter Binary Number:");
		// Compute two's complement of the inputted binary number
		String twosComplement = twosComplement(input);
		// Display two's complelment of the inputted binary number
		JOptionPane.showMessageDialog(null, "Twos Complement: " + twosComplement);
	}

	private static String twosComplement(String binary) {
		String result = "";
		char state = 'A'; // Initial state

		// Loop that ititerates over the binary number right to left
		for (int i = binary.length() - 1; i >= 0; i--) {
			char bit = binary.charAt(i);

			// Use mealy machine technique, find the state and process the bit
			switch (state) {
			case 'A':
				// If the current bit is '1', append '1' to the result and go to state 'B'
				if (bit == '1') {
					result = '1' + result;
					// Move to the next state
					state = 'B';

					// If the current bit is '0', append '0' to the result
				} else {
					result = '0' + result;
				}
				break;

				// If the current state is 'B'
			case 'B':
				// If the current bit is '1', append '0' to the result
				if (bit == '1') {
					result = '0' + result;

				// If the current bit is '0', append '1' to the result
				} else {
					result = '1' + result;
				}
				break;
			}
		}

		return result;
	}
}
