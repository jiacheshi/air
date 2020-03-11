package cn.sdyk.mapper;

import cn.sdyk.pojo.Device;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface DeviceMapper {
    /**
     * 根据mac值查询设备信息
     * @param mac
     * @return
     */
    @Select("SELECT * FROM device WHERE mac=#{mac}")
    Device selByMac(@Param("mac")String mac);

    /**
     * 根据mac和管理员密码查询设备信息
     * @param mac
     * @param adminpwd
     * @return
     */
    @Select("SELECT * FROM device WHERE mac=#{mac} AND adminpwd=#{adminpwd}")
    Device selByMacPwd(String mac,String adminpwd);

    /**
     * 向设备中添加用户
     * @param uname
     */
    @Update("UPDATE device SET binding=CONCAT(binding,#{name}) WHERE mac=#{mac}")
    void deviceBinding(@Param("name") String uname,@Param("mac") String mac);

    /**
     * 删除设备绑定的用户
     * @param uname
     * @param mac
     * @return
     */
    @Update("UPDATE device SET binding = REPLACE(binding,#{name},'') WHERE mac = #{mac}")
    int delForDevice(@Param("name") String uname,@Param("mac") String mac);

    /**
     * 根据mac修改昵称
     * @param nickname
     * @param mac
     */
    @Update("UPDATE device SET nickname=#{nickname} WHERE mac=#{mac}")
    void updByMac(@Param("nickname") String nickname,@Param("mac") String mac);

}

