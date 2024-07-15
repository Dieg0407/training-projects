from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        min = 9999
        max_profit = 0
        for _, n in enumerate(prices):
            if n <= min:
                min = n
                continue
            profit = n - min
            max_profit = max(max_profit, profit)
                
        return max_profit

solution = Solution()
print(solution.maxProfit([7,1,5,3,6,4]))
print(solution.maxProfit([7,6,4,3,1]))
