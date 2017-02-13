package com.cuiwei.share.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexCountTest {

    // ?可选项元素，0次或一次
    @Test
    public void test1() {
        String str = "cat";
        Pattern pattern = Pattern.compile("ca?t");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    @Test
    public void test2() {
        String str = "ct";
        Pattern pattern = Pattern.compile("ca?t");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    // +一次或多次
    @Test
    public void test3() {
        String str = "cat";
        Pattern pattern = Pattern.compile("ca+t");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    @Test
    public void test4() {
        String str = "ct";
        Pattern pattern = Pattern.compile("ca+t");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    // * 任意多次或不出现
    
    @Test
    public void test5() {
        String str = "ct";
        Pattern pattern = Pattern.compile("ca*t");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    @Test
    public void test6() {
        String str = "caaaat";
        Pattern pattern = Pattern.compile("ca*t");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    // {num}只能num次
    @Test
    public void test7() {
        String str = "caaat";
        Pattern pattern = Pattern.compile("ca{3}t");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    // {min,max}最少min次，最多max次
    
    @Test
    public void test8() {
        String str = "caaaaat";
        Pattern pattern = Pattern.compile("ca{0,3}t");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    @Test
    public void test9() {
        String str = "caaaaat";
        Pattern pattern = Pattern.compile("ca{4,6}t");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

}
