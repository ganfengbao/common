package com.junbao.string;

import org.junit.Test;

public class TestString {
    @Test
    public void  test1() {
        String str1 = "JavaEE";
        String str2 = "JavaEE";
        String str3 = new String("JavaEE");
        String str4 = "JavaEE" + "Android";
        String str5 = "Android";
        String str6 = str1 + str5;
        String str7 = str6.intern();
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str1.equals(str3));

        System.out.println(str4 == str6);
        System.out.println(str4.equals(str6));
        System.out.println(str4 == str7);

    }
}
