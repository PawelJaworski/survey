package pl.javorex.financialneeds.domain;

public final class CustomerID<T> {
    private final T raw;

    public static <T>CustomerID customerIDOf(T raw) {
        return new CustomerID<>(raw);
    }

    private CustomerID(T raw) {
        this.raw = raw;
    }

    public boolean isSameAs(CustomerID<?> other) {
        return raw.equals(other.raw);
    }
}
