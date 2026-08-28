import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class TilesManager {

    GamePanel gp;
    Tiles[] tiles;

    public TilesManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tiles[10]; // Adjust size as needed

        getTileImage();
    }

    private void getTileImage() {
        
        try {
            tiles[0] = new Tiles();
            tiles[0].image = loadImage("res/player/tiles/grass.png");

            tiles[1] = new Tiles();
            tiles[1].image = loadImage("res/player/tiles/wall.png");
            
            tiles[2] = new Tiles();
            tiles[2].image = loadImage("res/player/tiles/water.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private java.awt.image.BufferedImage loadImage(String resourcePath) throws IOException {
        InputStream classpathImage = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (classpathImage != null) {
            return ImageIO.read(classpathImage);
        }

        Path imagePath = Path.of(resourcePath);
        if (!Files.exists(imagePath)) {
            imagePath = Path.of("TotoroCustom2dGame", resourcePath);
        }
        return ImageIO.read(imagePath.toFile());
    }

    public void draw(Graphics2D g2) {
          
                g2.drawImage(tiles[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
            }
        }
