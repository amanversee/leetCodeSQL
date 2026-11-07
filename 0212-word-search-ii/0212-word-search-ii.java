
import java.util.*;

class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }

    private TrieNode root;
    private List<String> result;

    public List<String> findWords(char[][] board, String[] words) {

        root = new TrieNode();
        result = new ArrayList<>();

        // Step 1: Build Trie
        for (String word : words) {
            insert(word);
        }

        // Step 2: Start DFS from every cell
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root);
            }
        }

        return result;
    }

    // Insert word into Trie
    private void insert(String word) {

        TrieNode current = root;

        for (char ch : word.toCharArray()) {

            int index = ch - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.word = word;
    }

    // DFS + Backtracking
    private void dfs(char[][] board, int row, int col,
                     TrieNode node) {

        // Boundary and visited check
        if (row < 0 || col < 0 ||
            row >= board.length ||
            col >= board[0].length ||
            board[row][col] == '#') {
            return;
        }

        char ch = board[row][col];
        int index = ch - 'a';

        // Character not present in Trie
        if (node.children[index] == null) {
            return;
        }

        TrieNode next = node.children[index];

        // Word found
        if (next.word != null) {
            result.add(next.word);

            // Prevent duplicate results
            next.word = null;
        }

        // Mark current cell as visited
        board[row][col] = '#';

        // Explore four directions
        dfs(board, row + 1, col, next); // Down
        dfs(board, row - 1, col, next); // Up
        dfs(board, row, col + 1, next); // Right
        dfs(board, row, col - 1, next); // Left

        // Backtrack: restore original character
        board[row][col] = ch;
    }
}
