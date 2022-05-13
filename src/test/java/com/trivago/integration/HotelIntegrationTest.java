//
//package com.trivago.integration;
//
//import java.util.List;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.trivago.integration.model.Hotel;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockserver.integration.ClientAndServer;
//import org.mockserver.model.Header;
//import org.mockserver.model.HttpRequest;
//import org.mockserver.model.HttpResponse;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.web.client.RestTemplate;
//
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
//import static org.mockserver.integration.ClientAndServer.startClientAndServer;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class HotelIntegrationTest {
//
//    HotelIntegration hotelIntegration;
//
//    private static ClientAndServer server;
//
//    @BeforeEach
//    public void startServer() {
//        server = startClientAndServer();
//    }
//
//    @AfterEach
//    public void stopServer() {
//        server.close();
//    }
//
//    @BeforeEach
//    void setupClass() {
//        RestTemplate restTemplate = new RestTemplateBuilder()
//            .rootUri(String.format("http://localhost:%d", server.getPort()))
//            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//            .build();
//
//        hotelIntegration = new HotelIntegration(restTemplate);
//    }
//
//    @Test
//    void search() throws JsonProcessingException {
//
//        var objectMaper = new ObjectMapper();
//
//        var hoteResponse = Hotel.builder()
//            .hotelId("1")
//            .name("test")
//            .build();
//        var listHotelResponse = List.of(hoteResponse);
//        var responseBody = objectMaper.writeValueAsString(listHotelResponse);
//
//        HttpRequest request = HttpRequest.request()
//            .withPath("/search")
//            .withQueryStringParameter("q", "porto")
//            .withMethod("GET")
//            .withHeaders(Header.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
//
//        HttpResponse response = HttpResponse.response(responseBody)
//            .withStatusCode(HttpStatus.OK.value())
//            .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//
//        server.when(request).respond(response);
//
//        var responseIntegration = hotelIntegration.search("porto");
//        assertArrayEquals(listHotelResponse.toArray(),responseIntegration);
//    }
//}