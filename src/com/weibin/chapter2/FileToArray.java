package com.weibin.chapter2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 读取文件内容 转换为数组
 * Created by wei.bin on 2017/9/18.
 */
public class FileToArray {


    public static String[] toArray(String fileName) throws FileNotFoundException {



        File file = new File(".\\src\\" + fileName);
        Scanner scanner = new Scanner(file);
        ArrayList<String> list = new ArrayList<>();
        while (scanner.hasNext()){
            list.add(scanner.next());
        }
        int size = list.size();
        String[] c = list.toArray(new String[size]);

        return c;
    }
}
