from typing import List, Set

class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        i = 0
        triplets = []
        values = set()
        while i < len(nums) and nums[i] <= 0:
            target = nums[i]
            
            i = i + 1
            result = self.__two_sum(-1 * target, nums[i:], values)
            triplets.extend(result)

        return triplets 

    def __two_sum(self, target: int, nums: List[int], values: Set) -> List[List[int]]:
        print(f"\nTarget: {target} Partition: {nums} values: {values}")
        if len(nums) < 2:
            return []

        triplets = []
        x,y = 0, len(nums) - 1
        while x < y:
            vx,vy = nums[x], nums[y]
            sum = vx + vy
            print(f" (x,y) -> ({x}, {y}) sum = {sum}")
            if sum < target:
                x = x + 1
            elif sum > target:
                y = y - 1
            else:
                id = (-1 * target, vx, vy)
                print(f"id: {id} sum: {id} (x,y) -> ({x},{y})")
                if id not in values:
                    values.add(id)
                    triplets.append([-1 * target, vx, vy])
                y = y - 1
        return triplets

solution = Solution()
# print(solution.threeSum([-1,0,1,2,-1,-4]))
# print(solution.threeSum([-2,0,1,1,2]))
print(solution.threeSum([1,0,-2,-1,0,1,0,2,3]))
