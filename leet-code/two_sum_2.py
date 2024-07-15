from typing import List

class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        start, final = 0, len(numbers) - 1

        while True:
            calculated = numbers[start] + numbers[final]
            if calculated == target:
                return [start + 1, final + 1]
            elif calculated > target:
                final = final - 1
            else:
                start = start + 1


solution = Solution()
print(solution.twoSum([2,7,11,15], 9))
