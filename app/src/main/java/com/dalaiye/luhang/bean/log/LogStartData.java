package com.dalaiye.luhang.bean.log;

import com.dalaiye.luhang.bean.base.BaseListBean;
import com.dalaiye.luhang.constant.Constant;

import java.util.List;

/**
 * @author admin
 * @date 2019/4/28
 * @function 注释
 **/
public class LogStartData {

    private List<GoodsBean> goodsNameTypeList;
    private List<DriversBean> drivers;
    private List<EscortBean> escorts;
    private List<CheckBean> carCheckProject;
    private List<ConclusionBean> sureComment;
    private UserDriverBean firstDriver;

    public UserDriverBean getFirstDriver() {
        return firstDriver;
    }

    public void setFirstDriver(UserDriverBean firstDriver) {
        this.firstDriver = firstDriver;
    }

    public List<GoodsBean> getGoodsNameTypeList() {
        return goodsNameTypeList;
    }

    public void setGoodsNameTypeList(List<GoodsBean> goodsNameTypeList) {
        this.goodsNameTypeList = goodsNameTypeList;
    }

    public List<DriversBean> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriversBean> drivers) {
        this.drivers = drivers;
    }

    public List<EscortBean> getEscorts() {
        return escorts;
    }

    public void setEscorts(List<EscortBean> escorts) {
        this.escorts = escorts;
    }

    public List<CheckBean> getCarCheckProject() {
        return carCheckProject;
    }

    public void setCarCheckProject(List<CheckBean> carCheckProject) {
        this.carCheckProject = carCheckProject;
    }

    public List<ConclusionBean> getSureComment() {
        return sureComment;
    }

    public void setSureComment(List<ConclusionBean> sureComment) {
        this.sureComment = sureComment;
    }

    public static class UserDriverBean {

        /**
         * firstDriverId : string,驾驶员id
         * firstDriverName : string,驾驶员名称
         */

        private String firstDriverId;
        private String firstDriverName;

        public String getFirstDriverId() {
            return firstDriverId;
        }

        public void setFirstDriverId(String firstDriverId) {
            this.firstDriverId = firstDriverId;
        }

        public String getFirstDriverName() {
            return firstDriverName;
        }

        public void setFirstDriverName(String firstDriverName) {
            this.firstDriverName = firstDriverName;
        }
    }
    
    public static class GoodsBean extends BaseListBean {

        /**
         * id : 1
         * typeItem : null
         * goodsName : 普通货物1
         * typeItemName : null
         */

        private String id;
        private String typeItem;
        private String goodsName;
        private String typeItemName;

        @Override
        public String getListItem() {
            return getGoodsName();
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypeItem() {
            return typeItem;
        }

        public void setTypeItem(String typeItem) {
            this.typeItem = typeItem;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getTypeItemName() {
            return typeItemName;
        }

        public void setTypeItemName(String typeItemName) {
            this.typeItemName = typeItemName;
        }
    }

    public static class DriversBean extends BaseListBean {
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

    public static class EscortBean extends BaseListBean {
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

    public static class ConclusionBean extends BaseListBean {
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
}
