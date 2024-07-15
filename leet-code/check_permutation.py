class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        values = {}
        for _, c in enumerate(s1):
            if c in values:
                values[c] += 1
            else:
                values[c] = 1

        backup = values.copy()
        l,r = -1,0
        while r < len(s2):
            if l == - 1: # not evaluating
                if s2[r] in values:
                    values[s2[r]] -= 1
                    l = r
                    if len(s1) == 1:
                        return True
                r+=1
                continue

            if s2[r] not in values: # broken permutation
                l = -1
                r += 1
                values = backup.copy() # restore the old values
                continue

            while values[s2[r]] == 0: # need to shift
                values[s2[l]] += 1
                l += 1

            values[s2[r]] -= 1

            if r - l + 1 == len(s1):
                return True

            r += 1

        return False

solution = Solution()
print(solution.checkInclusion("ab", "eidbaooo"))
print(solution.checkInclusion("a", "ab"))
