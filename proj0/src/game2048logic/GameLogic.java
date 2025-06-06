package game2048logic;

import game2048rendering.Side;
import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /** Moves the given tile up as far as possible, subject to the minR constraint.
     *
     * @param board the current state of the board
     * @param r     the row number of the tile to move up
     * @param c -   the column number of the tile to move up
     * @param minR  the minimum row number that the tile can land in, e.g.
     *              if minR is 2, the moving tile should move no higher than row 2.
     * @return      if there is a merge, returns the 1 + the row number where the merge occurred.
     *              if no merge occurs, then return 0.
     */
    public static int moveTileUpAsFarAsPossible(int[][] board, int r, int c, int minR) {
        if (board[r][c] == 0) return 0;  //当前格子无数字直接结束，避免浪费时间

        int currVal = board[r][c];
        int targetR = r;

        //往上滑动直到不能再滑或碰到数字
        while (targetR > minR && board[targetR - 1][c] == 0) {
            targetR--;
        }

        //判断是否可以合并
        if (targetR > minR && board[targetR - 1][c] == currVal) {
            board[targetR - 1][c] *= 2;
            board[r][c] = 0;
            return 1 + (targetR - 1);
        }

        //否则尝试滑动（如果目标位置改变）
        if (targetR != r) {
            board[targetR][c] = currVal;
            board[r][c] = 0;
        }

        return 0; //什么都没发生
    }


    /**
     * Modifies the board to simulate the process of tilting column c
     * upwards.
     *
     * @param board     the current state of the board
     * @param c         the column to tilt up.
     */
    public static void tiltColumn(int[][] board, int c) {
        int minBoard = 0;
        int currRow = 0;
        while (currRow < board.length) {
            minBoard = moveTileUpAsFarAsPossible(board, currRow, c, minBoard);
            currRow++;
        }
    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board     the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        int currColumn = 0;
        while (currColumn < board[0].length) {
            tiltColumn(board, currColumn);
            currColumn++;
        }
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        if (side == Side.EAST) {
            rotateLeft(board);
            tiltUp(board);
            rotateRight(board);
        } else if (side == Side.WEST) {
            rotateRight(board);
            tiltUp(board);
            rotateLeft(board);
        } else if (side == Side.SOUTH) {
            rotateRight(board);
            rotateRight(board);
            tiltUp(board);
            rotateLeft(board);
            rotateLeft(board);
        } else {
            tiltUp(board);
        }
    }
}
