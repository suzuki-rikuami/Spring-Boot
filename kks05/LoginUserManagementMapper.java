package jp.co.amiactive.mapper.kks05;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.amiactive.dto.LoginUserManagement;

@Mapper
public interface LoginUserManagementMapper {
    
    //ユーザー検索メソッド
    List<LoginUserManagement> search(@Param("userId")String userId, @Param("password")String password);
    
    //ユーザーの登録メソッド
    int insert(LoginUserManagement loginUserManagement);
}
