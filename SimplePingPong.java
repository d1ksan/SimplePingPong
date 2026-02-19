public class SimplePingPong {
    public static void drawField() {
        int width = 40;
        int height = 15;

        for (int i = 0; i < width + 2; i++) {
            System.out.print("#");
        }
        System.out.println();

        for (int row = 0; row < height; row++) {
            System.out.print("#"); 
            for (int col = 0; col < width; col++) {
                System.out.print(" "); 
            }
            System.out.println("#"); 
        }

        for (int i = 0; i < width + 2; i++) {
            System.out.print("#");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        drawField();
    }
}