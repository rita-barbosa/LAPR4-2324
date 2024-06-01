package jobs4u.base.pluginmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;
import eapli.framework.strings.StringMixin;

import java.util.regex.Pattern;

@Embeddable
public class FullClassName implements ValueObject, Comparable<FullClassName>, StringMixin {

    private static final long serialVersionUID = 1L;

    private final String fullClassName;

    protected FullClassName(final String name) {
        Preconditions.nonEmpty(name);
        Preconditions.matches(Pattern.compile("([a-z]+\\.)+[A-Z][a-zA-Z0-9]*"), name, "The provided name is not a fully qualified class name.");
        this.fullClassName = name;
    }

    protected FullClassName() {
        fullClassName = null;
    }

    public static FullClassName valueOf(final String fqClassName) {
        return new FullClassName(fqClassName);
    }

    @Override
    public String toString() {
        return fullClassName;
    }

    @Override
    public int compareTo(final FullClassName o) {
        return fullClassName.compareTo(o.fullClassName);
    }
}
