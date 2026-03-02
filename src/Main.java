import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static ArrayList<Integer> player1position = new ArrayList<>();
    static ArrayList<Integer> player2position = new ArrayList<>();

    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);

        Scanner scanner = new Scanner(System.in);
        boolean player1Number;
        boolean player2Number;


        while (checkStatus()) {
            System.out.println("Player1 choose a position from 1 to 9 :");
            int player1Position = scanner.nextInt();

            if (player1Position < 0 || player1Position > 9) {
                player1Number = true;
            } else {
                player1Number = false;
            }

            while (player1Number) {
                System.out.println("Player1 please choose a number between 1-9 : ");
                player1Position = scanner.nextInt();

                if (player1Position < 0 || player1Position > 9) {
                    player1Number = true;
                } else {
                    player1Number = false;
                }
            }

            while (player1position.contains(player1Position) || player2position.contains(player1Position)) {
                System.out.println("Position is unavailable. Choose another position");
                player1Position = scanner.nextInt();
            }

            choosePosition(gameBoard, player1Position, "Player1");
            printGameBoard(gameBoard);
            System.out.println("Player2 choose a position from 1 to 9 :");
            int player2Position = scanner.nextInt();

            if (player2Position < 0 || player2Position > 9) {
                player2Number = true;
            } else {
                player2Number = false;
            }

            while (player2Number) {
                System.out.println("Player2 please choose a number between 1-9 : ");
                player2Position = scanner.nextInt();

                if (player2Position < 0 || player2Position > 9) {
                    player2Number = true;
                } else {
                    player2Number = false;
                }
            }

            while (player1position.contains(player2Position) || player2position.contains(player2Position)) {
                System.out.println("Position is unavailable. Choose another position");
                player2Position = scanner.nextInt();
            }

            choosePosition(gameBoard, player2Position, "Player2");
            printGameBoard(gameBoard);
        }
    }


    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char character : row) {
                System.out.print(character);
            }
            System.out.println();
        }
    }

    public static void choosePosition(char[][] gameBoard, int position, String player) {
        char symbol = ' ';

        if (player.equals("Player1")) {
            symbol = 'X';
            player1position.add(position);
        } else if (player.equals("Player2")) {
            symbol = 'O';
            player2position.add(position);
        }

        switch (position) {
            case 1 -> gameBoard[0][0] = symbol;
            case 2 -> gameBoard[0][2] = symbol;
            case 3 -> gameBoard[0][4] = symbol;
            case 4 -> gameBoard[2][0] = symbol;
            case 5 -> gameBoard[2][2] = symbol;
            case 6 -> gameBoard[2][4] = symbol;
            case 7 -> gameBoard[4][0] = symbol;
            case 8 -> gameBoard[4][2] = symbol;
            case 9 -> gameBoard[4][4] = symbol;
        }

    }

    public static boolean checkStatus() {
        List firstLine = Arrays.asList(1, 2, 3);
        List secondLine = Arrays.asList(4, 5, 6);
        List thirdLine = Arrays.asList(7, 8, 9);
        List firstColumn = Arrays.asList(1, 4, 7);
        List secondColumn = Arrays.asList(2, 5, 8);
        List thirdColumn = Arrays.asList(3, 6, 9);
        List firstDiagonal = Arrays.asList(1, 5, 9);
        List secondDiagonal = Arrays.asList(7, 5, 3);

        List<List> win = new ArrayList<List>();
        win.add(firstLine);
        win.add(secondLine);
        win.add(thirdLine);
        win.add(firstColumn);
        win.add(secondColumn);
        win.add(thirdColumn);
        win.add(firstDiagonal);
        win.add(secondDiagonal);

        for (List list : win) {
            if (player1position.containsAll(list)) {
                System.out.println("Congratulations, Player1 has won the game!!!");
                return false;
            } else if (player2position.containsAll(list)) {
                System.out.println("Congratulations, Player2 has won the game!!!");
                return false;
            } else if (player1position.size() + player2position.size() == 9) {
                System.out.println("This is a Draw");
                return false;
            }
        }

        return true;
    }
}