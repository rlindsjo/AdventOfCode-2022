package net.tilialacus.adventofcode2022;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageOutput {
    public static void export (boolean[][] data) {
        int height = data[0].length;
        int width = data.length;
        BufferedImage outImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                outImage.setRGB(x, y, data[x][y] ? 0x00000000 : 0xffffffff);
            }
        }
        try {
            ImageIO.write(outImage, "png", new File("image.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
