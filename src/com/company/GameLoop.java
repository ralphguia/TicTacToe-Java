package com.company;

import java.util.InputMismatchException;
import java.util.Random;
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
        boolean scnIsHuman;
        int NOUGHT = gameboard.NOUGHT;
        int CROSS = gameboard.CROSS;

        Random rand= new Random();

        System.out.println("Type in 'true' for 2 player and 'false' for single player");
        scnIsHuman =  scn.nextBoolean();
        if (scnIsHuman){
            p1.setHuman(true);
            p2.setHuman(true);
        } else {
            p1.setHuman(false);
            p2.setHuman(false);
        }

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

        if (p1.isHuman()){
            while (true) {
                if (gameboard.getSymbolCounter() == 9){
                    System.out.println("Draw!");
                    if(gameboard.restartGameOrLeave(scn)){
                        gameboard.printLeaderboard(p1,p2);
                        break;
                    }
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
        } else {
            while (true) {
                if (gameboard.getSymbolCounter() == 9){
                    System.out.println("Draw!");
                    if(gameboard.restartGameOrLeave(scn)){
                        gameboard.printLeaderboard(p1,p2);
                        break;
                    }
                } else {
                    if (gameboard.getPlayerTurn() % 2 == 1) {
                        System.out.println("Player 1 enter the row and column you want to place your symbol:");
                        p = p1;
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
                    } else {
                        System.out.println("Player 2 enter the row and column you want to place your symbol:");
                        p = p2;
                        int randomRow = rand.nextInt((3 - 1) + 1) + 1;
                        int randomCol = rand.nextInt((3 - 1) + 1) + 1;
                        //TODO 2.) KI-Upgrade: paste in the row & col, which are available
                        //TODO 3.) KI_Upgrade: paste in the row & col, which are favorable and interrupt opponent

                        if (gameboard.gameplayWinOrLose(scn, p, randomRow, randomCol)){
                            gameboard.printLeaderboard(p1,p2);
                            break;
                        }
                    }
                }
            }
        }
    }
}
