package com.homework.plugins;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) {
        try {
            URLClassLoader classLoader = new URLClassLoader(new URL[] {
                    new URL("file://" + pluginRootDirectory + "/" + pluginName + "/") });
            return (Plugin) classLoader.loadClass(pluginClassName).newInstance();
        } catch (MalformedURLException e) {
            System.out.println("Ошибка в пути к классу подключаемого модуля.");
        } catch (ClassNotFoundException e) {
            System.out.println("Класс подключаемого модуля не найден.");
        } catch (IllegalAccessException e) {
            System.out.println("Нет доступа к членам класса подключаемого модуля.");
        } catch (InstantiationException e) {
            System.out.println("Не удаётся создать экземпляр класса подключаемого модуля.");
        }
        return null;
    }
}
