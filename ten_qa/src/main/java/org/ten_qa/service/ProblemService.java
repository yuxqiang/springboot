package org.ten_qa.service;

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
import org.ten_common.util.IdWorker;
import org.ten_qa.dao.ProblemDao;
import org.ten_qa.pojo.Problem;

@Service
public class ProblemService {

	@Autowired
	private ProblemDao problemDao;
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部标签
	 * 
	 * @return
	 */
	public List<Problem> findAll() {
		return problemDao.findAll();
	}

	/**
	 * 根据ID查询标签
	 * 
	 * @return
	 */
	public Problem findById(String id) {
		return problemDao.findById(id).get();
	}

	public void add(Problem problem) {
		problem.setId(idWorker.nextId() + "");// 设置ID
		problemDao.save(problem);
	}

	/**
	 * 修改标签
	 * 
	 * @param label
	 */
	public void update(Problem problem) {
		problemDao.save(problem);
	}

	/**
	 * 删除标签
	 * 
	 * @param id
	 */
	public void deleteById(String id) {
		problemDao.deleteById(id);
	}

	/***
	 * 带条件的查询
	 * 
	 * @param searchMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Problem> findSearch(Map searchMap) {

		Specification specification = createSpecification(searchMap);
		return problemDao.findAll(specification);
	}

	/**
	 * 分页条件查询
	 * 
	 * @param searchMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Problem> findSearch(Map searchMap, int page, int size) {
		Specification specification = createSpecification(searchMap);
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return problemDao.findAll(specification, pageRequest);
	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private Specification<Problem> createSpecification(Map searchMap) {
		return new Specification<Problem>() {
			@Override
			public Predicate toPredicate(Root<Problem> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
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
	 * 根据标签ID查询问题列表
	 * 
	 * @param lableId 标签ID
	 * @param page 页码
	 * @param size 页大小
	 * @return
	 */
	public Page<Problem> findNewListByLabelId(String lableId, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return problemDao.findNewListByLabelId(lableId, pageRequest);
	}

	/**
	 * 根据标签ID查询热门问题列表
	 * 
	 * @param lableId 标签ID
	 * @param page 页码
	 * @param size 页大小
	 * @return
	 */
	public Page<Problem> findHotListByLabelId(String lableId, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return problemDao.findHotListByLabelId(lableId, pageRequest);
	}

	/**
	 * 根据标签ID查询等待回答列表
	 * 
	 * @param lableId 标签ID
	 * @param page 页码
	 * @param size 页大小
	 * @return
	 */
	public Page<Problem> findWaitListByLabelId(String lableId, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return problemDao.findWaitListByLabelId(lableId, pageRequest);
	}
}
