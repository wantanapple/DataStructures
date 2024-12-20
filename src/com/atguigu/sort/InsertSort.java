package com.atguigu.sort;

import java.time.Instant;
import java.util.Arrays;

/**
 * 简单插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {5,3,6,1,20,20,4,2,52,1,5};

//        sort(arr);
//
//        System.out.println("排序结果：");
//        System.out.println(Arrays.toString(arr));

        //测试8万条数据耗时多久
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        long s1 = Instant.now().toEpochMilli();
        sort(arr);
        long s2 = Instant.now().toEpochMilli();
        System.out.println("简单插入排序耗时：" + (s2 - s1) + "ms");

        /**
        //第一次排序
        //拿到要插入的数据
        int insert = arr[1];
        //拿到要比较的第一个数据的索引
        int index = 0;
        //跟有序列表里面的元素进行比较 从小到大进行排序
        while (index >= 0 && insert < arr[index]) {
            //大的数后移一位
            arr[index + 1] = arr[index];
            index --;
        }
        //循环结束找到要插入的位置了
        arr[index + 1] = insert;
        System.out.println("第一次排序结果为：");
        System.out.println(Arrays.toString(arr));

        //第二次排序
        insert = arr[2];
        index = 1;
        while (index >= 0 && insert < arr[index]) {
            arr[index + 1] = arr[index];
            index --;
        }
        arr[index + 1] = insert;
        System.out.println("第二次排序结果：");
        System.out.println(Arrays.toString(arr));

        //第三次排序
        insert = arr[3];
        index = 2;
        while (index >= 0 && insert < arr[index]) {
            arr[index + 1] = arr[index];
            index --;
        }
        arr[index + 1] = insert;
        System.out.println("第二次排序结果：");
        System.out.println(Arrays.toString(arr));**/

    }


    public static void sort(int[] arr) {
        //从1开始  因为要插入的数据第一个索引为1
        for (int i = 1; i < arr.length; i++) {
            //获取要插入的数据
            int insertDate = arr[i];
            //要比较第一个数据的索引
            int index = i - 1;

            while (index >= 0 && insertDate < arr[index]) {
                arr[index + 1] = arr[index];
                index --;
            }
            arr[index + 1] = insertDate;

        }

    }

}
