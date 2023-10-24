use std::{collections::HashSet, vec};

#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn three_sum(mut nums: Vec<i32>) -> Vec<Vec<i32>> {
        nums.sort();

        let mut tuples: HashSet<(i32, i32, i32)> = HashSet::new();
        let mut two_sum = |base: i32, from: usize| {
            let mut i = from;
            let mut j = nums.len() - 1;

            while i < j {
                let sum = nums[i] + nums[j] + base;
                if sum > 0 {
                    j -= 1;
                    continue;
                }
                if sum == 0 {
                   tuples.insert((base, nums[i], nums[j]));
                }
                i += 1; 
            }
            
        };

        nums.iter()
            .enumerate()
            .take_while(|&(pos, num)| *num <= 0 && pos < nums.len() - 2)
            .for_each(|element| two_sum(*element.1, element.0 + 1));

        tuples
            .into_iter()
            .map(|tuple| vec![tuple.0, tuple.1, tuple.2])
            .collect()
    }
}

#[cfg(test)]
mod test {
    use super::*;

    #[test]
    fn example01() {
        let nums = vec![-1, 0, 1, 2, -1, -4];
        let result = Solution::three_sum(nums);

        assert_eq!(result.contains(&vec![-1, -1, 2]), true);
        assert_eq!(result.contains(&vec![-1, 0, 1]), true);
    }

    #[test]
    fn example02() {
        let nums = vec![0, 1, 1];
        let result = Solution::three_sum(nums);
        let empty: Vec<Vec<i32>> = vec![];

        assert_eq!(result, empty);
    }

    #[test]
    fn example03() {
        let nums = vec![0, 0, 0];
        let result = Solution::three_sum(nums);

        assert_eq!(result.contains(&vec![0, 0, 0]), true);
    }

    #[test]
    fn example04() {
        let nums = vec![-1, 0, 1, 0];
        let result = Solution::three_sum(nums);

        assert_eq!(result.len(), 1);
        assert_eq!(result.contains(&vec![-1, 0, 1]), true);
    }
}
