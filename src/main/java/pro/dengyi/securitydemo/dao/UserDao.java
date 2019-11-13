package pro.dengyi.securitydemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import pro.dengyi.securitydemo.model.User;

/**
 * @author 邓艺
 * @version v1.0
 * @Title
 * @Description
 * @date 2019/11/13 12:52
 **/
@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {
}
