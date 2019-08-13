package com.azadljy.pleasantlibrary.adapter;

import android.text.TextUtils;

import androidx.annotation.Nullable;


import com.azadljy.pleasantlibrary.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.w3c.dom.Text;

import java.util.List;

public class SimpleAdapter extends BaseQuickAdapter<SimpleAdapter.SimpleModel, BaseViewHolder> {


    public SimpleAdapter(int layoutResId, @Nullable List<SimpleModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SimpleModel item) {
        if (!TextUtils.isEmpty(item.getName())) {
            helper.setText(R.id.tv_simple_name, item.getName());
        }
        if (item.getIcon() > 0) {
            helper.setImageResource(R.id.iv_simple_icon, item.getIcon());
        }

    }

    public static class SimpleModel {
        private String name;
        private int icon;
        private String tag;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}
