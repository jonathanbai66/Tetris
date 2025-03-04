package tetrisrecode;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Game {
    private Board board;
    private Pane gamePane;
    private Piece piece;
    private VBox vPane;
    private Button button;
    private Label message;
    boolean isStopped = false;
    boolean isGameOver = false;
    private Piece ghostPiece;
    public Game(Pane gamePane){
        this.gamePane = gamePane;
        this.gamePane.setFocusTraversable(true);
        this.board = new Board(this.gamePane);
        this.setUpTimeline();
        this.spawnPiece();
        this.gamePane.setOnKeyPressed((KeyEvent e) -> this.handleKeyPress(e));
        this.createControlPane();
    }
    private void createControlPane(){
        this.vPane = new VBox();
        this.vPane.setPrefSize(Constants.CONTROL_PANE_DIMENSION,
                Constants.CONTROL_PANE_DIMENSION);
        this.button = new javafx.scene.control.Button("Quit");
        this.vPane.getChildren().add(this.button);
        this.message = new Label(null);
        this.button.setOnAction((ActionEvent e) -> System.exit(0));
//        this.vPane.setFocusTraversable(false);
//        this.button.setFocusTraversable(false);
    }
    public Pane getButtonPane(){
        return this.vPane;
    }
    public void spawnPiece(){
        int randInt = (int)(Math.random() * 7);
        switch (randInt){
            case 0:
                this.piece = new Piece(Constants.I_PIECE_COORDS, this.gamePane, Constants.I_CENTER);
                this.piece.setPieceColor(Color.AQUA);
                break;
            case 1:
                this.piece = new Piece(Constants.T_PIECE_COORDS, this.gamePane, Constants.T_CENTER);
                this.piece.setPieceColor(Color.FUCHSIA);
                break;
            case 2:
                this.piece = new Piece(Constants.SQUARE_PIECE_COORDS, this.gamePane, Constants.SQUARE_CENTER);
                this.piece.setPieceColor(Color.YELLOW);
                break;
            case 3:
                this.piece = new Piece(Constants.L_PIECE_COORDS, this.gamePane, Constants.L_CENTER);
                this.piece.setPieceColor(Color.DEEPSKYBLUE);
                break;
            case 4:
                this.piece = new Piece(Constants.L2_PIECE_COORDS, this.gamePane, Constants.L2_CENTER);
                this.piece.setPieceColor(Color.ORANGE);
                break;
            case 5:
                this.piece = new Piece(Constants.ZIGZAG_PIECE_COORDS, this.gamePane, Constants.ZIGZAG_CENTER);
                this.piece.setPieceColor(Color.RED);
                break;
            case 6:
                this.piece = new Piece(Constants.ZIGZAG2_PIECE_COORDS, this.gamePane, Constants.ZIGZAG2_CENTER);
                this.piece.setPieceColor(Color.GREEN);
                break;
        }
        if (this.checkCollision(0,1, this.piece) && this.checkCollision(0,-1, this.piece) &&
        this.checkCollision(1,0, this.piece) || this.checkCollision(0,0, this.piece)){
            this.gameOver();
        }
        this.createGhostPiece(this.piece);
    }
    private void setUpTimeline(){
        KeyFrame frame1 = new KeyFrame(Duration.seconds(Constants.DURATION),
                (ActionEvent e) -> this.updateTimeline());
        Timeline timeline = new Timeline(frame1);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    private void updateTimeline(){
        if (this.isStopped){
        }
        else {
            if (!this.isGameOver) {
                if (!this.checkCollision(1, 0, this.piece)) {
                    this.piece.moveDown();
                }
                else {
                    this.removeGhostPiece();
                    this.addPiece();
                }
                this.board.checkRows();
            }
        }
    }
    public boolean checkCollision(int rowOffset, int colOffset, Piece thePiece){
        int[] pieceRows = thePiece.getRowCoords();
        int[] pieceCols = thePiece.getColCoords();
        for (int i = 0; i < 4; i++){
            if (this.board.getArray()[pieceRows[i] + rowOffset][pieceCols[i] + colOffset].getColor() != Color.BLACK
                    && this.board.getArray()[pieceRows[i] + rowOffset][pieceCols[i] + colOffset].getColor()
                    != Color.LIGHTGREY){
                return true;
            }
        }
        return false;
    }
    public void addPiece(){
            int[] pieceRows = this.piece.getRowCoords();
            int[] pieceCols = this.piece.getColCoords();
            this.piece.removeMe();
            for (int i = 0; i < 4; i++){
                this.board.getArray()[pieceRows[i]][pieceCols[i]].setColor(this.piece.getPieceColor());
            }
            this.spawnPiece();
    }
    private void handleKeyPress(KeyEvent e){
        KeyCode keyPressed = e.getCode();
        switch(keyPressed){
            case RIGHT:
                if (this.isStopped){
                }
                else {
                    if (!this.checkCollision(0,1, this.piece)){
                        this.piece.moveHorizontally(Constants.MOVE_AMOUNT);
                        this.ghostPiece.moveHorizontally(Constants.MOVE_AMOUNT);
                        this.setGhostPieceRow();
                        this.moveGhostPiece();
                    }
                }
                break;
            case LEFT:
                if (this.isStopped){
                }
                else {
                    if (!this.checkCollision(0,-1, this.piece)){
                        this.piece.moveHorizontally(-Constants.MOVE_AMOUNT);
                        this.ghostPiece.moveHorizontally(-Constants.MOVE_AMOUNT);
                        this.setGhostPieceRow();
                        this.moveGhostPiece();
                    }
                }
                break;
            case DOWN:
                if (this.isStopped){
                }
                else {
                    if (this.checkCollision(1,0, this.piece)) {
                        this.addPiece();
                    }
                    else {
                        this.piece.moveDown();
                    }
                }
                break;
            case SPACE:
                if (this.isStopped){
                }
                else {
                    while (!this.checkCollision(1, 0, this.piece)) {
                        this.piece.moveDown();
                    }
                    this.addPiece();
                    this.board.checkRows();
                }
                break;
            case UP:
                if (this.isStopped){
                }
                else {
                    this.piece.rotatePiece();
                    this.checkRotation(this.piece);
                    this.setGhostPieceRow();
                    this.ghostPiece.rotatePiece();
                    this.checkRotation(this.ghostPiece);
                    this.moveGhostPiece();
                }
                break;
            case P:
                if (!this.isGameOver){
                    if (this.isStopped) {
                        this.isStopped = false;
                        this.gamePane.getChildren().removeAll(this.message);
                    }
                    else {
                        this.isStopped = true;
                        this.gamePane.getChildren().add(this.message = new Label("Paused"));
                        this.message.setFont(new Font(Constants.LABEL_FONT_SIZE));
                        this.message.setTextFill(Color.WHITE);
                    }
                }
        }
        e.consume();
    }
    private void checkRotation(Piece thePiece){
        if (this.checkCollision(0,0, thePiece)){
            thePiece.undoRotate();
        }
    }
    public void gameOver(){
        this.isGameOver = true;
        this.gamePane.getChildren().add(this.message = new Label("Game Over!"));
        this.message.setFont(new Font(Constants.LABEL_FONT_SIZE));
        this.message.setTextFill(Color.WHITE);
        this.isStopped = true;
    }
    public void createGhostPiece(Piece copiedPiece){
        this.ghostPiece = copiedPiece.copyPiece();
        this.ghostPiece.setPieceColor(Color.LIGHTGREY);
        this.moveGhostPiece();
    }
    public void removeGhostPiece(){
        this.ghostPiece.setPieceColor(Color.BLACK);
        this.ghostPiece.removeMe();
    }
    public void moveGhostPiece(){
        if (!this.checkCollision(1, 0, this.ghostPiece)) {
            this.ghostPiece.moveDown();
        }
    }
    public void setGhostPieceRow(){
        this.ghostPiece.setRow(this.piece.getRowCoords(), this.piece.getCenterRow());
    }
}