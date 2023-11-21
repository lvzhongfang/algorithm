package com.tc.algorithm.utils;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * desc bit utils
 *
 * @author lvzf 2023年11月21日
 */
public class BitUtils {

    // for long, from -9223372036854775808 to 9223372036854775807, inclusive
    public static final int LONG_LENGTH = 64;

    // for int, from -2147483648 to 214748367, inclusive
    public static final int INT_LENGTH = 32;

    // for char from '\u0000' to '\uffff' inclusive, that is, from 0 to 65535
    public static final int CHAR_LENGTH = 16;

    // for short, from -32768 to 32767, inclusive
    public static final int SHORT_LENGTH = 16;

    // for byte, from -128 to 127, inclusive
    public static final int BYTE_LENGTH = 8;

    public static String longToBinary (long value) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BitUtils.LONG_LENGTH; i++) {
            boolean bit = ((value >> (BitUtils.LONG_LENGTH - 1 - i)) & 0x00000001) != 0;
            sb.append(bit ? 1 : 0);
        }
        return sb.toString();
    }
    public static String decimalToBinary (int value) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BitUtils.INT_LENGTH; i++) {
            boolean bit = ((value >> (BitUtils.INT_LENGTH - 1 - i)) & 0x0001) != 0;
            sb.append(bit ? 1 : 0);
        }
        return sb.toString();
    }

    public static String charToBinary (char value) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BitUtils.CHAR_LENGTH; i++) {
            boolean bit = ((value >> (BitUtils.CHAR_LENGTH - 1 - i)) & 0x01) != 0;
            sb.append(bit ? 1 : 0);
        }
        return sb.toString();
    }

    public static String shortToBinary (short value) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BitUtils.SHORT_LENGTH; i++) {
            boolean bit = ((value >> (BitUtils.SHORT_LENGTH - 1 - i)) & 0x01) != 0;
            sb.append(bit ? 1 : 0);
        }
        return sb.toString();
    }

    public static String byteToBinary (byte value) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BitUtils.BYTE_LENGTH; i++) {
            boolean bit = ((value >> (BitUtils.BYTE_LENGTH - 1 - i)) & 0x1) != 0;
            sb.append(bit ? 1 : 0);
        }
        return sb.toString();
    }

    /**
     * 将二进制字符串转换为字节数组
     * @param binaryString 二进制字符串
     * @return byte 数组
     */
    public static byte [] binaryStringToByteArray (String binaryString) {
        int length = binaryString.length() / 8;
        byte [] result = new byte[length];
        for (int i = 0; i < length; i++) {
            String byteString = binaryString.substring(i * 8, (i + 1) *8);
            result[i] = (byte)Integer.parseInt(byteString, 2);
        }
        return result;
    }

    public static String stringToBinary (String source, Charset charset) {
        byte [] bytes = source.getBytes(charset);
        StringBuilder binaryString = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            binaryString.append(BitUtils.byteToBinary(bytes[i]));
        }
        return binaryString.toString();
    }

    public static void main(String[] args) {
        int value = -1;
        String bitString = BitUtils.decimalToBinary(value);
        System.out.println(bitString);
        System.out.println(new BigInteger(bitString, 2).intValue());

        System.out.println(BitUtils.charToBinary('0'));

        System.out.println(BitUtils.byteToBinary((byte)1));

        System.out.println(BitUtils.longToBinary(1L));

        System.out.println(BitUtils.stringToBinary("你好", StandardCharsets.UTF_8));
    }
}
