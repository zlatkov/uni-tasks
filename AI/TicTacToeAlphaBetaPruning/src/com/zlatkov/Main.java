package com.zlatkov;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Player human = new Player(PlayerType.HUMAN, CellType.X);
        Player computer = new Player(PlayerType.COMPUTER, CellType.O);

        Engine engine = new Engine(human, computer);
        Scanner scanner = new Scanner(System.in);

        while(engine.hasEmptyCells()) {
            engine.printBoard();
            System.out.println("Enter row and col:");

            int row = scanner.nextInt();
            int col = scanner.nextInt();
            engine.makeTurn(human, new BoardPosition(row, col));

            if (engine.isGameOver()) {
                engine.printBoard();
                System.out.print("Human won!");
                System.exit(0);
            }
            else if (engine.hasEmptyCells()) {
                GameMove bestMove = engine.getBestMove(computer);
                engine.makeTurn(computer, bestMove.getPosition());
                if (engine.isGameOver()) {
                    engine.printBoard();
                    System.out.print("Computer won!");
                    System.exit(0);
                }
            }
        }

        System.out.println("Draw");
    }
}
