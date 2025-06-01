package com.atguigu.sort;

import javafx.beans.binding.When;

import java.time.Instant;
import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {20,40,7,2,5, 8,3,35,31,1};

//        sort(arr);
//        System.out.println(Arrays.toString(arr));

        //测试8万条数据耗时多久
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

//        long s1 = Instant.now().toEpochMilli();
//        sort(arr);
//        long s2 = Instant.now().toEpochMilli();
//        System.out.println("选择排序耗时：" + (s2 - s1) + "ms");

        long s1 = Instant.now().toEpochMilli();
        shellSort(arr);
        long s2 = Instant.now().toEpochMilli();
        System.out.println("选择排序耗时：" + (s2 - s1) + "ms");
//        System.out.println(Arrays.toString(arr));


       /**
        //第一次排序
        //第一次步长为3
        int step = 5;
        //步长为5有5组  比较5次
        for (int i = 5; i < arr.length; i++) {
            //定义一个中间变靓  用于数据交换
            int temp = 0;
            //每组要比较几次？  如果该索引前面也有元素，也需要进行比较
            for (int j = i - 5; j >= 0; j -= 5) {
                if (arr[j] > arr[j + 5]) {
                    //交换
                    temp = arr[j];
                    arr[j] = arr[j  + 5];
                    arr[j + 5] = temp;
                }
            }


        }
        System.out.println("第一次排序结果：");
        System.out.println(Arrays.toString(arr));
        
        //第二次排序
        //步长 5/2=2
        step = 2;
        for (int i = 2; i < arr.length; i++) {
            //定义一个中间变靓  用于数据交换
            int temp = 0;
            //每组要比较几次？  如果该索引前面也有元素，也需要进行比较
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] > arr[j + 2]) {
                    //交换
                    temp = arr[j];
                    arr[j] = arr[j  + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第二次排序结果：");
        System.out.println(Arrays.toString(arr));


        //第三次排序
        //步长 2/2=1
        step = 1;
        for (int i = 1; i < arr.length; i++) {
            //定义一个中间变靓  用于数据交换
            int temp = 0;
            //每组要比较几次？  如果该索引前面也有元素，也需要进行比较
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j] > arr[j + 1]) {
                    //交换
                    temp = arr[j];
                    arr[j] = arr[j  + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第三次排序结果：");
        System.out.println(Arrays.toString(arr));
        **/
    }

    /**
     * 希尔排序（交换法）
     * @param arr
     */
    private static void sort(int[] arr) {
        int step = arr.length / 2;

        while (step != 0) {
            //有几组就比较几次
            for (int i = step; i < arr.length; i++) {
                int temp = 0;
                for (int j = i - step; j >= 0 ; j -= step) {
                    if (arr[j] > arr[j + step]) {
                        temp = arr[j];
                        arr[j] = arr[j + step];
                        arr[j + step] = temp;
                    }
                }
            }

            step /= 2;
        }
    }

    /**
     * 希尔排序（移位法）
     * @param arr
     */
    private static void shellSort(int[] arr) {
        for (int step = arr.length / 2; step > 0 ; step /= 2) {

            //有几组就比较几次
            for (int i = step; i < arr.length; i++) {
                //要插入的值
                int insertValue = arr[i];
                //要比较的第一个元素索引
                int index = i - step;
                //要插入的元素比有序列表中的小  则将有序列表中的元素后移
                while (index >= 0 && insertValue < arr[index]) {
                    arr[index + step] = arr[index];
                    index -= step;
                }

                 arr[index + step] = insertValue;
            }
        }

    }


}
