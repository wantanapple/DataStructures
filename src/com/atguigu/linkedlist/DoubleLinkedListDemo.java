package com.atguigu.linkedlist;

import sun.net.idn.Punycode;

import java.nio.channels.Pipe;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        HeroNode2 hero5 = new HeroNode2(3, "吴用用", "智多星星");
        doubleLinkedList.update(3,hero5);
        System.out.println("修改后的链表为：");
        doubleLinkedList.list();

        doubleLinkedList.del(3);
        System.out.println("删除后的链表为：");
        doubleLinkedList.list();

    }


}

class DoubleLinkedList {
    //链表的头节点
    private HeroNode2 headNode = new HeroNode2(0, "", "");

    public HeroNode2 getHeadNode() {
        return headNode;
    }

    //添加一个节点到双向链表
    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = headNode;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改节点的属性
    public void update(int no, HeroNode2 heroNode) {
        if (headNode.next == null) {
            return;
        }

        HeroNode2 temp = headNode.next;
        boolean flag = false;
        while (temp != null) {
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        }else {
            System.out.println("未找到节点的编号：" + no);
        }

    }

    //删除某编号的节点
    public void del(int no) {
        if (headNode.next == null) {
            return;
        }

        HeroNode2 cur = headNode.next;
        boolean flag = false;
        while (cur != null) {
            if (cur.no == no) {
                flag = true;
                break;
            }
            cur = cur.next;
        }

        if (flag) {
            cur.pre.next = cur.next;
            if (cur.next != null) {
                cur.next.pre = cur.pre;
            }
        }else {
            System.out.println("未找到节点的编号：" + no);
        }

    }

    //遍历当前双向链表
    public void list() {
        if (headNode.next == null) {
            return;
        }

        HeroNode2 temp =  headNode.next;

        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public  HeroNode2 pre;

    //构造方法  初始化头节点
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;

    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
