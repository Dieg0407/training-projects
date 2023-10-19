#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn product_except_self(nums: Vec<i32>) -> Vec<i32> {
        let length = nums.len();
        let mut prefixes: Vec<i32> = Vec::with_capacity(length);
        prefixes.push(1);

        for i in 1..nums.len() {
            let num = nums.get(i - 1).unwrap();
            let prefix = prefixes.get(i - 1).unwrap();
            prefixes.push(num * prefix);
        }

        let mut sufixes: Vec<i32> = Vec::with_capacity(length);
        sufixes.push(1);

        for i in (0..nums.len() - 1).rev() {
            let num = nums.get(i + 1).unwrap();
            let sufix_idx = length - i - 2;
            let sufix = sufixes.get(sufix_idx).unwrap();

            sufixes.push(num * sufix);
        }

        sufixes.reverse();

        (0..nums.len()).into_iter().map(|i| prefixes.get(i).unwrap() * sufixes.get(i).unwrap()).collect()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn example01() {
        let nums = vec![1,2,3,4];
        let result = Solution::product_except_self(nums);

        assert_eq!(result, vec![24,12,8,6]);
    }
}
