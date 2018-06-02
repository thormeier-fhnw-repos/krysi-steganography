package ConsoleUI;

import java.util.Scanner;

/**
 * Generic console UI to input and output things on the CLI
 */
public class ConsoleUI extends Writer
{
    /**
     * Used for input
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Greet the user
     */
    public void greet()
    {
        hr();
        print("Steganographer");
        hr();
        ln();
    }

    /**
     * Tell the user that he first needs to choose an image to hide
     */
    public void sayInputFile()
    {
        print("You need to first select an image to hide");
    }

    /**
     * Choose the image the other one will be hidden in
     */
    public void sayTargetFile()
    {
        print("Now select the image the other one will be hidden in");
    }

    /**
     * Supply a list of choices to the user and ask to choose one of them
     * @param choices List of choices
     * @return The choice made (i.e. the String)
     */
    public String getChoice(String[] choices)
    {
        print("Please select one of the following:");
        int i = 0;
        for (String choice : choices) {
            print("  [" + i + "] " + choice);
            i++;
        }

        int in;
        while (true) {
            print("Your choice:");

            in = scanner.nextInt();

            if (in >= 0 && in < choices.length) {
                break;
            }

            print("Your choice was not valid...");
        }

        return choices[in];
    }
}
