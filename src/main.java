import ConsoleUI.ConsoleUI;
import Filesystem.Loader;

/**
 * Main class for steganography presentation demo
 */
public class main
{
    /**
     * Main methods
     * @param args Console args
     */
    public static void main(String args[])
    {
        Loader loader = new Loader("resources/in");
        ConsoleUI ui = new ConsoleUI();

        String[] files = loader.getFiles();

        ui.greet();
        ui.sayInputFile();
        String inputFile = ui.getChoice(files);

        ui.sayTargetFile();
        String hideInFile = ui.getChoice(files);

    }
}
