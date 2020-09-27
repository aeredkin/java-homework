package com.homework.encryptedclass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        EncryptedClassLoader classLoader = new EncryptedClassLoader("key",
                new File("out/production/Homework07N2/com/homework/encryptedclass/EncryptedClass.class"),
                ClassLoader.getSystemClassLoader());
        try {
            classLoader.encrypt();
            Class<?> encryptedClass = classLoader.findClass("com.homework.encryptedclass.EncryptedClass");
            Object encryptedClassInstance = encryptedClass.newInstance();
            encryptedClass.getMethod("doUseful").invoke(encryptedClassInstance);
        } catch (IOException e) {
            System.out.println("Файл класса не найден.");
        } catch (ClassNotFoundException | ClassFormatError e) {
            System.out.println(e.getMessage());
        } catch (InstantiationException e) {
            System.out.println("Ошибка при создании экземпляра класса.");
        } catch (IllegalAccessException e) {
            System.out.println("Ошибка доступа при создании экземпляра класса.");
        } catch (NoSuchMethodException e) {
            System.out.println("Вызываемый метод не найден.");
        } catch (InvocationTargetException e) {
            System.out.println("Ошибка при вызове метода.");
        }
    }
}
