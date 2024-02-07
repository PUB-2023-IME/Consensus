import java.util.Random;
import java.util.Scanner;

public class LEDPanel {
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LEDPanel ledPanel = new LEDPanel();

        System.out.println("Escolha duas posições (i, j) para colocar vermelho ou roxo:");

        for (int k = 0; k < 2; k++) {
            System.out.print("Posição (i): ");
            int i = scanner.nextInt();
            System.out.print("Posição (j): ");
            int j = scanner.nextInt();
            System.out.print("Escolha a cor (1 para vermelho, 2 para roxo): ");
            int colorChoice = scanner.nextInt();
            Color color = (colorChoice == 1) ? Color.RED : Color.PURPLE;
            ledPanel.setUserColors(i, j, color);
        }

        ledPanel.setRandomColors();
        ledPanel.printPanel();
        ledPanel.runConsensus();
    }

    public void setUserColors(int i, int j, Color color) {
        panel[i][j] = color;
    }

    private static final int SIZE = 20;
    private static final int[][] WEIGHTS = {
            {1, 2, 1},
            {2, 2, 2},
            {1, 2, 1}
    };

    private enum Color {
        RED, PURPLE, NONE
    }

    private Color[][] panel;

    public LEDPanel() {
        panel = new Color[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                panel[i][j] = Color.NONE;
            }
        }
    }


    public void setRandomColors() {
        Random random = new Random();
        for (int k = 0; k < 5; k++) {
            int i = random.nextInt(SIZE);
            int j = random.nextInt(SIZE);
            Color color = random.nextBoolean() ? Color.RED : Color.PURPLE;
            panel[i][j] = color;
        }
    }

    public void runConsensus() {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (panel[i][j] == Color.NONE) {
                        Color newColor = decideColor(i, j);
                        if (newColor != Color.NONE && newColor != panel[i][j]) {
                            panel[i][j] = newColor;
                            changed = true;
                        }
                    }
                }
            }
            printPanel();
        }
    }

    private Color decideColor(int i, int j) {
        int redNeighbors = 0;
        int purpleNeighbors = 0;

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                int neighborX = i + x;
                int neighborY = j + y;
                if (isValidIndex(neighborX, neighborY) && panel[neighborX][neighborY] != Color.NONE) {
                    int weight = WEIGHTS[x + 1][y + 1];
                    if (panel[neighborX][neighborY] == Color.RED) {
                        redNeighbors += weight;
                    } else if (panel[neighborX][neighborY] == Color.PURPLE) {
                        purpleNeighbors += weight;
                    }
                }
            }
        }

        if (redNeighbors > purpleNeighbors) {
            return Color.RED;
        } else if (purpleNeighbors > redNeighbors) {
            return Color.PURPLE;
        } else {
            return Color.NONE;
        }
    }

    private boolean isValidIndex(int i, int j) {
        return i >= 0 && i < SIZE && j >= 0 && j < SIZE;
    }

    public void printPanel() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                switch (panel[i][j]) {
                    case RED:
                        System.out.print("V ");
                        break;
                    case PURPLE:
                        System.out.print("R ");
                        break;
                    case NONE:
                        System.out.print("- ");
                        break;
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
