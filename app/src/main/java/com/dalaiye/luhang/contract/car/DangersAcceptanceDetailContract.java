package com.dalaiye.luhang.contract.car;

import com.dalaiye.luhang.bean.AcceptanceBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author AnYu
 * @date 2019/4/18
 * @function 注释
 **/
public interface DangersAcceptanceDetailContract {
    interface IDangersAcceptanceDetailView extends IView{
        void queryAcceptanceDetailSuccessful(AcceptanceBean acceptanceBean);
    }
    interface IDangersAcceptanceDetailPresenter extends IPresenter<IDangersAcceptanceDetailView>{
        /**
         * 验收详情，同整改详情调用同一个接口，返回数据解析不同
         */
        void queryAcceptanceDetail(String dangerId);
    }
}
