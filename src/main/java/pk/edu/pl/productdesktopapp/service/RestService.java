package pk.edu.pl.productdesktopapp.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import pk.edu.pl.productdesktopapp.tools.errorHandling.ErrorHandling;

@Service
@SuppressWarnings(value = {"unchecked"})
public abstract class RestService<T> {

  private static final String _id = "/{id}";
  private static final String defaultRestUrl = "http://localhost:7252/api/%s";

  protected final RestTemplate restTemplate;

  protected final String restUrl;

  protected final Class<T> clazz;

  public RestService(@Autowired RestTemplate restTemplate, Class<T> clazz, String lastUrlPart) {
    this.restTemplate = restTemplate;
    this.clazz = clazz;
    this.restUrl = defaultRestUrl.formatted(lastUrlPart);
  }

  public T getOne(String id) {
    try {
      return restTemplate.getForObject(restUrl + _id, clazz, id);
    } catch (HttpClientErrorException e) {
      ErrorHandling.showWarningWithMessage(e);
    } catch (HttpServerErrorException | ResourceAccessException e) {
      ErrorHandling.showErrorWithMessage(e);
    }
    return null;
  }

  public List<Map<String, String>> getAll(boolean active) {
    try {
      return restTemplate.getForObject(restUrl + "?enabled=" + active, List.class);
    } catch (HttpClientErrorException e) {
      ErrorHandling.showWarningWithMessage(e);
    } catch (HttpServerErrorException | ResourceAccessException e) {
      ErrorHandling.showErrorWithMessage(e);
    }
    return List.of();
  }

  public void updateOne(T toUpdate, String id) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.setAccessControlAllowCredentials(false);
      HttpEntity<T> requestBody = new HttpEntity<>(toUpdate, headers);
      restTemplate.exchange(restUrl + _id, HttpMethod.PUT, requestBody, clazz, id);
    } catch (HttpClientErrorException e) {
      ErrorHandling.showWarningWithMessage(e);
    } catch (HttpServerErrorException | ResourceAccessException e) {
      ErrorHandling.showErrorWithMessage(e);
    }
  }

  public void delete(String toDelete) {
    try {
      restTemplate.delete(restUrl + _id, toDelete);
    } catch (HttpClientErrorException e) {
      ErrorHandling.showWarningWithMessage(e);
    } catch (HttpServerErrorException | ResourceAccessException e) {
      ErrorHandling.showErrorWithMessage(e);
    }
  }

  public T addOne(T toAdd) {
    try {
      return restTemplate.postForObject(restUrl, toAdd, clazz);
    } catch (HttpClientErrorException e) {
      ErrorHandling.showWarningWithMessage(e);
    } catch (HttpServerErrorException | ResourceAccessException e) {
      ErrorHandling.showErrorWithMessage(e);
    }
    return null;
  }
}
