package org.ten_article.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.ten_article.pojo.Comment;

/**
 * 标签数据访问接口
 */
@Repository
public interface CommentDao extends JpaRepository<Comment, String>, JpaSpecificationExecutor<Comment> {

	/**
	 * 根据文章ID查询评论列表
	 * 
	 * @param articleid
	 * @return
	 */
	public List<Comment> findByArticleid(String articleid);
}
