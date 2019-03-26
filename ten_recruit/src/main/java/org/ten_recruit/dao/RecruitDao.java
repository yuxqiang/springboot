package org.ten_recruit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.ten_recruit.pojo.Recruit;

/**
 * 标签数据访问接口
 */
@Repository
public interface RecruitDao extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {

	/**
	 * 查询最新职位列表(按创建日期降序排序)
	 * 
	 * @return
	 */
	public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);

	/**
	 * 最新职位列表
	 * 
	 * @param state
	 * @return
	 */
	public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}
