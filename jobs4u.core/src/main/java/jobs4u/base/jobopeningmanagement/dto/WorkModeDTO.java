package jobs4u.base.jobopeningmanagement.dto;

public class WorkModeDTO {

    private final String workModeName;

    public WorkModeDTO(String denomination) {
        this.workModeName = denomination;
    }

    public String workModeName(){
        return workModeName;
    }

    @Override
    public String toString() {
        return workModeName;
    }
}
