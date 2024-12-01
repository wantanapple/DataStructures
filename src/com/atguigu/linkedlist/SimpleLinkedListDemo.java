package com.atguigu.linkedlist;

import jdk.nashorn.internal.objects.annotations.Where;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import java.util.Scanner;
import java.util.Stack;

public class SimpleLinkedListDemo {

    public static void main(String[] args) {
        //进行测试
        //先创建节点
//        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
//        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
//        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
//        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SimpleLinkedList simpleLinkedList = new SimpleLinkedList();
//        simpleLinkedList.add(hero1);
//        simpleLinkedList.add(hero2);
//        simpleLinkedList.add(hero3);
//        simpleLinkedList.add(hero4);

        HeroNode hero1 = new HeroNode(3, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(1, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(2, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
//        HeroNode hero5 = new HeroNode(4, "林冲", "豹子头");

        simpleLinkedList.addByOrder(hero1);
        simpleLinkedList.addByOrder(hero2);
        simpleLinkedList.addByOrder(hero3);
        simpleLinkedList.addByOrder(hero4);
//        simpleLinkedList.addByOrder(hero5);

        simpleLinkedList.list();


//        HeroNode hero5 = new HeroNode(5, "林冲!", "豹子头!");
//        simpleLinkedList.update(hero5);
//        System.out.println("修改后的链表为");
//        simpleLinkedList.list();

        simpleLinkedList.del(4);
        System.out.println("删除后的链表为");
        simpleLinkedList.list();

        System.out.println("链表的有效节点个数为：" + getLength(simpleLinkedList.getHeadNode()));

        int k = 1;
        System.out.println("查询倒数第" + k  + "个节点");
        System.out.println(findLastIndexNode(k, simpleLinkedList.getHeadNode()));

        System.out.println("反转后的链表为：");
        reverseList(simpleLinkedList.getHeadNode());
        simpleLinkedList.list();

        System.out.println("从尾到头打印单链表");
        reverseListPrint(simpleLinkedList.getHeadNode());

    }

    //获取链表的节点个数（不包含头节点）
    public static int getLength(HeroNode headNode) {
        if (headNode.next == null) {
            return 0;
        }

        HeroNode temp = headNode.next;
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        return length;

    }

    //查找链表中倒数第k个节点(头节点不计入)
    public static HeroNode findLastIndexNode(int index, HeroNode headNode) {
        if (headNode.next == null) {
            return null;
        }

        //获取节点的个数
        int length = getLength(headNode);
        //对index进行校验
        if (index <= 0 || index > length) {
            return null;
        }

        //遍历查找
        HeroNode next = headNode.next;
        for (int i = 0; i < (length - index); i++) {
            next = next.next;
        }

        return next;

    }

    //单链表反转  使用头插法
    public static void reverseList(HeroNode headNode) {
        //将原链表的节点一个个拆开，定义一个新链表，改变其指针
        if (headNode == null || headNode.next == null) {
            return;
        }

        HeroNode cur = headNode.next;
        HeroNode reverseNode = new HeroNode(0, "", "");
        while (cur != null) {
            HeroNode next = cur.next;
            //当前节点指向反转链表的最前端
            cur.next = reverseNode.next;
            //头节点指向当前节点
            reverseNode.next = cur;
            //当前节点后移
            cur = next;
        }

        headNode.next = reverseNode.next;

    }

    //从尾到头打印单链表（使用栈stack进行实现）
    public static void reverseListPrint(HeroNode headNode) {
        if (headNode.next == null) {
            return;
        }

        Stack<HeroNode> heroNodeStack = new Stack<>();
        HeroNode cur = headNode.next;
        while (cur != null) {
            //入栈
            heroNodeStack.push(cur);
            cur = cur.next;
        }

        while (heroNodeStack.size() > 0) {
            //出栈
            System.out.println(heroNodeStack.pop());
        }
    }

}


/**
 * 链表的实现
 */
class SimpleLinkedList {
    //链表的头节点
    private HeroNode headNode = new HeroNode(0, "", "");

    public HeroNode getHeadNode() {
        return headNode;
    }

    //添加节点到链表
    public void add(HeroNode heroNode) {
        //定义中间变靓，用于从头节点往后找出next为null的节点
        HeroNode temp = headNode;
        //需要在链尾部添加 找出next为null的节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            //next不为null  继续往下找
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //顺序添加元素  1、找到要添加的前一个位置  2、要添加元素指针指向后一个节点；要添加元素前一个节点指针指向改元色
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = headNode;
        //表示节点是否存在
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                //说明已经遍历到最后一个节点了
                break;
            }
            if (temp.next.no == heroNode.no) {
                //说明存在相同节点
                flag = true;
                break;
            } else if (temp.next.no > heroNode.no) {
                //找到添加节点的位置
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("存在相同节点%d~~~", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //根据节点的no修改节点的属性值
    public void update(HeroNode heroNode) {
        if (headNode.next == null) {
            System.out.println("链表为空～～～");
            return;
        }
        //头节点跳过
        HeroNode temp = headNode.next;
        //标识是否找到要修改的节点
        boolean flag = false;
        while (true) {
            if (temp == null) {
                //遍历到最后一个节点了
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.no = heroNode.no;
        } else {
            System.out.printf("不存在编号为 %d 的节点～～～", heroNode.no);
        }
    }

    //根据no删除节点
    public void del(int no) {
        if (headNode.next == null) {
            System.out.println("链表为空～～～");
            return;
        }
        HeroNode temp = headNode;
        //标识是否找到该节点
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的 %d 节点不存在~~~", no);
        }

    }


    //遍历所有节点
    public void list() {
        if (headNode.next == null) {
            System.out.println("链表中暂无数据");
            return;
        }
        //定义中间变靓，用于从头节点往后找出next为null的节点
        HeroNode temp = headNode;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            //指针后移
            temp = temp.next;
        }

    }

}


/**
 * 链表节点，存放数据和下一个数据的指针
 */
class HeroNode {

    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    //构造方法  初始化头节点
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;

    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                "}";
    }
}
