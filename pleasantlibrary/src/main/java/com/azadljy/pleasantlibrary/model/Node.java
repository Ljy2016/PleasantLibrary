package com.azadljy.pleasantlibrary.model;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vmmet on 2016/8/11.
 */
public class Node {

    private int type;
    private String name;

    private boolean isRoot;
    /**
     * 是否为根节点
     */
    private boolean isLeaf;
    /**
     * 是否为叶子节点
     */
    private boolean isExpand;
    /**
     * 该节点是否展开
     */
    private Node parent;
    /**
     * 父节点
     */
    private List<Node> children = new ArrayList<Node>();
    /**
     * 子节点
     */
    private int level = 0;
    /**
     * 该节点所属的层级
     */
    private boolean isSelect;
    /**
     * 是否被选择
     */
    private boolean isChecked;

    /**
     * 是否被选择
     */
    public boolean isSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    /**
     * 构造器
     */
    public Node() {
    }




    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public boolean isRoot() {
        return isRoot;
    }

    public void setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setIsExpand(boolean isExpand) {
        this.isExpand = isExpand;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public void setRoot(boolean root) {
        isRoot = root;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
