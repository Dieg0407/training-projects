#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn eval_rpn(tokens: Vec<String>) -> i32 {
        let mut stack: Vec<String> = vec![];
        let mut prev_is_number = false;

        for token in tokens.into_iter().rev() {
            if is_operator(&token) {
                stack.push(token);
                prev_is_number = false;
                continue;
            }

            if !prev_is_number {
                stack.push(token);
                prev_is_number = true;
                continue;
            }

            let mut pivot: i32 = token.parse().unwrap();
            while !stack.is_empty() && !is_operator(stack.last().unwrap()) {
                let val2: i32 = (*stack.pop().unwrap()).parse().unwrap();
                let operator = stack.pop().unwrap();

                pivot = operate(pivot, val2, &operator);
            }

            stack.push(pivot.to_string());
        }

        stack.pop().unwrap().parse().unwrap()
    }
}

fn is_operator(token: &str) -> bool {
    match token {
        "+" | "-" | "*" | "/" => true,
        _ => false,
    }
}

fn operate(val1: i32, val2: i32, operator: &str) -> i32 {
    match operator {
        "+" => val1 + val2,
        "-" => val1 - val2,
        "*" => val1 * val2,
        "/" => val1 / val2,
        _ => 0,
    }
}

#[cfg(test)]
mod tests {
    use super::Solution;

    #[test]
    fn example01() {
        let tokens = vec![
            "2".to_string(),
            "1".to_string(),
            "+".to_string(),
            "3".to_string(),
            "*".to_string(),
        ];
        let expected = 9;
        let result = Solution::eval_rpn(tokens);

        assert_eq!(result, expected);
    }

    #[test]
    fn example02() {
        let tokens = vec![
            "4".to_string(),
            "13".to_string(),
            "5".to_string(),
            "/".to_string(),
            "+".to_string(),
        ];
        let expected = 6;
        let result = Solution::eval_rpn(tokens);

        assert_eq!(result, expected);
    }

    #[test]
    fn example03() {
        let tokens = vec![
            "10".to_string(),
            "6".to_string(),
            "9".to_string(),
            "3".to_string(),
            "+".to_string(),
            "-11".to_string(),
            "*".to_string(),
            "/".to_string(),
            "*".to_string(),
            "17".to_string(),
            "+".to_string(),
            "5".to_string(),
            "+".to_string(),
        ];
        let expected = 22;
        let result = Solution::eval_rpn(tokens);

        assert_eq!(result, expected);
    }
}
