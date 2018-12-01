package car_dealer.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import car_dealer.util.FileUtil;
import car_dealer.util.FileUtilImpl;
import car_dealer.util.ValidatorUtil;
import car_dealer.util.ValidatorUtilImpl;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public FileUtil fileIOUtil() {
        return new FileUtilImpl();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }

    @Bean
    public ValidatorUtil validatorUtil() {
        return new ValidatorUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
