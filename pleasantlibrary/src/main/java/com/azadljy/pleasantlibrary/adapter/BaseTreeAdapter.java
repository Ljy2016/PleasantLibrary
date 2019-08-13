package com.azadljy.pleasantlibrary.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.azadljy.pleasantlibrary.model.Node;
import com.azadljy.pleasantlibrary.utils.TreeHelper;

import java.util.List;

public abstract class BaseTreeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Node> allNodes;
    protected List<Node> visibleNodes;



    public BaseTreeAdapter(List<Node> data) {
        allNodes = data;
        visibleNodes = TreeHelper.getVisibleNode(data);
    }


    /**
     * 响应的点击事件 展开或关闭某节点
     */
    public void expandOrCollapse(int position) {
        Node n = visibleNodes.get(position);
        if (n != null) {
            if (n.getChildren().size() > 0)// 不是叶子节点
            {
                n.setIsExpand(!n.isExpand());
                visibleNodes = TreeHelper.getVisibleNode(allNodes);
                notifyDataSetChanged();// 刷新视图
            }
        }
    }

    @Override
    public int getItemCount() {
        return visibleNodes.size();
    }
}
