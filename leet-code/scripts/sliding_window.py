from typing import List


class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        l,r = 0, k - 1
        curr_max = max(nums[l:(r+1)])
        max_values = [ curr_max ]
    
        l += 1
        r += 1
        while r < len(nums):
            if nums[r] >= curr_max:
                curr_max = nums[r]
            elif nums[l - 1] == curr_max:
                curr_max = max(nums[l:(r+1)])

                    
        return max_values

solution = Solution()
print(solution.maxSlidingWindow([1,3,-1,-3,5,3,6,7], 3))
print(solution.maxSlidingWindow([1], 1))
