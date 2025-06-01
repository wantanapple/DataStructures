package com.atguigu.sort;

import java.time.Instant;
import java.util.Arrays;


/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {11, 222, 1, 2, 3, 3, 3, 999, 999, 999, 520};
//
//        int[] temp = new int[arr.length];
//        mergeSort(arr,0,arr.length -1,temp);
//
//        System.out.println("排序结果：");
//        System.out.println(Arrays.toString(arr));

        //测试8万条数据耗时多久
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        int[] temp = new int[arr.length];
        long s1 = Instant.now().toEpochMilli();
        mergeSort(arr, 0, arr.length - 1, temp);
        long s2 = Instant.now().toEpochMilli();
        System.out.println("简单插入排序耗时：" + (s2 - s1) + "ms");

    }

    /**
     * 归并排序（拆分+合并）
     * @param arr 原数组
     * @param left 左索引
     * @param right 右索引
     * @param temp 中间数组
     */
    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        //只有一个元素的情况下，无需拆分和排序
        if (left == right) {
            return;
        }

        int mid = (left + right) / 2;
        //向左拆分
        mergeSort(arr, left, mid, temp);
        //向右拆分
        mergeSort(arr, mid + 1, right, temp);

        //合并
        merge(arr, left, mid, right, temp);
    }

    /**
     * 合并
     *
     * @param arr   愿数组
     * @param left  拆分后数组的左索引
     * @param mid   中间索引
     * @param right 拆分后数组的右索引
     * @param temp  中转数组
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //左数组的索引
        int i = left;
        //右数组的索引
        int j = mid + 1;
        //中间数组的索引
        int tempIndex = 0;

        //有一边数组完成排序才结束循环
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                //满足条件放入中间数组
                temp[tempIndex] = arr[i];
                //索引后移
                tempIndex++;
                i++;
            } else {
                //满足条件放入中间数组
                temp[tempIndex] = arr[j];
                //索引后移
                tempIndex++;
                j++;
            }
        }

        //将左边或者右边数组的剩余元素放到中间数组
        while (i <= mid) {
            temp[tempIndex] = arr[i];
            tempIndex++;
            i++;
        }

        while (j <= right) {
            temp[tempIndex] = arr[j];
            tempIndex++;
            j++;
        }

        //排序完成后将有序数组放回愿数组
        int index = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[index];
            index++;
            tempLeft++;
        }

    }

}