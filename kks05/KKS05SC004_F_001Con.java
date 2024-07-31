package jp.co.amiactive.controller.kks05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;
import jp.co.amiactive.dto.KKS05SC004_F_001Model;
import jp.co.amiactive.service.kks05.KKS05SC004_F_001LoginSer;

@Controller
@RequestMapping("/login")
@SessionAttributes("loginUser")
public class KKS05SC004_F_001Con {

    @Autowired
    private KKS05SC004_F_001LoginSer loginService;

    @GetMapping
    public String initialDisplay(@ModelAttribute("kks05sc004_F_001Model") KKS05SC004_F_001Model model) {
        // 画面の表示
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute("kks05sc004_F_001Model") KKS05SC004_F_001Model model, BindingResult result, Model viewModel, HttpSession session) {
        System.out.println("Model UserID: " + model.getUserId());
        System.out.println("Model Password: " + model.getPassword());

        KKS05SC004_F_001Model loginResult = loginService.authenticateUser(model, result);
        
        System.out.println(loginResult);
        
        if (loginResult != null && loginResult.getErrorCode() == 0) {
            // ログイン成功時の場合
            session.setAttribute("loginUser", loginResult);
            return "redirect:/menu";
            
        } else {
            // ログイン失敗時の処理
            viewModel.addAttribute("login", model);
            viewModel.addAttribute("error", "ログインに失敗しました。");
            return "login";
        }
    }
}
