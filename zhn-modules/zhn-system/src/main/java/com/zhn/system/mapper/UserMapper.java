package com.zhn.system.mapper;


import com.zhn.common.domain.User;
import com.zhn.common.domain.request.BookOrder;
import com.zhn.common.domain.request.RequestUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserMapper {
    User byphone(@Param("phone") String phone);

    int updatemenoy(RequestUser requestUser);

    int placeanorder(BookOrder bookOrder);

}
