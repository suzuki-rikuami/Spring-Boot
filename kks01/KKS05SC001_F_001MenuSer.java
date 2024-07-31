package jp.co.amiactive.service.kks01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jp.co.amiactive.dto.LoginUserData;
import jp.co.amiactive.mapper.kks01.PersonnelInfoMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class KKS05SC001_F_001MenuSer {

    @Autowired
    private PersonnelInfoMapper personnelInfoMapper;

    @Autowired
    private HttpSession session;
    
    
    public LoginUserData search(String employeeCd) {
        LoginUserData loginUserData = new LoginUserData();
        // テーブル検索
        LoginUserData personnelInfo = personnelInfoMapper.search(employeeCd);
        

        System.out.println(personnelInfo);
        System.out.println(loginUserData);

        //検索結果確認
        if (personnelInfo != null) {
            //検索結果が取得できた場合
            loginUserData.setEmployeeCd(personnelInfo.getEmployeeCd());
            loginUserData.setEmployeesLastName(personnelInfo.getEmployeesLastName());
            loginUserData.setEmployeesFirstName(personnelInfo.getEmployeesFirstName());
            
            //確認
            System.out.println(loginUserData.getEmployeeCd());
            System.out.println(loginUserData.getEmployeesLastName());
            System.out.println(loginUserData.getEmployeesFirstName());
            //sessionに値を設定
            session.setAttribute("loginUser", loginUserData);
            
        }
        return loginUserData;
    }
}