package com.atguigu.sparsearray;

import java.io.*;

/**
 * 目的：
 * 1、将二维数组转换为稀疏数组存储到磁盘上
 * 2、从磁盘上读取稀疏数组并转换为二维数组
 */
public class SparseArray {

    public static void main(String[] args) {

        // 1、定义一个11*11的二维数组
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        // 输出二维数组
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 稀疏数组，每行三个值，第一行分别代表行、列、有效个数；剩下行分别代表行、列、值
        // 2、求出稀疏数组有效值的个数
        int sum = 0;
        for (int[] row : chessArr1) {
            for (int data : row) {
                // 找出非0的有效值
                if (data != 0) {
                    sum++;
                }
            }
        }
        System.out.println("二维数组中有效值的个数为：" + sum);
        // 3、初始化稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1.length;
        sparseArr[0][2] = sum;

        // 4、找出有效值的位置,给稀疏数组赋值
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        System.out.println("二维数组转换为的稀疏数组为");
        for (int[] ints : sparseArr) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }

        // 稀疏数组写入磁盘
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("/Users/tomcat/Documents/arr.txt"));
            for (int[] ints : sparseArr) {
                for (int anInt : ints) {
                    bufferedWriter.write(anInt + " ");
                }
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        BufferedReader bufferedReader = null;
        int[][] chessArr3 = null;
        //从文件中读出稀疏数组
        try {
            bufferedReader = new BufferedReader(new FileReader("/Users/tomcat/Documents/arr.txt"));
            String line = null;
            // 控制是否实例化一个数组对象
            int num = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s1 = line.split(" ");
                num++;
                if (num == 1) {
                    //第一次读取 读到的是第一行
                    chessArr3 = new int[Integer.parseInt(s1[0])][Integer.parseInt(s1[1])];
                } else {
                    //读取到的全部为有效值
                    chessArr3[Integer.parseInt(s1[0])][Integer.parseInt(s1[1])] = Integer.parseInt(s1[2]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        System.out.println("从文件中还原的稀疏数组:");
        for (int[] ints : chessArr3) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }


        // 5、将稀疏数组还原二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("还原后的二维数组：");
        for (int[] ints : chessArr2) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }


    }
}
