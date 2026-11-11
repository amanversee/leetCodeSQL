
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth,
                            int need1, int need2) {

        // Common units required by both types
        long common = Math.min(need1, need2);

        // Cheapest way to satisfy common requirements
        long totalCost = common * Math.min(costBoth, cost1 + cost2);

        // Remaining Type 1 requirement
        long rem1 = need1 - common;
        totalCost += rem1 * Math.min(costBoth, cost1);

        // Remaining Type 2 requirement
        long rem2 = need2 - common;
        totalCost += rem2 * Math.min(costBoth, cost2);

        return totalCost;
    }
}
