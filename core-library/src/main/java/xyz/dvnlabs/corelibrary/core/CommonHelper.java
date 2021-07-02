package xyz.dvnlabs.corelibrary.core;

import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import xyz.dvnlabs.corelibrary.exception.AppException;
import xyz.dvnlabs.corelibrary.exception.InvalidInputException;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonHelper {

    /**
     * Is numeric boolean. for check string with Numeric
     *
     * @param strNum the str num
     * @return the boolean
     */
    public static boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    /**
     * Parse date with pattern date.
     *
     * @param strDate the str date
     * @param pattern the pattern
     * @return the date
     * @throws InvalidInputException the invalid input exception
     */
    public static Date parseDateWithPattern(String strDate, String pattern) throws InvalidInputException {

        if (pattern == null)
            pattern = "yyyy-MM-dd";

        DateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            throw new InvalidInputException("Format tanggal salah :: " + pattern);
        }

    }

    /**
     * Parse date sql with pattern java . sql . date.
     *
     * @param strDate the str date
     * @param pattern the pattern
     * @return the java . sql . date
     * @throws InvalidInputException the invalid input exception
     */
    public static java.sql.Date parseDateSqlWithPattern(String strDate, String pattern) throws InvalidInputException {
        return new java.sql.Date(parseDateWithPattern(strDate, pattern).getTime());
    }

    /**
     * Convert date to string with pattern string.
     *
     * @param date    the date
     * @param pattern the pattern
     * @return the string
     */
    public static String convertDateToStringWithPattern(Date date, String pattern) {

        if (pattern == null)
            pattern = "yyyy-MM-dd";

        DateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);

    }

    /**
     * Gets current date str.
     *
     * @return the current date str
     */
    public static String getCurrentDateStr() {
        return convertDateToStringWithPattern(getCurrentDate(), null);
    }

    /**
     * Gets current time str.
     *
     * @return the current time str
     */
    public static String getCurrentTimeStr() {
        return CommonHelper.convertDateToStringWithPattern(getCurrentDate(), "HH:mm:ss");
    }

    /**
     * Month to alfabet string.
     *
     * @param date the date
     * @return the string
     */
    public static String monthToAlfabet(Date date) {

        Map<String, String> alfaMonth = Stream.of(new String[][]{
                {"01", "A"},
                {"02", "B"},
                {"03", "C"},
                {"04", "D"},
                {"05", "E"},
                {"06", "F"},
                {"07", "G"},
                {"08", "H"},
                {"09", "I"},
                {"10", "J"},
                {"11", "K"},
                {"12", "L"}
        }).collect(Collectors.toMap(strings -> strings[0], strings -> strings[1]));

        SimpleDateFormat formatter = new SimpleDateFormat("MM");

        return alfaMonth.get(formatter.format(date));

    }

    /**
     * Get string seq string.
     *
     * @param lastSeq       the last seq
     * @param date          the date
     * @param prefix        the prefix
     * @param midfix        the midfix
     * @param zeroFill      the zero fill
     * @param withAlfaMonth the with alfa month
     * @param dateFormat    the date format
     * @return the string
     */
    @SneakyThrows
    public static String getStringSeq(
            String lastSeq, Date date, String prefix, String midfix, int zeroFill,
            boolean withAlfaMonth, @Nullable String dateFormat) {
        try {
            String strAlfaMonth = "";
            if (withAlfaMonth)
                strAlfaMonth = monthToAlfabet(date);

            long max = Long.parseLong(lastSeq.substring(lastSeq.length() - zeroFill));

            String dateStr = "";
            if (dateFormat != null) {
                SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
                dateStr = formatter.format(date);
            }

            return prefix + dateStr + strAlfaMonth + midfix + String.format("%0" + zeroFill + "d", (max + 1));
        } catch (Exception nfe) {
            throw new AppException("Last seq :: " + lastSeq + " tidak sesuai dengan zeroFill :: " + zeroFill, nfe);
        }
    }

    /**
     * Gets string seq with sequence.
     *
     * @param lastSeq       the last seq
     * @param date          the date
     * @param prefix        the prefix
     * @param midfix        the midfix
     * @param zeroFill      the zero fill
     * @param withAlfaMonth the with alfa month
     * @param dateFormat    the date format
     * @param sequence      the sequence
     * @return the string seq with sequence
     */
    public static String getStringSeqWithSequence(
            String lastSeq, Date date, String prefix, String midfix, int zeroFill,
            boolean withAlfaMonth, @Nullable String dateFormat, Integer sequence) {

        String strAlfaMonth = "";
        if (withAlfaMonth)
            strAlfaMonth = monthToAlfabet(date);

        long max = Long.parseLong(lastSeq.substring(lastSeq.length() - zeroFill));

        String dateStr = "";
        if (dateFormat != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            dateStr = formatter.format(date);
        }

        return prefix + dateStr + strAlfaMonth + midfix + String.format("%0" + zeroFill + "d", (max + sequence));

    }

    /**
     * Date to dayofweek string.
     *
     * @param date the date
     * @return the string
     */
    public static String dateToDayofweek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        Map<String, String> mapDaycd = Stream.of(new String[][]{
                {"1", "0"},
                {"2", "1"},
                {"3", "2"},
                {"4", "3"},
                {"5", "4"},
                {"6", "5"},
                {"7", "6"}
        }).collect(Collectors.toMap(strings -> strings[0], strings -> strings[1]));

        return mapDaycd.get(String.valueOf(cal.get(Calendar.DAY_OF_WEEK)));
    }

    /**
     * Map field sort pageable.
     *
     * @param pageable the pageable
     * @param mapField the map field
     * @return the pageable
     */
    public static Pageable mapFieldSort(Pageable pageable, Map<String, String> mapField) {
        List<Sort.Order> orders = pageable.getSort().stream().map(order -> {
            if (mapField.containsKey(order.getProperty()))
                return new Sort.Order(order.getDirection(), mapField.get(order.getProperty()));
            else
                return order;
        }).distinct().collect(Collectors.toList());

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
    }

    /**
     * Get date date.
     *
     * @return the date
     */
    public static Date getCurrentDate() {
        Calendar calendar = getCalendarNow();
        return calendar.getTime();
    }

    /**
     * Gets current time.
     *
     * @return the current time
     * @throws InvalidInputException the invalid input exception
     */
    public static Date getCurrentTime() throws InvalidInputException {
        return parseDateWithPattern(getCurrentTimeStr(), "HH:mm:ss");
    }

    /**
     * Gets time.
     *
     * @param date the date
     * @return the time
     * @throws InvalidInputException the invalid input exception
     */
    public static Date getTime(Date date) throws InvalidInputException {
        String timeStr = CommonHelper.convertDateToStringWithPattern(date, "HH:mm:ss");
        return parseDateWithPattern(timeStr, "HH:mm:ss");
    }

    /**
     * Gets time.
     *
     * @param timeStr the time str
     * @return the time
     * @throws InvalidInputException the invalid input exception
     */
    public static Date getTime(String timeStr) throws InvalidInputException {
        return parseDateWithPattern(timeStr, "HH:mm:ss");
    }

    /**
     * Combine date time date.
     *
     * @param date the date
     * @param time the time
     * @return the date
     */
    public static Date combineDateTime(Date date, Date time) {
        Calendar calDate = getCalendar(date);
        Calendar calTime = getCalendar(time);

        calDate.set(Calendar.HOUR_OF_DAY, calTime.get(Calendar.HOUR_OF_DAY));
        calDate.set(Calendar.MINUTE, calTime.get(Calendar.MINUTE));
        calDate.set(Calendar.SECOND, calTime.get(Calendar.SECOND));
        calDate.set(Calendar.MILLISECOND, calTime.get(Calendar.MILLISECOND));

        return calDate.getTime();
    }

    /**
     * Get time in millis long.
     *
     * @return the long
     */
    public static Long getCurrentTimeInMillis() {
        Calendar calendar = getCalendarNow();
        return calendar.getTimeInMillis();
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public static Integer getYear() {
        return getCalendarNow().get(Calendar.YEAR);
    }

    /**
     * Gets year.
     *
     * @param date the date
     * @return the year
     */
    public static Integer getYear(Date date) {
        Calendar cal = getCalendarNow();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * Get month int.
     *
     * @return the int
     */
    public static Integer getMonth() {
        return getCalendarNow().get(Calendar.MONTH) + 1;
    }

    /**
     * Get month int.
     *
     * @param date the date
     * @return the int
     */
    public static Integer getMonth(Date date) {
        Calendar cal = getCalendarNow();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * Gets day.
     *
     * @return the day
     */
    public static Integer getDay() {
        return getCalendarNow().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Gets day.
     *
     * @param date the date
     * @return the day
     */
    public static Integer getDay(Date date) {
        Calendar cal = getCalendarNow();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Gets calendar now.
     *
     * @return the calendar now
     */
    public static Calendar getCalendarNow() {
        return Calendar.getInstance();
    }

    /**
     * Gets calendar.
     *
     * @param date the date
     * @return the calendar
     */
    public static Calendar getCalendar(Date date) {
        Calendar cal = getCalendarNow();
        cal.setTime(date);
        return cal;
    }

    /**
     * Get umur map.
     *
     * @param dateBirth     the date birth
     * @param specifiedDate the specified date
     * @return the map
     */
    public static Map<String, Integer> getUmur(Date dateBirth, Date specifiedDate) {

        int yearAge;
        int monthAge;
        int dayAge;

        yearAge = CommonHelper.getYear(specifiedDate) - CommonHelper.getYear(dateBirth);
        if (CommonHelper.getMonth(specifiedDate) >= CommonHelper.getMonth(dateBirth))
            monthAge = CommonHelper.getMonth(specifiedDate) - CommonHelper.getMonth(dateBirth);
        else {
            yearAge--;
            monthAge = 12 + CommonHelper.getMonth(specifiedDate) - CommonHelper.getMonth(dateBirth);
        }
        if (CommonHelper.getDay(specifiedDate) >= CommonHelper.getDay(dateBirth))
            dayAge = CommonHelper.getDay(specifiedDate) - CommonHelper.getDay(dateBirth);
        else {
            monthAge--;
            dayAge = 30 + CommonHelper.getDay(specifiedDate) - CommonHelper.getDay(dateBirth);
            if (monthAge < 0) {
                monthAge = 11;
                yearAge--;
            }
        }

        /*12 Mar 2021 - dayAge ditambahkan jika yearAge == monthAge == dayAge == 0*/
        if (yearAge == 0 && monthAge == 0 && dayAge == 0)
            dayAge = 1;

        Map<String, Integer> mapUmur = new HashMap<>();
        mapUmur.put("day", dayAge);
        mapUmur.put("month", monthAge);
        mapUmur.put("year", yearAge);

        return mapUmur;
    }

    /**
     * Convert integer to roman string.
     *
     * @param number the number
     * @return the string
     */
    public static String convertIntegerToRoman(Integer number) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[number / 1000]
                + hundreds[(number % 1000) / 100]
                + tens[(number % 100) / 10]
                + units[number % 10];
    }


}
