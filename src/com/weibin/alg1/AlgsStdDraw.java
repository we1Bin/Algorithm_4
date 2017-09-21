package com.weibin.alg1;


import edu.princeton.cs.algs4.StdDraw;

/**
 * Created by wei.bin on 2017/8/22.
 */
public class AlgsStdDraw {
    public static void main(String[] args) {
        int n = 100;
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, n*n);
        StdDraw.setPenRadius(.01);
        for (int i = 0; i <= n; i++){
            StdDraw.point(i, i);
            StdDraw.point(i, i*i);
            StdDraw.point(i, i*Math.log(i));
        }
    }
}
