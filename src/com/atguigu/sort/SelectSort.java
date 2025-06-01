package com.atguigu.sort;

import java.time.Instant;
import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {4,1,7,2};
//        sort(arr);
//        System.out.println(Arrays.toString(arr));

        //测试8万条数据耗时多久
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        long s1 = Instant.now().toEpochMilli();
        sort(arr);
        long s2 = Instant.now().toEpochMilli();
        System.out.println("选择排序耗时：" + (s2 - s1) + "ms");

        /**
        //选择排序过程--遍历n-1次，每次找到数组中最值依次放在数组前面

        //从小到大进行排序
        //第一躺
        //先认为第一个数是最小值
        int minIndex = 0;
        int min = arr[0];
        //从索引为1的开始进行比较  比较三次
        for (int i = 0 + 1; i < arr.length; i++) {
            if (min > arr[i]) {
                //更新最小值
                min = arr[i];
                minIndex = i;
            }
        }
        if (minIndex != 0){
            arr[minIndex] = arr[0];
            arr[0] = min;
        }

        System.out.println("第一轮结果：");
        System.out.println(Arrays.toString(arr));

        //第二轮比较
        min = arr[1];
        minIndex = 1;
        for (int i = 0 + 2; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }

        }
        if (minIndex != 1){
            arr[minIndex] = arr[1];
            arr[1] = min;
        }

        System.out.println("第二轮结果：");
        System.out.println(Arrays.toString(arr));

        //第三轮比较
        min = arr[2];
        minIndex = 2;
        for (int i = 0 + 3; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }

        }
        if (minIndex != 2){
            arr[minIndex] = arr[2];
            arr[2] = min;
        }
        System.out.println("第二轮结果：");
        System.out.println(Arrays.toString(arr));
        **/

//        System.out.println("排序后的结果：");
//        System.out.println(Arrays.toString(arr));




    }

    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }

            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }

    }
}
