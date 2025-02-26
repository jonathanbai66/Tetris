package tetris1;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * This class is responsible for all piece functionality. This includes piece
 * generation, piece arrangement for each piece's specific coordinates, piece
 * rotation and undoing piece rotation, moving down, getting piece rows and
 * columns, getting and setting piece color, removing pieces, and moving
 * pieces horizontally.
 */

public class Piece {
    private int[][] coordinates;
    private Square[] pieceSquares;
    private Pane gamePane;
    private int centerCol;
    private int centerRow;
    private int[] center;

    /**
     * This is the constructor for the Piece class. This constructor
     * takes in the piece-specific coordinates, gamePane, and the specific
     * center for each piece. The constructor sets the specific piece
     * centers equal to the center array instance, and the center column
     * and center row to 0 and 1, respectively. The gamePane instance of
     * Pane is set equal to the gamePane passed in, and the coordinate array
     * instance equal to the coordinates passed-in. Additionally, the
     * square array that makes up the piece is instantiated, and the
     * pieces are generated and arranged accordingly.
     */

    public Piece(int [][] coords, Pane gamePane, int[] center){
        this.center = center;
        this.centerCol = center[0];
        this.centerRow = center[1];
        this.gamePane = gamePane;
        this.coordinates = coords;
        this.pieceSquares = new Square[4];
        this.generatePiece(gamePane);
        this.arrangePiece();
    }

    /**
     * This method is responsible for generating pieces. This includes
     * looping through the array of squares that makes up the pieces,
     * starting each square at a starting row and column, and passing
     * the gamePane to the square to be added to the gamePane in the
     * square class.
     */
    private void generatePiece(Pane gamePane){
        for(int i = 0; i < this.pieceSquares.length; i++){
            this.pieceSquares[i] = new Square(gamePane, Constants.STARTING_ROW_NUMBER,
                    Constants.STARTING_COLUMN_NUMBER);
        }
    }

    /**
     * This method is responsible for arranging the squares so that
     * they can create specific pieces. This is done by offsetting the
     * center column and center row, and adding the offsets to the
     * coordinates of the squares to make up the specific pieces.
     */
    private void arrangePiece(){
        this.centerCol += Constants.CENTER_COLUMN_OFFSET;
        this.centerRow += Constants.CENTER_ROW_OFFSET;
        /*offsetting the center columns and rows so that the piece
        centers are at the starting coordinates
         */
        for(int i = 0; i < this.coordinates.length; i++){
            this.pieceSquares[i].setCoordinates(Constants.CENTER_COLUMN_OFFSET
                            + this.coordinates[i][0],
                    Constants.CENTER_ROW_OFFSET + this.coordinates[i][1]);
            /*offsetting the coordinates of the squars that make
            up the piece so that the piece starts at the spawning position
             */
        }
    }

    /**
     * This method is responsible for rotating the piece. This is done
     * by using specific math functions to move the piece counter-clockwise.
     * This is done by looping through the array of squares that make up a
     * piece, and setting the new coordinates from the use of
     * the math function. If the center of the piece is the square center,
     * the method will not rotate as the square does not need to be rotated.
     */
    public void rotatePiece(){
        if (this.center == Constants.SQUARE_CENTER){
            //if the center is the square's center, the piece will not rotate
        }
        else {
            for (int i = 0; i < this.pieceSquares.length; i++) {
                this.pieceSquares[i].setCoordinates(this.centerCol +
                                this.centerRow - this.pieceSquares[i].getRow(),
                        this.centerRow - this.centerCol + this.pieceSquares[i].getCol());
                /*for each square in the piece, specific functions
                allow rotation counterclockwise by 90 degrees
                 */
            }
        }
    }

    /**
     * This method is used to reverse a counter-clockwise rotation. This is
     * done by reversing the math functions that rotate the piece counter-
     * clockwise, so that instead, the piece moves clockwise to its original
     * orientation. If the center of the piece is the square center,
     * the method will not rotate as the square does not need to be rotated.
     * */
    public void undoRotate(){
        if (this.center == Constants.SQUARE_CENTER){
        }
        else {
            for (int i = 0; i < this.pieceSquares.length; i++) {
                this.pieceSquares[i].setCoordinates(this.centerCol - this.centerRow
                                + this.pieceSquares[i].getRow(),
                        this.centerRow + this.centerCol - this.pieceSquares[i].getCol());
            }
        }
    }

    /**
     * This method is responsible for moving each square that makes up the
     * piece down. This is done by looping through each of the squares
     * that makes up the piece and shifting them down by the square method
     * down(). Additionally, the center of the piece moves down as well.
     */
    public void moveDown(){
        this.centerRow += 1;
        for(int i = 0; i < this.pieceSquares.length; i++){
            this.pieceSquares[i].down();
        }
    }

    /**
     * This method returns the row coordinates of each square in
     * the piece. This is done by creating a new array in which
     * the rows of the squares in the piece will be copied into,
     * which will be returned.
     */

    public int [] getRowCoords(){
        int [] piecesRow = new int[4];
        for(int i = 0; i < this.pieceSquares.length; i++){
            piecesRow[i] = this.pieceSquares[i].getRow();
        }
        return piecesRow;
    }

    /**
     * This method returns the column coordinates of each square in
     * the piece. This is done by creating a new array in which
     * the column of the squares in the piece will be copied into,
     * which will be returned.
     */
    public int [] getColCoords(){
        int [] piecesCol = new int[4];
        for(int i = 0; i < this.pieceSquares.length; i++){
            piecesCol[i] = this.pieceSquares[i].getCol();
        }
        return piecesCol;
    }

    /**
     * This method is responsible for getting the color of the
     * piece. This is done by returning the color of a square
     * within the array that makes up the piece.
     */

    public Paint getPieceColor(){
        return this.pieceSquares[1].getColor();
    }

    /**
     * This method is responsible for setting the color of the
     * piece. This is done by looping through the array of squares
     * that make up a piece, setting the color of the piece to
     * a color that is passed in elsewhere.
     */
    public void setPieceColor(Color color){
        for(int i = 0; i < this.pieceSquares.length; i++){
            this.pieceSquares[i].setColor(color);
        }
    }

    /**
     * This method is responsible for removing a piece from the
     * gamePane. This is done by looping through the array of
     * squares that make up a piece and calling the
     * removeSquare() method in the square class which
     * removes the square from the gamePane.
     */
    public void removeMe(){
        for(int i = 0; i < this.pieceSquares.length; i++){
            this.pieceSquares[i].removeSquare();
        }
    }

    /**
     * This method is responsible for moving a piece horizontally.
     * This is done by looping through the array of squares that
     * make up a piece, and moving horizontally by a set amount.
     * Additionally, the center column of the center of the piece
     * increments by the same horizontal amount.
     */
    public void moveHorizontally(int moveAmount){
        this.centerCol += moveAmount;
        for(int i = 0; i < this.pieceSquares.length; i++){
            this.pieceSquares[i].moveSide(moveAmount);
        }
    }
}