class Solution:
    def minWindow(self, s: str, t: str) -> str:
        values = {}
        accumulator = {}
        for _, c in enumerate(t):
            if c in values:
                values[c] += 1
            else:
                values[c] = 1
            accumulator[c] = 0

        l,r = -1,0
        min_window = ""
        completed_keys = set()
        while r < len(s):
            if s[r] not in values: # ignore till we find a matching character
                r += 1
                continue

            if len(t) == 1: # only one match needed
                return t

            if l == -1: # start evaluation
                l = r

            accumulator[s[r]] += 1
            if accumulator[s[r]] >= values[s[r]]:
                completed_keys.add(s[r])

            r += 1
            if len(completed_keys) != len(values.keys()): # at least one character is still on 't'
                continue

            sub_str = s[l:r]
            if len(sub_str) < len(min_window) or min_window == "": # if substr is bigger than the min window then don't replace
                min_window = sub_str

            accumulator[s[l]] -= 1
            if accumulator[s[l]] < values [s[l]]:
                completed_keys.remove(s[l])

            l += 1
            while s[l] not in values or len(completed_keys) == len(values.keys()):
                # there is still a valid substr in this substr
                if s[l] in values :
                    accumulator[s[l]] -= 1
                    if accumulator[s[l]] < values [s[l]]:
                        completed_keys.remove(s[l])

                    sub_str = s[l:r]
                    if len(sub_str) < len(min_window) or min_window == "": # if substr is bigger than the min window then don't replace
                        min_window = sub_str
                l += 1
        return min_window

solution = Solution()
print(solution.minWindow("ADOBECODEBANC", "ABC"))
print(solution.minWindow("a", "a"))
print(solution.minWindow("a", "aa"))
print(solution.minWindow("bba", "ba"))
