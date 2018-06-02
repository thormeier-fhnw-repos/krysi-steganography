import ConsoleUI.ConsoleUI;
import Filesystem.Loader;
import Filesystem.Writer;
import ImageHider.ImageHider;

/**
 * Main class for steganography presentation demo: Hiding
 */
public class HideImage
{
    /**
     * Main methods
     * @param args Console args
     */
    public static void main(String args[])
    {
        Loader loader = new Loader("resources/in");
        Writer writer = new Writer("resources/out");
        ImageHider imageHider = new ImageHider();
        ConsoleUI ui = new ConsoleUI();

        String[] files = loader.getFiles();

        ui.greet();

        // Get input files
        ui.sayInputFile();
        String inputFile = ui.getChoice(files);

        ui.sayTargetFile();
        String hideInFile = ui.getChoice(files);

        // Figure out where the image should be saved
        String outputFileName = ui.ask("a filename (without file ending)");

        // Hide image and save it
        writer.writeFile(
            imageHider.hideImage(loader.loadImage(inputFile), loader.loadImage(hideInFile)),
            outputFileName + ".png"
        );
    }
}
