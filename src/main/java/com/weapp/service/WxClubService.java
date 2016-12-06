package com.weapp.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.weapp.common.util.MongoPageable;
import com.weapp.entity.wxclub.Article;
import com.weapp.repository.WxClubRepository;

@Service
public class WxClubService {
	@Autowired
	private WxClubRepository clubRepository;
	private static final String CLUBHOST = "http://www.wxappclub.com";
	
	private static final String WX_CLUB_HOST = "http://www.wxappclub.com";
	private static final String[] paths = {"/column/1","/column/2","/column/3","/column/4","/column/5","/column/6","/column/7","/column/8","/column/10"};
	//抓取小程序专栏内容
	public void getWxClubColumn() throws IOException{
		List<Article>list = Lists.newArrayList();
		for(String path: paths){
			Document document = Jsoup.connect(WX_CLUB_HOST + path).get();
			if(document == null){
				continue;
			}
			Elements els = document.select(".topic_list li");
			if(els == null || els.size() == 0){
				continue;
			}
			String detailPath = "";
			String title = "";
			String desc = "";
			String content = "";
			Map<String,String> authorMap = null;
			String createTime = "";
			String comment = "0";
			String brower = "0";
			String remark = "";

			for(Element element : els){
				Element titleEle = element.select(".topic_title a").first();
				detailPath = titleEle.absUrl("href");
				title = titleEle.text();
				
				authorMap = Maps.newHashMap();
				Document detailDoc = Jsoup.connect(detailPath).get();
				if(detailDoc != null){
					Element detailEl = detailDoc.select(".topic_content").first();
					Elements imgEls = detailEl.select("img[src]");
					
					for (Element el : imgEls) {
						String imgUrl = el.attr("src");
						imgUrl =CLUBHOST + "/" +imgUrl;
						el.attr("src", imgUrl);
					}
					
					content = detailEl.html();
					String headimg = detailDoc.select(".panel-body p .avatar").first().absUrl("src");
					String nickname = detailDoc.select(".panel-body p .username").first().text();
					
					Elements markEls = detailDoc.select(".panel-body .userremark");
					if(markEls != null){
						remark = markEls.first().text();
					}
					authorMap.put("headimg", headimg);
					authorMap.put("nickname", nickname);
					authorMap.put("remark", remark);
				}
				
				Elements descEls = element.select(".topic_desc");
				if(descEls != null && descEls.size() > 0){
					if(descEls.size() == 1){
						createTime = descEls.get(0).select(".last_time").first().text();

						Elements elss = descEls.get(0).select(".reply_count .count_of_visits");
						if(elss != null && elss.size() == 2){
							comment = elss.get(0).text().replace("评论", "").replace(" ", "");
							brower = elss.get(1).text().replace("浏览", "").replace(" ", "");
						}
					}else {
						desc = descEls.get(0).text();
						createTime = descEls.get(1).select(".last_time").first().text();

						Elements elss = descEls.get(1).select(".reply_count .count_of_visits");
						if(elss != null && elss.size() == 2){
							comment = elss.get(0).text().replace("评论", "").replace(" ", "");
							brower = elss.get(1).text().replace("浏览", "").replace(" ", "");
						}
					}
				}
				Article article = new Article();
				article.setAuthor(authorMap);
				article.setPath(detailPath);
				article.setGroupPath(path);
				article.setBrowers(Integer.valueOf(brower));
				article.setComments(Integer.valueOf(comment));
				article.setContent(content);
				article.setCreateTime(createTime);
				article.setDigest(desc);
				article.setTitle(title);
				list.add(article);
				
				detailPath = "";
				title = "";
				desc = "";
				authorMap = null;
				createTime = "";
				comment = "0";
				brower = "0";
				content = "";
			}
		}
		clubRepository.save(list);
	}
	/**
	 * 清空
	 */
	public void deleteAll() {
		clubRepository.deleteAll();
	}
	/**
	 * 根据专栏ID搜索
	 * @param groupPath
	 * @return
	 */
	public List<Article> getByGroupPath(String groupPath) {
		return clubRepository.findByGroupPath(groupPath);
	}
	/**
	 * 根据文章标题模糊搜索（分页）
	 * @param title
	 * @param page
	 * @return
	 */
	public List<Article> getByTitle(String title, MongoPageable page) {
		return clubRepository.findByTitleLike(title,page);
	}
}
