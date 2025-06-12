import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private boolean[][] grid;
    private final WeightedQuickUnionUF uf;     // 用于 percolates()，连接 virtualTop 和 virtualBottom
    private final WeightedQuickUnionUF fullUF; // 用于 isFull()，只连接 virtualTop，避免回流
    private final int n;
    private final int virtualTop;
    private final int virtualBottom;
    private int opened;

    public Percolation(int N) {
        uf = new WeightedQuickUnionUF(N * N + 2);
        fullUF = new WeightedQuickUnionUF(N * N + 1);
        grid = new boolean[N][N];
        n = N;
        virtualTop = N * N;
        virtualBottom = N * N + 1;
        opened = 0;
    }

    public int xyTo1D(int row, int col) {
        return row * n + col;
    }

    private void validate(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IllegalArgumentException("row or col out of bounds");
        }
    }


    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) return;
        grid[row][col] = true;
        opened++;

        int current = xyTo1D(row, col);

        if (row > 0 && isOpen(row - 1, col)) {
            uf.union(current, xyTo1D(row - 1, col));
            fullUF.union(current, xyTo1D(row - 1, col));
        }
        if (row < n - 1 && isOpen(row + 1, col)) {
            uf.union(current, xyTo1D(row + 1, col));
            fullUF.union(current, xyTo1D(row + 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            uf.union(current, xyTo1D(row, col - 1));
            fullUF.union(current, xyTo1D(row, col - 1));
        }
        if (col < n - 1 && isOpen(row, col + 1)) {
            uf.union(current, xyTo1D(row, col + 1));
            fullUF.union(current, xyTo1D(row, col + 1));
        }

        if (row == 0) {
            uf.union(current, virtualTop);
            fullUF.union(current, virtualTop);
        }
        if (row == n - 1) {
            uf.union(current, virtualBottom);
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return fullUF.connected(xyTo1D(row, col), virtualTop);
    }

    public int numberOfOpenSites() {
        return opened;
    }

    public boolean percolates() {
        return uf.connected(virtualTop, virtualBottom);
    }
}
