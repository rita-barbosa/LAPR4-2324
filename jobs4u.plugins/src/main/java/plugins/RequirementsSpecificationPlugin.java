package plugins;

public interface RequirementsSpecificationPlugin {

    public boolean generateTextFile();

    public boolean evaluateRequirementSpecificationFile(String filePath);

}
