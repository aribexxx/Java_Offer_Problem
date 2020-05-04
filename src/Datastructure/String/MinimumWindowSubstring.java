package Datastructure.String;

import java.util.*;

public class MinimumWindowSubstring {


    //Solution 1 :
//Sliding window + HashMap(needs,windows)用来储存字母出现的次数

    public static String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap();
        Map<Character, Integer> window = new HashMap();//record existing matching chars inside window.
        //put all chars into "need" by their appear frequency.
        for (int i = 0; i < t.length(); i++) {
            int count = need.getOrDefault(t.charAt(i), 0);
            count = count + 1;
            System.out.println("ccccount" + count);
            need.put(t.charAt(i), count);
        }

        int left = 0, right = 0; //sliding index
        // int start=0;//keep track of the indexs of result
        int min_len = s.length() + 1;//initialize len of smallest result.
        int valid = 0;// the amount satisifed chars in src String
        String result = "";//store result string

        //extending window by increase right index
        while (right < s.length()) {
            char enterSWin = s.charAt(right);

            int count_R = window.getOrDefault(enterSWin, 0);
            window.put(enterSWin, count_R + 1);
            //if find one char that is matched,put in window/
            if (need.containsKey(enterSWin) && window.get(enterSWin) == need.get(enterSWin)) {

                //新加入滑窗的字符如果正好是target str里的一个字符，valid ++
                valid++;

            }
            //determin if left index should increase,and srhink window .
            //Now s[left:right] include all chars in t,need to find the shortest.
            while (valid == need.size()) {//once valid<need.size, exit loop
                char toremove = s.charAt(left);
                if (need.containsKey(toremove) && window.get(toremove) <= need.get(toremove)) {

                    valid--;
                }


                if (right - left + 1 < min_len) {
                    // start=left;
                    min_len = right - left + 1;
                    result = s.substring(left, right + 1);
                }
                int count_l = window.getOrDefault(toremove, 0);
                window.put(toremove, count_l - 1);
                left++;

            }

            right++;

        }
        return result;
    }


    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        HashMap<Character, Integer> need = new HashMap();
        HashMap<Character, Integer> window = new HashMap();

        //put all s1 chars in need record frequencies
        for (int i = 0; i < s1.length(); i++) {
            int count = need.getOrDefault(s1.charAt(i), 0);
            need.put(s1.charAt(i), count + 1);
        }

        int left = 0, right = 0;
        int validChar = 0;

        while (right < s2.length()) {
            char enterw = s2.charAt(right);
            // add entry into window record
            if (need.containsKey(enterw)) {
                int count = window.getOrDefault(enterw, 0);
                window.put(enterw, count + 1);
                System.out.println(window.get(enterw) + " " + need.get(enterw));
                if (window.get(enterw) == need.get(enterw)) {
                    validChar++;
                }
            }
            right++;

            while (validChar == need.size()) {//if window contains all the chars that valid
                //if valid char in window is the same size as "need"
                if (right - left == s1.length()) {
                    return true;
                }

                char removeleft = s2.charAt(left);

                if (need.containsKey(removeleft)) {

                    int countl = window.getOrDefault(removeleft, 0);
                    countl = countl - 1;
                    window.put(removeleft, countl);

                    if (window.get(removeleft) < need.get(removeleft)) {
                        validChar--;
                    }
                }

                left++;//keep removing left elements

            }

        }
        return false;

    }


    public static String multiply(String num1, String num2) {

        int l1 = num1.length();
        int l2 = num2.length();
        int outlen = l1 + l2;
        int[] out = new int[outlen];
        for (int i = num2.length() - 1; i >= 0; i--) {//timed

            for (int j = num1.length() - 1; j >= 0; j--) { //times
                int i_int = num2.charAt(i) - '0';
                int j_int = num1.charAt(j) - '0';
                int mul = i_int * j_int;
                //
                int sum = mul + out[i + j + 1];
                //int onetime=(i_int*j_int)%10;
                // int index_actual=l1+l2-i-j-2;
                out[i + j + 1] = sum % 10;
                out[i + j] = out[i + j] + sum / 10;

            }

        }


        String pre_zero = intArrayToString(out);
        System.out.println(pre_zero);
        //remove extra 0
        int z = 0;
        while (z < pre_zero.length() && pre_zero.charAt(z) == '0') {
            z++;
        }
        pre_zero = pre_zero.substring(z);

        return pre_zero.length() == 0 ? "0" : pre_zero;

    }

    public static String intArrayToString(int[] arr) {
        String out = "";
        for (int i = 0; i < arr.length; i++) {
            out += Integer.toString(arr[i]);

        }
        return out;
    }

    public static String reverseWords(String s) {
        String out = "";

        if (s.length() == 0) {
            return out;
        }
        //反转整个字符串
        String formated = "";
        String[] f = s.trim().split("\\s+");// 这里有个坑，一定要把首尾的空符都给trim了
        for (int i = 0; i < f.length; i++) {
            System.out.println(f[i]);
            formated += f[i];
            if (i == f.length - 1) {
                break;
            }
            formated += ' ';
        }
        System.out.println(formated);
        char[] arr = formated.toCharArray();
        char[] reversed = reverseString(arr, 0, arr.length - 1);
        //start from beginning to end revers words back
        int k = 0;
        int start = 0, end = 0;
        //find first space
        while (k < reversed.length) {
            if (reversed[k] == ' ') {//start swapping the word
                end = k - 1;
                reverseString(reversed, start, end);
                start = k + 1;
            }
            if (k == reversed.length - 1) {
                end = k;
                reverseString(reversed, start, end);
            }
            k++;
        }

        String unformated = String.valueOf(reversed);

        return unformated;
    }

    public static char[] reverseString(char[] arr, int start, int end) {

        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        return arr;
    }

    public static String simplifyPath(String path) {
        String out = "";
        if (path.isEmpty()) {
            return out;
        }
        // path: /home/.././//./c/
        Deque dq = new ArrayDeque();//LIFO

        String[] path_split = path.split("/");

        for (int i = 0; i < path_split.length; i++) {
            if (path_split[i].equals("..")) {
                if (!dq.isEmpty()) {
                    dq.removeLast();
                }
                continue;
            }
            if (path_split[i].equals(".") || path_split[i].equals("")) {
                continue;
            } else {
                dq.addLast(path_split[i]);
            }

        }
        System.out.println(dq);

        out += "/";
        while (!dq.isEmpty()) {
            out += dq.removeFirst() + "/";
        }


        return out.length() == 1 ? out : out.substring(0, out.length() - 1);
    }


        /*Use recursion (pos+i to keep track of string current position)
      inside a loop(to backtracking 3 nums combination in segment)

      record variables: 1)one array to keep track of all possible solutions.
                        2) each solution has four strings,so need another list to store this 4 segments.
    */


    public static List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();

        //Step1: check s is null or not
        if (s.isEmpty()) {
            return ans;
        }
        backtracking(s, ans, new ArrayList<>(), 0);
        // Every time need to new a cur_sol to store different solutions.
        return ans;
    }

    public static void backtracking(String s, List<String> ans, List<String> cur_sol, int pos) {


        //Step2: recursion base case:when there are 4 segments filled in the current solution,check if it is valid.
        //if it is then put it in the final solutions set.
        if (cur_sol.size() == 4) {
            if (pos == s.length()) {
                ans.add(String.join(".", cur_sol));
            }
            return;
        }

        //Step3:recursion body
        //Use one for loop to try all 3 nums combinations.everytime one combination added,remove the last element and recalculate the second combination. Use i<=3,to keep track of each combination.


        for (int i = 1; i <= 3; i++) {
            //if current pos +i >s.length(), no need to keep spliting
            if (pos + i > s.length()) {
                break;
            }

            String segment = s.substring(pos, pos + i);


            //if the current combination (3 combinations for each segment) is not valid (eg.>255 or is like "02"/ "002"),then immediately try next combination.

            if ((segment.startsWith("0") && segment.length() > 1) || (segment.length() == 3) && Integer.parseInt(segment) > 255) {
                continue;
            }

            cur_sol.add(segment);

            backtracking(s, ans, cur_sol, pos + i);

            cur_sol.remove(cur_sol.size() - 1); //everytime the current combination of segment fails, remove the last segment and try a new combination of this segment.


            //summary: 1)recursion keep track of each segments
            //         2)loop keep track of 3 combinations of segments.

        }


    }

    ;


    public static void main(String[] args) {
        //IP-----------------
        String IP = "2552551012";//
        List<String> IP_OUT = restoreIpAddresses(IP);

        System.out.println("IP :" + String.valueOf(IP_OUT) + "end");
        //----------------
        String path = "/../";//
        String path_out = simplifyPath(path);

        System.out.println("simplified path :" + path_out + "end");

        //reverse word
        String rev = "   hello  world!   ";//
        String rev_out = reverseWords(rev);

        System.out.println("reversed by word :" + rev_out + "end");

        //mutiply
        String num1 = "2";//
        String num2 = "0";
        String res = multiply(num1, num2);
        System.out.println("multiply : " + res);//56088
        //---------------------------------------
        String s = "ADDBECODEBANC";//ADDBECODEBANC
        String t = "ABC";
        String out = minWindow(s, t);
        System.out.println("out : " + out);

        //check inclusion  of permutation
        String s1 = "abc";
        String s2 = "cccccccccccccbcabbaaa";
        boolean include = checkInclusion(s1, s2);
        System.out.println("includ? : " + include);

    }

}
