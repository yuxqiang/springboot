package org.ten_spit.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.ten_spit.pojo.Spit;

/**
* 吐槽数据访问层
* @author Administrator
*
*/
public interface SpitDao extends MongoRepository<Spit, String>{
	
	/**
	* 根据上级ID查询吐槽列表（分页）
	* @param parentid
	* @param pageable
	* @return
	*/
	public Page<Spit> findByParentid(String parentid,Pageable pageable);
}
