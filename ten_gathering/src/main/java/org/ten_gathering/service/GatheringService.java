package org.ten_gathering.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ten_common.util.IdWorker;
import org.ten_gathering.dao.GatheringDao;
import org.ten_gathering.pojo.Gathering;

@Transactional
@Service
public class GatheringService {

	@Autowired
	private GatheringDao gatheringDao;
	@Autowired
	private IdWorker idWorker;

	@Autowired
	private RedisTemplate redisTemplate;

	@Cacheable(value="gathering",key="#id")
	public Gathering findById(String id) {
		// 从缓存中提取
		Gathering Gathering = (Gathering) redisTemplate.opsForValue().get("gathering_" + id);
		// 如果缓存没有则到数据库查询并放入缓存
		if (Gathering == null) {
			Gathering = gatheringDao.findById(id).get();
			redisTemplate.opsForValue().set("Gathering_" + id, Gathering, 1, TimeUnit.DAYS);
		}
		return Gathering;
	}

	/**
	 * 查询全部标签
	 * 
	 * @return
	 */
	public List<Gathering> findAll() {
		return gatheringDao.findAll();
	}

	/**
	 * 根据ID查询标签
	 * 
	 * @return
	 */
	// public Article findById(String id) {
	// return articleDao.findById(id).get();
	// }

	public void add(Gathering gathering ) {
		gathering.setId(idWorker.nextId() + "");// 设置ID
		gatheringDao.save(gathering);
	}

	/**
	 * 修改标签
	 * 
	 * @param label
	 */
	@CacheEvict(value="gathering",key="#gathering.id")
	public void update(Gathering gathering) {
		redisTemplate.delete("gathering_" + gathering.getId());// 删除缓存
		gatheringDao.save(gathering);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@CacheEvict(value="gathering",key="#id")
	public void deleteById(String id) {
		redisTemplate.delete("Gathering_" + id);// 删除缓存
		gatheringDao.deleteById(id);
	}

	/***
	 * 带条件的查询
	 * 
	 * @param searchMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Gathering> findSearch(Map searchMap) {

		Specification specification = createSpecification(searchMap);
		return gatheringDao.findAll(specification);
	}

	/**
	 * 分页条件查询
	 * 
	 * @param searchMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Gathering> findSearch(Map searchMap, int page, int size) {
		Specification specification = createSpecification(searchMap);
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return gatheringDao.findAll(specification, pageRequest);
	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private Specification<Gathering> createSpecification(Map searchMap) {
		return new Specification<Gathering>() {
			@Override
			public Predicate toPredicate(Root<Gathering> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
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
