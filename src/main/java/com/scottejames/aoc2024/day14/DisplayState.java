package com.scottejames.aoc2024.day14;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DisplayState {
    Graphics graphics;
    BufferedImage bi;
    File outputDir;
    public DisplayState(int W, int H) {
        outputDir= new File("/Users/scottejames/op");
        if (!outputDir.exists() && outputDir.isDirectory()) {
            System.out.println("please create dir " + outputDir);
            System.exit(1);
        }
        bi = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);
        graphics = bi.getGraphics();
    }

    public void drawBots(List<Robot> bots, int step) throws IOException {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        graphics.setColor(Color.BLACK);
        for (Robot bot : bots) {
            graphics.drawRect((int) bot.position.x, (int) bot.position.y, 1, 1);

        }
        ImageIO.write(bi, "png", new File(outputDir, "%04d.png".formatted(step)));

    }
}
