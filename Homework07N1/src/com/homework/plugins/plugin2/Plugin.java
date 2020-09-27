package com.homework.plugins.plugin2;

public class Plugin implements com.homework.plugins.Plugin {
    @Override
    public void doUseful() {
        System.out.println("Подключаемый модуль plugin2 работает.");
    }
}
