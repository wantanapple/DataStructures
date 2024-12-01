package com.atguigu.linkedlist;

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
//        circleSingleLinkedList.addBoy(5);
//        circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}


/**
 * 构建单向环形链表解决小孩出圈的约瑟夫问题
 */
class CircleSingleLinkedList {

    private Boy first = null;

    //添加count个小孩到环形单向链表
    public void addBoy(int count) {
        if (count < 1) {
            System.out.println("添加个数不能小于1");
        }
        //创建辅助指针，帮助构建环形链表
        Boy curBoy = null;
        for (int i = 1; i <= count; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                //第一个节点的下一个节点指向自己  构建环形
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                //后移
                curBoy = boy;
            }
        }

    }

    //遍历当前环形链表
    public void showBoy() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }

        //定义辅助指针
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }

    }

    /**
     * nums个小孩围成一个圈，从statNo开始数1，数到第countNum的小孩出圈，打印出圈顺序
     * @param statNo 第几个小孩开始数
     * @param countNum 数几下
     * @param nums 初始圈中有多少个小孩
     */
    public void countBoy(int statNo, int countNum, int nums) {
        if (statNo < 1 || statNo > nums || countNum < 1) {
            System.out.println("参数不正确");
            return;
        }

        addBoy(nums);
        showBoy();

        Boy helpBoy = first;
        //定义一个辅助指针，辅助指针下一个节点就是要出圈的小孩
        while (true) {
            if (helpBoy.getNext() == first) {
                break;
            }
            helpBoy = helpBoy.getNext();
        }

        //计算开始报数小孩的位置
        for (int i = 0; i < statNo - 1; i++) {
            first = first.getNext();
            helpBoy = helpBoy.getNext();

        }

        //计算小孩出圈的顺序
        while (true) {
            if (first == first.getNext()) {
                break;
            }
            //寻找出圈的小孩的位置
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helpBoy = helpBoy.getNext();
            }

            System.out.printf("出圈小孩的编号为 %d \n", first.getNo());
            //此时first指向的节点就是要出圈的小孩
            helpBoy.setNext(first.getNext());
            first = first.getNext();

        }

        System.out.printf("最后一个出圈的小孩的编号为: %d \n", first.getNo());


    }
}

class Boy {

    //编号
    private int no;
    //指向下一个节点  默认为null
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
