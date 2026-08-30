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
    public Tiles[] tiles;
    int mapTileNum[][];

    public TilesManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tiles[20];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("res/player/map/world01.txt");
    }

    private void getTileImage() {
        try {
            tiles[0] = new Tiles();
            tiles[0].image = loadImage("res/player/tiles/grass.png");
            tiles[0].collision = false;

            tiles[1] = new Tiles();
            tiles[1].image = loadImage("res/player/tiles/wall.png");
            tiles[1].collision = true;

            tiles[2] = new Tiles();
            tiles[2].image = loadImage("res/player/tiles/water.png");
            tiles[2].collision = true;

            tiles[3] = new Tiles();
            tiles[3].image = loadImage("res/player/tiles/earth.png");
            tiles[3].collision = false;

            tiles[4] = new Tiles();
            tiles[4].image = loadImage("res/player/tiles/tree.png");
            tiles[4].collision = true;

            tiles[5] = new Tiles();
            tiles[5].image = loadImage("res/player/tiles/sand.png");
            tiles[5].collision = false;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try (BufferedReader br = openMap(filePath)) {

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {

                    String[] numbers = line.split("\\s+");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
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

        int worldCol = 0;
        int worldRow = 0;
        

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];
            if (tileNum < 0 || tileNum >= tiles.length || tiles[tileNum] == null || tiles[tileNum].image == null) {
                worldCol++;
                if (worldCol == gp.maxWorldCol) {
                    worldCol = 0;
                    worldRow++;
                }
                continue;
            }

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}