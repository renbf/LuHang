package com.dalaiye.luhang.bean;

import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dalaiye.luhang.constant.Constant;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 * @date 2019/4/19
 * @function 考试详情
 **/
public class ExamDetailsBean {
 
    private String id;
    private String userId;
    private String paperId;
    private String beginDate;
    private String endDate;
    private int status = Constant.EXAM_NOT_STARTED;
    private Integer paperScore;
    private String startDate;
    private String commitDate;
    private String addUserId;
    private String createDate;
    private String updateDate;
    private String updateUserId;
    private String paperName;
    private int remainingTime;
    private int paperTimes;
    private List<QuestionsBean> examQuestions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getPaperScore() {
        return paperScore;
    }

    public void setPaperScore(Integer paperScore) {
        this.paperScore = paperScore;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(String commitDate) {
        this.commitDate = commitDate;
    }

    public String getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(String addUserId) {
        this.addUserId = addUserId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getPaperTimes() {
        return paperTimes;
    }

    public void setPaperTimes(int paperTimes) {
        this.paperTimes = paperTimes;
    }

    public List<QuestionsBean> getExamQuestions() {
        return examQuestions;
    }

    public void setExamQuestions(List<QuestionsBean> examQuestions) {
        this.examQuestions = examQuestions;
    }

    public static class QuestionsBean implements MultiItemEntity,Serializable { 
        private String id;
        /** 提交过答案的Id */
        private String userSubjectId;
        /** 考卷ID */
        private String paperId;
        /** 考卷时间 */
        private Integer paperTimes;
        /** 题库ID */
        private String subjectId;
        /** 题目类型 0单选 1多选 2判断 */
        private int subjectType =Constant.EXAM_QUESTION_TYPE_SINGLE;
        /** 题目 */
        private String subject;
        /** 注释 */
        private String annotation;
        /** 正确答案 */
        private String trueAnswer;
        /** 题目分数 */
        private Integer subjectScore; 
        /** 题目选项信息 */
        private List<OptionBean> optionContents;
        /** 用户考卷ID */
        private String userPaperId;  
        /** 用户答案 */
        private String userAnswer = "";
        /** 是否答对0否1是 */
        private Integer isTrue;
        /** 是否收藏本题1是0否 */
        private int isCollect  = Constant.EXAM_QUESTION_NOT_COLLECTION;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserSubjectId() {
            return userSubjectId;
        }

        public void setUserSubjectId(String userSubjectId) {
            this.userSubjectId = userSubjectId;
        }

        public String getPaperId() {
            return paperId;
        }

        public void setPaperId(String paperId) {
            this.paperId = paperId;
        }

        public Integer getPaperTimes() {
            return paperTimes;
        }

        public void setPaperTimes(Integer paperTimes) {
            this.paperTimes = paperTimes;
        }

        public String getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(String subjectId) {
            this.subjectId = subjectId;
        }

        public int getSubjectType() {
            return subjectType;
        }

        public void setSubjectType(int subjectType) {
            this.subjectType = subjectType;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getAnnotation() {
            return annotation;
        }

        public void setAnnotation(String annotation) {
            this.annotation = annotation;
        }

        public String getTrueAnswer() {
            return trueAnswer;
        }

        public void setTrueAnswer(String trueAnswer) {
            this.trueAnswer = trueAnswer;
        }

        public Integer getSubjectScore() {
            return subjectScore;
        }

        public void setSubjectScore(Integer subjectScore) {
            this.subjectScore = subjectScore;
        }
        

        public List<OptionBean> getOptionContents() {
            return optionContents;
        }

        public void setOptionContents(List<OptionBean> optionContents) {
            this.optionContents = optionContents;
        }

        public String getUserPaperId() {
            return userPaperId;
        }

        public void setUserPaperId(String userPaperId) {
            this.userPaperId = userPaperId;
        }

        public String getUserAnswer() {
            return userAnswer;
        }

        public void setUserAnswer(String userAnswer) {
            this.userAnswer = userAnswer;
        }

        public Integer getIsTrue() {
            return isTrue;
        }

        public void setIsTrue(Integer isTrue) {
            this.isTrue = isTrue;
        }

        public int getIsCollect() {
            return isCollect;
        }

        public void setIsCollect(int isCollect) {
            this.isCollect = isCollect;
        }

        @Override
        public int getItemType() {
            return TextUtils.isEmpty(userSubjectId)?Constant.EXAM_QUESTION_TYPE_NOT_ANSWERED:Constant.EXAM_QUESTION_TYPE_ANSWERED;
        }

        public static class OptionBean implements Serializable{
            /**
             * optionValue : A
             * optionSort : 1
             * content : 选A
             */

            private String optionValue;
            private int optionSort;
            private String content;
            private boolean isSelector;

            public String getOptionValue() {
                return optionValue;
            }

            public void setOptionValue(String optionValue) {
                this.optionValue = optionValue;
            }

            public int getOptionSort() {
                return optionSort;
            }

            public void setOptionSort(int optionSort) {
                this.optionSort = optionSort;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public boolean isSelector() {
                return isSelector;
            }

            public void setSelector(boolean selector) {
                isSelector = selector;
            }
        }
    }
}
