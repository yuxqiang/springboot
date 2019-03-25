package org.ten_base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.ten_base.pojo.Label;

/**
 * 标签数据访问接口
 */
@Repository
public interface LabelDao extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label> {
}
