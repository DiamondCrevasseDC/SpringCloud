package com.ck.userdemo.algorithm;

import java.util.Arrays;

public class DictionaryOrder {

    public static int[] findNearestNumber(int[] number){
        // 拷贝入参，避免直接修改入参
        int[] numberCopy = Arrays.copyOf(number, number.length);
        int index = findTransferPoint(numberCopy);
        if (index == 0){
            System.out.println("整个数组已逆序！");
            return null;
        }
        exchangeHead(numberCopy, index);
        reverse(numberCopy, index);
        return numberCopy;
    }

    private static int findTransferPoint(int[] number){
        for (int i = number.length - 1; i > 0; i --){
            if (number[i] > number[i - 1]){
                return i;
            }
        }
        return 0;
    }

    private static int[] exchangeHead(int[] number, int index){
        int temp = number[index - 1];
        for (int i = number.length - 1; i >= index; i --){
            if (number[i] > temp){
                number[index - 1] = number[i];
                number[i] = temp;
                break;
            }
        }
        return number;
    }

    private static int[] reverse(int[] number, int index){
        for(int i = index, j = number.length - 1; i < j; i ++, j --){
            int temp = number[i];
            number[i] = number[j];
            number[j] = temp;
        }
        return number;
    }

    public static void main(String[] args){
        int[] number = new int[]{1, 2, 3, 4, 5};
        int[] newNum = findNearestNumber(number);
        for(int i : newNum){
            System.out.print(i);
        }
    }
}
