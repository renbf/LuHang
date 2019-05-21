package com.dalaiye.luhang.utils;

import com.dalaiye.luhang.bean.UpLoadProgressBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/28
 * @function 注释
 **/
public class UploadProgressUtils {

    /**
     * 集合用于储存为上传的进度
     */
    private List<UpLoadProgressBean> mUpLoadList ;

    private UploadProgressUtils() {
        mUpLoadList = new ArrayList<>();
    }

    private static class Holder {
        private static final UploadProgressUtils INSTANCES = new UploadProgressUtils();
    }

    public static UploadProgressUtils getInstance() {
        return UploadProgressUtils.Holder.INSTANCES;
    }

    public void addUpLoadData(UpLoadProgressBean upLoadProgressBean){
        mUpLoadList.add(upLoadProgressBean);
    }

    public void cleanUpLoadData(){
        mUpLoadList.clear();
    }

    public List<UpLoadProgressBean> getUpLoadList(){
        return mUpLoadList;
    }
}
