package pk.edu.pl.productdesktopapp.service.product;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import pk.edu.pl.productdesktopapp.model.product.Product;
import pk.edu.pl.productdesktopapp.service.RestService;
import pk.edu.pl.productdesktopapp.tools.errorHandling.ErrorHandling;

@Service
public class ProductService extends RestService<Product> {

  public ProductService(RestTemplate restTemplate) {
    super(restTemplate, Product.class, "products");
  }

  public Product getOneByName(String name) {
    try {
      return restTemplate.getForObject(restUrl + "/name/" + name, clazz);
    } catch (HttpClientErrorException e) {
      ErrorHandling.showWarningWithMessage(e);
    } catch (HttpServerErrorException | ResourceAccessException e) {
      ErrorHandling.showErrorWithMessage(e);
    }
    return null;
  }

  @Override
  public Product getOne(String id) {
    return super.getOne(id);
  }

  @Override
  public List<Map<String, String>> getAll(boolean active) {
    return super.getAll(active);
  }

  @Override
  public void updateOne(Product toUpdate, String id) {
    super.updateOne(toUpdate, id);
  }

  @Override
  public void delete(String toDelete) {
    super.delete(toDelete);
  }

  @Override
  public Product addOne(Product toAdd) {
    return super.addOne(toAdd);
  }
}
