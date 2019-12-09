package godday.xin.job;

import godday.xin.utils.UtilFuns;

import java.text.ParseException;
import java.util.Date;

public class Test_Job {

    public void showtime() throws ParseException {
        System.out.println(UtilFuns.dateTimeFormat(new Date(),"HH:mm:ss"));
    }
}
