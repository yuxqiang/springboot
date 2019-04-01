package org.ten_article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ten_article.dao.CommentDao;
import org.ten_article.pojo.Comment;
import org.ten_common.util.IdWorker;

@Service
public class CommentService {
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private IdWorker idWorker;

	public void add(Comment comment) {
		comment.set_id(idWorker.nextId() + "");
		commentDao.save(comment);
	}

	public List<Comment> findByArticleid(String articleid) {
		return commentDao.findByArticleid(articleid);
	}
}
