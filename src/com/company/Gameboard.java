package com.company;

import java.util.Scanner;

public class Gameboard {
    final Integer ROWS = 11;
    final Integer COLS = 11;
    final Integer EMPTY = 0;
    final Integer NOUGHT = 1;
    final Integer CROSS = 2;
    private final String[][] visualBoard = new String[ROWS][COLS];
    private final int[][] board = new int[ROWS][COLS];
    private int playerTurn = 1;

    void printBoard(){ //gibt das aktuelle Spielbrett auf der Konsole aus.
        for(int i = 0; i < visualBoard.length;i++){
            for(int j = 0; j < visualBoard.length;j++){
                if (j % 4 == 3 && i % 4 == 3){
                    visualBoard[i][j] = "+";
                } else if (i % 4 == 3){
                    visualBoard[i][j] = "-";
                } else if (j % 4 == 3){
                    visualBoard[i][j] = "|";
                } else if (board[i][j] == 1){
                    visualBoard[i][j] = "O";
                } else if (board[i][j] == 2){
                    visualBoard[i][j] = "X";
                } else{
                    visualBoard[i][j] = " ";
                }
                System.out.print(visualBoard[i][j]);
            }
            System.out.println();
        }
    }

    private int boardRowAndColConverter(int value){ //bigger board has to adapt with user input
        if (value == 1){
            value = 1;
        }
        else if (value == 2){
            value = 5;
        }
        else if (value == 3){
            value = 9;
        }
        return value;
    }

    String checkField(int row, int col){ //überprüft ob ein Player auf eine bestimmte Stelle (x,y) im Spielfeld setzen darf.
        String message = "True";
        row = boardRowAndColConverter(row);
        col = boardRowAndColConverter(col);

        if (board[col][row] == NOUGHT || board[col][row] == CROSS) {
            return "There is already a symbol placed. Try again.";
        } else if (board[col][row] >= 1 && board[col][row] <= 3) {
            return "Please enter a valid number between 1 - 3";
        } else {
            return message;
        }
    }

    void set(Player p, int row, int col){ //setzt das entsprechende Zeichen des Spielers auf das Spielbrett.
        row = boardRowAndColConverter(row);
        col = boardRowAndColConverter(col);

        if (board[row][col] != NOUGHT && board[row][col] != CROSS){
            board[row][col] = p.getSymbol();
        }
    }

    boolean checkIfWon(Player p){ //TODO zum  Überprüfen  ob  der Spieler X oder O gewonnen hat.
        int sym = p.getSymbol();

        if (board[1][1] == sym && board[1][5] == sym && board[1][9] == sym){
            return true;
        } else if (board[5][1] == sym && board[5][5] == sym && board[5][9] == sym){
            return true;
        } else if (board[9][1] == sym && board[9][5] == sym && board[9][9] == sym){
            return true;
        } else if (board[1][1] == sym && board[5][1] == sym && board[9][1] == sym){
            return true;
        } else if (board[1][5] == sym && board[5][5] == sym && board[9][5] == sym){
            return true;
        } else if (board[1][9] == sym && board[5][9] == sym && board[9][9] == sym){
            return true;
        } else if (board[1][1] == sym && board[5][5] == sym && board[9][9] == sym){
            return true;
        } else if (board[9][1] == sym && board[5][5] == sym && board[1][9] == sym){
            return true;
        } else {
            return false;
        }
    }

    void reset(){  //TODO setzt alles wieder auf Anfang.
        for(int i = 0; i < board.length;i++){
            for(int j = 0; j < board.length;j++){
                board[i][j] = EMPTY;
            }
        }
    }

    public boolean gameplay(Scanner scn, Player p, int scnRow, int scnCol) {
        String scnReset;
        if (scnRow >= 1 && scnRow <= 3 && scnCol >= 1 && scnCol <= 3) {
            String field = checkField(scnRow, scnCol);
            if (field.equals("True")) {
                set(p, scnRow, scnCol);
                printBoard();
                setPlayerTurn(getPlayerTurn() + 1);
                if (checkIfWon(p)) {
                    System.out.println("p" + p.getSymbol() + " won!");
                    p.setVictories(p.getVictories() + 1);
                    System.out.println("Wanna play again? Type in y");
                    scn.nextLine();
                    scnReset = scn.nextLine();
                    if (scnReset.equals("y")) {
                        reset();
                        printBoard();
                    } else {
                        //printLeaderboard(p1, p2);
                        return true;
                    }
                }
            } else {
                System.out.println(field);
            }
        } else {
            System.out.println("--!!!--Please enter a valid number between 1 - 3--!!!--");
        }
        return false;
    }

    void printLeaderboard(Player p1, Player p2){
        System.out.println("++++++++++VICTORIES++++++++++");
        System.out.println("Victories of p1: " + p1.getVictories());
        System.out.println("Victories of p2: " + p2.getVictories());
        System.out.println("+++++++++++++++++++++++++++++");
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }
}
