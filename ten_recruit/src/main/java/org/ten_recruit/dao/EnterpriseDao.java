package org.ten_recruit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.ten_recruit.pojo.Enterprise;

/**
 * 标签数据访问接口
 */
@Repository
public interface EnterpriseDao extends JpaRepository<Enterprise, String>, JpaSpecificationExecutor<Enterprise> {

	/**
	 * 根据热门状态获取企业列表
	 * 
	 * @param ishot
	 * @return
	 */
	public List<Enterprise> findByIshot(String ishot);
}
