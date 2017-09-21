package com.weibin.chapter2;

import com.weibin.alg1.Example;

import java.io.FileNotFoundException;

import static com.weibin.alg1.Example.*;

/**
 * Created by wei.bin on 2017/9/18.
 */
public class Shell {

    public static void sort(Comparable a[]){
        int n = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n/3) h = 3*h + 1;

        while (h >= 1) {
            // 将数组变为h有序
            for (int i = h; i < n; i++) {
                // 将a[i]插入到a[i-h], a[i-2*h], a[i-3*h]... 之中
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String[] c = FileToArray.toArray("tiny.txt");
        Shell.sort(c);
        Example.show(c);
    }
}
