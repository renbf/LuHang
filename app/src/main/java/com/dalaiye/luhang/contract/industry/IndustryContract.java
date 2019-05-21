package com.dalaiye.luhang.contract.industry;

import com.dalaiye.luhang.bean.IndustryBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author admin
 * @date 2019/4/11
 * @function
 **/
public interface IndustryContract {
    interface IIndustryView extends IView {
        /**
         * 设置行业动态数据
         * @param industryBean
         */
        void setIndustryData(IndustryBean industryBean);
    }

    interface IIndustryPresenter extends IPresenter<IIndustryView> {
        /**
         * 查询行业动态数据
         * @param pageNumber
         * @param total
         */
        void queryIndustryData(int pageNumber,String total);
    }
}
