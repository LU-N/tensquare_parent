package com.tensquare.article.service;

import com.tensquare.article.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JinLu
 * @date 2020/1/5 19:52
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
}
