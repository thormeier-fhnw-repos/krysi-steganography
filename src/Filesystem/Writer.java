package Filesystem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Writes to a file
 */
public class Writer
{
    /**
     * Path to write files into
     */
    private String path;

    /**
     * Constructor
     * @param path Path to write files into
     */
    public Writer(String path)
    {
        this.path = path;
    }

    public void writeFile(BufferedImage image, String fileName)
    {
        File file = new File(path + "/" + fileName);
        try {
            ImageIO.write(image, "png", file);
        } catch(Exception e) {
            System.out.println("Error: Could not write file " + path + "/" + fileName);
        }
    }
}
