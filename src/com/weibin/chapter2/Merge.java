package com.weibin.chapter2;

import java.io.FileNotFoundException;

import static com.weibin.chapter2.Example.isSorted;
import static com.weibin.chapter2.Example.less;
import static com.weibin.chapter2.Example.show;

/**
 * 归并排序：将两个不同的有序数组归并到第三个数组中。
 * 每次合并操作的平均时间复杂度为O(n)，
 * 而完全二叉树的深度为|log2n|。
 * 总的平均时间复杂度为O(nlogn)。
 * 归并排序的最好，最坏，平均时间复杂度均为O(nlogn)。
 * 递归深度为log2n。
 * Created by wei.bin on 2017/9/21.
 */
public class Merge {

    // 稳定的归并a[lo .. mid]和a[mid+1 ..hi]到aux[lo .. hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // 首先: a[lo .. mid] and a[mid+1 .. hi] 是排好序的子数组
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        // 将数组复制到aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // 归并回到a[]
        // 左序列指针
        int i = lo;
        //右序列指针
        int j = mid+1;
        //循环的判断 填充数组
        for (int k = lo; k <= hi; k++) {
            //左半边元素用尽，取右边
            if      (i > mid)              a[k] = aux[j++];
            //右半边用尽，去左边
            else if (j > hi)               a[k] = aux[i++];
            //右半边的当前元素小于左边的当前元素，取右半边的元素
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            //右半边的当前元素大于等于左边的当前元素，取左半边的元素
            else                           a[k] = aux[i++];
        }

        // post condition: a[lo .. hi] is sorted
        assert isSorted(a, lo, hi);
    }

    // merge sort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        //左边的归并排序，使得左子序列有序
        sort(a, aux, lo, mid);
        //右边的归并排序，使得右子序列有序
        sort(a, aux, mid + 1, hi);
        //将两个有序子数组合并
        merge(a, aux, lo, mid, hi);
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        //在排序前，先建好一个长度等于原数组长度的临时数组，
        // 避免递归中频繁开辟空间
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
        assert isSorted(a);
    }




    /***************************************************************************
     *  Index mergesort.
     ***************************************************************************/
    // stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
    private static void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = index[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                    index[k] = aux[j++];
            else if (j > hi)                     index[k] = aux[i++];
            else if (less(a[aux[j]], a[aux[i]])) index[k] = aux[j++];
            else                                 index[k] = aux[i++];
        }
    }

    /**
     * 返回一个以升序给出数组中元素的排序
     * @param a the array
     * @return a permutation {@code p[]} such that {@code a[p[0]]}, {@code a[p[1]]},
     *    ..., {@code a[p[N-1]]} are in ascending order
     */
    public static int[] indexSort(Comparable[] a) {
        int n = a.length;
        int[] index = new int[n];
        for (int i = 0; i < n; i++)
            index[i] = i;

        int[] aux = new int[n];
        sort(a, index, aux, 0, n-1);
        return index;
    }

    // merge sort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, index, aux, lo, mid);
        sort(a, index, aux, mid + 1, hi);
        merge(a, index, aux, lo, mid, hi);
    }




    public static void main(String[] args) throws FileNotFoundException {
        String[] a = FileToArray.toArray("tiny.txt");
        Merge.sort(a);
        show(a);
    }
}
