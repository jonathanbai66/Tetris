package tetrisrecode;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Square {
    private Rectangle rect;
    private int row;
    private int col;
    private Pane gamePane;

    public Square(Pane gamePane, int row, int col) {
        this.gamePane = gamePane;
        this.createSquare(row, col);
    }

    public void createSquare(int row, int col) {
        this.row = row;
        this.col = col;
        this.rect = new Rectangle(col * Constants.SQUARE_WIDTH, row * Constants.SQUARE_WIDTH,
                Constants.SQUARE_WIDTH, Constants.SQUARE_WIDTH);
        this.rect.setStroke(Color.BLACK);
        this.gamePane.getChildren().add(this.rect);
    }

    public void setColor(Paint color) {
        this.rect.setFill(color);
    }
    public void setCoordinates(int col, int row){
        this.col = col;
        this.row = row;
        this.rect.setX(col * Constants.SQUARE_WIDTH);
        this.rect.setY(row * Constants.SQUARE_WIDTH);
    }
    public void down(){
        this.setCoordinates(this.col, this.row + 1);
    }
    public int getCol(){
        return this.col;
    }
    public int getRow(){
        return this.row;
    }
    public Paint getColor(){
        return this.rect.getFill();
    }
    public void removeSquare(){
        this.gamePane.getChildren().removeAll(this.rect);
    }
    public void moveSide(int moveAmount){
        this.setCoordinates(this.col + moveAmount, this.row);
    }
}
