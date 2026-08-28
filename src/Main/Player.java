import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import java.awt.Color;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    private int spriteCounter = 0;
    private int spriteNum = 1;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPLayerImage();
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
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

    public void update () {

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

        if (keyH.upPressed == true) {
            direction = "up";
            y -= speed;
        }  
        else if (keyH.downPressed == true) {
            direction = "down";
            y += speed;
        }
        else if (keyH.leftPressed == true) {
            direction = "left";
            x -= speed;
        }
        else if (keyH.rightPressed == true) {
            direction = "right";
            x += speed;
        }
    }

        spriteCounter++;
        if (spriteCounter > 12) { 
            if (spriteNum == 1) {
                spriteNum = 2;
            } 
            else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
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
                    image = left2;
                }
                break;
            case "right":
                image = right1;
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }

}
