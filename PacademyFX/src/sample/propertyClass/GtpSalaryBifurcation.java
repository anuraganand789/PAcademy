package sample.propertyClass;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by anurag on 9/28/2014.
 */
public
class GtpSalaryBifurcation {
    private
    StringProperty da;
    private
    StringProperty splPay;
    private
    StringProperty personalPay;
    private
    StringProperty conveyance;
    private
    StringProperty otherAllowance;
    private
    StringProperty totAmount;
    private
    StringProperty perAnnum;
    private
    StringProperty totPerAnnum;
    private
    StringProperty ltc;
    private
    StringProperty medical;
    private
    StringProperty bonus;
    private
    StringProperty otherComponents;
    private
    StringProperty pf;
    private
    StringProperty gratuity;
    private
    StringProperty superAnnuation;
    private
    StringProperty gross;

    public GtpSalaryBifurcation(final String da,final String splDay,final String personalPay,
                                final String conveyance,final String otherAllowance,
                                final String totAmount,final String perAnnum,final String totPerAnnum,
                                final  String ltc,final String medical,
                                final String bonus,final String otherComponents,final String pf,
                                final String gratuity,final String superAnnuation,final String gross){
        this.da =  new SimpleStringProperty(da);
        this.splPay =  new SimpleStringProperty(splDay);
        this.personalPay = new SimpleStringProperty(personalPay);
        this.conveyance =  new SimpleStringProperty(conveyance);
        this.otherAllowance =  new SimpleStringProperty(otherAllowance);
        this.totAmount =  new SimpleStringProperty(totAmount);
        this.perAnnum =  new SimpleStringProperty(perAnnum);
        this.totPerAnnum =  new SimpleStringProperty(totPerAnnum);
        this.ltc =  new SimpleStringProperty(ltc);
        this.medical =  new SimpleStringProperty(medical);
        this.bonus =  new SimpleStringProperty(bonus);
        this.otherComponents =  new SimpleStringProperty(otherComponents);
        this.pf = new SimpleStringProperty(pf);
        this.gratuity =  new SimpleStringProperty(gratuity);
        this.superAnnuation =  new SimpleStringProperty(superAnnuation);
        this.gross =  new SimpleStringProperty(gross);

    }

    public StringProperty daProperty(){ return da; }
    public StringProperty splDayProperty(){ return splPay; }
    public StringProperty personalPayProperty(){ return personalPay; }
    public StringProperty conveyanceProperty(){ return conveyance; }
    public StringProperty otherAllowanceProperty(){ return otherAllowance; }
    public StringProperty totAmountProperty(){ return totAmount; }
    public StringProperty perAnnumProperty(){ return perAnnum; }
    public StringProperty totPerAnnumProperty(){ return totPerAnnum; }
    public StringProperty ltcProperty(){ return ltc; }
    public StringProperty medicalProperty(){ return medical; }
    public StringProperty bonusProperty(){ return bonus; }
    public StringProperty otherComponentsProperty(){ return otherComponents; }
    public StringProperty pfProperty(){ return pf; }
    public StringProperty gratuityProperty(){ return gratuity; }
    public StringProperty superAnnuationProperty(){ return superAnnuation; }
    public StringProperty grossProperty(){ return gross; }

}
