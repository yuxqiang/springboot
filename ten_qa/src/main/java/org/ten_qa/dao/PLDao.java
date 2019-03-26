package org.ten_qa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.ten_qa.pojo.Pl;

/**
 * 标签数据访问接口
 */
@Repository
public interface PLDao extends JpaRepository<Pl, String>, JpaSpecificationExecutor<Pl> {
}
