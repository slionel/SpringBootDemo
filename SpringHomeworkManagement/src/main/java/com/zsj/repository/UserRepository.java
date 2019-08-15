package com.zsj.repository;

import com.zsj.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author zsj55
 */
public interface UserRepository extends CrudRepository<User, String> {
    /**通过用户名及密码登录
     * @param name
     * @param pwd
     * @return
     */
    public User findByNameAndPwd(String name, String pwd);

    /**通过名字查找status
     * @param name
     * @return
     */
    public User findByName(String name);
}
