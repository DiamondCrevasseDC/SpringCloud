package com.ck.userdemo.entity;

import java.util.List;

public class Tree {

    private String id;

    private String name;

    private String parentId;

    private List<Tree> childrenTree;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<Tree> getChildrenTree() {
        return childrenTree;
    }

    public void setChildrenTree(List<Tree> childrenTree) {
        this.childrenTree = childrenTree;
    }

    public Tree(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }
}
