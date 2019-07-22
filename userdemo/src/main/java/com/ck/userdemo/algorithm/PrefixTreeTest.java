package com.ck.userdemo.algorithm;

import java.util.ArrayList;
import java.util.List;

public class PrefixTreeTest {

    private static List<String> dictionary = new ArrayList<>(16);

    static{
        dictionary.add("apple");
        dictionary.add("china");
        dictionary.add("zelda");
        dictionary.add("bloodstained");
        dictionary.add("blood");
    }

    public static void main(String[] args){
        PrefixTree tree = new PrefixTree(dictionary);
        System.out.println(tree.depthFirstSearch("appl"));
    }

}
