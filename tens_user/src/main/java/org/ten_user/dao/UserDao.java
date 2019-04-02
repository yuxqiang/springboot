package org.ten_user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.ten_user.pojo.User;

/**
 * 标签数据访问接口
 */
@Repository
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

}
