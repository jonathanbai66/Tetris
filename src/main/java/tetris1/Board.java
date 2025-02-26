package tetris1;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
public class Board {
    private Square[][] squares;
    private Pane gamePane;

    /**
     * Constructs the board logically and graphically.
     *
     * @param gamePane the pane on which to add the board
     */

    /**
     * This is the constructor for the board. This constructor takes in the
     * gamePane, instantiates and sets the instance variable of the Pane equal
     * to the gamePane, instantiates the squares array instance with a
     * specified number of rows and columns, and creates the board.
     */

    public Board(Pane gamePane) {
        this.gamePane = gamePane;
        this.squares = new Square[Constants.NUMBER_OF_ROWS]
                [Constants.NUMBER_OF_COLUMNS];
        this.createBoard();
    }

    /**
     * This method is responsible for creating the board. This is done using
     * two for loops, one for the rows and one for the columns. If the row
     * is the 0th or 21st row, or if the column is the 0th or 11th column,
     * the corresponding squares will be grey. This creates the border of
     * the board. If not, the squares will be black, creating the inside
     * of the board.
     */
    public void createBoard(){
        for (int row = 0; row < Constants.NUMBER_OF_ROWS; row++) {
            for (int col = 0; col < Constants.NUMBER_OF_COLUMNS; col++) {
                if (row == 0 || row == 21 || col == 0 || col == 11){
                    this.squares[row][col] = new Square(this.gamePane, row, col);
                    this.squares[row][col].setColor(Color.GREY);
                    /*ensuring the border of grey squares is created for specific
                    rows and columns.
                     */
                }
                else{
                    this.squares[row][col] = new Square(this.gamePane, row, col);
                    this.squares[row][col].setColor(Color.BLACK);
                }
            }
        }
    }

    /**
     * This method checks the rows to see if there is a full row of colored
     * squares. This is done by using a variable "counter" to count the
     * number of colored squares in each row of the board. If the counter
     * is 10, which is the number of colored squares within a row excluding
     * grey border squares, then the colored squares are colored to black.
     * Then, the checkCols method is called, taking in the cleared row
     * number, to move onto the next step of line-clearing: moving
     * the colored squares above down.
     */

    public void checkRows(){
        for (int row = 0; row < Constants.NUMBER_OF_ROWS; row++) {
            int counter = 0;
            for (int col = 0; col < Constants.NUMBER_OF_COLUMNS; col++) {
                if (this.squares[row][col].getColor() != Color.BLACK &&
                        this.squares[row][col].getColor() != Color.GREY){
                    /*making sure the squares in question are not part of
                    the board, made up of black and grey squares.
                     */
                    counter ++;
                }
            }
            if (counter == 10){
                for (int col = 1; col < 10; col++){
                    this.squares[row][col].setColor(Color.BLACK);
                }
                this.checkCols(row);
                //passing in the fully colored row to the checkCols method
            }
        }
    }

    /**
     * This method is responsible for moving colored squares above the full row
     * down. The method runs through the rows above the full row, and runs through
     * the columns of the board. The method then sets the lower squares' colors
     * equal to the squares' colors.
     */

    public void checkCols(int stopRow){
        for (int row = stopRow - 1; row > 0; row--) {
            /*going from the full row to the rows above so that
            the next loop does not continuously set all lower
            squares to the color of the square
             */
            for (int col = 0; col < Constants.NUMBER_OF_COLUMNS; col++) {
                if (this.squares[row][col].getColor() != Color.GREY){
                    this.squares[row + 1][col].setColor(this.squares[row][col].getColor());
                    //setting the below square to the color of the square
                }
            }
        }
    }

    /**
     * This method is responsible for allowing access to the board array, made up
     * of squares. This board array is specifically accessed in the Game class,
     * to determine collisions.
     */

    public Square[][] getArray(){
        return this.squares;
    }
}