package skmt.com.officeutq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SkmtApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SkmtApplication.class, args);
	}
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SkmtApplication.class);
    }

    @RequestMapping(value = "nagap", method = RequestMethod.GET)
    public String getSomething() {
        return "something";
    }

}
