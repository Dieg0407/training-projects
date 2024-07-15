from typing import Dict


class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        max_length = -9999
        values = {}
        l,r = 0, 0

        while r < len(s):
            if s[r] in values:
                values[s[r]] += 1
            else:
                values[s[r]] = 1

            max_key = self.__calculate_max_key(values)
            length = r - l + 1
            
            while length - values[max_key] > k:
                values[s[l]] -= 1
                l += 1

                max_key = self.__calculate_max_key(values)
                length = r - l + 1

            print(f"r -> {s[r]} max_key -> {max_key} len -> {length}")
            max_length = max(max_length, length)
            r += 1

        return max_length
    
    def __calculate_max_key(self, values: Dict) -> str:
        max_value = -9999
        max_key = '&'
        for key, value in values.items():
            if value > max_value:
                max_key, max_value = key, value

        return max_key
solution = Solution()
print(solution.characterReplacement("CCDDEBABAAB", 2))
