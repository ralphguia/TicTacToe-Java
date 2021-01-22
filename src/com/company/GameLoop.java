package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameLoop {

    public static void main(String[] args) {
	    Gameboard gameboard = new Gameboard();
        Scanner scn = new Scanner(System.in);
        Player p1 = new Player();
        Player p2 = new Player();
        int scnSymbol = 0;
        int scnRow;
        int scnCol;

        int NOUGHT = gameboard.NOUGHT;
        int CROSS = gameboard.CROSS;


        System.out.println("Choose one of those numbers provided: 1)O or 2)X");

        while (scnSymbol != 1 && scnSymbol != 2){
            try{
                scnSymbol = scn.nextInt();
            } catch (InputMismatchException e){
                scn.next();
                System.out.println("Invalid input! Only the numbers 1 or 2 are valid! Try again.");
                continue;
            }
            if (scnSymbol == 1 || scnSymbol == 2){
                continue;
            } else {
                System.out.println("Invalid number! Choose between 1 for O and 2 for X!");
            }
        }

        while (true){
            p1.setSymbol(scnSymbol);
            if (scnSymbol == NOUGHT){
                p2.setSymbol(CROSS);
            } else {
                p2.setSymbol(NOUGHT);
            }

            if (gameboard.getPlayerTurn() % 2 == 1){
                try{
                    System.out.println("Player 1 enter the row and column you want to place your symbol:");
                    System.out.println("row:");
                    scnRow = scn.nextInt();
                    System.out.println("col:");
                    scnCol = scn.nextInt();
                } catch (InputMismatchException e){
                    String bad_input = scn.next();
                    System.out.println("Invalid input! Try again.");
                    continue;
                }
                if (scnRow >= 1 && scnRow <=3  && scnCol >= 1 && scnCol <= 3){
                    gameboard.set(p1, scnRow, scnCol);
                    gameboard.printBoard();
                    gameboard.setPlayerTurn(gameboard.getPlayerTurn()+1);
                }
            } else{
                try{
                    System.out.println("Player 2 enter the row and column you want to place your symbol:");
                    System.out.println("row:");
                    scnRow = scn.nextInt();
                    System.out.println("col:");
                    scnCol = scn.nextInt();
                } catch (InputMismatchException e){
                    String bad_input = scn.next();
                    System.out.println("Invalid input! Try again.");
                    continue;
                }
                if (scnRow >= 1 && scnRow <=3  && scnCol >= 1 && scnCol <= 3){
                    gameboard.set(p2, scnRow, scnCol);
                    gameboard.printBoard();
                    gameboard.setPlayerTurn(gameboard.getPlayerTurn()+1);
                }
            }
        }
    }
}
