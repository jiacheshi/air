package cn.sdyk.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;

@Component
public interface DeviceDataMapper {

    @Update("UPDATE devicedata SET data1=#{data},finallyTime=#{time} WHERE devicename=#{devicename}")
    int updateDate1(@Param("devicename")String devicename,@Param("data")String data1,@Param("time")Date finallyTime);

    @Update("UPDATE devicedata SET data2=#{data},finallyTime=#{time} WHERE devicename=#{devicename}")
    int updateDate2(@Param("devicename")String devicename,@Param("data")String data2,@Param("time")Date finallyTime);

    @Select("select data1,data2 from devicedata where mac=#{mac}")
    String StrData(@Param("mac") String mac);

    /**
     * 根据mac找时间
     * @param mac
     * @return
     */
    @Select("SELECT finallyTime FROM devicedata WHERE mac=#{mac}")
    String StrTime(String mac);



}
