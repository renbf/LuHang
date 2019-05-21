package com.dalaiye.luhang.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dalaiye.luhang.adapter.ExpandableListViewAdapter;
import com.dalaiye.luhang.bean.base.BaseListBean;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/24
 * @function 注释
 **/
public class UpLoadDangersDefaultInfoBean{

    private List<DeptListBean> deptList;
    private List<CheckTypesBean> checkTypes;
    private List<DangerTypesBean> dangerTypes;
    private List<DangerLevelsBean> dangerLevels;
    private List<MaybeResultsBean> maybeResults;
    private List<HiddenDangersFromBean> hiddenDangersFrom;

    public List<DeptListBean> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<DeptListBean> deptList) {
        this.deptList = deptList;
    }

    public List<CheckTypesBean> getCheckTypes() {
        return checkTypes;
    }

    public void setCheckTypes(List<CheckTypesBean> checkTypes) {
        this.checkTypes = checkTypes;
    }

    public List<DangerTypesBean> getDangerTypes() {
        return dangerTypes;
    }

    public void setDangerTypes(List<DangerTypesBean> dangerTypes) {
        this.dangerTypes = dangerTypes;
    }

    public List<DangerLevelsBean> getDangerLevels() {
        return dangerLevels;
    }

    public void setDangerLevels(List<DangerLevelsBean> dangerLevels) {
        this.dangerLevels = dangerLevels;
    }

    public List<MaybeResultsBean> getMaybeResults() {
        return maybeResults;
    }

    public void setMaybeResults(List<MaybeResultsBean> maybeResults) {
        this.maybeResults = maybeResults;
    }

    public List<HiddenDangersFromBean> getHiddenDangersFrom() {
        return hiddenDangersFrom;
    }

    public void setHiddenDangersFrom(List<HiddenDangersFromBean> hiddenDangersFrom) {
        this.hiddenDangersFrom = hiddenDangersFrom;
    }


    public static class DeptListBean extends BaseListBean {
        /**
         * deptId : string,部门id
         * deptName : string,部门名称
         */

        private String deptId;
        private String deptName;

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        @Override
        public String getListItem() {
            return getDeptName();
        }
    }

    public static class CheckTypesBean extends BaseListBean{
        /**
         * id : string,检查类型id
         * dictName : string,检查类型名称
         */

        private String id;
        private String dictName;

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

        @Override
        public String getListItem() {
            return getDictName();
        }
    }

    public static class DangerTypesBean extends AbstractExpandableItem<DangerTypesBean.ChildrenBean>
            implements MultiItemEntity {
        /**
         * id : string,id
         * dictName : string,隐患类型
         * children : [{"id":"string,二级隐患类型id","dictName":"string,二级隐患类型名称"}]
         */

        private String id;
        private String dictName;
        private List<ChildrenBean> children;

        public String getId() {
            return id;
        }

        @Override
        public ChildrenBean getSubItem(int position) {
            return children.get(position);
        }

        @Override
        public void addSubItem(ChildrenBean subItem) {
            super.addSubItem(subItem);
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

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        @Override
        public List<ChildrenBean> getSubItems() {
            return children;
        }

        @Override
        public int getLevel() {
            return 0;
        }

        @Override
        public int getItemType() {
            return ExpandableListViewAdapter.TYPE_LEVEL_0;
        }

        public static class ChildrenBean implements MultiItemEntity{
            /**
             * id : string,二级隐患类型id
             * dictName : string,二级隐患类型名称
             */

            private String id;
            private String dictName;

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

            @Override
            public int getItemType() {
                return ExpandableListViewAdapter.TYPE_LEVEL_1;
            }
        }
    }

    public static class DangerLevelsBean extends BaseListBean{
        /**
         * id : string,隐患等级id
         * dictName : string,隐患等级
         */

        private String id;
        private String dictName;

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

        @Override
        public String getListItem() {
            return getDictName();
        }
    }

    public static class MaybeResultsBean {
        /**
         * id : string,可能后果id
         * dictName : string,可能后果名称
         */

        private String id;
        private String dictName;

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
    }

    public static class HiddenDangersFromBean extends BaseListBean{
        /**
         * id : string,风险源id
         * dictName : string,风险源名称
         */

        private String id;
        private String dictName;

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

        @Override
        public String getListItem() {
            return getDictName();
        }
    }
}
