import java.util.Scanner;

public class SimplePingPong {
    private static final int WIDTH = 40;
    private static final int HEIGHT = 15;
    
    private static int leftPaddleY = HEIGHT / 2;
    private static int rightPaddleY = HEIGHT / 2;
    private static final int PADDLE_HEIGHT = 3;
    
    public static void drawField() {
        clearConsole();
        
        for (int i = 0; i < WIDTH + 2; i++) {
            System.out.print("#");
        }
        System.out.println();

        for (int row = 0; row < HEIGHT; row++) {
            System.out.print("#");
            
            for (int col = 0; col < WIDTH; col++) {
                if (col == 2 && row >= leftPaddleY && row < leftPaddleY + PADDLE_HEIGHT) {
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
    }
    
    public static void movePaddles() {
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
                    
                    drawField();
                }
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
        movePaddles();
    }
}