package jp.co.amiactive.service.kks05;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;
import jp.co.amiactive.dto.KKS05SC004_F_001Model;
import jp.co.amiactive.dto.LoginUserManagement;
import jp.co.amiactive.mapper.kks05.LoginUserManagementMapper;

@Service
public class KKS05SC004_F_001LoginSer {
    private static final String LOGIN_ERROR_CODE = "KKS05SC004_E003";
    private static final String LOGIN_ERROR_MESSAGE = "ユーザーIDかパスワードに誤りがあります";
    private static final int LOGIN_ERROR_CODE_VALUE = 3;

    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private HttpSession session;
    
    @Autowired
    private LoginUserManagementMapper loginUserManagementMapper;
    
    public boolean validateInput(KKS05SC004_F_001Model model, BindingResult result) {
        boolean hasError = false;
        
        // ユーザーID検証
        String userId = model.getUserId();
        if (!userId.matches("^[A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]*$")) {
            String errorMessage = messageSource.getMessage("KKS05SC004_E001", 
                    new Object[] {"ユーザーID", "半角英数字記号"}, LocaleContextHolder.getLocale());
            result.rejectValue("userId", "KKS05SC004_E001", errorMessage);
            model.setErrorCode(1);
            hasError = true;
        }
        
        if (userId.length() > 256) {
            String errorMessage = messageSource.getMessage("KKS05SC004_E002", 
                    new Object[] {"ユーザーID", "256"}, LocaleContextHolder.getLocale());
            result.rejectValue("userId", "KKS05SC004_E002", errorMessage);
            model.setErrorCode(1);
            hasError = true;
        }
        
        // パスワード検証
        String password = model.getPassword();
        if (!password.matches("^[A-Za-z0-9@$!&\\\\]*$")) {
            String errorMessage = messageSource.getMessage("KKS05SC004_E001", 
                    new Object[] {"パスワード", "半角英数字記号"}, LocaleContextHolder.getLocale());
            result.rejectValue("password", "KKS05SC004_E001", errorMessage);
            model.setErrorCode(2);
            hasError = true;
        }
        
        if (password.length() > 256) {
            String errorMessage = messageSource.getMessage("KKS05SC004_E002", 
                    new Object[] {"パスワード", "256"}, LocaleContextHolder.getLocale());
            result.rejectValue("password", "KKS05SC004_E002", errorMessage);
            model.setErrorCode(2);
            hasError = true;
        }
        
        // 確認
        System.out.println(userId);
        System.out.println(password);
        
        return !hasError;
    }
    
    public KKS05SC004_F_001Model authenticateUser(KKS05SC004_F_001Model model, BindingResult result) {
        if (!validateInput(model, result)) {
            return null;
        }
        
        String userId = model.getUserId();
        String password = model.getPassword();
        
        System.out.println("get" + userId);
        System.out.println("get" + password);
        
        List<LoginUserManagement> loginUserManagementList = loginUserManagementMapper.search(userId, password);
        
        KKS05SC004_F_001Model resultModel = new KKS05SC004_F_001Model();
        
        if (loginUserManagementList.size() == 1) {
            LoginUserManagement loginUserData = loginUserManagementList.get(0);
            
            System.out.println("get" + loginUserManagementList);
            System.out.println("get" + loginUserData);
            
            resultModel.setUserId(loginUserData.getUserId());
            resultModel.setPassword(loginUserData.getPassword());
            resultModel.setHighestAuthority(loginUserData.getHighestAuthority());
            resultModel.setEmployeeCd(loginUserData.getEmployeeCd());
            
           System.out.println(resultModel.getEmployeeCd());
           
            session.setAttribute("loginUser", resultModel);
            return resultModel;
            
        } else {
            
            result.reject(LOGIN_ERROR_CODE, LOGIN_ERROR_MESSAGE);
            resultModel.setErrorCode(LOGIN_ERROR_CODE_VALUE);
            
            return resultModel;
        }
    }
}
