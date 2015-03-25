package sample.propertyClass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by anurag on 10/23/2014.
 */
public
class PAcadDefaulter {
    private
    StringProperty membershipNumber;
    private
    StringProperty name;
    private
    StringProperty address;
    private
    StringProperty mobileNumber;
    private
    StringProperty residenceNumber;
    private
    StringProperty email;


    public
    PAcadDefaulter(String membershipNumber, String name,
                     String address, String mobileNumber, String residenceNumber,String email
                    ) {
        this.membershipNumber = new SimpleStringProperty(membershipNumber);
        this.name = new SimpleStringProperty(name);
        this.address =  new SimpleStringProperty(address);
        this.mobileNumber = new SimpleStringProperty(mobileNumber);
        this.residenceNumber =new SimpleStringProperty(residenceNumber) ;
        this.email =  new SimpleStringProperty(email);
    }

    public  StringProperty membershipNumberProperty(){  return membershipNumber;}
    public StringProperty nameProperty(){ return name; }
    public StringProperty addressProperty(){ return address; }
    public StringProperty mobileNumberProperty(){ return mobileNumber; }
    public StringProperty residenceNumberProperty(){ return residenceNumber; }
    public StringProperty emailProperty(){ return email; }

}


