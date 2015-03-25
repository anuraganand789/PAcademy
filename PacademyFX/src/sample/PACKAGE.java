package sample;

/**
 * Created by anurag on 10/23/2014.
 */
public
enum PACKAGE {

        MONTH_1(1),MONTH_3(3),MONTH_6(6),YEAR_1(12);

    private int months;

    PACKAGE(int months){
        this.months = months;
    }

    public int getMonths(){
        return months;
    }

}
