class Solution:
    def findSubtreeSizes(self, parent: List[int], s: str) -> List[int]:
        n = len(parent)
        children = [[] for _ in range(n)]

        for i in range(1, n):
            children[parent[i]].append(i)

        new_parent = parent[:]

        last = {}

        def dfs(node, par):
            ch = s[node]

            if ch in last:
                new_parent[node] = last[ch]

            old = last.get(ch)
            last[ch] = node

            for child in children[node]:
                dfs(child, node)

            if old is None:
                del last[ch]
            else:
                last[ch] = old

        dfs(0, -1)

        new_children = [[] for _ in range(n)]

        for i in range(1, n):
            new_children[new_parent[i]].append(i)

        ans = [0] * n

        def calculate(node):
            ans[node] = 1

            for child in new_children[node]:
                ans[node] += calculate(child)

            return ans[node]

        calculate(0)

        return ans