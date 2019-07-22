package com.ck.userdemo.algorithm;

import java.util.ArrayList;

public class BackTrackingAlgorithm {

    public static ArrayList<String> letters = new ArrayList<String>(5);

    static {
        letters.add("a");
        letters.add("b");
        letters.add("c");
        letters.add("d");
        letters.add("e");
    }

    public static void permutate(ArrayList<String> letterList, ArrayList<String> result){
        if (letterList.size() == 1){
            System.out.println(result);
            return;
        }

        for(int i = 0; i < letterList.size(); i ++){
            ArrayList<String> newResult = (ArrayList<String>) result.clone();
            newResult.add(letterList.get(i));

            ArrayList<String> newLetterList = (ArrayList<String>) letterList.clone();
            newLetterList.remove(i);
            permutate(newLetterList, newResult);
        }
    }

    public static void main(String[] args){
        permutate(letters, new ArrayList<String>(4));
    }
}
