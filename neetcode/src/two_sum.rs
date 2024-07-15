use std::collections::HashMap;

#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let mut complements: HashMap<i32, usize> = HashMap::new();

        for (pos, num) in nums.iter().enumerate() {
            if let Some(complement_pos) = complements.get(num) {
                return vec![
                    complement_pos.clone().try_into().unwrap(),
                    pos.try_into().unwrap()
                ];
            } else {
                complements.insert(target - *num, pos);
            }
        }

        vec![]
    }
}


#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn example01() {
        let nums = vec![2,7,11,15];
        let target = 9;

        assert_eq!(Solution::two_sum(nums, target), vec![0,1]);
    }

    #[test]
    fn example02() {
        let nums = vec![3,2,4];
        let target = 6;

        assert_eq!(Solution::two_sum(nums, target), vec![1,2]);
    }

    #[test]
    fn example03() {
        let nums = vec![3,3];
        let target = 6;

        assert_eq!(Solution::two_sum(nums, target), vec![0,1]);
    }
}
