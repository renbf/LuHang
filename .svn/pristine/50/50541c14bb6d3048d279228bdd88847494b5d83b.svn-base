package com.dalaiye.luhang.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.UpLoadDangersDefaultInfoBean;
import com.dalaiye.luhang.widget.dialog.ExpandableListViewDialog;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/23
 * @function 注释
 **/
public class ExpandableListViewAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    private ExpandableListViewDialog.onItemPostionClick mOnItemPostionClick;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableListViewAdapter(List<MultiItemEntity> data, ExpandableListViewDialog.onItemPostionClick mOnItemPostionClick) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.expandbale_item_one);
        addItemType(TYPE_LEVEL_1, R.layout.expandbale_item_two);
        this.mOnItemPostionClick = mOnItemPostionClick;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_0:
                UpLoadDangersDefaultInfoBean.DangerTypesBean dangerTypesBean = (UpLoadDangersDefaultInfoBean.DangerTypesBean) item;
                helper.setText(R.id.tv_type,dangerTypesBean.getDictName());
                helper.setImageResource(R.id.iv_type_img, dangerTypesBean.isExpanded() ? R.mipmap.ic_up_light: R.mipmap.ic_down_light);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dangerTypesBean.isExpanded()) {
                            collapse(helper.getAdapterPosition());
                            helper.setImageResource(R.id.iv_type_img,R.mipmap.ic_up_light);
                        }else {
                            expand(helper.getAdapterPosition());
                            helper.setImageResource(R.id.iv_type_img,R.mipmap.ic_down_light);
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                UpLoadDangersDefaultInfoBean.DangerTypesBean.ChildrenBean childrenBean = (UpLoadDangersDefaultInfoBean.DangerTypesBean.ChildrenBean) item;
                helper.setText(R.id.tv_child_type,childrenBean.getDictName());
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        helper.getAdapterPosition();
                        mOnItemPostionClick.onClick(childrenBean.getId(),childrenBean.getDictName());
                    }
                });
                break;
            default:
                break;
        }
    }
}
