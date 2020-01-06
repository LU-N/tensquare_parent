package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JinLu
 * @date 2020/1/6 16:56
 */
@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * PUT /thumbup/{commentId}
     * 根据文章id点赞评论
     *
     * @param commentId
     * @return
     */
    @RequestMapping(value = "thumbup/{commentId}", method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String commentId) {
        //把用户点赞信息保存到Redis中
        //每次点赞之前，先查询用户点赞信息
        //如果没有点赞信息，用户可以点赞
        //如果有点赞信息，用户不能重复点赞

        //模拟用户id
        String userId = "123";

        //查询用户点赞信息，根据用户id和评论id
        Object flag = redisTemplate.opsForValue().get("thumbup_" + userId + "_" + commentId);

        //判断查询到的结果是否为空
        if (flag == null) {
            //如果为空，表示用户没有点过赞，可以点赞
            commentService.thumbup(commentId);

            //点赞成功，保存点赞信息
            redisTemplate.opsForValue().set("thumbup_" + userId + "_" + commentId, 1);

            return new Result(true, StatusCode.OK, "点赞成功");
        }

        //如果不为空，表示用户点过赞，不可以重复点赞
        return new Result(false, StatusCode.REPERROR, "不能重复点赞");

    }

    /**
     * GET /article/{commentId}
     * 根据id查询文章评论数量
     *
     * @param commentId
     * @return
     */
    @RequestMapping(value = "article/commentId", method = RequestMethod.GET)
    public Result findByArticleId(@PathVariable String commentId) {
        List<Comment> list = commentService.findByArticleId(commentId);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * DELETE /comment/{commentId}
     *
     * @param commentId
     * @return
     */
    @RequestMapping(value = "{commentId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String commentId) {
        commentService.deleteById(commentId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * PUT /{commentId}
     * 修改评论
     *
     * @param commentId
     * @param comment
     * @return
     */
    @RequestMapping(value = "{commentId}", method = RequestMethod.PUT)
    public Result updateById(@PathVariable String commentId, @RequestBody Comment comment) {
        comment.set_id(commentId);
        commentService.updateById(comment);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * POST /comment
     * 添加评论
     *
     * @param comment
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Comment comment) {
        commentService.save(comment);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * GET /{commentId}
     * 根据id查询评论
     *
     * @param commentId
     * @return
     */
    @RequestMapping(value = "{commentId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String commentId) {
        Comment comment = commentService.findById(commentId);
        return new Result(true, StatusCode.OK, "查询成功", comment);
    }

    /**
     * GET /comment
     * 查询所有评论
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Comment> list = commentService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", list);
    }
}
