import java.util.Scanner;
import java.util.Random;

public class SimplePingPong {
    private static final int WIDTH = 40;
    private static final int HEIGHT = 15;
    
    private static int leftPaddleY = HEIGHT / 2;
    private static int rightPaddleY = HEIGHT / 2;
    private static final int PADDLE_HEIGHT = 3;
    
    private static int ballX = WIDTH / 2;
    private static int ballY = HEIGHT / 2;
    private static int ballDirX = 1;
    private static int ballDirY = 1;
    
    private static int leftScore = 0;
    private static int rightScore = 0;
    
    public static void drawField() {
        clearConsole();
        
        for (int i = 0; i < WIDTH + 2; i++) {
            System.out.print("#");
        }
        System.out.println();

        for (int row = 0; row < HEIGHT; row++) {
            System.out.print("#");
            
            for (int col = 0; col < WIDTH; col++) {
                if (col == ballX && row == ballY) {
                    System.out.print("O");
                }
                else if (col == 2 && row >= leftPaddleY && row < leftPaddleY + PADDLE_HEIGHT) {
                    System.out.print("]");
                }
                else if (col == WIDTH - 3 && row >= rightPaddleY && row < rightPaddleY + PADDLE_HEIGHT) {
                    System.out.print("[");
                }
                else {
                    System.out.print(" ");
                }
            }
            
            System.out.println("#");
        }

        for (int i = 0; i < WIDTH + 2; i++) {
            System.out.print("#");
        }
        System.out.println();
        
        System.out.println("Player 1 (LEFT): W/S | Player 2 (RIGHT): K/M | Q - quit");
        System.out.println("Score: " + leftScore + " - " + rightScore);
    }
    
    public static void moveBall() {
        ballX += ballDirX;
        ballY += ballDirY;
        
        if (ballY <= 0 || ballY >= HEIGHT - 1) {
            ballDirY = -ballDirY;
        }
        
        if (ballX <= 3 && ballY >= leftPaddleY && ballY < leftPaddleY + PADDLE_HEIGHT) {
            ballDirX = -ballDirX;
            Random rand = new Random();
            ballDirY = rand.nextBoolean() ? 1 : -1;
        }
        
        if (ballX >= WIDTH - 4 && ballY >= rightPaddleY && ballY < rightPaddleY + PADDLE_HEIGHT) {
            ballDirX = -ballDirX;
            Random rand = new Random();
            ballDirY = rand.nextBoolean() ? 1 : -1;
        }
        
        if (ballX < 0) {
            rightScore++;
            resetBall();
        }
        
        if (ballX > WIDTH) {
            leftScore++;
            resetBall();
        }
    }
    
    public static void resetBall() {
        ballX = WIDTH / 2;
        ballY = HEIGHT / 2;
        Random rand = new Random();
        ballDirX = rand.nextBoolean() ? 1 : -1;
        ballDirY = rand.nextBoolean() ? 1 : -1;
    }
    
    public static void gameLoop() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press keys to move paddles (W/S for left, K/M for right). Press Q to quit.");
        
        while (true) {
            if (scanner.hasNext()) {
                String input = scanner.nextLine().toLowerCase();
                
                if (input.length() > 0) {
                    char key = input.charAt(0);
                    
                    if (key == 'q') {
                        break;
                    }
                    
                    if (key == 'w' && leftPaddleY > 0) {
                        leftPaddleY--;
                    } else if (key == 's' && leftPaddleY < HEIGHT - PADDLE_HEIGHT) {
                        leftPaddleY++;
                    }
                    
                    if (key == 'k' && rightPaddleY > 0) {
                        rightPaddleY--;
                    } else if (key == 'm' && rightPaddleY < HEIGHT - PADDLE_HEIGHT) {
                        rightPaddleY++;
                    }
                }
            }
            
            moveBall();
            drawField();
            
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                break;
            }
        }
        scanner.close();
    }
    
    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }
    
    public static void main(String[] args) {
        drawField();
        gameLoop();
    }
}