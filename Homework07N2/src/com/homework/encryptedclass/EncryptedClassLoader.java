package com.homework.encryptedclass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EncryptedClassLoader extends ClassLoader {
    private final String key;
    private final File dir;

    public EncryptedClassLoader(String key, File dir, ClassLoader parent) {
        super(parent);
        this.key = key;
        this.dir = dir;
    }

    @Override
    protected Class<?> findClass(String s) throws ClassNotFoundException {
        int key = this.key.hashCode();
        try {
            byte[] byteArray = Files.readAllBytes(dir.toPath());
            for (int i = 0; i < byteArray.length; ++i) {
                byteArray[i] += key;
            }
            return defineClass(s, byteArray, 0, byteArray.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Файл класса не найден.");
        } catch (ClassFormatError e) {
            throw new ClassFormatError("Ошибка при расшифровке класса.");
        }
    }

    public void encrypt() throws IOException {
        int key = this.key.hashCode();
        byte[] byteArray = Files.readAllBytes(dir.toPath());
        for (int i = 0; i < byteArray.length; ++i) {
            byteArray[i] -= key;
        }
        Files.write(Paths.get(dir.toURI()), byteArray);
    }
}
