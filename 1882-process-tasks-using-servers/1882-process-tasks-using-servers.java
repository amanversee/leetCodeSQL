
import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        // Available servers: weight first, then index
        PriorityQueue<Integer> available = new PriorityQueue<>(
            (a, b) -> {
                if (servers[a] != servers[b]) {
                    return Integer.compare(servers[a], servers[b]);
                }
                return Integer.compare(a, b);
            }
        );

        // Busy servers: finish time first, then index
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                int cmp = Long.compare(a[0], b[0]);
                if (cmp != 0) return cmp;
                return Long.compare(a[1], b[1]);
            }
        );

        // Initially, all servers are available
        for (int i = 0; i < servers.length; i++) {
            available.offer(i);
        }

        int[] result = new int[tasks.length];
        long time = 0;

        for (int i = 0; i < tasks.length; i++) {

            // Task i arrives at time i
            time = Math.max(time, i);

            // Release servers that have finished
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] server = busy.poll();
                available.offer((int) server[1]);
            }

            // If no server is free, move time forward
            if (available.isEmpty()) {
                time = busy.peek()[0];

                // Release every server free at this time
                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    long[] server = busy.poll();
                    available.offer((int) server[1]);
                }
            }

            // Select the best available server
            int serverIndex = available.poll();

            result[i] = serverIndex;

            // Mark this server as busy
            long finishTime = time + tasks[i];
            busy.offer(new long[]{finishTime, serverIndex});
        }

        return result;
    }
}
