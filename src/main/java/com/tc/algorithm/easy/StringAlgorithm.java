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

    public static void main(String[] args) {
        String [] strs = {"flower", "flow", "flight"};

        String str = StringAlgorithm.longestCommonPrefix(strs);

        System.out.println(str);

        String str1 = "a good   example";
        System.out.println(StringAlgorithm.reverseWords(str1));

        String haystack = "abc";
        String needle = "c";

        System.out.println(StringAlgorithm.strStr(haystack, needle));
    }
}
