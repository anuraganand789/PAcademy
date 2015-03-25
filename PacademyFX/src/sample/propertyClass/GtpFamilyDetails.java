package sample.propertyClass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by anurag on 9/27/2014.
 */
public
class GtpFamilyDetails {
    private
    StringProperty name;
    private
    StringProperty relation;
    private
    StringProperty profession;
    private
    StringProperty age;
    private
    StringProperty isDependent;

    public GtpFamilyDetails(final String name,final String relation,final String profession,
                           final String age,final String isDependent){

        this.name = new SimpleStringProperty(name);
        this.relation = new SimpleStringProperty(relation);
        this.profession = new SimpleStringProperty(profession);
        this.age = new SimpleStringProperty(age);
        this.isDependent = new SimpleStringProperty(isDependent);
    }

    public StringProperty nameProperty(){ return name;}
    public StringProperty relationProperty(){ return relation;}
    public StringProperty professionProperty(){ return profession;}
    public StringProperty ageProperty(){ return age;}
    public StringProperty isDependentProperty(){ return isDependent;}

}
