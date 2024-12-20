package com.atguigu.sort;

import java.nio.channels.Pipe;
import java.time.Instant;
import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {2,42,4,25,64,4,2,1};
//        sort(arr,0,arr.length -1);

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long s1 = Instant.now().toEpochMilli();
        sort(arr,0,arr.length -1);
        long s2 = Instant.now().toEpochMilli();
        System.out.println("选择排序耗时：" + (s2 - s1) + "ms");
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr, int l, int r) {
        //1、第一次排序触发说明数组只有一个值；2、最左或最右序列只有一个值的情况；
        //3、最左或最右序列索引重合的情况
        if (l >= r){
            return;
        }

        //获取最左边的索引
        int left = l;
        //获取最右边的索引
        int right = r;
        //确定最左边为中轴
        int pivot = arr[left];
        while (left < right) {
            //寻找比中轴小的值，并移到左边
            while (right > left && arr[right] >= pivot) {
                right--;
            }
            if (left < right) {
                arr[left] = arr[right];
            }

            //寻找比中轴大的值，并移到右边
            while (right > left && arr[left] <= pivot) {
                left++;
            }
            if (left < right) {
                arr[right] = arr[left];
            }

            //重合
            if (left == right) {
                arr[left] = pivot;
            }
        }

        //向左遍历
        sort(arr,l,right-1);
        //向右遍历
        sort(arr,right+1,r);
    }
}


