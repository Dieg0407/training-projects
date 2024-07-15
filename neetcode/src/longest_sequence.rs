use std::collections::HashSet;

#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn longest_consecutive(nums: Vec<i32>) -> i32 {
        let mut set: HashSet<i32> = HashSet::with_capacity(nums.len());
        let mut acc: i32 = 0;
        let mut max: i32 = 0;
        nums.iter().for_each(|num| {
            set.insert(num.clone());
        });

        nums.iter().for_each(|num| {
            Solution::recursive(num, &mut acc, &mut set);
            if acc > max {
                max = acc;
            }
            acc = 0;
        });

        max
    }

    fn recursive(current_element: &i32, current_count: &mut i32, set: &mut HashSet<i32>) {
        if !set.contains(current_element) {
            return;
        }
        *current_count += 1;
        set.remove(current_element);

        let prev = current_element - 1;
        Solution::recursive(&prev, current_count, set);

        let next = current_element + 1;
        Solution::recursive(&next, current_count, set);
    }
}

#[cfg(test)]
mod tests {
    use super::Solution;

    #[test]
    fn example01() {
        let nums = vec![100, 4, 200, 1, 3, 2];
        let result = Solution::longest_consecutive(nums);

        assert_eq!(result, 4);
    }
}
