package org.ten_recruit.service;

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
import org.ten_recruit.dao.RecruitDao;
import org.ten_recruit.pojo.Recruit;

@Service
public class RecruitService {

	@Autowired
	private RecruitDao recruitDao;
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部标签
	 * 
	 * @return
	 */
	public List<Recruit> findAll() {
		return recruitDao.findAll();
	}

	/**
	 * 根据ID查询标签
	 * 
	 * @return
	 */
	public Recruit findById(String id) {
		return recruitDao.findById(id).get();
	}

	public void add(Recruit recruit) {
		recruit.setId(idWorker.nextId() + "");// 设置ID
		recruitDao.save(recruit);
	}

	/**
	 * 修改标签
	 * 
	 * @param label
	 */
	public void update(Recruit recruit) {
		recruitDao.save(recruit);
	}

	/**
	 * 删除标签
	 * 
	 * @param id
	 */
	public void deleteById(String id) {
		recruitDao.deleteById(id);

	}

	/***
	 * 带条件的查询
	 * 
	 * @param searchMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Recruit> findSearch(Map searchMap) {

		Specification specification = createSpecification(searchMap);
		return recruitDao.findAll(specification);
	}

	/**
	 * 分页条件查询
	 * 
	 * @param searchMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Recruit> findSearch(Map searchMap, int page, int size) {
		Specification specification = createSpecification(searchMap);
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return recruitDao.findAll(specification, pageRequest);
	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private Specification<Recruit> createSpecification(Map searchMap) {
		return new Specification<Recruit>() {
			@Override
			public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
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
	 * 根据状态查询
	 * 
	 * @param state
	 * @return
	 */
	public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state) {
		return recruitDao.findTop4ByStateOrderByCreatetimeDesc(state);
	}

	/**
	 * 最新职位列表
	 * 
	 * @return
	 */
	public List<Recruit> newlist() {
		return recruitDao.findTop12ByStateNotOrderByCreatetimeDesc("0");
	}
}
