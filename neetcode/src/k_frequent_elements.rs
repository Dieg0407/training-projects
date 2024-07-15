use std::collections::HashMap;

#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn top_k_frequent(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let mut map = HashMap::new();
        nums.into_iter().for_each(|n| *map.entry(n).or_insert(0) += 1);

        let mut values: Vec<(i32,i32)> = map.into_iter().collect();
        values.sort_by(|x, y| x.1.cmp(&y.1));

        let mut ret: Vec<i32> = vec![];
        for _ in 0..k {
            ret.push(values.pop().unwrap().0);
        }
        ret
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn example01() {
        let nums = vec![1,1,1,2,2,3];
        let k = 2;

        assert_eq!(Solution::top_k_frequent(nums, k), vec![1,2]);
    }

    #[test]
    fn example02() {
        let nums = vec![-1,-1];
        let k = 1;

        assert_eq!(Solution::top_k_frequent(nums, k), vec![-1]);
    }
}
