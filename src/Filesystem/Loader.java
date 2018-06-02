package Filesystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads files and returns lists etc.
 */
public class Loader
{
    /**
     * Path to search files in
     */
    private String path;

    /**
     * Constructor
     * @param path Path to search files in
     */
    public Loader(String path)
    {
        this.path = path;
    }

    /**
     * Get a String list of all filenames in the given path
     * @return List of filenames
     */
    public String[] getFiles()
    {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles == null) { // No files found
            return new String[0];
        }

        List<String> files = new ArrayList<>();

        for (File file : listOfFiles) {
            if (file.getName().equals(".gitkeep")) { // Skip .gitkeep
                continue;
            }

            files.add(file.getName());
        }

        return files.toArray(new String[0]);
    }
}
