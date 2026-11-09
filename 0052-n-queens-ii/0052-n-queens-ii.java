
class Solution {
    int count = 0;

    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] diagonal1 = new boolean[2 * n - 1];
        boolean[] diagonal2 = new boolean[2 * n - 1];

        solve(0, n, cols, diagonal1, diagonal2);

        return count;
    }

    void solve(int row, int n, boolean[] cols,
               boolean[] diagonal1, boolean[] diagonal2) {

        // All queens placed successfully
        if (row == n) {
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {

            int d1 = row - col + n - 1;
            int d2 = row + col;

            // Check if queen can be placed
            if (cols[col] || diagonal1[d1] || diagonal2[d2]) {
                continue;
            }

            // Place queen
            cols[col] = true;
            diagonal1[d1] = true;
            diagonal2[d2] = true;

            // Move to next row
            solve(row + 1, n, cols, diagonal1, diagonal2);

            // Backtrack
            cols[col] = false;
            diagonal1[d1] = false;
            diagonal2[d2] = false;
        }
    }
}
