use std::collections::HashSet;

// leetcode problem
// https://leetcode.com/problems/contains-duplicatep
pub struct Solution;

impl Solution {
    pub fn contains_duplicate(nums: Vec<i32>) -> bool {
        let mut set = HashSet::new();
        !nums.into_iter().all(|n| set.insert(n))    
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn case1() {
        let vector = vec![1, 2, 3, 1];
        assert_eq!(Solution::contains_duplicate(vector), true);
    }

    #[test]
    fn case2() {
        let vector = vec![1, 2, 3, 4];
        assert_eq!(Solution::contains_duplicate(vector), false);
    }

    #[test]
    fn case3() {
        let vector = vec![1,1,1,3,3,4,3,2,4,2];
        assert_eq!(Solution::contains_duplicate(vector), true);
    }
}
