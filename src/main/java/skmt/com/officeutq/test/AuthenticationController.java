package skmt.com.officeutq.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ログインフォームを表示させるためのHandlerメソッド
 * @author Koji Sakamoto
 *
 */
@Controller
public class AuthenticationController {

	@GetMapping("/login")
    public String viewLoginForm(Model model) {
		return "loginForm";
    }
    
	@GetMapping("/loginFailure")
    public String viewLoginFailureForm(Model model) {
        model.addAttribute("isError", true);
		return "loginForm";
    }
 
    @GetMapping("/invalidSession")
    public String invalidSessionForm(Model model) {
        return "loginForm";
    }

}
