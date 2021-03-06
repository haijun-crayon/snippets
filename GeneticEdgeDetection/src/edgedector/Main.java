package edgedector;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.util.HashSet;

public class Main {
    public static void main (String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java -cp EdgeDetector.jar edgedector.Main [path and filename of the input image]");
            System.exit(0);
        }
   
        String inputFileName = args[0];

        Environment environment = new Environment(inputFileName);
        HashSet<Specimen> specimens = environment.evolve(100);

        // save to output image
        BufferedImage output =new BufferedImage(
                                    environment.width(), 
                                    environment.height(),
                                    BufferedImage.TYPE_INT_RGB);                

        for (Specimen s : specimens) {
            output.setRGB(s.place().x, s.place().y, Color.WHITE.getRGB());
        }
        
        ImageIO.write(output, "PNG", new File("output.png"));
    }
}