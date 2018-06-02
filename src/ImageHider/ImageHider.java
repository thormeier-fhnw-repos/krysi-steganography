package ImageHider;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Hides an image in another image
 */
public class ImageHider
{
    /**
     * Hides an image within another image
     * @param data Image that is to be hidden in another one
     * @param in   Image to hide the other one in
     * @return     New raw BufferedImage
     */
    public BufferedImage hideImage(BufferedImage data, BufferedImage in)
    {
        int width = data.getWidth();
        int height = data.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                data.setRGB(x, y, mergeColorsAtPixel(x, y, data, in).getRGB());
            }
        }

        return data;
    }

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
     * Merges the color of a specific pixel in both data and in image
     * @param x X coordinate
     * @param y Y coordinate
     * @param data Data image to hide
     * @param in Image to hide in
     * @return Mixed color
     */
    private Color mergeColorsAtPixel(int x, int y, BufferedImage data, BufferedImage in)
    {
        Color dataColor = new Color(data.getRGB(x, y));
        Color inColor = new Color(in.getRGB(x, y));

        return new Color(
            mergeColorChannel(dataColor.getRed(), inColor.getRed()),
            mergeColorChannel(dataColor.getGreen(), inColor.getGreen()),
            mergeColorChannel(dataColor.getBlue(), inColor.getBlue())
        );
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
     * Merges a single color channel (f.e. red) into a single color
     * @param data Data color channel value
     * @param in In color channel value
     * @return Merged color value
     */
    private int mergeColorChannel(int data, int in)
    {
        String dataBitString = leftPadBinaryToLength(Integer.toBinaryString(data), 8);
        String inBitString = leftPadBinaryToLength(Integer.toBinaryString(in), 8);

        String dataBitStringSignificant = getMostSignificantBits(dataBitString, 4);
        String inBitStringSignificant = getMostSignificantBits(inBitString, 4);

        return Integer.parseInt(inBitStringSignificant + dataBitStringSignificant, 2);
    }

    /**
     * Returns the most significant bits of a given bit string
     * @param bitString Bit string to extract MSB from
     * @param length Number of MSB
     * @return MSB
     */
    private String getMostSignificantBits(String bitString, int length)
    {
        return bitString.substring(0, length);
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
}
