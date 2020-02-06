package com.example.hotel.utils;

import java.util.List;

public class Pages {
    //当前页
    private  Integer pageNum=1;
    //每页显示的总条数
    private  Integer pageSize=10;
    //总条数
    private Long totalNum;
    //总页数
    private Integer totalPage;


    public Pages(Integer pageNum, Integer pageSize, Long totalNum, Integer totalPage) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalNum = totalNum;
        this.totalPage = totalPage;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }


}
