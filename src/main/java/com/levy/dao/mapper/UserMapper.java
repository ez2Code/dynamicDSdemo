package com.levy.dao.mapper;

import com.levy.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by li_weia on 2017/6/30.
 */
public interface UserMapper {
    List<User> getAllSite();
    User getUserById(@Param("id") int id);
}
