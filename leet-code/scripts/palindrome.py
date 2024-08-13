class Solution:
    def isPalindrome(self, s: str) -> bool:
        x = 0
        y = len(s) - 1

        while x < y:
            if not s[x].isalpha() and not s[x].isdigit():
                x = x + 1
                continue
            if not s[y].isalpha() and not s[y].isdigit():
                y = y - 1
                continue

            cx = s[x]
            cy = s[y]

            print(f"cx {cx} cy: {cy}")
            if cx.lower() != cy.lower():
                return False

            x = x + 1
            y = y - 1

        return True 


solution = Solution()
print(solution.isPalindrome("A man, a an, a canal: Panama"))
