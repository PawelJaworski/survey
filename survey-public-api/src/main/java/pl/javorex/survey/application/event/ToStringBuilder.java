package pl.javorex.survey.application.event;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ToStringBuilder {
    private final Object obj;
    private final Map<String, Object> props;

    public ToStringBuilder(Object obj) {
        this.obj = obj;
        this.props = new HashMap<>();
    }

    public ToStringBuilder append(String name, Object value) {
        this.props.put(name, value);

        return this;
    }

    public String toString() {
        return obj.getClass().getSimpleName() + " { " + this.props.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining(", ")) + " }";
    }
}
