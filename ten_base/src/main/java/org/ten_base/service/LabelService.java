package org.ten_base.service;

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
import org.ten_base.dao.LabelDao;
import org.ten_base.pojo.Label;
import org.ten_common.util.IdWorker;

@Service
public class LabelService {

	@Autowired
	private LabelDao labelDao;
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部标签
	 * 
	 * @return
	 */
	public List<Label> findAll() {
		return labelDao.findAll();
	}

	/**
	 * 根据ID查询标签
	 * 
	 * @return
	 */
	public Label findById(String id) {
		return labelDao.findById(id).get();
	}

	public void add(Label label) {
		label.setId(idWorker.nextId() + "");// 设置ID
		labelDao.save(label);
	}

	/**
	 * 修改标签
	 * 
	 * @param label
	 */
	public void update(Label label) {
		labelDao.save(label);
	}

	/**
	 * 删除标签
	 * 
	 * @param id
	 */
	public void deleteById(String id) {
		labelDao.deleteById(id);
	}

	/***
	 * 带条件的查询
	 * 
	 * @param searchMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Label> findSearch(Map searchMap) {

		Specification specification = createSpecification(searchMap);
		return labelDao.findAll(specification);
	}

	/**
	 * 分页条件查询
	 * 
	 * @param searchMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Label> findSearch(Map searchMap, int page, int size) {
		Specification specification = createSpecification(searchMap);
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return labelDao.findAll(specification, pageRequest);
	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private Specification<Label> createSpecification(Map searchMap) {
		return new Specification<Label>() {
			@Override
			public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
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

}
