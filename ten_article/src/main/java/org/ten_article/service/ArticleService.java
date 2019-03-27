package org.ten_article.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ten_article.dao.ArticleDao;
import org.ten_article.pojo.Article;
import org.ten_common.util.IdWorker;

@Transactional
@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部标签
	 * 
	 * @return
	 */
	public List<Article> findAll() {
		return articleDao.findAll();
	}

	/**
	 * 根据ID查询标签
	 * 
	 * @return
	 */
	public Article findById(String id) {
		return articleDao.findById(id).get();
	}

	public void add(Article label) {
		label.setId(idWorker.nextId() + "");// 设置ID
		articleDao.save(label);
	}

	/**
	 * 修改标签
	 * 
	 * @param label
	 */
	public void update(Article label) {
		articleDao.save(label);
	}

	/**
	 * 删除标签
	 * 
	 * @param id
	 */
	public void deleteById(String id) {
		articleDao.deleteById(id);
	}

	/***
	 * 带条件的查询
	 * 
	 * @param searchMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Article> findSearch(Map searchMap) {

		Specification specification = createSpecification(searchMap);
		return articleDao.findAll(specification);
	}

	/**
	 * 分页条件查询
	 * 
	 * @param searchMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Article> findSearch(Map searchMap, int page, int size) {
		Specification specification = createSpecification(searchMap);
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return articleDao.findAll(specification, pageRequest);
	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private Specification<Article> createSpecification(Map searchMap) {
		return new Specification<Article>() {
			@Override
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				if (searchMap.get("labelname") != null && !"".equals(searchMap.get("labelname"))) {
					predicateList.add(cb.like(root.get("labelname").as(String.class),
							"%" + (String) searchMap.get("labelname") + "%"));
				}
				if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
					predicateList.add(cb.equal(root.get("state").as(String.class), (String) searchMap.get("state")));
				}
				if (searchMap.get("recommend") != null && !"".equals(searchMap.get("recommend"))) {
					predicateList
							.add(cb.equal(root.get("recommend").as(String.class), (String) searchMap.get("recommend")));
				}
				return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

	/**
	 * 文章审核
	 * 
	 * @param id
	 */
	@Transactional
	public void examine(String id) {
		articleDao.examine(id);
	}

	/**
	 * 点赞
	 * 
	 * @param id 文章ID
	 * @return
	 */
	@Transactional
	public int updateThumbup(String id) {
		return articleDao.updateThumbup(id);
	}
}
