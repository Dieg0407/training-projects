#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn is_palindrome(s: String) -> bool {
        let characters: Vec<char> = s.chars().collect();
        let mut left = 0;
        let mut right = characters.len() - 1;

        while left < right {
            if !characters[left].is_alphanumeric() { 
                left += 1;
                continue;
            }
            if !characters[right].is_alphanumeric() { 
                right -= 1; 
                continue;
            }

            let l_char = characters[left].to_lowercase().next().unwrap();
            let r_char = characters[right].to_lowercase().next().unwrap();

            if l_char != r_char {
                return false;
            }
            left += 1;
            right -= 1;
        }
        true
    }
}


#[cfg(test)]
mod tests {
    use super::Solution;


    #[test]
    fn example01() {
        let s = "A man, a plan, a canal: Panama".to_string();
        let r = Solution::is_palindrome(s);

        assert_eq!(r, true);
    }

    #[test]
    fn example02() {
        let s = "0P".to_string();
        let r = Solution::is_palindrome(s);

        assert_eq!(r, false);

    }
}
