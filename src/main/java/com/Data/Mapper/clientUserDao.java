package com.Data.Mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.Object.clientUser;

public interface clientUserDao {
	@Select("SELECT username,telphone,province,city,area,address,level,grades "
			+ "FROM clientuser WHERE telphone = #{telphone} AND password = #{password}")
	public clientUser userLoginByTelPass(@Param("telphone") String telphone,@Param("password") String password);
	
	@Select("SELECT username,telphone,province,city,area,address,level,grades "
			+ "FROM clientuser WHERE telphone = #{telphone}")
	public clientUser userLoginByTelCode(@Param("telphone") String telphone);

}
