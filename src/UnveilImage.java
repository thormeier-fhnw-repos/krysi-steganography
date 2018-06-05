import ConsoleUI.ConsoleUI;
import Filesystem.Loader;
import Filesystem.Writer;
import ImageHider.ImageUnveiler;

/**
 * Main class for steganography presentation demo: Showing
 */
public class UnveilImage
{
    /**
     * Input path
     */
    private static String inPath = "resources/in";

    /**
     * Output path
     */
    private static String outPath = "resources/out";

    /**
     * Main methods
     * @param args Console args
     */
    public static void main(String args[])
    {
        Loader loader = new Loader(inPath);
        Writer writer = new Writer(outPath);
        ConsoleUI ui = new ConsoleUI();

        String[] files = loader.getFiles();

        ui.greet();

        // Get input file
        ui.sayUnveilFile();
        String inputFile = ui.getChoice(files);

        // Figure out where the image should be saved
        String outputFileName = ui.ask("a filename (without file ending)");

        // Hide image and save it
        ImageUnveiler imageUnveiler = new ImageUnveiler();

        writer.writeFile(
            imageUnveiler.unveilImage(loader.loadImage(inputFile)),
            outputFileName + ".png"
        );
    }
}
