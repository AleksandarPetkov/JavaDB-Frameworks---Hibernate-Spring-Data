package mostwantedtest.config;

import mostwantedtest.util.FileUtil;
import mostwantedtest.util.FileUtilImpl;
import mostwantedtest.util.ValidationUtil;
import mostwantedtest.util.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public FileUtil fileIOUtil() {
        return new FileUtilImpl();
    }

//    @Bean
//    public Gson gson() {
//        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
//    }

    @Bean
    public ValidationUtil validatorUtil() {
        return new ValidationUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
