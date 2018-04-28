package com.mobvista.leidu.parse;

/**
 * author: dulei
 * date: 18-4-16
 * desc:
 */
public class DateCompare {
    public static void main(String[] args) {
        String date ="2018-04-11";
        String newDate = "2018-04-10";
        if(date.compareTo(newDate) < 0){
            System.out.println(true);
        }else {
            System.out.println(false);
        }
    }
}
