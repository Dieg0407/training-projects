use std::collections::VecDeque;

#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn max_sliding_window(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let mut l = 0 as usize;
        let mut r = 0 as usize;
        let mut queue: VecDeque<usize> = VecDeque::new();
        let mut window: Vec<i32> = vec![];

        while r < nums.len() {
            while !queue.is_empty() && nums[*queue.back().unwrap()] < nums[r] {
                queue.pop_back();
            }
            queue.push_back(r);
            if l > *queue.front().unwrap_or(&0) {
                queue.pop_front();
            }
            if r + 1 >= k as usize {
                window.push(nums[*queue.front().unwrap()]);
                l += 1;
            }
            r += 1;
        }

        window
    }
}

#[cfg(test)]
mod tests {
    use super::Solution;

    #[test]
    fn example01() {
        let nums = vec![1, 3, -1, -3, 5, 3, 6, 7];
        let k = 3;
        let expected = vec![3, 3, 5, 5, 6, 7];
        let res = Solution::max_sliding_window(nums, k);

        assert_eq!(expected, res);
    }

    #[test]
    fn example02() {
        let nums = vec![1];
        let k = 1;
        let expected = vec![1];
        let res = Solution::max_sliding_window(nums, k);

        assert_eq!(expected, res);
    }

    #[test]
    fn case38() {
        let nums = vec![-7, -8, 7, 5, 7, 1, 6, 0];
        let k = 4;
        let expected = vec![7, 7, 7, 7, 7];
        let res = Solution::max_sliding_window(nums, k);

        assert_eq!(expected, res);
    }

    #[test]
    fn case47() {
        let nums = vec![9, 10, 9, -7, -4, -8, 2, -6];
        let k = 5;
        let expected = vec![10, 10, 9, 2];
        let res = Solution::max_sliding_window(nums, k);

        assert_eq!(expected, res);
    }
}
