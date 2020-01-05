package com.tensquare.article.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author JinLu
 * @date 2020/1/5 19:54
 */
@RestController
@RequestMapping("article")
@CrossOrigin
public class ArticleController {
    @Autowired
    private ArticleService articleService;


    /**
     * 测试公共异常处理
     *
     * @return
     */
    @RequestMapping(value = "exception", method = RequestMethod.GET)
    public Result test() {
        int a = 1 / 0;

        return null;
    }

    /**
     * POST /article/search/{page}/{size}
     * 文章分页
     *
     * @param page
     * @param size
     * @param map
     * @return
     */
    @RequestMapping(value = "search/{page}/{size}", method = RequestMethod.POST)
    /**
     * 之前接受文章数据，使用pojo，但是现在根据条件查询
     * 而所有的条件都需要进行判断，遍历pojo的所有属性需要使用反射的方式，成本较高，性能较低
     * 直接使用集合的方式遍历，这里接受数据改为Map集合
     */
    public Result findByPage(@PathVariable Integer page,
                             @PathVariable Integer size,
                             @RequestBody Map<String, Object> map) {
        //根据条件分页查询
        Page<Article> pageData = articleService.findByPage(map, page, size);

        //封装分页返回对象
        PageResult<Article> pageResult = new PageResult<>(
                pageData.getTotal(), pageData.getRecords()
        );

        //返回数据
        return new Result(true, StatusCode.OK, "查询成功", pageResult);

    }

    /**
     * Delete /article/{articleId}
     * 删除文章
     *
     * @param articleId
     * @return
     */
    @RequestMapping(value = "{articleId}", method = RequestMethod.DELETE)
    public Result updateById(@PathVariable String articleId) {
        articleService.deleteById(articleId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * Put /article/{articleId}
     * 修改文章
     *
     * @param articleId
     * @return
     */
    @RequestMapping(value = "{articleId}", method = RequestMethod.PUT)
    public Result updateById(@PathVariable String articleId,
                             @RequestBody Article article) {
        //设置Id
        article.setId(articleId);

        //执行修改
        articleService.updateById(article);
        return new Result(true, StatusCode.OK, "修改成功", article);
    }

    /**
     * Post/article
     * 增加文章
     *
     * @param article
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article) {
        articleService.save(article);
        return new Result(true, StatusCode.OK, "新增成功");
    }

    /**
     * Get/article/{articleId}
     * 根据Id查询文章
     *
     * @param articleId
     * @return
     */
    @RequestMapping(value = "{articleId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String articleId) {
        Article article = articleService.findById(articleId);
        return new Result(true, StatusCode.OK, "查询成功", article);
    }

    /**
     * Get/article
     * 文章全部列表
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result finaAll() {
        List<Article> list = articleService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", list);
    }
}
