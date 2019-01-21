package com.junbao.set;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TestSet {

    @Test
    public void test1() {
        Set set = new HashSet<>();
        set.add("aaa");
        System.out.println(set.add("aaa"));
    }
}
