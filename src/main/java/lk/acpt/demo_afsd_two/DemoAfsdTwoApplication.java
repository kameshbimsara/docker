package lk.acpt.demo_afsd_two;

import lk.acpt.demo_afsd_two.dto.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties(StorageProperties.class)
public class DemoAfsdTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoAfsdTwoApplication.class, args);
	}

}
