package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author TODO: YOUR NAME HERE
 *  (3-10 hours) writing the tilt method
 *  will be to figure out which of these methods and instance variables are useful.
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        // 1.modify the board using move(), tile()
        // 2.score += new merged tile's value
        // 3.if any changes, changed = true
        // at least 2 helper methods
        // for non-up, use board.setViewingPerspective(s), as if s is north
        //      restore to north before call to tilt: board.setViewingPerspective(Side.NORTH)
//        for (int c = 0; c < board.size(); c++) {
//            for (int r = 0; r < board.size(); r++) {
//                Tile t = board.tile(c, r);
//                if (board.tile(c, r) != null) {
//                    board.move(c, 3, t);
//                    score += 1;
//                    changed = true;
//                }
//            }
//        }
//        board.setViewingPerspective(side);
//        for (int c = 0; c < board.size(); c++) {
//            align(c);
//            tripleMerge(c);
//            doubleMerge(c);
////            align(c);
////            doubleMerge(c);
//        }
//        board.setViewingPerspective(Side.NORTH);
//        changed = true;
        board.setViewingPerspective(side);
        for (int col = 0; col < board.size(); col++) {
            boolean merged = false;
            for (int row = board.size() - 2; row >= 0; row--) {
                Tile tile = board.tile(col, row);
                if (tile == null) {
                    continue;
                }
                int rowDest = rowDest(col, row, merged);
                if (rowDest != row) {
                    changed = true;
                }
                if (board.move(col, rowDest, tile)) {
                    score += board.tile(col, rowDest).value();
                    merged = true;
                }
            }
        }
        board.setViewingPerspective(Side.NORTH);

        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    public int rowDest(int col, int row, boolean merged) {
//        int col = tile.col();
//        int row = tile.row();
        int rowDest = row;
        try {
            while (board.tile(col, rowDest + 1) == null) {
                rowDest++;
            }
        }
        catch (IndexOutOfBoundsException e) {

        }
        try {
            if (board.tile(col, rowDest + 1) != null && board.tile(col, rowDest + 1).value() == board.tile(col, row).value()) {
                if (!merged) {
                    rowDest++;
                } else if (board.tile(col, rowDest + 2).value() == 2 * board.tile(col, row).value()) {
                    rowDest++;
                }

            }
        }
        catch (IndexOutOfBoundsException e) {

        }
//        if (!merged && board.tile(col, rowDest + 1) != null && board.tile(col, rowDest + 1).value() == tile.value()) {
//            rowDest++;
//        }
//        if (merged && board.tile(col, rowDest + 2) != null && board.tile(col, rowDest + 2).value() == 2 * tile.value()) {
//            rowDest++;
//        }
        return rowDest;
    }
//    public void align(int c) {
//        for (int r = board.size() - 1; r >= 1; r--) {
//            if (board.tile(c, r) == null || board.tile(c, r) == board.tile(c, r - 1)) {
//                if (board.tile(c, r - 1) != null) {
//                    board.move(c, r, board.tile(c, r - 1));
//                }
//            }
//        }
//    }
//
//    boolean merge = true;
//    public void tripleMerge(int c) {
//        for (int r = 3; r >= 1; r--) {
//            if (board.tile(c, r) == null) {
//                return;
//            }
//        }
//        if (board.tile(c, 3).value() == board.tile(c, 2).value() && board.tile(c, 2).value() == board.tile(c, 1).value() || (board.tile(c, 3).value() + board.tile(c, 2).value()) == board.tile(c, 1).value()) {
//            board.move(c, 3, board.tile(c, 2));
//            score += board.tile(c, 3).value();
//            board.move(c, 2, board.tile(c, 1));
//            merge = false;
//        }
//    }
//
//    public void doubleMerge(int c) {
//        for (int r = 0; r <= board.size() - 2; r++) {
//            if (merge && board.tile(c, r) != null && board.tile(c, r).value() == board.tile(c, r + 1).value()) {
//                board.move(c, r + 1, board.tile(c, r));
//                score += board.tile(c, r + 1).value();
//            }
//        }
//    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
        // tile, size from Board class
        int length = b.size();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (b.tile(i, j) == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // TODO: Fill in this function.
        // t.value, MAX_PIECE,
        int length = b.size();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (b.tile(i, j) != null && b.tile(i, j).value() == MAX_PIECE) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // TODO: Fill in this function.
        // at least one empty space, or two adjacent tiles with the same value
        if (emptySpaceExists(b)) {
            return true;
        }

        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size() - 1; j++) {
                if (b.tile(i, j) != null && b.tile(i, j).value() == b.tile(i, j + 1).value()) {
                    return true;
                }
            }
        }
        for (int j = 0; j < b.size(); j++) {
            for (int i = 0; i < b.size() - 1; i++) {
                if (b.tile(i, j) != null && b.tile(i, j).value() == b.tile(i + 1, j).value()) {
                    return true;
                }
            }
        }

        return false;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
