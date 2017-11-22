package studio.limo.web.blog.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public DateUtil() {
    }

    public static String revertDate(String date) {
        if(date != null && !"".equals(date)) {
            if(date.getBytes().length != 10) {
                return date;
            } else {
                String delemiter = date.substring(4, 5);
                return date.replaceAll(delemiter, "");
            }
        } else {
            return date;
        }
    }

    public static Timestamp toSqlTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    public static Timestamp toSqlTimestamp_BD(String date) {
        if(date != null && !"".equals(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

            try {
                return toSqlTimestamp(sdf.parse(date + "000000"));
            } catch (ParseException var3) {
                logger.warn("Failed to parse date(" + date + ") into timestamp!");
                return null;
            }
        } else {
            return null;
        }
    }

    public static Timestamp toSqlTimestamp_ED(String date) {
        if(date != null && !"".equals(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

            try {
                return toSqlTimestamp(sdf.parse(date + "235959"));
            } catch (ParseException var3) {
                logger.warn("Failed to parse date(" + date + ") into timestamp!");
                return null;
            }
        } else {
            return null;
        }
    }

    public static Date toDate_BD(String date) {
        if(date != null && !"".equals(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date startDate = null;

            try {
                startDate = sdf.parse(date + "000000");
            } catch (ParseException var4) {
                logger.warn("Failed to parse date(" + date + ") into Date!");
            }

            return startDate;
        } else {
            return null;
        }
    }

    public static Date toDate_ED(String date) {
        if(date != null && !"".equals(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date endDate = null;

            try {
                endDate = sdf.parse(date + "235959");
            } catch (ParseException var4) {
                logger.warn("Failed to parse date(" + date + ") into Date!");
            }

            return endDate;
        } else {
            return null;
        }
    }

    public static Date getFirstDayOfMonth() {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.set(5, 1);
        return gc.getTime();
    }

    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        return sdf.format(new Date());
    }

    public static String formateDate(String pattern, Date date) {
        if(date == null) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        }
    }

    public static Date parseDate(String pattern, String strDate) {
        if(strDate != null && !"".equals(strDate)) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);

            try {
                String tmpDate = strDate;
                if(strDate.getBytes().length > pattern.getBytes().length) {
                    tmpDate = strDate.substring(0, pattern.getBytes().length);
                }

                return sdf.parse(tmpDate);
            } catch (ParseException var4) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String formatROCDate(String rocPattern, Date date) {
        if(date == null) {
            return null;
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(1, -1911);
            SimpleDateFormat sdf = new SimpleDateFormat(rocPattern);
            return sdf.format(cal.getTime());
        }
    }

    public static Date parseROCDate(String rocPattern, String strRocDate) {
        if(rocPattern != null && !"".equals(rocPattern)) {
            SimpleDateFormat sdf = new SimpleDateFormat(rocPattern);

            try {
                String tmpDate = strRocDate;
                if(strRocDate.getBytes().length > rocPattern.getBytes().length) {
                    tmpDate = strRocDate.substring(0, rocPattern.getBytes().length);
                }

                Calendar cal = Calendar.getInstance();
                cal.setTime(sdf.parse(tmpDate));
                cal.add(1, 1911);
                return cal.getTime();
            } catch (ParseException var5) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Calendar string2Calendar(String dateString) {
        int year = 0;
        int month = 0;
        int date = 0;
        int hour = 0;
        int min = 0;
        int sec = 0;
        if(dateString == null) {
            return null;
        } else {
            int myLen = dateString.length();
            if(myLen == 8 || myLen == 14) {
                year = Integer.parseInt(dateString.substring(0, 4));
                month = Integer.parseInt(dateString.substring(4, 6)) - 1;
                date = Integer.parseInt(dateString.substring(6, 8));
                if(myLen == 14) {
                    dateString = dateString.substring(8);
                }
            }

            if(dateString.length() == 6) {
                hour = Integer.parseInt(dateString.substring(0, 2));
                min = Integer.parseInt(dateString.substring(2, 4));
                sec = Integer.parseInt(dateString.substring(4, 6));
            }

            Calendar calendarObj = Calendar.getInstance();
            if(myLen == 8) {
                calendarObj.set(year, month, date);
                if(year != calendarObj.get(1) || month != calendarObj.get(2) || date != calendarObj.get(5)) {
                    return null;
                }
            } else if(myLen == 6) {
                if(hour < 0 || hour >= 24 || min < 0 || min >= 60 || sec < 0 || sec >= 60) {
                    return null;
                }
            } else {
                if(myLen != 14) {
                    return null;
                }

                calendarObj.set(year, month, date, hour, min, sec);
                if(year != calendarObj.get(1) || month != calendarObj.get(2) || date != calendarObj.get(5) || hour != calendarObj.get(11) || min != calendarObj.get(12) || sec != calendarObj.get(13)) {
                    return null;
                }
            }

            return calendarObj;
        }
    }

    public static String countDate(String orgDate, int dayCnt) {
        String myType = null;
        Calendar calendar = string2Calendar(orgDate);
        calendar.add(5, dayCnt);
        if(orgDate.length() == 8) {
            myType = "yyyyMMdd";
        } else if(orgDate.length() == 14) {
            myType = "yyyyMMddHHmmss";
        }

        return formateDate(myType, calendar.getTime());
    }
}
