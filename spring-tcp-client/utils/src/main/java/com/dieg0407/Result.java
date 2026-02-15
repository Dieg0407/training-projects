package com.dieg0407;

import java.util.function.Supplier;
import javax.validation.constraints.NotNull;

public sealed class Result<T> {
  private Result() {}

  /**
   * Creates a successful result containing the given value.
   * @param value the value to be contained in the success result, must not be null
   * @return a Success instance containing the provided value
   * @param <T> the type of the value contained in the success result
   * @throws IllegalArgumentException if the provided value is null
   */
  public static <T> Success<T> success(@NotNull T value) {
    if (value == null) {
      throw new IllegalArgumentException("Value cannot be null");
    }
    return new Success<>(value);
  }

  /**
   * Executes the given supplier and returns a Result containing either the successful value or the error.
   * @param supplier the Supplier to be executed, must not be null
   * @return a Success instance if the supplier executes successfully, or a Failure instance if an exception is thrown
   * @param <T> the type of the value contained in the success result
   * @throws IllegalArgumentException if the provided supplier is null
   */
  public static <T> Result<T> fromThrowableCode(@NotNull Supplier<T> supplier) {
    try {
      return success(supplier.get());
    } catch (Throwable e) {
      return failure(e);
    }
  }

  /**
   * Creates a failure result containing the given error.
   * @param error the Throwable to be contained in the failure result, must not be null
   * @return a Failure instance containing the provided error
   * @throws IllegalArgumentException if the provided error is null
   */
  public static <T> Failure<T> failure(@NotNull Throwable error) {
    if (error == null) {
      throw new IllegalArgumentException("Error cannot be null");
    }
    return new Failure<>(error);
  }

  public static final class Success<T> extends Result<T> {
    @NotNull
    private final T value;

    Success(@NotNull T value) {
      this.value = value;
    }

    @NotNull
    public T getValue() {
      return value;
    }
  }

  public static final class Failure<T> extends Result<T> {
    @NotNull
    private final Throwable error;

    Failure(@NotNull Throwable error) {
      this.error = error;
    }

    @NotNull
    public Throwable getError() {
      return error;
    }
  }
}