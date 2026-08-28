import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.InputStreamReader;

public class TilesManager {

    GamePanel gp;
    Tiles[] tiles;
    int mapTileNum[][];

    public TilesManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tiles[10]; // Adjust size as needed
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("res/player/map/map101.txt");
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

    public void loadMap(String filePath) {
        try (BufferedReader br = openMap(filePath)) {

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

                String line = br.readLine();

                while (col < gp.maxScreenCol) {

                    String[] numbers = line.split("\\s+");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BufferedReader openMap(String filePath) throws IOException {
        InputStream classpathMap = getClass().getClassLoader().getResourceAsStream(filePath);
        if (classpathMap != null) {
            return new BufferedReader(new InputStreamReader(classpathMap));
        }

        Path mapPath = Path.of(filePath);
        if (!Files.exists(mapPath)) {
            mapPath = Path.of("TotoroCustom2dGame", filePath);
        }
        Reader fileMap = new FileReader(mapPath.toFile());
        return new BufferedReader(fileMap);
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

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tiles[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}