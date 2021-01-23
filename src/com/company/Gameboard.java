package com.company;

import java.util.Scanner;

public class Gameboard {
    final Integer ROWS = 11;
    final Integer COLS = 11;
    final Integer EMPTY = 0;
    final Integer NOUGHT = 1;
    final Integer CROSS = 2;
    private final String[][] visualBoard = new String[ROWS][COLS];
    private final int[][] logicBoard = new int[ROWS][COLS];
    private int playerTurn = 1;
    private int symbolCounter = 0;

    void printBoard(){ //print out the console based board
        for(int i = 0; i < visualBoard.length;i++){
            for(int j = 0; j < visualBoard.length;j++){
                if (j % 4 == 3 && i % 4 == 3){
                    visualBoard[i][j] = "+";
                } else if (i % 4 == 3){
                    visualBoard[i][j] = "-";
                } else if (j % 4 == 3){
                    visualBoard[i][j] = "|";
                } else if (logicBoard[i][j] == 1){
                    visualBoard[i][j] = "O";
                } else if (logicBoard[i][j] == 2){
                    visualBoard[i][j] = "X";
                } else{
                    visualBoard[i][j] = " ";
                }
                System.out.print(visualBoard[i][j]);
            }
            System.out.println();
        }
    }

    private int boardRowAndColToVisualBoardConverter(int value){ // this bigger board has to be adapted with user input
        if (value == 1){
            return value;
        }
        else if (value == 2){
            return 5;
        }
        else if (value == 3){
            return 9;
        } else {
            return value;
        }
    }

    String checkField(int row, int col){ //check if one player can set a certain symbol onto a certain field (x,y)
        String message = "True";

        if (logicBoard[row][col] == NOUGHT || logicBoard[row][col] == CROSS) {
            return "There is already a symbol placed. Try again.";
        } else if (logicBoard[row][col] >= 1 && logicBoard[row][col] <= 3) {
            return "Please enter a valid number between 1 - 3";
        } else {
            return message;
        }
    }

    void set(Player p, int row, int col){ //set the symbol onto the board
        if (logicBoard[row][col] != NOUGHT && logicBoard[row][col] != CROSS){
            logicBoard[row][col] = p.getSymbol();
            setSymbolCounter(getSymbolCounter()+1);
        }
    }

    boolean checkIfWon(Player p){ //TODO maybe a better way to code it?
        int sym = p.getSymbol();

        if (logicBoard[1][1] == sym && logicBoard[1][5] == sym && logicBoard[1][9] == sym){
            return true;
        } else if (logicBoard[5][1] == sym && logicBoard[5][5] == sym && logicBoard[5][9] == sym){
            return true;
        } else if (logicBoard[9][1] == sym && logicBoard[9][5] == sym && logicBoard[9][9] == sym){
            return true;
        } else if (logicBoard[1][1] == sym && logicBoard[5][1] == sym && logicBoard[9][1] == sym){
            return true;
        } else if (logicBoard[1][5] == sym && logicBoard[5][5] == sym && logicBoard[9][5] == sym){
            return true;
        } else if (logicBoard[1][9] == sym && logicBoard[5][9] == sym && logicBoard[9][9] == sym){
            return true;
        } else if (logicBoard[1][1] == sym && logicBoard[5][5] == sym && logicBoard[9][9] == sym){
            return true;
        } else if (logicBoard[9][1] == sym && logicBoard[5][5] == sym && logicBoard[1][9] == sym){
            return true;
        } else {
            return false;
        }
    }

    void reset(){ //clear the board for another round
        for(int i = 0; i < logicBoard.length; i++){
            for(int j = 0; j < logicBoard.length; j++){
                logicBoard[i][j] = EMPTY;
            }
        }
    }

    void printLeaderboard(Player p1, Player p2){
        System.out.println("+++++++++LEADERBOARD+++++++++");
        System.out.println("Victories of p1: " + p1.getVictories());
        System.out.println("Victories of p2: " + p2.getVictories());
        System.out.println("+++++++++++++++++++++++++++++");
    }

    public boolean gameplayWinOrLose(Scanner scn, Player p, int scnRow, int scnCol) {
        String fieldMessage = checkField(scnRow, scnCol);

        if (scnRow >= 1 && scnRow <= 3 && scnCol >= 1 && scnCol <= 3) {
            scnRow = boardRowAndColToVisualBoardConverter(scnRow);
            scnCol = boardRowAndColToVisualBoardConverter(scnCol);
            if (fieldMessage.equals("True")) {
                set(p, scnRow, scnCol);
                printBoard();
                setPlayerTurn(getPlayerTurn() + 1);
                if (checkIfWon(p)) {
                    System.out.println("p" + p.getSymbol() + " won!");
                    p.setVictories(p.getVictories() + 1);
                    restartGameOrLeave(scn);
                }
            } else {
                System.out.println(fieldMessage);
            }
        } else {
            System.out.println("--!!! Please enter a valid number between 1-3 !!!--");
        }
        return false;
    }

    public boolean restartGameOrLeave(Scanner scn){
        String scnReset;
        System.out.println("Wanna play again? Type in 'y' or if you wanna leave press any other key");
        scn.nextLine();
        scnReset = scn.nextLine();
        if (scnReset.equals("y")) {
            reset();
            printBoard();
            setSymbolCounter(0);
            return false;
        } else {
            return true;
        }
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int getSymbolCounter() {
        return symbolCounter;
    }

    public void setSymbolCounter(int symbolCounter) {
        this.symbolCounter = symbolCounter;
    }

}
