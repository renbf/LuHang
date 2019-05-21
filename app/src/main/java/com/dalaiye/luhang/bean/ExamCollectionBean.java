package com.dalaiye.luhang.bean;

import com.dalaiye.luhang.constant.Constant;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 * @date 2019/4/30
 * @function 注释
 **/
public class ExamCollectionBean {


    /**
     * total : 2
     * pageNumber : 1
     * rows : [{"id":"1","subject":"题目1题目1题目1题目1题目1题目1题目1题目1题目1题目1题目1","annotation":"注释1","subjectType":null,"trueAnswer":"A","optionContents":[{"optionValue":"A","optionSort":1,"content":"选A选A选A选A选A选A"},{"optionValue":"B","optionSort":2,"content":"选B选B选B选B选B选B选B选B"},{"optionValue":"C","optionSort":3,"content":"选C"},{"optionValue":"D","optionSort":4,"content":"选D"}],"isCollect":"1"},{"id":"2","subject":"题目2题目2题目2题目2题目2题目2题目2题目2","annotation":"注释2","subjectType":null,"trueAnswer":"C","optionContents":[{"optionValue":"A","optionSort":1,"content":"选A"},{"optionValue":"B","optionSort":2,"content":"选B"},{"optionValue":"C","optionSort":3,"content":"选C"},{"optionValue":"D","optionSort":4,"content":"选D"}],"isCollect":"1"}]
     */

    private int total;
    private int pageNumber;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable {
        /**
         * id : 1
         * subject : 题目1题目1题目1题目1题目1题目1题目1题目1题目1题目1题目1
         * annotation : 注释1
         * subjectType : null
         * trueAnswer : A
         * optionContents : [{"optionValue":"A","optionSort":1,"content":"选A选A选A选A选A选A"},{"optionValue":"B","optionSort":2,"content":"选B选B选B选B选B选B选B选B"},{"optionValue":"C","optionSort":3,"content":"选C"},{"optionValue":"D","optionSort":4,"content":"选D"}]
         * isCollect : 1
         */

        private String id;
        private String subject;
        private String annotation;
        private int subjectType = Constant.EXAM_QUESTION_TYPE_SINGLE;
        private String trueAnswer;
        private int isCollect = Constant.EXAM_QUESTION_COLLECTION;
        private List<OptionContentsBean> optionContents;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public int getSubjectType() {
            return subjectType;
        }

        public void setSubjectType(int subjectType) {
            this.subjectType = subjectType;
        }

        public String getTrueAnswer() {
            return trueAnswer;
        }

        public void setTrueAnswer(String trueAnswer) {
            this.trueAnswer = trueAnswer;
        }

        public int getIsCollect() {
            return isCollect;
        }

        public void setIsCollect(int isCollect) {
            this.isCollect = isCollect;
        }

        public List<OptionContentsBean> getOptionContents() {
            return optionContents;
        }

        public void setOptionContents(List<OptionContentsBean> optionContents) {
            this.optionContents = optionContents;
        }

        public static class OptionContentsBean implements Serializable {
            /**
             * optionValue : A
             * optionSort : 1
             * content : 选A选A选A选A选A选A
             */

            private String optionValue;
            private int optionSort;
            private String content;

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
        }
    }
}
