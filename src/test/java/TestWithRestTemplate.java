import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.melardev.models.Article;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestWithRestTemplate {

    private static final String BASE_URI = "http://localhost:8081/rest-template/articles";
    private static RestTemplate restTemplate;


    @BeforeAll
    public static void beforeTest() {
        restTemplate = new RestTemplate();

        // restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
    }

    // GET

    @Test
    public void givenResourceUrl_whenSendGetForRequestEntity_thenStatusOk() throws IOException {
        String url = BASE_URI + "/{id}";
        final ResponseEntity<Article> response = restTemplate.getForEntity(url, Article.class, 1);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void givenResourceUrl_whenSendGetForRequestEntity_thenBodyCorrect() throws IOException {
        String url = BASE_URI + "/{id}";
        final ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        final ObjectMapper mapper = new ObjectMapper();
        final JsonNode root = mapper.readTree(response.getBody());
        final JsonNode name = root.path("title");
        assertThat(name.asText(), notNullValue());
    }

    @Test
    public void givenResourceUrl_whenRetrievingResource_thenCorrect() throws IOException {
        final Article article = restTemplate.getForObject(BASE_URI + "/1", Article.class);
        assertThat(article.getTitle(), notNullValue());
        assertThat(article.getBody(), CoreMatchers.is(1L));
    }

    // HEAD, OPTIONS

    @Test
    public void givenArticleService_whenCallHeadForHeaders_thenReceiveAllHeadersForThatResource() {
        String url = BASE_URI + "/{id}";
        final HttpHeaders httpHeaders = restTemplate.headForHeaders(url);
        assertTrue(httpHeaders.getContentType()
                .includes(MediaType.APPLICATION_JSON));
    }

    // POST

    @Test
    public void givenArticleService_whenPostForObject_thenCreatedObjectIsReturned() {
        String url = BASE_URI + "/{id}";
        final HttpEntity<Article> request = new HttpEntity<>(new Article("bar", ""));
        final Article article = restTemplate.postForObject(url, request, Article.class);
        assertThat(article, notNullValue());
        assertThat(article.getTitle(), CoreMatchers.is("bar"));
    }
/*
    @Test
    public void givenArticleService_whenPostForLocation_thenCreatedLocationIsReturned() {
        final HttpEntity<Article> request = new HttpEntity<>(new Article("bar"));
        final URI location = restTemplate.postForLocation(articleResourceUrl, request);
        assertThat(location, notNullValue());
    }

    @Test
    public void givenArticleService_whenPostResource_thenResourceIsCreated() {
        final Article article = new Article("bar");
        final ResponseEntity<Article> response = restTemplate.postForEntity(articleResourceUrl, article, Article.class);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        final Article articleResponse = response.getBody();
        assertThat(articleResponse, notNullValue());
        assertThat(articleResponse.getTitle(), is("bar"));
    }

    @Test
    public void givenArticleService_whenCallOptionsForAllow_thenReceiveValueOfAllowHeader() {
        final Set<HttpMethod> optionsForAllow = restTemplate.optionsForAllow(articleResourceUrl);
        final HttpMethod[] supportedMethods = {HttpMethod.GET, HttpMethod.POST, HttpMethod.HEAD};

        assertTrue(optionsForAllow.containsAll(Arrays.asList(supportedMethods)));
    }

    // PUT

    @Test
    public void givenArticleService_whenPutExistingEntity_thenItIsUpdated() {
        final HttpHeaders headers = prepareBasicAuthHeaders();
        final HttpEntity<Article> request = new HttpEntity<>(new Article("bar"), headers);

        // Create Resource
        final ResponseEntity<Article> createResponse = restTemplate.exchange(articleResourceUrl, HttpMethod.POST, request, Article.class);

        // Update Resource
        final Article updatedInstance = new Article("newName");
        updatedInstance.setId(createResponse.getBody()
                .getId());
        final String resourceUrl = articleResourceUrl + '/' + createResponse.getBody()
                .getId();
        final HttpEntity<Article> requestUpdate = new HttpEntity<>(updatedInstance, headers);
        restTemplate.exchange(resourceUrl, HttpMethod.PUT, requestUpdate, Void.class);

        // Check that Resource was updated
        final ResponseEntity<Article> updateResponse = restTemplate.exchange(resourceUrl, HttpMethod.GET, new HttpEntity<>(headers), Article.class);
        final Article article = updateResponse.getBody();
        assertThat(article.getName(), is(updatedInstance.getName()));
    }

    @Test
    public void givenArticleService_whenPutExistingEntityWithCallback_thenItIsUpdated() {
        final HttpHeaders headers = prepareBasicAuthHeaders();
        final HttpEntity<Article> request = new HttpEntity<>(new Article("bar"), headers);

        // Create entity
        ResponseEntity<Article> response = restTemplate.exchange(articleResourceUrl, HttpMethod.POST, request, Article.class);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        // Update entity
        final Article updatedInstance = new Article("newName");
        updatedInstance.setId(response.getBody()
                .getId());
        final String resourceUrl = articleResourceUrl + '/' + response.getBody()
                .getId();
        restTemplate.execute(resourceUrl, HttpMethod.PUT, requestCallback(updatedInstance), clientHttpResponse -> null);

        // Check that entity was updated
        response = restTemplate.exchange(resourceUrl, HttpMethod.GET, new HttpEntity<>(headers), Article.class);
        final Article article = response.getBody();
        assertThat(article.getName(), is(updatedInstance.getName()));
    }

    // PATCH

    @Test
    public void givenArticleService_whenPatchExistingEntity_thenItIsUpdated() {
        final HttpHeaders headers = prepareBasicAuthHeaders();
        final HttpEntity<Article> request = new HttpEntity<>(new Article("bar"), headers);

        // Create Resource
        final ResponseEntity<Article> createResponse = restTemplate.exchange(articleResourceUrl, HttpMethod.POST, request, Article.class);

        // Update Resource
        final Article updatedResource = new Article("newName");
        updatedResource.setId(createResponse.getBody()
                .getId());
        final String resourceUrl = articleResourceUrl + '/' + createResponse.getBody()
                .getId();
        final HttpEntity<Article> requestUpdate = new HttpEntity<>(updatedResource, headers);
        final ClientHttpRequestFactory requestFactory = getSimpleClientHttpRequestFactory();
        final RestTemplate template = new RestTemplate(requestFactory);
        template.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
        template.patchForObject(resourceUrl, requestUpdate, Void.class);

        // Check that Resource was updated
        final ResponseEntity<Article> updateResponse = restTemplate.exchange(resourceUrl, HttpMethod.GET, new HttpEntity<>(headers), Article.class);
        final Article article = updateResponse.getBody();
        assertThat(article.getName(), is(updatedResource.getName()));
    }

    // DELETE

    @Test
    public void givenArticleService_whenCallDelete_thenEntityIsRemoved() {
        final Article article = new Article("Spring MVC", "Rest Template Tutorial");
        final ResponseEntity<Article> response = restTemplate.postForEntity(articleResourceUrl, article, Article.class);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        final String entityUrl = articleResourceUrl + "/" + response.getBody()
                .getId();
        restTemplate.delete(entityUrl);
        try {
            restTemplate.getForEntity(entityUrl, Article.class);
            fail();
        } catch (final HttpClientErrorException ex) {
            assertThat(ex.getStatusCode(), is(HttpStatus.NOT_FOUND));
        }
    }

    //
/*
    private HttpHeaders prepareBasicAuthHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        final String encodedLogPass = getBase64EncodedLogPass();
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + encodedLogPass);
        return headers;
    }

    private String getBase64EncodedLogPass() {
        final String logPass = "user1:user1Pass";
        final byte[] authHeaderBytes = encodeBase64(logPass.getBytes(Charsets.US_ASCII));
        return new String(authHeaderBytes, Charsets.US_ASCII);
    }

    private RequestCallback requestCallback(final Article updatedInstance) {
        return clientHttpRequest -> {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(clientHttpRequest.getBody(), updatedInstance);
            clientHttpRequest.getHeaders()
                    .add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            clientHttpRequest.getHeaders()
                    .add(HttpHeaders.AUTHORIZATION, "Basic " + getBase64EncodedLogPass());
        };
    }

    // Simply setting restTemplate timeout using ClientHttpRequestFactory

    ClientHttpRequestFactory getSimpleClientHttpRequestFactory() {
        final int timeout = 5;
        final HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout * 1000);
        return clientHttpRequestFactory;
    }
    */
}
