package sample.propertyClass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by anurag on 9/27/2014.
 */


public
class GtpBasicDetails {
    private
    StringProperty postApplied;
    private
    StringProperty name;
    private
    StringProperty fatherName;
    private
    StringProperty dateOfBirth;
    private
    StringProperty address;
    private
    StringProperty state;
    private
    StringProperty pincode;
    private
    StringProperty phoneNumber;
    private
    StringProperty email;
    private
    StringProperty martialStatus;

    public GtpBasicDetails(final String postApplied, final String name,final String fatherName,
                          final String dateOfBirth,final String address,final String state,final String pincode,
                          final String phoneNumber,final String email,final String martialStatus
                          ){
        this.postApplied = new SimpleStringProperty(postApplied);
        this.name = new SimpleStringProperty(name);
        this.fatherName = new SimpleStringProperty(fatherName);
        this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
        this.address = new SimpleStringProperty(address);
        this.state = new SimpleStringProperty(state);
        this.pincode = new SimpleStringProperty(pincode);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.email = new SimpleStringProperty(email);
        this.martialStatus = new SimpleStringProperty(martialStatus);
    }

    public StringProperty postAppliedProperty(){
        return postApplied;
    }
    public StringProperty nameProperty(){
        return name;
    }

    public StringProperty fatherNameProperty(){
        return fatherName;
    }

    public StringProperty dateOfBirthProperty(){
        return dateOfBirth;
    }

    public StringProperty addressProperty(){
        return address;
    }

    public StringProperty stateProperty(){
        return state;
    }

    public StringProperty pincodeProperty(){
        return pincode;
    }

    public StringProperty phoneNumberProperty(){
        return phoneNumber;
    }

    public StringProperty emailProperty(){
        return email;
    }

    public StringProperty martialStatusProperty(){
        return martialStatus;
    }
}
