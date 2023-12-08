package com.tc.algorithm;

import com.tc.algorithm.utils.BitUtils;

import java.nio.charset.StandardCharsets;

/**
 * desc TODO
 *
 * @author lvzf 2023年11月27日
 */
public class Demo {
    public static void main(String[] args) {

        Demo demo = new Demo();

        String catalog = "db_mask";
        String tableName = "t_table";
        String columnName = "column_001";
        Integer lengthMoreThan = 4;

        String sql =
                String.format("SELECT count(*)"

                        + " FROM [%s].[%s] WHERE length(%s) =%s", catalog, tableName, columnName, lengthMoreThan);
        System.out.println(sql);

        String s = BitUtils.stringToBinary("水印测试", StandardCharsets.UTF_8);
        long currentTime = System.currentTimeMillis() / 1000;
        String base = BitUtils.decimalToBinary(Integer.parseInt(String.valueOf(currentTime)));
        System.out.println("base is " + base);
        char [] chars = base.toCharArray();

        if (s.length() < 32) {
            s = demo.leftPad(s, 32, "0");
            demo.or(chars, s.toCharArray());
        } else {
            int length = s.length();
            int group = length / 32;
            if (length % 32 != 0) {
                group += 1;
            }
            for (int i = 0; i < group; i++) {
                String sub = "";
                if (i < group - 1) {
                    sub = s.substring(32 * i, 32 * (i + 1));
                } else {
                    sub = s.substring(32 * i);
                }

                sub = demo.leftPad(sub, 32, "0");

                demo.or(chars, sub.toCharArray());
            }

            System.out.println("watermark is " + new String(chars));
        }
    }

    public void or (char [] base, char [] salt) {
        for (int m = 0; m < base.length; m++) {
            int val1 = Integer.parseInt(String.valueOf(base[m]));
            int val2 = Integer.parseInt(String.valueOf(salt[m]));
            base[m] = String.valueOf(val1 | val2).charAt(0);
        }
    }

    public String leftPad (String source, int targetLength, String padChar) {
        if (source.length() < targetLength) {
            int l = source.length();
            StringBuilder sourceBuilder = new StringBuilder(source);
            for (int n = 0; n < 32 - l; n ++) {
                sourceBuilder.insert(0, padChar);
            }
            source = sourceBuilder.toString();
        }

        return source;
    }
}
