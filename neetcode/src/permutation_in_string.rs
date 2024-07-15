use std::collections::HashMap;

#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn check_inclusion(s1: String, s2: String) -> bool {
        let mut map = HashMap::new();
        s1.chars().for_each(|c| *map.entry(c).or_insert(0) += 1);

        let chars: Vec<char> = s2.chars().collect();
        let mut l = 0;
        let mut r = 0;

        while r < chars.len() {
            if map.contains_key(&chars[r]) {
                let count = map.get_mut(&chars[r]).unwrap();
                *count -= 1;

                if *count == 0 {
                    map.remove_entry(&chars[r]);
                }
                if map.is_empty() {
                    return true;
                }
                r += 1;
                continue;
            }

            if !map.contains_key(&chars[r]) && l == r {
                l += 1;
                r += 1;
                continue;
            }

            // shift and align
            while !map.contains_key(&chars[r]) {
                map.entry(chars[l])
                    .and_modify(|count| *count += 1)
                    .or_insert(1);
                l += 1;
            }
        }

        false
    }
}

#[cfg(test)]
mod tests {
    use super::Solution;

    #[test]
    fn example01() {
        let s1 = "ab".to_string();
        let s2 = "eidbaooo".to_string();

        let expected = true;
        let res = Solution::check_inclusion(s1, s2);
        assert_eq!(expected, res);
    }

    #[test]
    fn example02() {
        let s1 = "ab".to_string();
        let s2 = "eidboaoo".to_string();

        let expected = false;
        let res = Solution::check_inclusion(s1, s2);
        assert_eq!(expected, res);
    }

    #[test]
    fn case82() {
        let s1 = "adc".to_string();
        let s2 = "dcda".to_string();

        let expected = true;
        let res = Solution::check_inclusion(s1, s2);
        assert_eq!(expected, res);
    }

    #[test]
    fn case91() {
        let s1 = "adc".to_string();
        let s2 = "dcda".to_string();

        let expected = true;
        let res = Solution::check_inclusion(s1, s2);
        assert_eq!(expected, res);
    }
}
