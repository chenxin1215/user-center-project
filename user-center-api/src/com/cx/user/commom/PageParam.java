package com.cx.user.commom;

import java.io.Serializable;
import java.util.Optional;

public class PageParam implements Serializable {

    /**
     * 分页参数构建<br>
     * 
     * <i>建议使用{@link #PageParam(Integer, Integer)}
     */
    public PageParam() {}

    /**
     * 分页参数构建
     * 
     * @param page 页码(从1开始)
     * @param pageSize 分页大小
     */
    public PageParam(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    private static final long serialVersionUID = 1L;

    /** 分页页码 (默认1) */
    private Integer page;

    /** 分页大小 (默认10) */
    private Integer pageSize;

    public Integer getPage() {
        return Optional.ofNullable(page).orElse(1);
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return Optional.ofNullable(pageSize).orElse(10);
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return (this.getPage() - 1) * this.getPageSize();
    }

}
