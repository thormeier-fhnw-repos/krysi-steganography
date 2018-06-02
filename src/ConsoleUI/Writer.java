package ConsoleUI;

public class Writer
{
    /**
     * Horizontal line
     */
    public void hr()
    {
        print("==============================");
    }

    /**
     * New line/linefeed
     */
    public void ln()
    {
        print("\n");
    }

    /**
     * Conveience method for System.out.println()
     * @param arg String to output
     */
    public void print(String arg)
    {
        System.out.println(arg);
    }
}
