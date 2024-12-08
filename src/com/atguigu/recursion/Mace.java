package com.atguigu.recursion;

/**
 * 实现一个简单的迷宫
 */
public class Mace {
    public static void main(String[] args) {
        //先初始化一个二维数组模拟迷宫
        int[][] maze = new int[8][7];

        //设置障碍物
        for (int i = 0; i < 7; i++) {
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        for (int j = 0; j < 8; j++) {
            maze[j][0] = 1;
            maze[j][6] = 1;
        }
        maze[3][1] = 1;
        maze[3][2] = 1;

        print(maze);

        System.out.println("路线如下");
        satWay(maze, 1, 1);


    }

    /**
     * 利用递归的思想，对每一步进行判断是否可行
     *
     * @param maze 迷宫
     * @param row  行
     * @param col  列
     * @return
     */
    public static boolean satWay(int[][] maze, int row, int col) {
        //定义终点  表示游戏结束
        if (maze[6][5] == 2) {
            print(maze);
            return true;
        }

        //位置为0 认为该位置可以走  0:可走  1:障碍物  2:走过的位置  3:死路
        if (maze[row][col] == 0) {
            maze[row][col] = 2;

            //利用递归的思想  判断路线是否可行  路线 下  右  上  左
            if (satWay(maze, row + 1, col)) {
                return true;
            } else if (satWay(maze, row, col + 1)) {
                return true;
            }else if (satWay(maze, row - 1, col)) {
                return true;
            }else if (satWay(maze, row, col - 1)) {
                return true;
            }
        } else {
            return false;
        }

        //该步无路可走
        maze[row][col] = 3;
        return false;
    }

    public static void print(int[][] maze) {
        for (int[] ints : maze) {
            for (int anInt : ints) {
                System.out.printf(anInt + "\t");
            }
            System.out.println();
        }
    }
}
