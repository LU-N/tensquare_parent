package com.tensquare.article.repository;

import com.tensquare.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
 * @author JinLu
 * @date 2020/1/6 14:52
 */
public interface CommentRepository extends MongoRepository<Comment, String> {
    /**
     * 根据文章id查询文章评论数
     *
     * @param articleId
     * @return
     */
    List<Comment> findByArticleid(String articleId);

    //根据发布时间和点赞数查询
//    List<Comment> findByPushAndPublishdateAndThumbup(Date date, String thumbup);
}
