package com.weibin.chapter2;

import com.weibin.alg1.Example;
import java.io.FileNotFoundException;


/**
 * Created by wei.bin on 2017/9/18.
 */
public class Insertion {

    public static void sort(Comparable[] a){
        int N = a.length;
        for (int i = 1; i < N; i++){
            for (int j = i; j >  0 && Example.less(a[j], a[j - 1]); j--){
                Example.exch(a, j, j-1);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String[] c = FileToArray.toArray("tiny.txt");
//        for (int i = 0; i < c.length; i++){
//            System.out.println(c[i]);
//        }
//        for (Object e : list.toArray()){
//            System.out.println(e);
//        }
       // String[] b = StdIn.readAllStrings();
        Insertion.sort(c);
        Example.show(c);

    }
}
