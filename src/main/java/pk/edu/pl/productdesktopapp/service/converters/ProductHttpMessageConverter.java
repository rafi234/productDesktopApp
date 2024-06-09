package pk.edu.pl.productdesktopapp.service.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StreamUtils;
import pk.edu.pl.productdesktopapp.model.product.Product;

@RequiredArgsConstructor
public class ProductHttpMessageConverter implements HttpMessageConverter<Product> {

    private final MappingJackson2HttpMessageConverter jsonConverter;
    private final ObjectMapper objectMapper;
    

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return true;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return true;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return jsonConverter.getSupportedMediaTypes();
    }

    @Override
    public Product read(Class<? extends Product> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream inputStream = inputMessage.getBody();
        String data = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
        Map<String, String> resultMap = objectMapper.readValue(data, new TypeReference<>() {});
        return Product.fromHttp(resultMap);
    }

    @Override
    public void write(Product product, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        HttpHeaders headers = outputMessage.getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        OutputStream outputStream = outputMessage.getBody();
        String json = jsonConverter.getObjectMapper().writeValueAsString(product);
        StreamUtils.copy(json, StandardCharsets.UTF_8, outputStream);
    }
}
