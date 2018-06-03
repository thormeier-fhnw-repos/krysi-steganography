package ImageHider;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageUnveiler
{
    /**
     * Unveils a hidden image
     * @param in The image to unveil an image from
     * @return The unveiled image
     */
    public BufferedImage unveilImage(BufferedImage in)
    {
        int width = in.getWidth();
        int height = in.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                in.setRGB(x, y, extractColorAtPixel(x, y, in).getRGB());
            }
        }

        return in;
    }

    /**
     * Extracts the hidden color at a certain pixel
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param in Image
     * @return The hidden color
     */
    private Color extractColorAtPixel(int x, int y, BufferedImage in)
    {
        Color inColor = new Color(in.getRGB(x, y));

        return new Color(
            extractColorChannel(inColor.getRed()),
            extractColorChannel(inColor.getGreen()),
            extractColorChannel(inColor.getBlue())
        );
    }

    /**
     * Extracts the hidden color at a given pixel
     * @param in The color that something was hidden in
     * @return The hidden color
     */
    private int extractColorChannel(int in)
    {
        String bitString = leftPadBinaryToLength(Integer.toBinaryString(in), 8);
        String leastSignificantBits = getLeastSignificantBits(bitString, 4);
        String paddedBitString = rightPadBinaryToLength(leastSignificantBits, 8);

        return Integer.parseInt(paddedBitString, 2);
    }

    /**
     * Extract the least significant bits from a given bit string
     * @param bitString Bit String to extract the LSB from
     * @param length Number of LSB
     * @return LSB
     */
    private String getLeastSignificantBits(String bitString, int length)
    {
        return bitString.substring(bitString.length() - length);
    }

    /**
     * Right-pad bit string with 0s until target length is reached
     * @param bitString String to pad
     * @param targetLength Target length
     * @return Padded bit string
     */
    private String rightPadBinaryToLength(String bitString, int targetLength)
    {
        while (bitString.length() < targetLength) {
            bitString = bitString + "0"; // Pad 0s until target length of 8 bits is reached
        }

        return bitString;
    }

    /**
     * Left-pad bit string with 0s until target length is reached
     * @param bitString String to pad
     * @param targetLength Target length
     * @return Padded bit string
     */
    private String leftPadBinaryToLength(String bitString, int targetLength)
    {
        while (bitString.length() < targetLength) {
            bitString = "0" + bitString; // Pad 0s until target length of 8 bits is reached
        }

        return bitString;
    }
}
