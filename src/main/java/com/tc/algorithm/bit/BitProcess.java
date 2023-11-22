package com.tc.algorithm.bit;

import org.springframework.util.Assert;

/**
 * desc 字节操作相关算法
 *
 * @author lvzf 2023年11月22日
 */
public class BitProcess {

    public static void main(String[] args) {
        System.out.println(BitProcess.findLargestPowerOf2LessThanGivenNumberUsingBitShiftApproach(7L));
    }

    public static long findLargestPowerOf2LessThanGivenNumberUsingBitShiftApproach (long input) {
        Assert.isTrue(input > 1, "Invalid input");
        long result = 1;
        long powerOf2 = 0;
        for (long i = 0; i < Long.BYTES * 8; i++) {
            powerOf2 = 1L << i;
            if (powerOf2 > input) {
                break;
            }
            result = powerOf2;
        }
        return result;
    }
}
