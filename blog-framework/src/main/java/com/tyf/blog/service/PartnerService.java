package com.tyf.blog.service;

import com.tyf.blog.vo.Pager;
import com.tyf.blog.vo.Partner;

import java.util.List;

/**
* Created by GeneratorFx on 2017-04-10.
*/
public interface PartnerService {

    List<Partner> findAll();

    void savePartner(Partner partner);

    /**
     * 分页查询好友列表
     * @param pager 分页对象
     * @param param
     * @return
     */
    List<Partner> loadPartner(Pager pager, String param);

    Partner getPartnerById(Integer id);

    void deletePartner(Integer id);

    void updatePartner(Partner partner);

    void initPage(Pager pager);
}
