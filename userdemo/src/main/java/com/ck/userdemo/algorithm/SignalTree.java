package com.ck.userdemo.algorithm;

import com.ck.userdemo.entity.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SignalTree {

    public Tree convertToTree(List<Tree> treeList){
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
}
