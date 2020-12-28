package skmt.com.officeutq.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * test002 データベース接続テスト 002 テーブル作成
 * @author Koji Sakamoto
 *
 */
@Controller
public class Test002Controller {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @RequestMapping(value = "/test002", method = RequestMethod.GET)
    public String getSomething(Model model) {
    	create();
    	insert();
		model.addAttribute("userName", "成功！");
		return "test002";
    }
	
	@Bean
	public String create() {
		
		jdbcTemplate.execute("DROP TABLE IF EXISTS test001");
		jdbcTemplate.execute("CREATE TABLE test001 (user_id VARCHAR(20) NOT NULL, user_name VARCHAR(20), PRIMARY KEY(user_id))");

		return null;
	}

	@Bean
	public String insert() {
		
		String[][] data = {{"001","aaa"},{"002","bbb"},{"003","ccc"}};

		for (int i=0; i<data.length; i++) {
			jdbcTemplate.update("INSERT INTO test001 (user_id, user_name) VALUES (?, ?)", data[i][0], data[i][1]);
		}

		return null;
	}

}
