package com.tc.algorithm.easy;

/**
 * desc 字符串相关算法
 *
 * @author lvzf
 * @date 2023/12/8
 */
public class StringAlgorithm {

    public static String longestCommonPrefix(String[] strs) {

        String result = "";

        for (int i = 0; i < strs[0].length(); i++) {
            if (i < strs[0].length() - 1) {
                result = strs[0].substring(0, i + 1);
            } else {
                result = strs[0];
            }

            for (int n = 1; n < strs.length; n++) {
                String temp = strs[n];
                if (temp.length() < result.length()) {
                    result = result.substring(0, result.length() - 1);
                    return result;
                } else {
                    String prefix = temp.substring(0, result.length());
                    if (!result.equals(prefix)) {
                        result = result.substring(0, result.length() - 1);
                        return result;
                    }
                }
            }
        }
        return result;
    }

    public static String reverseWords(String s) {
        String [] array = s.split(" ");

        StringBuilder sb = new StringBuilder();
        for (int i = array.length - 1; i >= 0; i--) {
            if (!array[i].trim().equals("")) {
                sb.append(array[i].trim());
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static int strStr(String haystack, String needle) {
        if ("".equals(haystack) && "".equals(needle)) {
            return -1;
        }

        if (haystack.length() < needle.length()) {
            return -1;
        }

        if (haystack.length() == needle.length()) {
            if (haystack.equals(needle)) {
                return 0;
            } else {
                return -1;
            }
        }

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            String temp = haystack.substring(i, i + needle.length());
            if (temp.equals(needle)) {
                return i;
            }
        }

        return -1;
    }

    public static String longestPalindrome (String s) {
        //用数组分别记录长度，起点，终点
        int [] res = new int [3];

        for (int i = 0; i < s.length(); i++) {
            int begin = i;
            int end = i;
            //奇数回文串，直接开始中心回文。
            while (begin - 1 >= 0 && end + 1 < s.length() && s.charAt(begin - 1) == s.charAt(end + 1)) {
                end++;
                begin--;
                if (end - begin > res[0]) {
                    res[0] = end - begin;
                    res[1] = begin;
                    res[2] = end;
                }
            }
            //偶数回文串，第一次先判断和下一个是否相等，然后中心回文
            begin = i;
            end = i + 1;
            while (begin >= 0 && end < s.length() && s.charAt(begin) == s.charAt(end)) {
                if (end - begin > res[0]) {
                    res[0] = end - begin;
                    res[1] = begin;
                    res[2] = end;
                }
                end++;
                begin--;
            }
        }
        return s.substring(res[1], res[2] + 1);
    }

    public static String reverseString (String s) {
        int right = s.length() - 1;
        int left = 0;
        char [] chars = s.toCharArray();
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        String [] strs = {"flower", "flow", "flight"};

        String str = StringAlgorithm.longestCommonPrefix(strs);

        System.out.println(str);

        String str1 = "a good   example";
        System.out.println(StringAlgorithm.reverseWords(str1));

        String haystack = "abc";
        String needle = "c";

        System.out.println(StringAlgorithm.strStr(haystack, needle));

        System.out.println("longest palindrome is " + StringAlgorithm.longestPalindrome("babadc"));

        String s = "string";
        System.out.println("string is '" + s + "' after reverse is '" + StringAlgorithm.reverseString(s) + "'");
    }
}
