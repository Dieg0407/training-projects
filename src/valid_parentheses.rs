#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn is_valid(s: String) -> bool {
        let mut stack: Vec<char> = vec![];

        for c in s.chars() {
            if is_left(&c) {
                stack.push(obtain_complement(&c));
                continue;
            }
            if stack.is_empty() {
                return false;
            }
            let last = stack.last().unwrap();
            if *last != c {
                return false;
            }
            stack.pop();
        }

        stack.is_empty()
    }
}

fn is_left(c: &char) -> bool {
    match c {
        '{' | '(' | '[' => true,
        _ => false,
    }
}

fn obtain_complement(c: &char) -> char {
    match c {
        '{' => '}',
        '(' => ')',
        '[' => ']',
        '}' => '{',
        ')' => '(',
        ']' => '[',
        _ => '-',
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn example01() {
        let expected = true;
        let result = Solution::is_valid("()".to_string());

        assert_eq!(expected, result);
    }
    #[test]
    fn example02() {
        let expected = true;
        let result = Solution::is_valid("()[]{}".to_string());

        assert_eq!(expected, result);
    }
    #[test]
    fn example03() {
        let expected = false;
        let result = Solution::is_valid("(]".to_string());

        assert_eq!(expected, result);
    }
}
