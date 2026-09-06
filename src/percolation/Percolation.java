package src.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n;
    private final boolean[] open;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF fullUf;
    private int openSites;

    private final int virtualTop;
    private final int virtualBottom;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        this.open = new boolean[n * n];

        virtualTop = n * n;
        virtualBottom = n * n + 1;

        uf = new WeightedQuickUnionUF(n * n + 2);
        fullUf = new WeightedQuickUnionUF(n * n + 1);

        openSites = 0;
    }

    public void open(int row, int col) {
        checkIndex(row, col);

        int index = getIndex(row, col);

        if (isOpen(row, col)) {
            return;
        }

        open[index] = true;
        openSites++;

        // Верхняя строка
        if (row == 1) {
            uf.union(index, virtualTop);
            fullUf.union(index, virtualTop);
        }

        // Нижняя строка
        if (row == n) {
            uf.union(index, virtualBottom);
        }

        // Верхний сосед
        if (row > 1 && isOpen(row - 1, col)) {
            unionBoth(index, getIndex(row - 1, col));
        }

        // Нижний сосед
        if (row < n && isOpen(row + 1, col)) {
            unionBoth(index, getIndex(row + 1, col));
        }

        // Левый сосед
        if (col > 1 && isOpen(row, col - 1)) {
            unionBoth(index, getIndex(row, col - 1));
        }

        // Правый сосед
        if (col < n && isOpen(row, col + 1)) {
            unionBoth(index, getIndex(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        checkIndex(row, col);
        return open[getIndex(row, col)];
    }

    public boolean isFull(int row, int col) {
        checkIndex(row, col);

        if (!isOpen(row, col)) {
            return false;
        }

        return fullUf.find(getIndex(row, col)) == fullUf.find(virtualTop);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }

    private int getIndex(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    private void checkIndex(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException();
        }
    }

    private void unionBoth(int first, int second) {
        uf.union(first, second);
        fullUf.union(first, second);
    }
}