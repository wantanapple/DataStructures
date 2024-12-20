package com.atguigu.sort;

import java.time.Instant;
import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BobbleSort {
    public static void main(String[] args) {

//        int[] arr = {6,3,5,2,4,3};

        //测试8万条数据耗时多久
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        long s1 = Instant.now().toEpochMilli();
        sort(arr);
        long s2 = Instant.now().toEpochMilli();
        System.out.println("冒泡排序耗时：" + (s2 - s1) + "ms");


        /**
        //第一躺排序
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i+ 1] = temp;
            }

        }

        //第二躺排序
        for (int i = 0; i < arr.length - 1 - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i+ 1] = temp;
            }

        }

        //第三躺排序
        for (int i = 0; i < arr.length - 1 - 2; i++) {
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i+ 1] = temp;
            }

        }

        //第四躺排序
        for (int i = 0; i < arr.length - 1 - 3; i++) {
            if (arr[i] > arr[i + 1]) {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i+ 1] = temp;
            }

        } **/

//        System.out.println("排序后的结果：");
//        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {

        int temp = 0;
        //用于标识某一躺排序是否发生数据交换
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            //flag为false表示没有发生了数据交换 直接结束排序
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
