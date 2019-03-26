package org.ten_qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ten_qa.pojo.Problem;

/**
 * 标签数据访问接口
 */
@Repository
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {

	@Query("select p from Problem p where id in( select problemid from Pl	where labelid=?1 )	order by replytime desc")
	public Page<Problem> findNewListByLabelId(String labelId, Pageable pageable);
}
