package com.company;

public class Gameboard {

    final Integer ROWS = 11; //changed the initial size of 3 to 11 to increase the board
    final Integer COLS = 11;
    final Integer EMPTY = 0;
    final Integer NOUGHT = 1;
    final Integer CROSS = 2;
    private final String[][] visualBoard = new String[ROWS][COLS];
    private int[][] board = new int[ROWS][COLS];
    private int playerTurn = 1;//if ungerade player one if gerade player 2 starting to count with 1

    public Gameboard() {
        this.board = board;
        this.playerTurn = playerTurn;
    }

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

    void checkField(int row, int col){ //überprüft ob ein Player auf eine bestimmte Stelle (x,y) im Spielfeld setzen darf.
        row = boardRowAndColConverter(row); //in gameloop when i save the value with a scanner 1 time
        col = boardRowAndColConverter(col);

        if (board[col][row] == NOUGHT || board[col][row] == CROSS) {
            System.out.println("There is already a symbol placed");
        } else {
            System.out.println("Please enter a valid number between 1 - 3");
        }
    }

    void set(Player p, int row, int col){
        //TODO setzt das entsprechende Zeichen des Spielers auf das Spielbrett.
        row = boardRowAndColConverter(row);
        col = boardRowAndColConverter(col);

        if (board[row][col] != NOUGHT && board[row][col] != CROSS){
            board[row][col] = p.getSymbol();
        }
    }

    boolean checkIfWon(Player p){
        //TODO zum  Überprüfen  ob  der Spieler X oder O gewonnen hat.
        return true;
    }

    void reset(){
        //TODO setzt alles wieder auf Anfang.
    }
}
