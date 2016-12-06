package com.weapp.common.util;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class MongoPageable implements Serializable, Pageable{

	/**
	 * mongodb分页
	 */
	private static final long serialVersionUID = 1L;
	// 当前页  
    private Integer pagenumber = 1;  
    // 当前页面条数  
    private Integer pagesize = 10;  
   
	@Override
	public int getPageNumber() {
		return 0;
	}

	@Override
	public int getPageSize() {
		return 0;
	}

	@Override
	public int getOffset() {
		return 0;
	}
	
	@Override
	public Pageable next() {
		return null;
	}

	@Override
	public Pageable previousOrFirst() {
		return null;
	}

	@Override
	public Pageable first() {
		return null;
	}

	@Override
	public boolean hasPrevious() {
		return false;
	}

	public Integer getPagenumber() {
		return pagenumber;
	}

	public void setPagenumber(Integer pagenumber) {
		this.pagenumber = pagenumber;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	@Override
	public Sort getSort() {
		return null;
	}
}
