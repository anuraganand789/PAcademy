package sample.propertyClass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by anurag on 9/28/2014.
 */
public
class GtpWorkExperience {
    private
    StringProperty durationFrom;
    private
    StringProperty durationTo;
    private
    StringProperty location;
    private
    StringProperty orgContact;
    private
    StringProperty orgName;
    private
    StringProperty empBusiness;
    private
    StringProperty designation;
    private
    StringProperty jobDesc;
    private
    StringProperty salaryStarting;
    private
    StringProperty salaryLeaving;
    private
    StringProperty reasonLeave;
    private
    StringProperty workDuration;
    private
    StringProperty expMonth;
    private
    StringProperty expYear;


    public GtpWorkExperience(String durationFrom,String durationTo,String location,String orgContact,String orgName,
                             String empBusiness,String designation,String jobDesc,String salaryStarting,
                             String salaryLeaving,String reasonLeave,String workDuration,String expMonth,
                             String expYear){
        this.durationFrom = new SimpleStringProperty(durationFrom);
        this.durationTo = new SimpleStringProperty(durationTo);
        this.location = new SimpleStringProperty(location);
        this.orgContact = new SimpleStringProperty(orgContact);
        this.empBusiness = new SimpleStringProperty(empBusiness);
        this.designation = new SimpleStringProperty(designation);
        this.jobDesc = new SimpleStringProperty(jobDesc);
        this.salaryStarting = new SimpleStringProperty(salaryStarting);
        this.salaryLeaving = new SimpleStringProperty(salaryLeaving);
        this.reasonLeave = new SimpleStringProperty(reasonLeave);
        this.workDuration = new SimpleStringProperty(workDuration);
        this.expMonth = new SimpleStringProperty(expMonth);
        this.expYear = new SimpleStringProperty(expYear);
        this.orgName = new SimpleStringProperty(orgName);

    }
    public StringProperty durationFromProperty(){ return durationFrom; }
    public  StringProperty durationToProperty(){ return durationTo; }
    public  StringProperty locationProperty(){ return location;}
    public StringProperty orgContactProperty(){ return orgContact ; }
    public StringProperty orgNameProperty(){ return orgName; }
    public StringProperty empBusinessProperty(){ return  empBusiness; }
    public StringProperty designationProperty(){ return designation; }
    public StringProperty jobDescProperty(){ return jobDesc; }
    public StringProperty salaryStartingProperty(){ return salaryStarting; }
    public StringProperty salaryLeavingProperty(){ return salaryLeaving; }
    public StringProperty reasonLeaveProperty(){ return reasonLeave; }
    public StringProperty workDurationProperty(){ return workDuration; }
    public StringProperty expMonthProperty(){ return expMonth; }
    public StringProperty expYearProperty(){ return expYear; }

}
