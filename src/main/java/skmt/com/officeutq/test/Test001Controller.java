package skmt.com.officeutq.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * test001 データベース接続テスト 001
 * @author Koji Sakamoto
 *
 */
@Controller
public class Test001Controller {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @RequestMapping(value = "/test001", method = RequestMethod.GET)
    public String getSomething(Model model) {
		model.addAttribute("userName", findUserName("001"));
		return "test001";
    }
	
    public String findUserName(String userId) {
    	String sql = "SELECT user_name From test001 WHERE user_id = ?";
    	return jdbcTemplate.queryForObject(sql, String.class, userId);
    }

}
