package jp.co.amiactive.mapper.kks01;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.amiactive.dto.LoginUserData;
import jp.co.amiactive.dto.PersonnelInfo;

@Mapper
public interface PersonnelInfoMapper {
    
    LoginUserData search(@Param("employeeCd")String employeeCd);
    
    PersonnelInfo empSerch(String employeeCd);

}
