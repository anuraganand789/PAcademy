package sample.propertyClass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

/**
 * Created by anurag on 10/2/2014.
 */
public
class GtpDailySalesReport {
    private
    StringProperty membershipNumber;
    private
    StringProperty name;
    private
    StringProperty fatherName;
    private
    StringProperty gender;
    private
    StringProperty mobileNumber;
    private
    StringProperty residenceNumber;
    private
    StringProperty address;
    private
    StringProperty email;
    private
    StringProperty gymPackage;
    private
    Date           dateCurrent;
    private
    Date           dateOfBirth;

    public
    GtpDailySalesReport(String MembershipNumber,String name, String fatherName, String gender,
                        String mobileNumber, String residenceNumber,String address,
                        String email,String gymPackage,Date dateCurrent,Date dateOfBirth
                       ) {
        this.membershipNumber = membershipNumber;
        this.name = new SimpleStringProperty(name);
        this.fatherName = new SimpleStringProperty(fatherName);
        this.gender = new SimpleStringProperty(gender);
        this.mobileNumber = new SimpleStringProperty(mobileNumber);
        this.residenceNumber = new SimpleStringProperty(residenceNumber);
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
        this.gymPackage = new SimpleStringProperty(gymPackage);
        this.dateCurrent = dateCurrent;
        this.dateOfBirth = dateOfBirth;
    }

    public  StringProperty membershipNumber(){  return membershipNumber;}
    public StringProperty nameProperty(){
        return name;
    }

    public StringProperty fatherNameProperty(){
        return fatherName;
    }

    public StringProperty genderProperty(){
        return gender;
    }

    public StringProperty mobileNumberProperty(){
        return mobileNumber;
    }

    public StringProperty residenceNumberProperty(){ return residenceNumber; }

    public StringProperty addressProperty(){ return address; }

    public StringProperty emailProperty(){ return email; }

    public StringProperty gymPackageProperty(){ return gymPackage; }
    
    public Date getDateCurrent(){ return dateCurrent; }

    public Date getDateOfBirth(){ return dateOfBirth; }
}
