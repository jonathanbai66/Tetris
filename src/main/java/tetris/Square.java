package tetris;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * This class is responsible for all square functionality.
 * This includes square creation, square removal, setting and
 * getting square color, setting square coordinates, moving
 * squares downward, getting specific square rows and columns,s
 * and moving squares horizontally.
 */
public class Square {
    private Rectangle rect;
    private int row;
    private int col;
    private Pane gamePane;

    /**
     * This is the constructor for the Square class. This
     * constructor takes in the gamePane and a row and column,
     * sets the instance of Pane called gamePane equal to the
     * gamePane, and calls the createSquare() method.
     */
    public Square(Pane gamePane, int row, int col) {
        this.gamePane = gamePane;
        this.createSquare(row, col);
    }

    /**
     * This method creates the squares. By passing in a row and
     * column, this method sets the integers row and col equal
     * to the inputted row and column, setting the position of
     * the square. This method then creates a new square out of
     * a rectangle and specific row and column locations. The
     * new square is then added to the gamePane.
     */
    public void createSquare(int row, int col){
        this.row = row;
        this.col = col;
        this.rect = new Rectangle(col * Constants.SQUARE_WIDTH, row * Constants.SQUARE_WIDTH,
                Constants.SQUARE_WIDTH, Constants.SQUARE_WIDTH);
        this.rect.setStroke(Color.BLACK);
        //setting the outlines of a square to black
        this.gamePane.getChildren().add(this.rect);
    }

    /**
     * This method is used to remove squares from the gamePane.
     */
    public void removeSquare(){
        this.gamePane.getChildren().removeAll(this.rect);
    }

    /**
     * This method is used to set the fill color of a square, by
     * passing in a specific paint color.
     */
    public void setColor(Paint color){
        this.rect.setFill(color);
    }

    /**
     * This method is used to get the color of a square. This is
     * done by returning the specific square's fill color.
     */
    public Paint getColor(){
        return this.rect.getFill();
    }

    /**
     * This method is used to set the coordinates of the square.
     * This method is especially important because it sets the X and
     * Y values of the square in terms of pixels. Thus, the entire
     * game of squares can be in terms of rows and columns, as long as
     * the rows and columns are expressed in terms of pixels via this
     * method.
     */

    public void setCoordinates(int col, int row){
        this.col = col;
        this.row = row;
        this.rect.setX(col * Constants.SQUARE_WIDTH);
        this.rect.setY(row * Constants.SQUARE_WIDTH);
    }

    /**
     * This method moves a square down by one row.
     */
    public void down(){
        this.setCoordinates(this.col, this.row + 1);
    }

    /**
     * This method returns the row number of a square.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * This method returns the column number of a square.
     */
    public int getCol() {
        return this.col;
    }

    /**
     * This method moves a square horizontally by a specific
     * amount that is passed in.
     */
    public void moveSide(int moveAmount){
        this.setCoordinates(this.col + moveAmount, this.row);
    }
}

