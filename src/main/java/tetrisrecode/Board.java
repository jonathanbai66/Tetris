package tetrisrecode;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Board {
    private Pane gamePane;
    private Square[][] squares;

    public Board (Pane gamePane) {
        this.gamePane = gamePane;
        this.squares = new Square [Constants.NUMBER_OF_ROWS][Constants.NUMBER_OF_COLUMNS];
        this.createBoard();
    }
    public void createBoard(){
        for (int row = 0; row < Constants.NUMBER_OF_ROWS; row++){
            for (int col = 0; col < Constants.NUMBER_OF_COLUMNS; col++){
                if (row == 0 || row == 21 || col == 0 || col == 11){
                    this.squares[row][col] = new Square(this.gamePane, row, col);
                    this.squares[row][col].setColor(Color.GREY);
                }
                else {
                    this.squares[row][col] = new Square(this.gamePane, row, col);
                    this.squares[row][col].setColor(Color.BLACK);
                }
            }
        }
    }
}
