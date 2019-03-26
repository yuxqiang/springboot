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
import org.ten_qa.dao.PLDao;
import org.ten_qa.pojo.Pl;

@Service
public class PLService {

	@Autowired
	private PLDao pLDao;
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部标签
	 * 
	 * @return
	 */
	public List<Pl> findAll() {
		return pLDao.findAll();
	}

	/**
	 * 根据ID查询标签
	 * 
	 * @return
	 */
	public Pl findById(String id) {
		return pLDao.findById(id).get();
	}

	/*
	 * public void add(Pl pl) { pl.setId(idWorker.nextId() + "");// 设置ID
	 * pLDao.save(pl); }
	 */
	/**
	 * 修改标签
	 * 
	 * @param label
	 */
	public void update(Pl pl) {
		pLDao.save(pl);
	}

	/**
	 * 删除标签
	 * 
	 * @param id
	 */
	public void deleteById(String id) {
		pLDao.deleteById(id);
	}

	/***
	 * 带条件的查询
	 * 
	 * @param searchMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Pl> findSearch(Map searchMap) {

		Specification specification = createSpecification(searchMap);
		return pLDao.findAll(specification);
	}

	/**
	 * 分页条件查询
	 * 
	 * @param searchMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Pl> findSearch(Map searchMap, int page, int size) {
		Specification specification = createSpecification(searchMap);
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return pLDao.findAll(specification, pageRequest);
	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private Specification<Pl> createSpecification(Map searchMap) {
		return new Specification<Pl>() {
			@Override
			public Predicate toPredicate(Root<Pl> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
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
