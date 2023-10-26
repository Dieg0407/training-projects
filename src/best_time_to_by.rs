#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn max_profit(prices: Vec<i32>) -> i32 {
        let mut max_profit = 0;
        if prices.len() == 1 {
            return max_profit;
        }

        let mut l = 0;
        let mut r = 1;

        while l < prices.len() && r < prices.len() {
            if prices[r] < prices[l] {
                l = r;
                r += 1;
                continue;
            }
            let profit = prices[r] - prices[l];
            if max_profit < profit {
                max_profit = profit;
            }
            r += 1;
        }

        max_profit
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn example01() {
        let prices = vec![7, 1, 5, 3, 6, 4];
        let expected = 5;
        let res = Solution::max_profit(prices);

        assert_eq!(res, expected);
    }

    #[test]
    fn example02() {
        let prices = vec![7, 6, 4, 3, 1];
        let expected = 0;
        let res = Solution::max_profit(prices);

        assert_eq!(res, expected);
    }
}
