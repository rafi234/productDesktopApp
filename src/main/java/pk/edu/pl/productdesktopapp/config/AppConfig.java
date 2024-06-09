package pk.edu.pl.productdesktopapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXMLLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import pk.edu.pl.productdesktopapp.model.factories.ModelFactory;
import pk.edu.pl.productdesktopapp.service.converters.ProductHttpMessageConverter;
import pk.edu.pl.productdesktopapp.view.converter.StringStringPropertyConverter;
import pk.edu.pl.productdesktopapp.viewmodel.ViewModelFactory;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

  @Bean
  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
    return new MappingJackson2HttpMessageConverter();
  }

  @Bean()
  public FXMLLoader fxmlLoader() {
    return new FXMLLoader();
  }

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate
        .getMessageConverters()
        .add(
            new ProductHttpMessageConverter(mappingJackson2HttpMessageConverter(), objectMapper()));
    return restTemplate;
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public ViewModelFactory viewModelFactory() {
    return new ViewModelFactory(modelFactory());
  }

  @Bean
  public ModelFactory modelFactory() {
    return new ModelFactory();
  }

  @Bean
  public StringStringPropertyConverter stringStringPropertyConverter() {
    return new StringStringPropertyConverter();
  }
}
