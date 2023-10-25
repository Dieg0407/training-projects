#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn trap(height: Vec<i32>) -> i32 {
        let mut total = 0;
        if height.len() <= 2 {
            return 0;
        }

        let mut i = 0;
        let mut j = height.len() - 1;

        let mut max_left = height[i];
        let mut max_right = height[j];

        while i != j {
            if height[i] < height[j] {
                i += 1;
                if height[i] > max_left {
                    max_left = height[i];
                } else {
                    total += max_left - height[i];
                }
            } else {
                j -= 1;
                if height[j] > max_right {
                    max_right = height[j];
                } else {
                    total += max_right - height[j];
                }
            }
        }

        total
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn example01() {
        let height = vec![0,1,0,2,1,0,1,3,2,1,2,1];
        let expected = 6;

        let result = Solution::trap(height);
        assert_eq!(expected, result);
    }

    #[test]
    fn example02() {
        let height = vec![4,2,0,3,2,5];
        let expected = 9;

        let result = Solution::trap(height);
        assert_eq!(expected, result);

    }

    #[test]
    fn case1() {
        let height = vec![4,2,3];
        let expected = 1;
        let result = Solution::trap(height);

        assert_eq!(expected, result);
    }


    #[test]
    fn case2() {
        let height = vec![6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3];
        let expected = 83;
        let result = Solution::trap(height);

        assert_eq!(expected, result);
    }
}
