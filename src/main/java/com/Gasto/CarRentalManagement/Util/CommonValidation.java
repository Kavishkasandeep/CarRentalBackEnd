package com.Gasto.CarRentalManagement.Util;

import com.Gasto.CarRentalManagement.Const.CommonStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

public class CommonValidation {
    //**************-REGEX-PATTERNS-*******************
    final private static String regexLETTERS_ONLY= "^[a-zA-Z]+$";
    final private static String regexLETTERS_AND_SPACES = "^[a-zA-Z0-9 -]+$";

    final private static String regexNIC1 = "^[0-9]{9}[VX]$";
    final private static String regexNIC2 = "^[0-9]{12}$";
    final private static String regexEMAIL = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    final private static String regexPHONE_NO_10_DIGIT = "^0[0-9]+$";
    final private static String regexPHONE_NO_12_DIGIT = "^\\+94[0-9]+$";
    final private static String regexUSER_NAME = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#$%^&*()-+]).+$";
    final private static String regexPASSWORD = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#$%^&*()-+]).+$";
//**************-REGEX-PATTERNS-*******************



    //**************-MATCHERS-*********************
    final private static Pattern patternNIC1 = Pattern.compile(regexNIC1);
    final private static Pattern patternNIC2 = Pattern.compile(regexNIC2);
    final private static Pattern patternLETTERS_ONLY = Pattern.compile(regexLETTERS_ONLY);
    final private static Pattern patternLETTERS_AND_SPACE_HIPHEN_ONLY = Pattern.compile(regexLETTERS_AND_SPACES);
    final private static Pattern patternEMAIL = Pattern.compile(regexEMAIL);
    final private static Pattern patternPHONE_NO_10_DIGIT = Pattern.compile(regexPHONE_NO_10_DIGIT);
    final private static Pattern patternPHONE_NO_12_DIGIT = Pattern.compile(regexPHONE_NO_12_DIGIT);
    final private static  Pattern patternUSER_NAME = Pattern.compile(regexUSER_NAME);
    final private static Pattern patternPASSWORD = Pattern.compile(regexPASSWORD);
//**************-MATCHERS-*********************

    public static boolean commenStatusNullValidation(CommonStatus inputStatus) {
        if (inputStatus != null) {
            return false;
        }
        return true;
    }


    public static boolean stringNullValidation(String inputString){
        return inputString == null || inputString.isEmpty() || inputString.isBlank();
    }

    public static boolean integerNumberNullValidation(Integer inputInteger){
        return inputInteger == null ;
    }

    public static boolean LongNumberNullValidation(Long inputLong){
        return inputLong == null ;
    }

    public static boolean StringNumberNullValidation(String inputString){
        return inputString == null || inputString.isEmpty() || inputString.isBlank();
    }


    public static boolean iDNullValidation(Long id){
        if(id!=null){
            return false;
        }
        return true;
    }









    public static boolean isValidCommenStatus(CommonStatus inputStatus) {


        switch (inputStatus) {
            case ACTIVE:
            case INACTIVE:
            case DELETE:
                return false;
            default:
                return true;
        }

    }


    public static boolean isValidId(Long id){
        if(id>0){
            return false;
        }
        return true;
    }

    public static boolean isValidIntegerNumber(Integer inputInteger){
        return !(inputInteger != null && inputInteger>=0 );
    }
    public static boolean isValidDoubleNumber(Double inputNumber){
        return !(inputNumber != null && inputNumber>=0 );
    }

    public static boolean isValidLongNumber(Long inputLong){
        return !(inputLong != null && inputLong>=0 );
    }

    public static boolean isValidStringNumber(String inputString){
        return !(inputString != null && !inputString.isEmpty() && !inputString.isBlank() && Integer.parseInt(inputString)>=0);
    }







    public static boolean isValidText(String text){
        Matcher matcher = patternLETTERS_AND_SPACE_HIPHEN_ONLY.matcher(text);

        if(matcher.matches()){
            return false;
        }
        return true;
    }

    public static boolean isvalidNic(String nic) {
        Matcher matcher1 = patternNIC1.matcher(nic);
        Matcher matcher2 = patternNIC2.matcher(nic);

        if (matcher1.matches() || matcher2.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isValidName(String name){
        Matcher matcher = patternLETTERS_ONLY.matcher(name);

        if(name.length()>=3 && matcher.matches()){
            return false;
        }
        return true;
    }

    public static boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {

            Date inputDate = dateFormat.parse(date);
            Date currentDate = new Date();
            if (inputDate.before(currentDate)) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("date parsing error");
            LOGGER.error("/**************Exception in Common validation -> isValidDate(^)!");
        }

        return true;
    }
    public static boolean isValidEmail(String email){
        Matcher matcher = patternEMAIL.matcher(email);

        if(email.length()>5 && matcher.matches()){
            return false;
        }
        return true;
    }
    public static boolean isValidContactNo(String contactNo){
        Matcher matcher1 = patternPHONE_NO_10_DIGIT.matcher(contactNo);
        Matcher matcher2 = patternPHONE_NO_12_DIGIT.matcher(contactNo);

        if(contactNo.length()==10 && matcher1.matches()){
            return false;
        }else if(contactNo.length()==12 && matcher2.matches()){
            return false;
        }
        return true;

    }
    public static boolean isValidUserName(String userName){
        Matcher matcher = patternUSER_NAME.matcher(userName);

        if(userName.length()>4 && matcher.matches()){
            return false;
        }
        return true;
    }
    public static boolean isValidPassword(String password){
        Matcher matcher = patternPASSWORD.matcher(password);
        if(password.length()>8 && matcher.matches()){
            return false;
        }
        return true;
    }
}
