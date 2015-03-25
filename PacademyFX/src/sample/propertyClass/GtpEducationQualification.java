package sample.propertyClass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by anurag on 9/27/2014.
 */
public
class GtpEducationQualification {
    private
    StringProperty standard;
    private
    StringProperty board;
    private
    StringProperty mainSubject;
    private
    StringProperty passingYear;
    private
    StringProperty percentage;
    private
    StringProperty question1;
    private
    StringProperty question2;

    public
    GtpEducationQualification(final String standard,final String board,final String mainSubject,
                              final String passingYear,final String percentage){

        this.standard =  new SimpleStringProperty(standard);
        this.board  = new SimpleStringProperty(board);
        this.mainSubject = new SimpleStringProperty(mainSubject);
        this.passingYear =  new SimpleStringProperty(passingYear);
        this.percentage = new SimpleStringProperty(percentage);
   //     this.question1 = new SimpleStringProperty(question1);
   //     this.question2 = new SimpleStringProperty(question2);

    }

    public StringProperty standardProperty(){return standard;}
    public StringProperty boardProperty(){ return board;}
    public StringProperty mainSubjectProperty(){ return mainSubject;}
    public StringProperty passingYearProperty(){ return passingYear;}
    public StringProperty percentageProperty(){ return percentage;}
  //  public StringProperty question1Property(){ return question1;}
  //  public StringProperty question2Property(){ return question2;}
}
