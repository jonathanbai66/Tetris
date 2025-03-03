package tetrisrecode;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Piece {
    private int[][] coordinates;
    private Square[] pieceSquares;
    private Pane gamePane;
    private int centerCol;
    private int centerRow;
    private int[] center;
    public Piece(int[][] coords, Pane gamePane, int[] center){
        this.center = center;
        this.centerCol = center[0];
        this.centerRow = center[1];
        this.gamePane = gamePane;
        this.coordinates = coords;
        this.pieceSquares = new Square[4];
        this.generatePiece(gamePane);
        this.arrangePiece();
    }
    private void generatePiece(Pane gamePane){
        for(int i = 0; i < this.pieceSquares.length; i++){
            this.pieceSquares[i] = new Square(gamePane, Constants.STARTING_ROW_NUMBER,
                    Constants.STARTING_COLUMN_NUMBER);
        }
    }
    private void arrangePiece(){
        this.centerCol += Constants.CENTER_COLUMN_OFFSET;
        this.centerRow += Constants.CENTER_ROW_OFFSET;
        for(int i = 0; i < this.coordinates.length; i++){
            this.pieceSquares[i].setCoordinates(Constants.CENTER_COLUMN_OFFSET + this.coordinates[i][0],
                    Constants.CENTER_ROW_OFFSET + this.coordinates[i][1]);
        }
    }
    public Paint getPieceColor(){
        return this.pieceSquares[1].getColor();
    }
    public void setPieceColor(Color color){
        for(int i = 0; i < this.pieceSquares.length; i++){
            this.pieceSquares[i].setColor(color);
        }
    }
    public void moveDown(){
        this.centerRow += 1;
        for(int i = 0; i < this.pieceSquares.length; i++){
            this.pieceSquares[i].down();
        }
    }
    public int[] getColCoords(){
        int[] piecesCol = new int[4];
        for(int i = 0; i < this.pieceSquares.length; i++){
            piecesCol[i] = this.pieceSquares[i].getCol();
        }
        return piecesCol;
    }
    public int[] getRowCoords(){
        int[] piecesRow = new int[4];
        for(int i = 0; i < this.pieceSquares.length; i++){
            piecesRow[i] = this.pieceSquares[i].getRow();
        }
        return piecesRow;
    }
    public void removeMe(){
        for (int i = 0; i < this.pieceSquares.length; i++){
            this.pieceSquares[i].removeSquare();
        }
    }
    public void moveHorizontally(int moveAmount){
        this.centerCol += moveAmount;
        for (int i = 0; i < this.pieceSquares.length; i++){
            this.pieceSquares[i].moveSide(moveAmount);
        }
    }
    public void rotatePiece(){
        if (this.center == Constants.SQUARE_CENTER){
        }
        else {
            for (int i = 0; i < this.pieceSquares.length; i++){
                this.pieceSquares[i].setCoordinates(this.centerCol + this.centerRow - this.pieceSquares[i].getRow(),
                        this.centerRow - this.centerCol + this.pieceSquares[i].getCol());
            }
        }
    }
    public void undoRotate(){
        if (this.center == Constants.SQUARE_CENTER){
        }
        else {
            for (int i = 0; i < this.pieceSquares.length; i++){
                this.pieceSquares[i].setCoordinates(this.centerCol - this.centerRow + this.pieceSquares[i].getRow(),
                        this.centerRow + this.centerCol - this.pieceSquares[i].getCol());
            }
        }
    }
}