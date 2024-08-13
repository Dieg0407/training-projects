from typing import List


class Solution:
    def trap(self, height: List[int]) -> int:
        l, r = 0, len(height) - 1
        max_left, max_right = height[l], height[r]
        sum = 0
        while l < r:
            if height[l] <= height[r]:
                l+=1
                max_left = max(height[l], max_left)
                sum += max_left - height[l]
            else:
                r-=1
                max_right = max(height[r], max_right)
                sum += max_right - height[r]
 
        return sum

solution = Solution()

print(solution.trap([0,1,0,2,1,0,1,3,2,1,2,1]))
