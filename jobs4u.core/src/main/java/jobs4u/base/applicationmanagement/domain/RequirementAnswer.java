package jobs4u.base.applicationmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class RequirementAnswer implements ValueObject {

    private String requirementAnswer;

    protected RequirementAnswer(String answer) {
        Preconditions.noneNull(answer);
        Preconditions.nonEmpty(answer);
        Preconditions.matches(Pattern.compile("([a-zA-Z]:)?(\\\\\\\\[a-zA-Z0-9_.-]+)*(\\\\\\\\[a-zA-Z0-9_.-]+)?"), answer, "The provided filepath is not correct.");

        this.requirementAnswer = answer;
    }

    protected RequirementAnswer() {
    }

    public String requirementAnswer() {
        return requirementAnswer;
    }

    public static RequirementAnswer valueOf(final String answer) {
        return new RequirementAnswer(answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequirementAnswer)) return false;
        RequirementAnswer that = (RequirementAnswer) o;
        return Objects.equals(requirementAnswer, that.requirementAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requirementAnswer);
    }
}
