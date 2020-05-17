package pl.javorex.survey.domain;

import static java.util.Objects.requireNonNull;

public final class RespondentID<T> {
  private final T raw;

  public static <T>RespondentID<T> respondentIDOf(T obj) {
    return new RespondentID<T>(obj);
  }

  private RespondentID(T raw) {
    this.raw = requireNonNull(raw);
  }

  public T getRaw() {
    return raw;
  }

  public boolean isSameAs(RespondentID<?> other) {
    return raw.equals(other.raw);
  }
}
