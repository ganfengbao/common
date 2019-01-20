package com.junbao.template;

public class Test {
    public static void main(String[] args) {
        System.out.println("咖啡准备中...");
        RefreshBeverage coffer = new Coffer();
        coffer.prepareBeverageTemplate();
        System.out.println("咖啡准完成");

        System.out.println("******************************");

        System.out.println("茶叶准备中...");
        RefreshBeverage tea = new Tea();
        tea.prepareBeverageTemplate();
        System.out.println("茶叶冲泡完成");
    }
}
