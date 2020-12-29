package skmt.com.officeutq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Skmt Topページ
 * @author Koji Sakamoto
 *
 */
@SpringBootApplication
@Controller
public class SkmtApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SkmtApplication.class, args);
	}
	
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getSomething() {
		return "index";
    }
    
}
