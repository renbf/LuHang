package com.dalaiye.luhang.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/5/7
 * @function 注释
 **/
public class RandomUtils {

    public static List<Integer> generateRandom(int duration, int count) {

        int min = 60 * 1000;
        int max = duration - 60 * 1000;
        boolean isEnd = false;
        List<Integer> randoms = new ArrayList<>();
        int[] randomArray = new int[count] ;
        while (!isEnd) {
            isEnd = true;
            randomArray = randomCommon(min, max, count);
            for (int i = 0; i < randomArray.length; i++) {
                if (i + 1 < randomArray.length) {
                    if (randomArray[i + 1] - randomArray[i] < 5 * 60 * 1000) {
                        isEnd = false;
                    }
                }
            }
        }

        for (int i = 0; i < randomArray.length; i++) {
            randoms.add(randomArray[i]);
        }
        return randoms;
    }

    /**
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     *
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n   随机数个数
     */
    private static int[] randomCommon(int min, int max, int n) {
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while (count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (num == result[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result[count] = num;
                count++;
            }
        }
        return result;
    }
}
