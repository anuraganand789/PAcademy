package sample.propertyClass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by anurag on 9/28/2014.
 */
public
class GtpMiscellaneous {
    private
    StringProperty ctc;
    private
    StringProperty strength;
    private
    StringProperty weakness;
    private
    StringProperty question1;
    private
    StringProperty question2;
    private
    StringProperty question3;
    private
    StringProperty question4;
    private
    StringProperty question5;
    private
    StringProperty question6;

    public GtpMiscellaneous(final String ctc,final String strength,final String weakness,
                            final String question1,final String question2,final String question3,
                            final String question4,final String question5,final String question6){
        this.ctc =  new SimpleStringProperty(ctc);
        this.strength =  new SimpleStringProperty(strength);
        this.weakness =  new SimpleStringProperty(weakness);
        this.question1 =  new SimpleStringProperty(question1);
        this.question2 =  new SimpleStringProperty(question2);
        this.question3 =  new SimpleStringProperty(question3);
        this.question4 =  new SimpleStringProperty(question4);
        this.question5 =  new SimpleStringProperty(question5);
        this.question6 =  new SimpleStringProperty(question6);
    }

    public StringProperty ctcProperty(){ return ctc;}
    public StringProperty strengthProperty(){ return strength;}
    public StringProperty weaknessProperty(){ return weakness;}
    public StringProperty question1Property(){ return question1;}
    public StringProperty question2Property(){ return question2;}
    public StringProperty question3Property(){ return question3;}
    public StringProperty question4Property(){ return question4;}
    public StringProperty question5Property(){ return question5;}
    public StringProperty question6Property(){ return question6;}

}
