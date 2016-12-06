package com.weapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weapp.common.annotation.Api;
import com.weapp.common.constant.ApiConstant;
import com.weapp.common.util.MongoPageable;
import com.weapp.entity.wxclub.Article;
import com.weapp.service.WxClubService;

@RestController
public class WxClubController extends BaseController{
	@Autowired
	private WxClubService wxClubService;
	
	/**
	 * 专栏文字列表
	 * @param id
	 * @param page
	 * @return
	 */
	@Api(name = ApiConstant.WX_CLUB_ARTICLES)
	@RequestMapping(value = "/api/v1/wxclub/column/{id}-{page}", method = RequestMethod.GET, produces = "application/json")
	public Map<String, ? extends Object>getArticles(@PathVariable String id,@PathVariable Integer page){
		List<Article>list = wxClubService.getByGroupPath("/column/" + id);
		return rtnParam(0, list);
	}
	/**
	 * 文章搜索
	 * @param pageNo
	 * @param searchText
	 * @return
	 */
	@Api(name = ApiConstant.WX_CLUB_SEARCH)
	@RequestMapping(value = "/api/v1/wxclub/column/search/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public Map<String, ? extends Object>getArticles(@PathVariable(value="pageNo",required=false) Integer pageNo,
			@RequestParam(required=false,value="text",defaultValue="")String searchText){
		
		MongoPageable page = new MongoPageable();
		page.setPagenumber(pageNo);
		List<Article>list = wxClubService.getByTitle(searchText, page);
		return rtnParam(0, list);
	}
}
