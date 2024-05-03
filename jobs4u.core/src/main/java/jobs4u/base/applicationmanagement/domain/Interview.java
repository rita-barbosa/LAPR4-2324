package jobs4u.base.applicationmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import java.util.Date;

public class Interview implements ValueObject {

    private String interviewTypeDenomination;
    private Date schedule;
    private String interviewResult;
    private Integer interviewGrade;
    private String justification;
    private String interviewAnswer;


    public Interview(String interviewTypeDenomination, Date schedule, String interviewResult, Integer interviewGrade, String justification, String interviewAnswer) {
        this.interviewTypeDenomination = interviewTypeDenomination;
        this.schedule = schedule;
        this.interviewResult = interviewResult;
        this.interviewGrade = interviewGrade;
        this.justification = justification;
        this.interviewAnswer = interviewAnswer;
    }

    protected Interview(){
        //for ORM
    }
}
