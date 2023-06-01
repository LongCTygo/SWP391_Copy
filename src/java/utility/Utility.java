/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import dao.DAO;
import entity.StudentTestDetail;
import entity.Selection;
import entity.TestDetail;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 *
 * @author Asus
 */
public class Utility {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public static String generateRandCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
    
    public static String encode(String password) {
        String code = "";
        for (Character c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                code += c.toString().toLowerCase() + ((c < 'a') ? "" : "%");
            } else {
                code += c;
            }
        }
        return code;
    }

    public static String decode(String code) {
        String password = "";
        code += '.';
        for (int i = 0; i < code.length() - 1; ++i) {
            String reg = code.substring(i, i + 2);
            if (Pattern.compile("[a-z]%").matcher(reg).find()) {
                password += reg.charAt(0);
                ++i;
            } else {
                password += reg.toUpperCase().charAt(0);
            }
        }
        return password;
    }
    
    public static boolean isNumber(String candidate) {
        try {
            int n = Integer.parseInt(candidate);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public static String timeToString(Long time, int unit) {
        return (unit == 0 ? time.toString() : (time/(int)Math.pow(60, unit)) + ":" + timeToString(time%((int)Math.pow(60, unit)), unit-1));
    } 
    
    
    public static long timeToInt(String time) {
        long t = 0;
        int unit = 2;
        for (String i : time.split(":")) {
            t += Integer.parseInt(i) * Math.pow(60, unit);
            unit--;
        }
        return t;
    }
    
    public static double calculateCoefficent(String selected, Vector<Selection> selections) {
        double coef = 1;
        int penalty = selected.length();
        for (Selection selection : selections) {
            if (!selected.contains(selection.getCharId())) {
                coef -= selection.getCoefficent();
            } else {
                --penalty;
            }
        }
        return coef * Math.pow(0.25, penalty);
    }
    
    protected static String monthInWord(Integer month) {
        String MIW = "";
        switch (month) {
            case 1:
                MIW = "Jan";
                break;
            case 2:
                MIW = "Feb";
                break;
            case 3:
                MIW = "Mar";
                break;
            case 4:
                MIW = "Apr";
                break;
            case 5:
                MIW = "May";
                break;
            case 6:
                MIW = "Jun";
                break;
            case 7:
                MIW = "Jul";
                break;
            case 8:
                MIW = "Aug";
                break;
            case 9:
                MIW = "Sep";
                break;
            case 10:
                MIW = "Nov";
                break;
            case 11:
                MIW = "Oct";
                break;
            case 12:
                MIW = "Dec";
                break;
        }
        return MIW;
    }
    
    public static String changDateFormat(Timestamp date) {
        String[] timeUnit = date.toString().split("-|\\s");
        return monthInWord(Integer.parseInt(timeUnit[1])) + " " + timeUnit[2] + ", " + timeUnit[0] + " "+ timeUnit[3].substring(0,8);
    }
    
    public static double calculateScore(StudentTestDetail ctd) {
        DAO dao = new DAO();
        TestDetail testDetail = dao.getTestDetail(ctd.getTDid());
        System.out.println(ctd.getTDid());
        return testDetail.getCoefficient() * calculateCoefficent(ctd.getSelected(), dao.getAnswer(testDetail.getQuestionId()));
    }
    
    public static void main(String[] args) {
        System.out.println(changDateFormat(new Timestamp(System.currentTimeMillis())));
    }
}
