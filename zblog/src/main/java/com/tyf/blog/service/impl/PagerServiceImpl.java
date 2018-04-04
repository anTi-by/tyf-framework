package com.tyf.blog.service.impl;

import com.tyf.blog.mapper.ArticleMapper;
import com.tyf.blog.mapper.PagerMapper;
import com.tyf.blog.service.PagerService;
import com.tyf.blog.vo.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Do
 * @package com.tyf.blog.service.impl
 * @name PagerServiceImpl
 * @date 2017/4/11
 * @time 21:46
 */
@Service
public class PagerServiceImpl implements PagerService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private PagerMapper pagerMapper;
    @Override
    public void initPage(Pager pager) {
        int count = articleMapper.getArticleCount();
        pager.setTotalCount(count);
    }

    @Override
    public void loadCategoryPager(Pager pager,Integer categoryId) {
        int count = pagerMapper.loadCategoryPager(categoryId);
        pager.setTotalCount(count);
    }

    @Override
    public void loadTagPager(Pager pager, Integer tagId) {
        int count = pagerMapper.loadTagPager(tagId);
        pager.setTotalCount(count);
    }

    @Override
    public void loadArchivePager(Pager pager, String createTime) {
        int count = pagerMapper.loadArchivePager(createTime);
        pager.setTotalCount(count);
    }
}
