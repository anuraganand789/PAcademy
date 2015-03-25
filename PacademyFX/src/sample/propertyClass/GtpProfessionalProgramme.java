package sample.propertyClass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by anurag on 9/27/2014.
 */
public
class GtpProfessionalProgramme {
    private
    StringProperty courseName;
    private
    StringProperty courseDuration;
    private
    StringProperty instituteName;
    private
    StringProperty institutePlace;
    private
    StringProperty yearOfPassing;

    public GtpProfessionalProgramme(final String courseName,final String courseDuration,
                                    final String instituteName,final String institutePlace,
                                    final String yearOfPassing){
        this.courseName = new SimpleStringProperty(courseName);
        this.courseDuration =  new SimpleStringProperty(courseDuration);
        this.instituteName = new SimpleStringProperty(instituteName);
        this.institutePlace =  new SimpleStringProperty(institutePlace);
        this.yearOfPassing = new SimpleStringProperty(yearOfPassing);
    }

    public StringProperty courseNameProperty(){ return courseName; }
    public StringProperty courseDurationProperty(){ return courseDuration;  }
    public StringProperty instituteNameProperty(){ return instituteName;  }
    public StringProperty institutePlaceProperty(){ return institutePlace;  }
    public StringProperty yearOfPassingProperty(){ return yearOfPassing;  }

}
