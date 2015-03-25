package sample.propertyClass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

/**
 * Created by anurag on 10/5/2014.
 */
public
class PAcadAddMemberProp {
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
    Date           registrationDate;
    private
    Date           dateOfBirth;

    public
    PAcadAddMemberProp(String membershipNumber, String name, String fatherName, String gender,
                       Date registrationDate,String mobileNumber, String residenceNumber, String address,
                        String email,String gymPackage,Date dateOfBirth
                       ) {
        this.membershipNumber = new SimpleStringProperty(membershipNumber);
        this.name = new SimpleStringProperty(name);
        this.fatherName = new SimpleStringProperty(fatherName);
        this.gender = new SimpleStringProperty(gender);
        this.mobileNumber = new SimpleStringProperty(mobileNumber);
        this.residenceNumber = new SimpleStringProperty(residenceNumber);
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
        this.gymPackage = new SimpleStringProperty(gymPackage);
        this.registrationDate = registrationDate;
        this.dateOfBirth = dateOfBirth;
    }

    public  StringProperty membershipNumberProperty(){  return membershipNumber;}
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

    public String getRegistrationDate(){ return registrationDate.toString(); }

    public String getDateOfBirth(){ return dateOfBirth.toString(); }
}

