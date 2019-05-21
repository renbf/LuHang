package com.dalaiye.luhang.bean.log;

import com.dalaiye.luhang.bean.base.BaseListBean;
import com.dalaiye.luhang.constant.Constant;

/**
 * @author admin
 * @date 2019/4/28
 * @function 注释
 **/
public class CheckBean extends BaseListBean {
    /**
     * id : string,行车前事项id
     * dictName : string,行车前事项名称
     */

    private String id;
    private String dictName;
    private String dictCode;
    private int status = Constant.CHECK_OPTION_UNSELECTED;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getListItem() {
        return getDictName();
    }
}
