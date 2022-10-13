package com.Zlibrary.response;

import java.io.Serializable;
import java.util.List;

/**
 * @className: PageBean
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-10-13
 **/

public class PageBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public long totalCount;

    public List<T> list;

    public PageBean() {

    }

    public PageBean(long totalCount, List<T> list) {
        this.totalCount = totalCount;
        this.list = list;
    }

}

