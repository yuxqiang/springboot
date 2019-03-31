package org.ten_gathering.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.ten_gathering.pojo.Gathering;

/**
 * 标签数据访问接口
 */
@Repository
public interface GatheringDao extends JpaRepository<Gathering, String>, JpaSpecificationExecutor<Gathering> {

	/**
	 * 审核
	 * 
	 * @param id
	 *//*
	@Modifying
	@Query("update Article set state='1' where id=?1")
	public void examine(String id);

	*//**
	 * 点赞
	 * 
	 * @param id
	 * @return
	 *//*
	@Modifying
	@Query("update Article a set thumbup=thumbup+1 where id=?1")
	public int updateThumbup(String id);*/
}
