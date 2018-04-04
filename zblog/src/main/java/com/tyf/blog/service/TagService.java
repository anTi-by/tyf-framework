package com.tyf.blog.service;

import com.tyf.blog.vo.ArticleCustom;
import com.tyf.blog.vo.Pager;
import com.tyf.blog.vo.Tag;

import java.util.List;

/**
 * @author eumji
 * package com.tyf.blog.service
 * @name TagService
 * @date 2017/4/13
 * @time 18:55
 */
public interface TagService {
    /**
     * 获取当前tag下的文章列表
     * @param pager 分页对象
     * @param tagId
     * @return
     */
    List<ArticleCustom> loadArticleByTag(Pager pager, Integer tagId);

    int getTagCount();

    Tag getTagById(Integer id);

    List<Tag> loadTagList(Pager pager, String tagName);

    void saveTag(Tag tag);

    boolean checkExist(Tag tag);

    void updateTag(Tag tag);

    void initPage(Pager pager);

    List<Tag> getTagList();

    /**
     * 初始化分页
     * @param pager 分页对象
     * @param tagId
     */
    void articleTagPage(Pager pager, int tagId);

    /**
     * 根据id删除标签
     * @param id
     */
    void deleteTagById(int id);
}
