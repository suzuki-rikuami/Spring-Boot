package jp.co.amiactive.service.kks05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;
import jp.co.amiactive.dto.KKS05SC003_F_001Model;
import jp.co.amiactive.dto.LoginUserManagement;
import jp.co.amiactive.dto.PersonnelInfo;
import jp.co.amiactive.mapper.kks01.PersonnelInfoMapper;
import jp.co.amiactive.mapper.kks05.LoginUserManagementMapper;

@Service
public class KKS05SC003_F_001InsertSer {
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private PersonnelInfoMapper personnelInfoMapper;
    
    @Autowired
    private LoginUserManagementMapper loginUserManagementMapper;

    public Integer validateInsertUser(KKS05SC003_F_001Model model, BindingResult result) {
        boolean hasError = false;
        
        //社員番号検証
        String employeeCd = model.getEmployeeCd();
        if (employeeCd == null || !employeeCd.matches("\\d{9}")) {
            String errorMessage = messageSource.getMessage("KKS05SC003_E001", 
                    new Object[] {"社員番号", "桁数9桁の半角数字"}, LocaleContextHolder.getLocale());
            result.rejectValue("employeeCd", "KKS05SC003_E001", errorMessage);
            model.setErrorCode(1);
            hasError = true;
        }
        System.out.println(employeeCd);
        
        //ユーザーID検証
        String userId = model.getUserId();
        if (userId == null || !userId.matches("^[A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{1,256}$")) {
            String errorMessage = messageSource.getMessage("KKS05SC003_E001", 
                    new Object[] {"ユーザーID", "桁数256桁以内の半角英数字記号"}, LocaleContextHolder.getLocale());
            result.rejectValue("userId", "KKS05SC003_E001", errorMessage);
            model.setErrorCode(2);
            hasError = true;
        }
        
        // パスワード検証
        String password = model.getPassword();
        if (password == null || !password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!&\\\\]).{8,256}$")) {
            String errorMessage = messageSource.getMessage("KKS05SC003_E006", 
                    new Object[] {}, LocaleContextHolder.getLocale());
            result.rejectValue("password", "KKS05SC003_E006", errorMessage);
            model.setErrorCode(3);
            hasError = true;
        }
        
        //パスワード確認検証
        String confirmPassword = model.getConfirmPassword();
        if (!password.equals(confirmPassword)) {
            String errorMessage = messageSource.getMessage("KKS05SC003_E002", 
                    new Object[] {}, LocaleContextHolder.getLocale());
            result.rejectValue("confirmPassword", "KKS05SC003_E002", errorMessage);
            model.setErrorCode(4);
            hasError = true;
        }
        
        // 確認
        System.out.println(employeeCd);
        System.out.println(userId);
        System.out.println(password);
        
        if(hasError) {
            return null;
        }
        
        //社員番号の存在の確認
        PersonnelInfo personnelInfo = personnelInfoMapper.empSerch(employeeCd);
        if(personnelInfo == null) {
            //社員番号が存在しない場合,テーブルDTOにデータを設定
            LoginUserManagement loginUserManagement = new LoginUserManagement();
            loginUserManagement.setEmployeeCd(model.getEmployeeCd());
            loginUserManagement.setUserId(model.getUserId());
            loginUserManagement.setPassword(model.getPassword());

            System.out.println(loginUserManagement);
            
            Integer resultCount = loginUserManagementMapper.insert(loginUserManagement);
            if (resultCount == 1) {
                //登録成功
                return resultCount;
            } else {
                //登録失敗（既に登録済みの場合）
                String errorMessage = messageSource.getMessage("KKS05SC003_E004", 
                        new Object[] {}, LocaleContextHolder.getLocale());
                result.rejectValue("userId", "KKS05SC003_E004", errorMessage);
                model.setErrorCode(5);
                return null;
            }
        } else {
            // 社員番号が存在する場合
            String errorMessage = messageSource.getMessage("KKS05SC003_E003",
                    new Object[] {}, LocaleContextHolder.getLocale());
            result.rejectValue("employeeCd", "KKS05SC003_E003", errorMessage);
            model.setErrorCode(1);
            return null;
        }
    }
}