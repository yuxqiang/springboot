package org.ten_article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ten_article.pojo.Comment;
import org.ten_article.service.CommentService;
import org.ten_common.entity.Result;
import org.ten_common.entity.StatusCode;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody Comment comment) {
		commentService.add(comment);
		return new Result(true, StatusCode.OK, "提交成功 ");
	}

	@RequestMapping(value = "/article/{articleid}", method = RequestMethod.GET)
	public Result findByArticleid(@PathVariable String articleid) {
		return new Result(true, StatusCode.OK, "查询成功", commentService.findByArticleid(articleid));
	}
}