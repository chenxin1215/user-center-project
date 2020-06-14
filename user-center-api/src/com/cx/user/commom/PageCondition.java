package com.cx.user.commom;

import java.io.Serializable;

/**
 * 〈分页类 不设默认值〉
 *
 * @author chenxin
 * @date 2020/6/14
 */
public class PageCondition implements Serializable {

    private Integer page;

    private Integer pageSize;

    public PageCondition() {}

    public PageCondition(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        if (this.getPage() == null || this.getPageSize() == null) {
            return 0;
        } else {
            return (this.getPage() - 1) * this.getPageSize();
        }
    }
}