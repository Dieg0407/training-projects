use std::collections::HashMap;

#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn min_window(s: String, t: String) -> String {
        let mut start = 0;
        let mut end = 0;
        let mut min = 99999;

        let mut map: HashMap<char, i32> = HashMap::new();
        t.chars().for_each(|c| {
            map.entry(c).and_modify(|count| *count += 1).or_insert(1);
        });

        let mut current_hit = 0;
        let mut hits: Vec<(usize, char)> = Vec::new();

        for (pos, c) in s.chars().enumerate() {
            if !map.contains_key(&c) {
                continue;
            }
            hits.push((pos, c));
            map.entry(c).and_modify(|count| *count -= 1);

            while is_valid(&map) {
                let len = pos - hits[current_hit].0 + 1;
                if len < min {
                    min = len;
                    start = hits[current_hit].0;
                    end = pos;
                }

                map.entry(hits[current_hit].1)
                    .and_modify(|count| *count += 1)
                    .or_insert(1);

                current_hit += 1;
            }
        }
        if min == 99999 {
            return "".to_string();
        }
        s[start..end + 1].to_string()
    }
}

fn is_valid(map: &HashMap<char, i32>) -> bool {
    for (_, v) in map {
        if *v > 0 {
            return false;
        }
    }

    return true;
}

#[cfg(test)]
mod tests {
    use super::Solution;

    #[test]
    fn example01() {
        let s = "ADOBECODEBANC".to_string();
        let t = "ABC".to_string();
        let expected = "BANC".to_string();
        let res = Solution::min_window(s, t);

        assert_eq!(expected, res);
    }

    #[test]
    fn example02() {
        let s = "a".to_string();
        let t = "a".to_string();
        let expected = "a".to_string();
        let res = Solution::min_window(s, t);

        assert_eq!(expected, res);
    }

    #[test]
    fn example03() {
        let s = "a".to_string();
        let t = "aa".to_string();
        let expected = "".to_string();
        let res = Solution::min_window(s, t);

        assert_eq!(expected, res);
    }
}
