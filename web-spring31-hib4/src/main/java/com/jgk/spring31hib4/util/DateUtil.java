package com.jgk.spring31hib4.util;

import java.util.Date;

public class DateUtil {
    public static Date getDateCopyOrNull(Date someDate) {
        return (someDate!=null)?new Date(someDate.getTime()):(Date)null;
    }

}
