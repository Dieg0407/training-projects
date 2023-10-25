#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn max_area(height: Vec<i32>) -> i32 {
        let mut i = 0 as usize;
        let mut j = height.len() - 1;
        let mut max = -1;

        while i < j {
            let min = if height[i] < height[j] { height[i] } else { height[j] };
            let sum = min * (j - i) as i32;

            if sum > max {
                max = sum;
            }

            if height[i] < height[j] {
                i += 1;
            }
            else {
                j -= 1;
            }
        }

        max
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    pub fn example01() {
        let height = vec![1,8,6,2,5,4,8,3,7];
        let expected = 49;

        let result = Solution::max_area(height);

        assert_eq!(result, expected);
    }

    #[test]
    pub fn example02() {
        let height = vec![1,1];
        let expected = 1;

        let result = Solution::max_area(height);

        assert_eq!(result, expected);
    }
}
