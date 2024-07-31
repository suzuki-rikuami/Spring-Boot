package jp.co.amiactive.controller.kks01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.amiactive.dto.KKS05SC004_F_001Model;
import jp.co.amiactive.dto.LoginUserData;
import jp.co.amiactive.service.kks01.KKS05SC001_F_001MenuSer;

@Controller
public class KKS05SC001_F_001Con {
    
    @Autowired
    private KKS05SC001_F_001MenuSer menuService;
    
    @GetMapping(value = {"/menu"})
    public String showMenu(HttpSession session, Model model) {
        
        //セッション情報の取得
        String loginInfo = (String) session.getAttribute("login_Info");
        KKS05SC004_F_001Model loginUser = (KKS05SC004_F_001Model) session.getAttribute("loginUser");
        
        System.out.println(loginInfo);
        
        if(loginInfo == null) {
            if(loginUser != null) {
                if(loginUser.getEmployeeCd() == null) {
                    session.setAttribute("login_info", "-/SU");
                } else {
                    LoginUserData updatedUser =  menuService.search(loginUser.getEmployeeCd());
                    if(updatedUser != null) {
                        System.out.println(updatedUser);
                        
                        // Modelにユーザーデータを追加
                        model.addAttribute("employeeCd", updatedUser.getEmployeeCd());
                        model.addAttribute("lastName", updatedUser.getEmployeesLastName());
                        model.addAttribute("firstName", updatedUser.getEmployeesFirstName());
                        // 値の編集
                        // loginUserData内の値を文字連結
                        session.setAttribute("login_info", updatedUser.getEmployeeCd() + " / " 
                                + updatedUser.getEmployeesLastName() + " "
                                + updatedUser.getEmployeesFirstName());
                    }
                }
            }
        }
        

        //ModelにloginInfoを追加
        model.addAttribute("loginInfo", loginInfo);

        return "menu";
    }
    

}
