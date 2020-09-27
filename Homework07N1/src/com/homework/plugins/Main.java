package com.homework.plugins;

public class Main {

    public static void main(String[] args) {
        PluginManager manager = new PluginManager("../../../../out/production/Homework07N1/com/homework/plugins");

        Plugin plugin1 = manager.load("plugin1", "com.homework.plugins.plugin1.Plugin");
        if (plugin1 != null) {
            plugin1.doUseful();
        }

        Plugin plugin2 = manager.load("plugin2", "com.homework.plugins.plugin2.Plugin");
        if (plugin2 != null) {
            plugin2.doUseful();
        }
    }
}
