class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        max_length = -9999
        min_pos = 0
        chars = {}

        for i, char in enumerate(s):
            if char in chars and chars[char] >= min_pos:
                old_pos = chars[char]
                length = i - min_pos
                max_length = max(max_length, length)
                min_pos = old_pos + 1

            chars[char] = i

        max_length = max(max_length, len(s) - min_pos)
        return max_length

solution = Solution()
print(solution.lengthOfLongestSubstring("abcabcbb"))
print(solution.lengthOfLongestSubstring("bbbbb"))
