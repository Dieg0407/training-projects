use std::cmp::Ordering;

#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn two_sum(numbers: Vec<i32>, target: i32) -> Vec<i32> {
        let mut i = 0;
        let mut j = numbers.len() - 1;

        while i < j {
            match (numbers[i] + numbers[j]).cmp(&target) {
                Ordering::Less => i += 1,
                Ordering::Greater => j -= 1,
                Ordering::Equal => return  vec![i as i32 + 1, j as i32 + 1]
            }
        }

        unreachable!()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn example01() {
        let numbers = vec![2,7,11,15];
        let target = 9;
        let result = Solution::two_sum(numbers, target);

        assert_eq!(result, vec![1, 2]);
    }

    #[test]
    fn example02() {
        let numbers = vec![2,3,4];
        let target = 6;
        let result = Solution::two_sum(numbers, target);

        assert_eq!(result, vec![1, 3]);
    }
}
