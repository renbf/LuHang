package com.dalaiye.luhang.contract.car;

import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

import java.io.File;
import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/18
 * @function 注释
 **/
public interface DangersAccpetRectificationContract {
    interface IDangersAccpetRectificationView extends IView{
        /**
         * 整改提交返回
         * @param message
         */
        void rectificationSubmitSuccessful(String message);
    }
    interface IDangersAccpetRectificationPresenter extends IPresenter<IDangersAccpetRectificationView> {
        /**
         * 整改提交
         * @param id
         * @param dochangeStep 整改措施
         * @param dochangeCapital
         * @param fileList
         */
        void rectificationSubmit(String id,String dochangeStep,String dochangeCapital,List<File> fileList);
    }
}
