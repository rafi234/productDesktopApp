package pk.edu.pl.productdesktopapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pk.edu.pl.productdesktopapp.service.converters.ProductHttpMessageConverter;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
  private final ObjectMapper objectMapper;

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(
        new ProductHttpMessageConverter(mappingJackson2HttpMessageConverter, objectMapper));
  }
}
