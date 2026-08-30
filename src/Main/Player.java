import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;;


public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY; 

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle( 8, 16, 32, 32);

        setDefaultValues();
        getPLayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPLayerImage() {
        // Load player images here

        try {
            up1 = loadImage("res/player/to_up_1.png");
            up2 = loadImage("res/player/to_up_2.png");
            down1 = loadImage("res/player/to_down_1.png");
            down2 = loadImage("res/player/to_down_2.png");
            left1 = loadImage("res/player/to_left_1.png");
            left2 = loadImage("res/player/to_left_2.png");
            right1 = loadImage("res/player/to_right_1.png");
            right2 = loadImage("res/player/to_right_2.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage loadImage(String resourcePath) throws IOException {
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

    public void update1() {

    if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

        if (keyH.upPressed) {
            direction = "up";
        }
        else if (keyH.downPressed) {
            direction = "down";
        }
        else if (keyH.leftPressed) {
            direction = "left";
        }
        else if (keyH.rightPressed) {
            direction = "right";
        }

        collisionOn = false;
        gp.cChecker.checkTile(this);

        // If collision is false, player can move
        if (!collisionOn) {
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }

        SpriteCounter++;
        if (SpriteCounter > 12) {
            SpriteNum = (SpriteNum == 1) ? 2 : 1;
            SpriteCounter = 0;
        }
    }
}

public void update() {

    if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

        if (keyH.upPressed) {
            direction = "up";
        }
        else if (keyH.downPressed) {
            direction = "down";
        }
        else if (keyH.leftPressed) {
            direction = "left";
        }
        else if (keyH.rightPressed) {
            direction = "right";
        }

        collisionOn = false;
        gp.cChecker.checkTile(this);

        // If collision is false, player can move
        if (!collisionOn) {
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }

        SpriteCounter++;
        if (SpriteCounter > 12) {
            SpriteNum = (SpriteNum == 1) ? 2 : 1;
            SpriteCounter = 0;
        }
    }
}

    public void draw(Graphics2D g2) {

//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (SpriteNum == 1) {
                    image = up1;
                }
                if (SpriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (SpriteNum == 1) {
                    image = down1;
                } 
                if (SpriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (SpriteNum == 1) {
                    image = left1;
                }
                if (SpriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (SpriteNum == 1) {
                    image = right1;
                }
                if (SpriteNum == 2) {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }

}
