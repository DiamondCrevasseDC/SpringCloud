package com.ck.userdemo.algorithm;

import com.ck.userdemo.entity.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SignalTree {

    public static Tree convertToTree(List<Tree> treeList){
        String root = "root";
        Map<String, Tree> map = new HashMap<String, Tree>(16);
        Map<String, List<Tree>> parentMap = new HashMap<String, List<Tree>>(16);
        for (Tree tree : treeList){
            map.put(tree.getId(), tree);
            if (!root.equals(tree.getId())){
                List<Tree> list = parentMap.get(tree.getParentId());
                if (list == null){
                    list = new ArrayList<Tree>(10);
                    parentMap.put(tree.getParentId(), list);
                }
                list.add(tree);
            }
        }

        Set<String> keySet = parentMap.keySet();
        for (String id : keySet){
            map.get(id).setChildrenTree(parentMap.get(id));
        }

        return map.get(root);
    }

    public static void main(String[] args){
        List<Tree> list = new ArrayList<Tree>(10);
        list.add(new Tree("root", "根节点", "0"));
        list.add(new Tree("1", "节点1", "root"));
        list.add(new Tree("2", "节点2", "root"));
        list.add(new Tree("3", "节点3", "1"));
        list.add(new Tree("4", "节点4", "2"));
        list.add(new Tree("5", "节点5", "2"));

        Tree tree = convertToTree(list);
    }

}