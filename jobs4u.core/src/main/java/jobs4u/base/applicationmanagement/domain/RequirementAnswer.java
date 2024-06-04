package jobs4u.base.applicationmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

import java.io.File;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class RequirementAnswer implements ValueObject {

    private File requirementAnswerFile;
    private String filename;
    private String filepath;

    protected RequirementAnswer(String requirementAnswerFile) {
        Preconditions.noneNull(requirementAnswerFile);
        Preconditions.nonEmpty(requirementAnswerFile);
        Preconditions.matches(Pattern.compile("([a-zA-Z]:)?(\\\\\\\\[a-zA-Z0-9_.-]+)*(\\\\\\\\[a-zA-Z0-9_.-]+)?"), requirementAnswerFile, "The provided filepath is not correct.");

        File uploaded = new File(requirementAnswerFile);
        this.requirementAnswerFile = uploaded;
        this.filename = uploaded.getName();
        this.filepath = uploaded.getPath();
    }

    protected RequirementAnswer() {
    }

    public File requirementAnswerFile() {
        return requirementAnswerFile;
    }

    public String name() {
        return filename;
    }

    public String filepath() {
        return this.filepath;
    }

    public static RequirementAnswer valueOf(final String answer) {
        return new RequirementAnswer(answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequirementAnswer)) return false;
        RequirementAnswer that = (RequirementAnswer) o;
        return Objects.equals(requirementAnswerFile, that.requirementAnswerFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requirementAnswerFile);
    }
}
