use std::collections::HashMap;

// https://leetcode.com/problems/longest-repeating-character-replacement/
#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn character_replacement(s: String, k: i32) -> i32 {
        let chars: Vec<char> = s.chars().collect();
        let mut max_frequency = 0;
        let mut frequencies: HashMap<char, i32> = HashMap::new();
        let mut res = 0;

        let mut l = 0 as usize;

        for (r, c) in chars.iter().enumerate() {
            let entry = frequencies.entry(*c).and_modify(|e| *e += 1).or_insert(1);

            if *entry > max_frequency {
                max_frequency = *entry;
            }

            while (r - l + 1) - max_frequency as usize > k as usize {
                let entry = frequencies.entry(chars[l]).or_default();
                *entry -= 1;
                l += 1;
            }

            res = if r - l + 1 > res { r - l + 1 } else { res };
        }

        res as i32
    }
}

#[cfg(test)]
mod test {
    use super::Solution;

    #[test]
    fn case01() {
        let s = "ABAB".to_string();
        let k = 2;
        let expected = 4;

        let result = Solution::character_replacement(s, k);

        assert_eq!(expected, result);
    }

    #[test]
    fn case02() {
        let s = "AABABBA".to_string();
        let k = 1;
        let expected = 4;

        let result = Solution::character_replacement(s, k);

        assert_eq!(expected, result);
    }
}
