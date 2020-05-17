package pl.javorex.survey.application.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ApiResult<T> {
  private final T value;
  private final List<String> errors;

  public static ApiResult success() {
    return new ApiResult(new Object(), new ArrayList<>());
  }

  public static <T>ApiResult<T> successOf(T value) {
    return new ApiResult(value, new ArrayList<>());
  }

  public static <T>ApiResult<T> failureOf(List<String> errors) {
    return new ApiResult(errors);
  }

  private ApiResult(T value, List<String> errors) {
    this.errors = errors;
    this.value = Objects.requireNonNull(value, "ApiResult value cannot be null.");
  }

  private ApiResult(List<String> errors) {
    this.errors = Objects.requireNonNull(errors, "Errors cannot be null.");
    this.value = null;
  }
}
