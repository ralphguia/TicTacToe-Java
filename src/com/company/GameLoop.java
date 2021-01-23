package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameLoop {

    public static void main(String[] args) {
        Gameboard gameboard = new Gameboard();
        Scanner scn = new Scanner(System.in);
        Player p1 = new Player();
        Player p2 = new Player();
        Player p;
        int scnSymbol;
        int scnRow;
        int scnCol;
        int NOUGHT = gameboard.NOUGHT;
        int CROSS = gameboard.CROSS;

        System.out.println("Choose one of those numbers provided: 1)'O' or 2)'X'");
        while (true) {
            try {
                scnSymbol = scn.nextInt();
            } catch (InputMismatchException e) {
                scn.next();
                System.out.println("Invalid input! Only the numbers 1 or 2 are valid! Try again.");
                continue;
            }
            if (scnSymbol == NOUGHT) {
                break;
            } else if (scnSymbol == CROSS) {
                break;
            } else {
                System.out.println("Invalid number! Choose between 1 for O and 2 for X!");
            }
        }

        gameboard.printBoard();
        p1.setSymbol(scnSymbol);
        if (scnSymbol == NOUGHT) {
            p2.setSymbol(CROSS);
        } else {
            p2.setSymbol(NOUGHT);
        }

        while (true) {
            if (gameboard.getSymbolCounter() == 9){
                System.out.println("Draw!");
                gameboard.restartGameOrLeave(scn);
            } else {
                if (gameboard.getPlayerTurn() % 2 == 1) {
                    System.out.println("Player 1 enter the row and column you want to place your symbol:");
                    p = p1;
                } else {
                    System.out.println("Player 2 enter the row and column you want to place your symbol:");
                    p = p2;
                }

                try {
                    System.out.println("row:");
                    scnRow = scn.nextInt();
                    System.out.println("col:");
                    scnCol = scn.nextInt();
                } catch (InputMismatchException e) {
                    scn.next();
                    System.out.println("Invalid input! Try again.");
                    continue;
                }
                if (gameboard.gameplayWinOrLose(scn, p, scnRow, scnCol)){
                    gameboard.printLeaderboard(p1,p2);
                    break;
                }
            }
        }
    }
}
