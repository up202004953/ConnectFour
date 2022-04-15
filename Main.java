import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Screen.clear();
        System.out.println("Welcome to Connect Four!");

        Scanner stdin = new Scanner(System.in);

        System.out.print("Type the width of your board: ");
        int w = stdin.nextInt();
        System.out.print("Type the height of your board: ");
        int h = stdin.nextInt();

        Board board = new Board(w,h);

        System.out.print("Which algorithm do you want? Min-Max (1), Alfa-Beta (2) or MCTS (3)? ");
        int algorithm = stdin.nextInt();

        System.out.print("Do you want to start first? (yes or no) ");
        if (stdin.next().equals("yes")) Game.start(board, PlayerType.Player, stdin, algorithm);
        else Game.start(board, PlayerType.Computer, stdin, algorithm);

    }
}