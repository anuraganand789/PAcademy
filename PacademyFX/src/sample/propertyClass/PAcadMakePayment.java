package sample.propertyClass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

/**
 * Created by anurag on 10/22/2014.
 */
public
class PAcadMakePayment {
    private
    StringProperty membershipNumber;
    StringProperty gymPackage;
    private
    Date           dateCurrent;
    private
    Date           nextPaymentDate;
    private StringProperty comment;

    public
    PAcadMakePayment(String membershipNumber,Date dateCurrent,
                     String comment, String gymPackage,Date nextPaymentDate
                    ) {
        this.membershipNumber = new SimpleStringProperty(membershipNumber);
         this.gymPackage = new SimpleStringProperty(gymPackage);
        this.dateCurrent = dateCurrent;
        this.comment = new SimpleStringProperty(comment);
        this.nextPaymentDate = nextPaymentDate;
    }

    public  StringProperty membershipNumberProperty(){  return membershipNumber;}
    public StringProperty gymPackageProperty(){ return gymPackage; }
    public StringProperty commentProperty(){ return comment; }

    public Date getDateCurrent(){ return dateCurrent; }
    public Date getDateNextPayment(){ return nextPaymentDate; }

}


