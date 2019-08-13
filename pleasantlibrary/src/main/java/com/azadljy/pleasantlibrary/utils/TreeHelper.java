package com.azadljy.pleasantlibrary.utils;

import com.azadljy.pleasantlibrary.model.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vmmet on 2016/8/11.
 */
public class TreeHelper {
    private static List<Node> allNodes;
    /**
     * 用来存放所有的node
     */
    private static List<Node> rootNodes; /**用来存放所有的根node*/
    /**
     * 得到所有的node（属性全处理好的）
     */
//    public static List<Node> getAllNodes(String data) {
//        List<Node> nodes = new ArrayList<Node>();
//        String[] nodeArray = data.split("#&");
//        for (int i = 0; i < nodeArray.length; i++) {
//            String nodeItem = "";
//            if (nodeArray[i].endsWith("|")) {
//                nodeItem = nodeArray[i] + "*";
//            } else {
//                nodeItem = nodeArray[i];
//            }
//            String[] nodeStr = nodeItem.split("\\|");
//            if (nodeStr.length > 8) {
//                Node node = new Node(nodeStr[0], nodeStr[1], nodeStr[2], nodeStr[3], nodeStr[4], nodeStr[5], nodeStr[6], nodeStr[7], nodeStr[8]);
//                if (node.getPid().trim().length() == 0) {
//                    node.setPid("0");
//                }
//                nodes.add(node);
//            }
//        }
////        第一个树的模拟数据
////        nodes.add(new Node("1", "0", "娱乐"));
////        nodes.add(new Node("2", "1", "游戏"));
////        nodes.add(new Node("3", "1", "音乐"));
////        nodes.add(new Node("4", "1", "电影"));
////        nodes.add(new Node("5", "2", "英雄联盟"));
////        nodes.add(new Node("6", "3", "贝加尔湖畔"));
////        nodes.add(new Node("7", "3", "克罗地亚狂想曲"));
////        nodes.add(new Node("8", "4", "无法触碰"));
////        nodes.add(new Node("9", "4", "返老还童"));
////        //第二个树的模拟数据
////        nodes.add(new Node("10", "0", "学习"));
////        nodes.add(new Node("11", "10", "Java"));
////        nodes.add(new Node("12", "10", "数据库"));
////        nodes.add(new Node("13", "10", "网络"));
////        nodes.add(new Node("14", "11", "封装，继承，多态"));
////        nodes.add(new Node("15", "11", "抽象类，接口"));
////        nodes.add(new Node("16", "12", "建表"));
////        nodes.add(new Node("17", "12", "增删改查"));
////
////        nodes.add(new Node("18", "4", "增删改查"));
////        nodes.add(new Node("19", "18", "增删改查"));
////        nodes.add(new Node("20", "19", "增删改查"));
////        nodes.add(new Node("21", "20", "增删改查"));
////        nodes.add(new Node("22", "21", "增删改查"));
//
//        Map<String, Node> map_nodes = new HashMap<String, Node>();
//        for (int i = 0; i < nodes.size(); i++) {
//            map_nodes.put(nodes.get(i).getId(), nodes.get(i));
//        }
//        /**设置父子关系*/
//        for (int i = 0; i < nodes.size(); i++) {
//            Node childnode = nodes.get(i);
//            String pid = childnode.getPid();
//            if (pid.equals("0")) {
//                /**根节点level为1*/
//                childnode.setLevel(1);
//            } else {
//                //子节点的pid为父结点的id，根据子节点pid找到父节点
//                Node parentnode = map_nodes.get(pid);
//                if (parentnode != null) {
//                    childnode.setParent(parentnode);
//                    childnode.setLevel(parentnode.getLevel() + 1);
//                    parentnode.getChildren().add(childnode);
//                }
//            }
//        }
//        return nodes;
//    }

//    public static List<Node> getOriganize(String data) {
//        List<Node> nodes = new ArrayList<Node>();
//        String[] nodeArray = data.split("\\|");
//        for (int i = 0; i < nodeArray.length; i++) {
//            String nodeItem = nodeArray[i];
//            String[] nodeStr = nodeItem.split("\\^");
//            if (nodeStr.length > 2) {
//                Node node = new Node(nodeStr[0], nodeStr[2], nodeStr[1]);
//                if (node.getPid().trim().length() == 0) {
//                    node.setPid("0");
//                }
//                nodes.add(node);
//            }
//        }
//        Map<String, Node> map_nodes = new HashMap<String, Node>();
//        for (int i = 0; i < nodes.size(); i++) {
//            map_nodes.put(nodes.get(i).getId(), nodes.get(i));
//        }
//        /**设置父子关系*/
//        for (int i = 0; i < nodes.size(); i++) {
//            Node childnode = nodes.get(i);
//            String pid = childnode.getPid();
//            if (pid.equals("0")) {
//                /**根节点level为1*/
//                childnode.setLevel(1);
//            } else {
//                //子节点的pid为父结点的id，根据子节点pid找到父节点
//                Node parentnode = map_nodes.get(pid);
//                if (parentnode != null) {
//                    childnode.setParent(parentnode);
//                    childnode.setLevel(parentnode.getLevel() + 1);
//                    parentnode.getChildren().add(childnode);
//                }
//            }
//        }
//        return nodes;
//    }
    public static List<Node> getVisibleNode(List<Node> nodes) {
        List<Node> result = new ArrayList<Node>();
        rootNodes = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node.getParent() == null) {
                rootNodes.add(node);
            }
        }
        for (Node node : rootNodes) {
            filterNode(result, node);
        }
        return result;
    }

    //递归重新添加Node
    private static void filterNode(List<Node> nodes, Node node) {
        nodes.add(node);
        if (node.isExpand()) {
            for (int i = 0; i < node.getChildren().size(); i++) {
                filterNode(nodes, node.getChildren().get(i));
            }
        }
    }

    public static int getLeafNode(Node node) {
        List<Node> leafNodes = new ArrayList<Node>();
        leafNode(leafNodes, node);
        return leafNodes.size();
    }

    //递归重新添加Node
    private static void leafNode(List<Node> nodes, Node node) {

        if (node.getChildren().size() != 0) {
            for (int i = 0; i < node.getChildren().size(); i++) {
                Node ChildNode = node.getChildren().get(i);
                if (ChildNode.getChildren().size() == 0) {
                    nodes.add(ChildNode);
                } else {
                    leafNode(nodes, ChildNode);
                }
            }
        }
    }

    //递归重新添加Node
    public static void SelectNode(Node node, boolean isSelect) {
        node.setIsSelect(isSelect);
        for (int i = 0; i < node.getChildren().size(); i++) {
            SelectNode(node.getChildren().get(i), isSelect);
        }
    }

    //展开所有的父节点
    public static void ExpandAllParentNode(Node node) {
        if (node.getParent() != null) {
            ExpandAllParentNode(node.getParent());
        }
        node.setIsExpand(true);
    }
}
