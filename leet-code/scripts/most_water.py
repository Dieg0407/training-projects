from typing import List


class Solution:
    def maxArea(self, height: List[int]) -> int:
        x,y,max = 0, len(height) - 1, -99999
        while x < y:
            area = (y - x) * min(height[x], height[y])
            if area > max:
                print(f"(x,y) -> ({x},{y}) :: {area}")
                max = area
            if height[x] < height[y]: 
                x = x + 1
            else:
                y = y - 1
        return max

sol = Solution()
print(sol.maxArea([1,8,6,2,5,4,8,3,7]))
