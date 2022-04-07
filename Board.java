package ConnectFour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private static final char empty = '-';

    private final char[][] matrix;
    private final int[] high;
    private final int width;
    private final int height;

    public Board(int width, int height) {
        this.matrix = new char[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                matrix[i][j] = empty;
            }
        }

        this.high = new int[width];
        this.width = width;
        this.height = height;
    }

    public Board(char[][] matrix, int width, int height) {
        this.matrix = matrix;
        this.high = new int[width];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (matrix[i][j] == empty) break;
                high[i]++;
            }
        }

        this.width = width;
        this.height = height;
    }

    public Board(char[][] matrix, int[] high, int width, int height) {
        this.matrix = matrix;
        this.high = high;
        this.width = width;
        this.height = height;
    }

    public char[][] getMatrix() {return matrix;}
    public int[] getHigh() {return high;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}

    public List<Board> getSucc(PlayerType pt) {
        ArrayList<Board> succ = new ArrayList<>();

        for (int n = 0; n < width; n++) {
            if (high[n] < height) {
                char[][] curMatrix = new char[width][height];
                int[] curHigh = new int[width];

                for (int i = 0; i < width; i++) {
                    curHigh[i] = high[i];
                    for (int j = 0; j < height; j++) {
                        if (n == i && j == high[i]) {
                            curMatrix[i][j] = pt.getSymbol();
                            curHigh[i]++;
                        }
                        else curMatrix[i][j] = matrix[i][j];
                    }
                }

                succ.add(new Board(curMatrix, curHigh, width, height));
            }
        }

        return succ;
    }

    private int value(int player, int computer) {
        if (player > 0 && computer > 0) return 0;
        if (player == 0 && computer == 0) return 0;

        if (player == 4) return -512;
        if (player == 3) return -50;
        if (player == 2) return -10;
        if (player == 1) return -1;

        if (computer == 4) return 512;
        if (computer == 3) return 50;
        if (computer == 2) return 10;
        if (computer == 1) return 1;

        System.out.println("Deu 999");
        return 0;
    }

    public int getUtility() {
        int sum = 0;

        //Horizontal
        for (int i = 0; i < height; i++) {
            for (int j = 0; j <= width-4; j++) {
                int player = 0;
                int computer = 0;
                for (int k = 0; k < 4; k++) {
                    if (matrix[j+k][i] == PlayerType.Player.getSymbol()) player++;
                    else if (matrix[j+k][i] == PlayerType.Computer.getSymbol()) computer++;
                }
                int v = value(player,computer);
                if (v == 512 || v == -512) return v;
                sum += v;
            }
        }

        //Vertical
        for (int i = 0; i < width; i++) {
            for (int j = 0; j <= height-4; j++) {
                int player = 0;
                int computer = 0;
                for (int k = 0; k < 4; k++) {
                    if (matrix[i][j+k] == PlayerType.Player.getSymbol()) player++;
                    else if (matrix[i][j+k] == PlayerType.Computer.getSymbol()) computer++;
                }
                int v = value(player,computer);
                if (v == 512 || v == -512) return v;
                sum += v;
            }
        }

        //Diagonal (Left -> Right)
        for (int i = 0; i < height+width-1; i++) {
            int player = 0;
            int computer = 0;
            int count = 0;
            for (int j = 0; j <= i; j++) {
                if (j >= width || (i-j) >= height) continue;

                if (matrix[j][i-j] == PlayerType.Player.getSymbol()) player++;
                else if (matrix[j][i-j] == PlayerType.Computer.getSymbol()) computer++;

                if (++count == 4) {
                    int v = value(player, computer);
                    if (v == 512 || v == -512) return v;
                    sum += v;

                    j -= 3; count = 0; player = 0; computer = 0;
                }
            }
        }

        //Diagonal (Right -> Left)
        for (int i = 0; i <= height+width-1; i++) {
            int player = 0;
            int computer = 0;
            int count = 0;
            for (int j = 0; j <= i; j++) {
                int w = i-j;
                int h = height-j;

                if (w >= width || w < 0 || h >= height || h < 0) continue;

                if (matrix[w][h] == PlayerType.Player.getSymbol()) player++;
                else if (matrix[w][h] == PlayerType.Computer.getSymbol()) computer++;

                if (++count == 4) {
                    int v = value(player, computer);
                    if (v == 512 || v == -512) return v;
                    sum += v;

                    j -= 3; count = 0; player = 0; computer = 0;
                }
            }
        }

        return sum;
    }

    @Override
    public String toString() {
        StringBuilder bfr = new StringBuilder("");

        for (int i = height-1; i >= 0; i--) {
            bfr.append("|");
            for (int j = 0; j < width; j++) {
                bfr.append(" ").append(matrix[j][i]);
            }
            bfr.append("|\n");
        }

        return bfr.toString();
    }
}
