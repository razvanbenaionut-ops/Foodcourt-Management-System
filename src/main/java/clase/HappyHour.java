package clase;
import java.time.*;
public class HappyHour {
    public DayOfWeek zi;
    public LocalTime oraStart;
    public LocalTime oraStop;
    public int procent;

    public HappyHour(DayOfWeek zi,LocalTime oraStart,LocalTime oraStop,int procent) {
        this.zi=zi;
        this.oraStart=oraStart;
        this.oraStop=oraStop;
        this.procent=procent;
    }

    public DayOfWeek getZi() { return zi; }
    public LocalTime getOraStart() { return oraStart; }
    public LocalTime getOraStop() { return oraStop; }
    public int getProcent() { return procent; }

    public boolean activ(LocalDate data,LocalTime ora){
        return data.getDayOfWeek()==zi && !ora.isBefore(oraStart) && !ora.isAfter(oraStop);
    }
}
