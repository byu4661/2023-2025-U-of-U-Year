package assign11;
import java.util.Scanner;
/**
 * @author Brian Yu
 * @version JavaSE-21
 */
public class ImageProcessor {
	/**
	 * Procides a menu and uses the command-line arguments fox.png and fox_redBlueSwap.png. 
	 * User input is then used to do different filters on the image, and can stack filters.
	 */
	
	/**
	 * This displays options for the user to pick from integers 1-5.
	 */
	private static void displayMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("  1 -- Swap red and blue");
        System.out.println("  2 -- Convert to black and white");
        System.out.println("  3 -- Rotate clockwise");
        System.out.println("  4 -- Mirror Image");
        System.out.println("  5 -- Write image to file and quit");
    }
	public static void main(String[] args) {
		if (args.length < 2) {
		    System.out.println("Usage: java ImageProcessor <input_image> <output_image>");
		    System.exit(1);
		}
		String inputFile = args[0];
		String outputFile = args[1];
		Image image = new Image(inputFile);
    	displayMenu();

		Scanner userInput = new Scanner(System.in);
		
		int option = userInput.nextInt();
		
	    while(userInput.hasNext()) {
	    	while (!userInput.hasNextInt()) {
				System.out.println("Invalid input, enter an integer between 1 - 5.");
				userInput.next();
			}
	    	displayMenu();
	    	System.out.println("Reading image from: " + inputFile);
		    System.out.println("Saving filtered image to: " + outputFile);
	    	if(userInput.hasNextInt())
	    		option = userInput.nextInt();
			switch (option) {
			case 1:
				image.redBlueSwapFilter();
				image.writeImage(outputFile);
				break;
			case 2:
				image.blackAndWhiteFilter();
				image.writeImage(outputFile);
				break;
			case 3:
				image.rotateClockwiseFilter();
				image.writeImage(outputFile);
				break;
			case 4:
				image.customFilter();
				image.writeImage(outputFile);
				break;
			case 5:
				image.writeImage(outputFile);
				userInput.close();
			default:
				System.out.println("Invalid input. Please try again");
				
			}
	    	
	    }
	    userInput.close(); 
	}
}
