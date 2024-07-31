package jp.co.amiactive.controller.kks05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.amiactive.dto.KKS05SC003_F_001Model;
import jp.co.amiactive.service.kks05.KKS05SC003_F_001InsertSer;

@Controller
public class KKS05SC003_F_001Con {
    
    @Autowired
    private KKS05SC003_F_001InsertSer kks05sc003_F_001InsertSer;
    
    @GetMapping("/insert_user")
    public String registerForm(@ModelAttribute("kks05sc003_F_001Model") KKS05SC003_F_001Model model) {
        
        return "insert_user";
    }
    
    @PostMapping("/insert_user")
    public String register(@ModelAttribute("kks05sc003_F_001Model") KKS05SC003_F_001Model model, BindingResult result, Model modelAttribute, HttpSession session) {
        
        System.out.println(model);
        System.out.println("Model UserID: " + model.getUserId());
        System.out.println("Model Password: " + model.getPassword());
        
        // サービスクラスを呼び出して登録処理を実行
        Integer registrationCount = kks05sc003_F_001InsertSer.validateInsertUser(model, result);

        if (result.hasErrors()) {
            // エラーメッセージがある場合、モデルに設定して画面を再表示
            modelAttribute.addAttribute("login_info_err", model);
            return "insert_user";
            
        }
        
        if (registrationCount != null) {
            
            // 登録件数が1件の場合、成功メッセージと画面DTOをモデルに設定して画面を再表示
            modelAttribute.addAttribute("compMsg", "登録完了");
            modelAttribute.addAttribute("kks05sc003_F_001Model", model);
            return "insert_user";
        }

        // それ以外の場合
        return "insert_user";
    }
}
