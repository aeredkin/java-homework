package com.homework.plugins.plugin1;

public class Plugin implements com.homework.plugins.Plugin {
    @Override
    public void doUseful() {
        System.out.println("Подключаемый модуль plugin1 работает.");
    }
}
