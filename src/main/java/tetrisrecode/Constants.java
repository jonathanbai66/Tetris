package tetrisrecode;

public class Constants {
    public static final int GAME_WIDTH = 360;
    public static final int GAME_HEIGHT = 660;
    public static final int NUMBER_OF_ROWS = 22;
    public static final int NUMBER_OF_COLUMNS = 12;
    public static final int SQUARE_WIDTH = 30;
    public static final int CONTROL_PANE_DIMENSION = 50;
    public static final int STARTING_ROW_NUMBER = 1;
    public static final int STARTING_COLUMN_NUMBER = 6;
    public static final int CENTER_COLUMN_OFFSET = 5;
    public static final int CENTER_ROW_OFFSET = 1;
    public static final int[][] I_PIECE_COORDS = {{0, 0}, {0, 1}, {0, 2}, {0, 3}};
    public static final int[][] T_PIECE_COORDS = {{0, 0}, {0, 1}, {0, 2}, {1, 1}};
    public static final int[][] SQUARE_PIECE_COORDS = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
    public static final int[][] L_PIECE_COORDS = {{0, 0}, {1, 0}, {0, 1}, {0, 2}};
    public static final int[][] L2_PIECE_COORDS = {{1, 0}, {0, 0}, {1, 1}, {1, 2}};
    public static final int[][] ZIGZAG_PIECE_COORDS = {{0, 0}, {0, 1}, {1, 1}, {1, 2}};
    public static final int[][] ZIGZAG2_PIECE_COORDS = {{0, 1}, {1, 1}, {0, 0}, {-1, 0}};
    public static final int [] I_CENTER = {0,2};
    public static final int [] T_CENTER = {0,1};
    public static final int [] SQUARE_CENTER = {1,1};
    public static final int [] L_CENTER = {0,1};
    public static final int [] L2_CENTER = {0,1};
    public static final int [] ZIGZAG_CENTER = {1,1};
    public static final int [] ZIGZAG2_CENTER = {0,1};
    public static final double DURATION = 0.9;

}
