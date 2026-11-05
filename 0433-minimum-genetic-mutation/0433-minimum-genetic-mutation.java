import java.util.*;

class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {

        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

        // Target must exist in the bank
        if (!bankSet.contains(endGene)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(startGene);
        visited.add(startGene);

        char[] genes = {'A', 'C', 'G', 'T'};

        int mutations = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                String current = queue.poll();

                // Target reached
                if (current.equals(endGene)) {
                    return mutations;
                }

                char[] chars = current.toCharArray();

                // Try changing every position
                for (int j = 0; j < chars.length; j++) {

                    char original = chars[j];

                    for (char gene : genes) {

                        if (gene == original) {
                            continue;
                        }

                        chars[j] = gene;

                        String next = new String(chars);

                        if (bankSet.contains(next)
                                && visited.add(next)) {

                            queue.offer(next);
                        }
                    }

                    // Restore original character
                    chars[j] = original;
                }
            }

            mutations++;
        }

        return -1;
    }
}