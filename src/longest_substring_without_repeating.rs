use std::collections::HashSet;

#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let chars: Vec<char> = s.chars().into_iter().collect();

        let mut distinct = HashSet::new();
        let mut i = 0;
        let mut max = 0;

        for c in chars.iter() {
            if !distinct.contains(c) {
                distinct.insert(*c);
                continue;
            }

            max = if max < distinct.len() { distinct.len() } else { max };
            
            loop {
                if chars[i] == *c {
                    i += 1;
                    break;
                }
                distinct.remove(&chars[i]);
                i += 1;
            }
        }
        max = if max < distinct.len() { distinct.len() } else { max };
        max as i32
    }
}


#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn example01() {
        let s = "abcabcbb".to_string();
        let expected = 3;

        let result = Solution::length_of_longest_substring(s);
        assert_eq!(expected, result);
    }

    #[test]
    fn example02() {
        let s = "bbbbb".to_string();
        let expected = 1;

        let result = Solution::length_of_longest_substring(s);
        assert_eq!(expected, result);
    }

    #[test]
    fn example03() {
        let s = "pwwkew".to_string();
        let expected = 3;

        let result = Solution::length_of_longest_substring(s);
        assert_eq!(expected, result);
    }

    #[test]
    fn case284() {
        let s = "aab".to_string();
        let expected = 2;

        let result = Solution::length_of_longest_substring(s);
        assert_eq!(expected, result);
    }
}