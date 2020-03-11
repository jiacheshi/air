package cn.sdyk.mapper;

import cn.sdyk.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
//    @Select("select * from user where id = #{id}")
//    User queryUserById(@Param(value = "id") int id);

    /**
     * 登录功能
     * @param uname
     * @param upass
     * @return 一个用户
     */
    @Select("select * from user where uname = #{name} and upass=#{pass}")
    User login(@Param("name")String uname,@Param("pass")String upass);

    /**
     *注册功能
     * @param user 参数为一个用户
     * @return 返回受影响的行数
     */
    @Insert("INSERT INTO user VALUES (null,#{uname},#{upass},#{phonenumber},#{qq},#{wechat},#{email},#{mac})")
    int register(User user);

    /**
     * 用户中添加设备操作
     * @param mac
     */
    @Update("UPDATE user SET mac=CONCAT(mac,#{mac}) WHERE uname=#{name}")
    void usersBinding(@Param("mac") String mac,@Param("name") String uname);

    /**
     * 查询用户名是否重复
     */
    @Select("SELECT id FROM USER WHERE uname=#{name}")
    String hintByUname(@Param("name") String uname);

    /**
     * 根据uname查询用户对象
     * @param uname
     * @return
     */
    @Select("SELECT * FROM USER WHERE uname=#{name}")
    User getUserByUname(@Param("name") String uname);

    /**
     * 删除用户表中绑定的指定设备
     * @param uname
     * @param mac
     * @return
     */
    @Update("UPDATE USER SET mac = REPLACE(mac,#{mac},'') WHERE uname = #{name}")
    int delForUser(@Param("name") String uname,@Param("mac") String mac);


}
