package com.dieg0407.leetcode.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CombinationSum2Test {

  private CombinationSum2 combinationSum2 = new CombinationSum2();

  @Test
  void shouldRunExample1() {
    final var candidates = new int[] {10, 1, 2, 7, 6, 1, 5};
    final var target = 8;

    final var solutions = combinationSum2.combinationSum2(candidates, target);
    final var expected = List.of(List.of(1, 1, 6), List.of(1, 2, 5), List.of(1, 7), List.of(2, 6));

    assertEquals(new HashSet<>(solutions), new HashSet<>(expected));
  }
}
