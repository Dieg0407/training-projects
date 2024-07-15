use std::collections::HashMap;

#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn group_anagrams(strs: Vec<String>) -> Vec<Vec<String>> {
        let mut values = HashMap::new();
        for s in strs {
            let mut as_char: Vec<char> = s.chars().collect();
            as_char.sort_unstable();
            values.entry(as_char).or_insert(vec![]).push(s);
        }

        values.into_iter().map(|(_,v)| v).collect()
    }
}


