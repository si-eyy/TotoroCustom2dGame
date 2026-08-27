import javax.swing.JFrame;

public class main {
    
    public static void main(String[] args) { 

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Totoro Custom 2D Game");

        gamepanel gamePanel = new gamepanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
