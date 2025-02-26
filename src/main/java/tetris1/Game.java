package tetris1;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;

/**
 * This is the top level logical class of the tetris program. This class
 * deals with the main functionality of the program, and handles timelines,
 * keyEvents, piece spawning, collisions, panes and labels.
 */
public class Game {
    private Board board;
    private Pane gamePane;
    private Piece piece;
    private VBox vPane;
    private Button button;
    boolean isStopped = false;
    private Label message;
    boolean isGameOver = false;

    /**
     * This is the constructor for the Game class. This constructor sets
     * the gamePane equal to the instance of Pane, instantiates
     * the board instance while passing in the gamePane into the board,
     * calling the timeline setup, spawning the first piece, and
     * calling the control pane creation.
     */

    public Game(Pane gamePane) {
        this.gamePane = gamePane;
        this.gamePane.setFocusTraversable(true);
        this.board = new Board(this.gamePane);
        this.setUpTimeline();
        this.spawnPiece();
        this.gamePane.setOnKeyPressed((KeyEvent e) -> this.handleKeyPress(e));
        this.createControlPane();
    }

    /**
     * This method sets up the timeline for tetris using a keyframe and a
     * lambda expression based on an ActionEvent.
     */
    private void setUpTimeline() {
        KeyFrame frame1 = new KeyFrame(Duration.seconds(Constants.DURATION),
                (ActionEvent e) -> this.updateTimeline());
        Timeline timeline = new Timeline(frame1);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * This method is responsible for repeating a series of functions
     * as the timeline continues. This includes checking if the
     * game has stopped, in which nothing happens or can happen with
     * the current situation, potentially adding a piece to the board,
     * moving pieces downwards if they can, and checking for line-clearing.
     */

    public void updateTimeline() {
        if (this.isStopped){
        }
        else {
            this.addPiece();
            if (!this.isGameOver){
                if (!this.checkCollision(1,0)) {
                    this.piece.moveDown();
                    /*only moving a piece downwards if there is no collision
                    in the downwards direction
                     */
                }
                this.board.checkRows();
            }
        }
    }

    /**
     * This method is responsible for spawning pieces. This is done via a
     * switch statement, and a random integer generator. This method passes
     * the piece-specific coordinates into the new piece, and colors the piece
     * accordingly. If a piece is spawned but cannot move, aka if it
     * has collided in all possible ways, or if the piece collides exactly
     * where it is spawned, then the gameOver method is called in which
     * the game will stop.
     */

    public void spawnPiece() {
        int randInt = (int) (Math.random() * 7);
        switch (randInt) {
            case 0:
                this.piece = new Piece(tetris1.Constants.I_PIECE_COORDS,
                        this.gamePane, Constants.I_CENTER);
                this.piece.setPieceColor(Color.AQUA);
                break;
            case 1:
                this.piece = new Piece(Constants.T_PIECE_COORDS, this.gamePane,
                        Constants.T_CENTER);
                this.piece.setPieceColor(Color.FUCHSIA);
                break;
            case 2:
                this.piece = new Piece (Constants.SQUARE_PIECE_COORDS, this.gamePane,
                        Constants.SQUARE_CENTER);
                this.piece.setPieceColor(Color.YELLOW);
                break;
            case 3:
                this.piece = new Piece (Constants.L_PIECE_COORDS, this.gamePane,
                        Constants.L_CENTER);
                this.piece.setPieceColor(Color.DEEPSKYBLUE);
                break;
            case 4:
                this.piece = new Piece (Constants.L2_PIECE_COORDS, this.gamePane,
                        Constants.L2_CENTER);
                this.piece.setPieceColor(Color.ORANGE);
                break;
            case 5:
                this.piece = new Piece (Constants.ZIGZAG_PIECE_COORDS, this.gamePane,
                        Constants.ZIGZAG_CENTER);
                this.piece.setPieceColor(Color.RED);
                break;
            case 6:
                this.piece = new Piece (Constants.ZIGZAG2_PIECE_COORDS, this.gamePane,
                        Constants.ZIGZAG2_CENTER);
                this.piece.setPieceColor(Color.GREEN);
        }
        if (this.checkCollision(0,1) &&
                this.checkCollision(0,-1) &&
                this.checkCollision(1,0) || this.checkCollision(0,0)){
            this.gameOver();
            /*if the piece spawns and collides in any way, or if the spawning point causes the
            piece to instantly collide, the gameOver method is called.
             */
        }
    }

    /**
     * This method is responsible for handling what happens when the
     * game is deemed over. This method sets the label text, font size,
     * and color, and sets the booleans isGameOver and isStopped to "true",
     * which result in other functions, such as keypresses, to disable.
     */
    public void gameOver(){
        this.isGameOver = true;
        this.gamePane.getChildren().add(this.message = new Label("Game Over"));
        this.message.setFont(new Font(Constants.LABEL_FONT_SIZE));
        this.message.setTextFill(Color.WHITE);
        this.isStopped = true;
        //used to disable keypresses
    }

    /**
     * This method is responsible for determining what happens when the player
     * presses certain keys, via a switch statement. This includes moving the
     * piece to the right and left, moving the piece down, moving the piece
     * down as far as it can go, and rotating the piece. This is only
     * possible if the game has not stopped. This method also includes
     * the pause function, which allows for play-pause functionality,
     * and diplays a message on the label.
     */

    private void handleKeyPress(KeyEvent e) {
        KeyCode keyPressed = e.getCode();
        switch(keyPressed){
            case RIGHT:
                if (this.isStopped){
                    //keypress will only work if the game has not stopped
                }
                else {
                    if (!this.checkCollision(0, 1)) {
                            /*keypress will only work if there is no upcoming
                            rightward collision
                             */
                        this.piece.moveHorizontally(Constants.MOVE_RIGHT_AMOUNT);
                    }
                }
                break;
            case LEFT:
                if (this.isStopped){
                }
                else {
                    if (!this.checkCollision(0, -1)) {
                            /*keypress will only work if there is no upcoming
                            leftward collision
                             */
                        this.piece.moveHorizontally(Constants.MOVE_LEFT_AMOUNT);
                    }
                }
                break;
            case DOWN:
                if (this.isStopped){
                }
                else {
                    this.addPiece();
                    this.piece.moveDown();
                }
                break;
            case SPACE:
                if (this.isStopped){
                }
                else {
                    for (int i = 0; i < 20; i++) {
                        //prevents infinite loop
                        if (this.checkCollision(1, 0)) {
                            this.addPiece();
                            //adds the piece to the board instantly
                            break;
                        }
                        this.piece.moveDown();
                    }
                }
                break;
            case UP:
                if (this.isStopped){
                }
                else {
                    this.piece.rotatePiece();
                    this.checkRotation();
                    //checks to see if the piece is able to rotate
                }
                break;
            case P:
                if (!this.isGameOver) {
                    //makes sure the game cannot be paused if it is game over
                    if (this.isStopped) {
                        this.isStopped = false;
                        //allows pressing "P" again to play the game
                        this.gamePane.getChildren().removeAll(this.message);
                    } else {
                        this.isStopped = true;
                        //allows pressing "P" again to play the game
                        this.gamePane.getChildren().add(this.message =
                                new Label("Paused"));
                        this.message.setFont
                                (new Font(Constants.LABEL_FONT_SIZE));
                        this.message.setTextFill(Color.WHITE);
                        //adding a message to the label, of specific font and color
                    }
                }
        }
        e.consume();
    }

    /**
     * This method is responsible for checking if a piece has collided or not.
     * This is done by getting in the specific row and column coordinates of
     * the piece's squares from the piece class, adding the specific offsets
     * to the coordinates, and looping through these piece squares to determine
     * if they are not black at the offset coordinate point. If the piece is not
     * black at that specific offset, then the piece has collided and the method
     * returns "true". If not, the method returns "false".
     */
    public boolean checkCollision(int rowOffset, int colOffset){
        //passing in specific row and column offsets
        int[] pieceRows = this.piece.getRowCoords();
        int[] pieceCols = this.piece.getColCoords();
        for (int i = 0; i < 4; i++) {
            if (this.board.getArray()[pieceRows[i] + rowOffset][pieceCols[i] +
                    colOffset].getColor() != Color.BLACK) {
                return true;
                //returning "true" if the offsetted piece coordinate is not black.
            }
        }
        return false;
    }

    /**
     * This method is responsible for checking if a piece rotation will cause
     * a collision. This is done by calling the checkCollision method for the
     * exact location of the piece, denoted by 0 offsets. If the rotated piece
     * collides, the piece will undo the rotation that caused the collision.
     *
     */
    public void checkRotation(){
        if (this.checkCollision(0,0)){
            //checking the collision of the exact location of the piece
            this.piece.undoRotate();
        }
    }

    /**
     * This method adds a piece to the board. This is done by checking if the
     * piece has collided with another non-black piece below. If so, the piece
     * is removed from the gamePane and added to the board array, via coloring
     * the corresponding board squares the color of the collided piece.
     */
    public void addPiece() {
        if (this.checkCollision(1, 0)) {
            //checking the collision of one square below the piece
            int[] pieceRows = this.piece.getRowCoords();
            int[] pieceCols = this.piece.getColCoords();
            this.piece.removeMe();
            /*removing the piece from the gamePane via a series of methods,
            from the piece class to the square class, which removes the square
             */
            for (int j = 0; j < 4; j++) {
                this.board.getArray()[pieceRows[j]][pieceCols[j]].setColor
                        (this.piece.getPieceColor());
                /*coloring the corresponding board squares with the color\
                of the piece
                */
            }
            this.board.checkRows();
            this.spawnPiece();
            /*checking if a line is full, and spawning a new piece
            once the piece has collided
             */
        }
    }

    /**
     * Creates the Pane on the top that includes the quit button, and the label
     * that displays the game over and pause message. It also handles the process
     * of quitting itself.
     */
    private void createControlPane() {
        this.vPane = new VBox();
        this.vPane.setPrefSize(Constants.CONTROL_PANE_DIMENSION,
                Constants.CONTROL_PANE_DIMENSION);
        this.button = new javafx.scene.control.Button("Quit");
        this.vPane.getChildren().add(this.button);
        this.message = new Label(null);
        this.button.setOnAction((ActionEvent e) -> System.exit(0));
        this.vPane.setFocusTraversable(false);
        this.button.setFocusTraversable(false);
    }

    /**
     * Returns the button pane to the PaneOrganizer class.
     */
    public Pane getButtonPane() {
        return this.vPane;
    }
}