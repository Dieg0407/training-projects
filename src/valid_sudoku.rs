use std::collections::HashSet;

#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn is_valid_sudoku(board: Vec<Vec<char>>) -> bool {
        let mut column_values: Vec<HashSet<char>> = (0..9).map(|_| HashSet::new()).collect();
        let mut row_values: Vec<HashSet<char>> = (0..9).map(|_| HashSet::new()).collect();
        let mut sudokus: Vec<HashSet<char>> = (0..9).map(|_| HashSet::new()).collect();

        for i in 0..9 {
            for j in 0..9 {
                let value = board[i][j];
                if value == '.' {
                    continue;
                }
                if row_values[i].insert(value) == false {
                    return false;
                }
                if column_values[j].insert(value) == false {
                    return false;
                }

                let row = i / 3;
                let col = j / 3;

                let sudoku_idx = row * 3 + col;
                if sudokus[sudoku_idx].insert(value) == false {
                    return false;
                }
            }
        }
        true
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn example01() {
        let board = vec![
            vec!['5', '3', '.', '.', '7', '.', '.', '.', '.'],
            vec!['6', '.', '.', '1', '9', '5', '.', '.', '.'],
            vec!['.', '9', '8', '.', '.', '.', '.', '6', '.'],
            vec!['8', '.', '.', '.', '6', '.', '.', '.', '3'],
            vec!['4', '.', '.', '8', '.', '3', '.', '.', '1'],
            vec!['7', '.', '.', '.', '2', '.', '.', '.', '6'],
            vec!['.', '6', '.', '.', '.', '.', '2', '8', '.'],
            vec!['.', '.', '.', '4', '1', '9', '.', '.', '5'],
            vec!['.', '.', '.', '.', '8', '.', '.', '7', '9']
        ];

        let result = Solution::is_valid_sudoku(board);
        assert_eq!(result, true);
    }
}
