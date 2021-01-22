package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameLoop {

    public static void main(String[] args) {
	    Gameboard gameboard = new Gameboard();
        Scanner scn = new Scanner(System.in);
        Player p1 = new Player();
        Player p2 = new Player();

        int symbol;
        int row;
        int col;

        System.out.println("Choose one of those numbers provided: 1)O or 2)X");

        while (true){
            try{
                symbol = scn.nextInt();
            } catch (InputMismatchException e){
                String bad_input = scn.next();
                System.out.println("Invalid input! Only the numbers 1 or 2 are valid! Try again.");
                continue;
            }

            if (symbol == 1 || symbol == 2){
                p1.setSymbol(symbol);
                gameboard.printBoard();
                try{
                    System.out.println("Player enter the row and column you want to place your symbol:");
                    System.out.println("row:");
                    row = scn.nextInt();
                    System.out.println("col:");
                    col = scn.nextInt();
                } catch (InputMismatchException e){
                    String bad_input = scn.next();
                    System.out.println("Invalid input! Try again.");
                    continue;
                }
                if (row >= 1 && row <=3  && col >= 1 && col <= 3){
                    gameboard.set(p1, row, col);
                    gameboard.printBoard();
                }

            } else {
                System.out.println("Invalid number! Choose between 1 for O and 2 for X!");
                continue;
            }

        }


    }
}
