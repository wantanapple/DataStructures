package com.atguigu.recursion;

import javax.xml.stream.XMLOutputFactory;

/**
 * 使用递归思想解决八皇后的问题
 */
public class EightQueue {
    //放八个皇后
    int max = 8;
    //初始化一个数组  索引表示行  值表示列
    int[] map = new int[8];
    static int planCount = 0;

    static String s;

    public static void main(String[] args) {
        EightQueue eightQueue = new EightQueue();
        eightQueue.putQueue(0);
        s = "1";

        System.out.println("所有方案的个数为：" + planCount);
    }

    public void putQueue(int n) {
        //n = 8时表示要放第九个皇后  说明八个皇后已经放完  游戏结束
        if (n == max) {
            print(map);
            return;
        }

        //判断每一行皇后位置的可能性  有八个位置可放
        for (int i = 0; i < 8; i++) {
            //将第n个皇后放到第n行的第i列
            map[n] = i;
            //检查该位置是否可放
            if (check(n)) {
                putQueue(n + 1);
            }
        }
    }

    public void print(int[] map) {
        planCount ++;
        for (int i : map) {
            System.out.printf(i + "\t");
        }
        System.out.println();
    }

    /**
     * 判断第n个皇后与其他皇后位置是否冲突
     * @param n
     * @return
     */
    public boolean check(int n) {
        //与每一行的皇后进行位置判断
        for (int i = 0; i < n; i++) {
            //同一列或者在同一对角线都不行  对角线可以使用计算斜率的公式进行计算
            if (map[i] == map[n] || Math.abs(map[n] - map[i]) == Math.abs(n - i)) {
                return false;
            }
        }
        return true;

    }
}
