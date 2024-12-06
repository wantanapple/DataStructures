package com.atguigu.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

public class ReversePolishMultiCalc {

    /**
     * 鍖归厤 + - * / ( ) 杩愮畻绗�
     */
    static final String SYMBOL = "\\+|-|\\*|/|\\(|\\)";

    static final String LEFT = "(";
    static final String RIGHT = ")";
    static final String ADD = "+";
    static final String MINUS= "-";
    static final String TIMES = "*";
    static final String DIVISION = "/";

    /**
     * 鍔犳笡 + -
     */
    static final int LEVEL_01 = 1;
    /**
     * 涔橀櫎 * /
     */
    static final int LEVEL_02 = 2;

    /**
     * 鎷�鍙�
     */
    static final int LEVEL_HIGH = Integer.MAX_VALUE;


    static Stack<String> stack = new Stack<>();
    static List<String> data = Collections.synchronizedList(new ArrayList<String>());

    /**
     * 鍘婚櫎鎵€鏈夌┖鐧界��
     * @param s
     * @return
     */
    public static String replaceAllBlank(String s ){
        // \\s+ 鍖归厤浠讳綍绌虹櫧瀛楃�︼紝鍖呮嫭绌烘牸銆佸埗琛ㄧ�︺€佹崲椤电�︾瓑绛�, 绛変环浜嶽 \f\n\r\t\v]
        return s.replaceAll("\\s+","");
    }

    /**
     * 鍒ゆ柇鏄�涓嶆槸鏁板瓧 int double long float
     * @param s
     * @return
     */
    public static boolean isNumber(String s){
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(s).matches();
    }

    /**
     * 鍒ゆ柇鏄�涓嶆槸杩愮畻绗�
     * @param s
     * @return
     */
    public static boolean isSymbol(String s){
        return s.matches(SYMBOL);
    }

    /**
     * 鍖归厤杩愮畻绛夌骇
     * @param s
     * @return
     */
    public static int calcLevel(String s){
        if("+".equals(s) || "-".equals(s)){
            return LEVEL_01;
        } else if("*".equals(s) || "/".equals(s)){
            return LEVEL_02;
        }
        return LEVEL_HIGH;
    }

    /**
     * 鍖归厤
     * @param s
     * @throws Exception
     */
    public static List<String> doMatch (String s) throws Exception{
        if(s == null || "".equals(s.trim())) throw new RuntimeException("data is empty");
        if(!isNumber(s.charAt(0)+"")) throw new RuntimeException("data illeagle,start not with a number");

        s = replaceAllBlank(s);

        String each;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            if(isSymbol(s.charAt(i)+"")){
                each = s.charAt(i)+"";
                //鏍堜负绌猴紝(鎿嶄綔绗︼紝鎴栬€� 鎿嶄綔绗︿紭鍏堢骇澶т簬鏍堥《浼樺厛绾� && 鎿嶄綔绗︿紭鍏堢骇涓嶆槸( )鐨勪紭鍏堢骇 鍙婃槸 ) 涓嶈兘鐩存帴鍏ユ爤
                if(stack.isEmpty() || LEFT.equals(each)
                        || ((calcLevel(each) > calcLevel(stack.peek())) && calcLevel(each) < LEVEL_HIGH)){
                    stack.push(each);
                }else if( !stack.isEmpty() && calcLevel(each) <= calcLevel(stack.peek())){
                    //鏍堥潪绌猴紝鎿嶄綔绗︿紭鍏堢骇灏忎簬绛変簬鏍堥《浼樺厛绾ф椂鍑烘爤鍏ュ垪锛岀洿鍒版爤涓虹┖锛屾垨鑰呴亣鍒颁簡(锛屾渶鍚庢搷浣滅�﹀叆鏍�
                    while (!stack.isEmpty() && calcLevel(each) <= calcLevel(stack.peek()) ){
                        if(calcLevel(stack.peek()) == LEVEL_HIGH){
                            break;
                        }
                        data.add(stack.pop());
                    }
                    stack.push(each);
                }else if(RIGHT.equals(each)){
                    // ) 鎿嶄綔绗︼紝渚濇�″嚭鏍堝叆鍒楃洿鍒扮┖鏍堟垨鑰呴亣鍒颁簡绗�涓€涓�)鎿嶄綔绗︼紝姝ゆ椂)鍑烘爤
                    while (!stack.isEmpty() && LEVEL_HIGH >= calcLevel(stack.peek())){
                        if(LEVEL_HIGH == calcLevel(stack.peek())){
                            stack.pop();
                            break;
                        }
                        data.add(stack.pop());
                    }
                }
                start = i ;    //鍓嶄竴涓�杩愮畻绗︾殑浣嶇疆
            }else if( i == s.length()-1 || isSymbol(s.charAt(i+1)+"") ){
                each = start == 0 ? s.substring(start,i+1) : s.substring(start+1,i+1);
                if(isNumber(each)) {
                    data.add(each);
                    continue;
                }
                throw new RuntimeException("data not match number");
            }
        }
        //濡傛灉鏍堥噷杩樻湁鍏冪礌锛屾�ゆ椂鍏冪礌闇€瑕佷緷娆″嚭鏍堝叆鍒楋紝鍙�浠ユ兂璞℃爤閲屽墿涓嬫爤椤朵负/锛屾爤搴曚负+锛屽簲璇ヤ緷娆″嚭鏍堝叆鍒楋紝鍙�浠ョ洿鎺ョ炕杞�鏁翠釜stack 娣诲姞鍒伴槦鍒�
        Collections.reverse(stack);
        data.addAll(new ArrayList<>(stack));

        System.out.println(data);
        return data;
    }

    /**
     * 绠楀嚭缁撴灉
     * @param list
     * @return
     */
    public static Double doCalc(List<String> list){
        Double d = 0d;
        if(list == null || list.isEmpty()){
            return null;
        }
        if (list.size() == 1){
            System.out.println(list);
            d = Double.valueOf(list.get(0));
            return d;
        }
        ArrayList<String> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list1.add(list.get(i));
            if(isSymbol(list.get(i))){
                Double d1 = doTheMath(list.get(i - 2), list.get(i - 1), list.get(i));
                list1.remove(i);
                list1.remove(i-1);
                list1.set(i-2,d1+"");
                list1.addAll(list.subList(i+1,list.size()));
                break;
            }
        }
        doCalc(list1);
        return d;
    }

    /**
     * 杩愮畻
     * @param s1
     * @param s2
     * @param symbol
     * @return
     */
    public static Double doTheMath(String s1,String s2,String symbol){
        Double result ;
        switch (symbol){
            case ADD : result = Double.valueOf(s1) + Double.valueOf(s2); break;
            case MINUS : result = Double.valueOf(s1) - Double.valueOf(s2); break;
            case TIMES : result = Double.valueOf(s1) * Double.valueOf(s2); break;
            case DIVISION : result = Double.valueOf(s1) / Double.valueOf(s2); break;
            default : result = null;
        }
        return result;

    }

    public static void main(String[] args) {
        //String math = "9+(3-1)*3+10/2";
        String math = "12.8 + (2 - 3.55)*4+10/5.0";
        try {
            doCalc(doMatch(math));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
