package spring.data.jpa.demo.springdatajpademo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringDataJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaDemoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
    ModelMapper mapper = new ModelMapper();
    mapper.getConfiguration().setPreferNestedProperties(false);
    // String to LocalDate
    mapper.addConverter(
      new AbstractConverter<String, LocalDate>() {
        @Override
        protected LocalDate convert(String source) {
          if (source == null) {
            return null;
          }
          DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
          return LocalDate.parse(source, format);
	}
    });
    // LocalDate to String
    mapper.addConverter(
      new AbstractConverter<LocalDate, String>() {
        @Override
        protected String convert(LocalDate source) {
          if (source == null) {
            return null;
          }
          DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	  return source.format(format);
       }
    });
    return mapper;
  }

}
