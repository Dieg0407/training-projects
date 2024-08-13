package com.dieg0407.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class CombinationSum2 {
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    final var solutions = new HashSet<List<Integer>>();

    for (var i = 0; i < candidates.length; i++) {
      if (candidates[i] == target) {
        solutions.add(List.of(candidates[i]));
        break;
      }
      if (candidates[i] > target) {
        break;
      }

      var stack = new ArrayList<Integer>();
      stack.add(candidates[i]);

      var left = target - candidates[i];
      for (var j = i + 1; j < candidates.length; j++) {
        if (candidates[j] < left) {
          stack.add(candidates[j]);
          left -= candidates[j];
          continue;
        }

        if (candidates[j] == left) {
          stack.add(candidates[j]);
          solutions.add(List.copyOf(stack));

          stack.removeLast();
        }

        if (stack.size() > 0) {
          left += stack.removeLast();
          j--;
        } else {
          break;
        }
      }
    }

    return new ArrayList<List<Integer>>(solutions);
  }
}
