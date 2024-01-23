package com.nashtech.service.impl;

import com.nashtech.model.Car;
import com.nashtech.model.CarBrand;
import com.nashtech.service.CloudDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.http.server.reactive.ChannelSendOperator;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.*;
import java.util.function.Function;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
@Slf4j
class ReactiveDataServiceImplTest {
    // private static final Logger log = LoggerFactory.getLogger(ReactiveDataServiceImpl.class);

    @Mock
    private CloudDataService cloudDataService;

    @Mock
    private WebClient webClient;

    @InjectMocks
    private ReactiveDataServiceImpl reactiveDataService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testGetCarsByBrand() {
        // Mock the CloudDataService behavior
        String brand = "Toyota";
        Car car1 = new Car(0, "Toyota", "model", 2020L, "color", 0.0, 0.0, 0, 0.0);
        Car car2 = new Car(1, "Toyota", "model", 2020L, "color", 0.0, 0.0, 0, 0.0);
        Flux<Car> carFlux = Flux.just(car1, car2);
        when(cloudDataService.getCarsByBrand(brand)).thenReturn(carFlux);

        // Invoke the method to test
        Flux<Car> resultFlux = reactiveDataService.getCarsByBrand(brand);

        // StepVerifier to verify the Flux subscription
        StepVerifier.create(resultFlux)
                .expectNext(car1)
                .expectNext(car2)
                .verifyComplete();
    }

    @Test
    void testGetAllBrands() {
        // Mock the CloudDataService behavior
        CarBrand brand1 = new CarBrand("Toyota");
        CarBrand brand2 = new CarBrand("Honda");
        Flux<CarBrand> carBrandFlux = Flux.just(brand1, brand2);
        when(cloudDataService.getAllBrands()).thenReturn(carBrandFlux);

        // Invoke the method to test
        Flux<CarBrand> resultFlux = reactiveDataService.getAllBrands();

        // StepVerifier to verify the Flux subscription
        StepVerifier.create(resultFlux)
                .expectNext(brand1)
                .expectNext(brand2)
                .verifyComplete();
    }

    @Test
    void testGetAllBrands_NoDuplicateBrandsReturned() {
        // Prepare mock data
        CarBrand brand1 = new CarBrand("BMW");
        CarBrand brand2 = new CarBrand("Toyota");
        CarBrand brand3 = new CarBrand("Mercedes");

        // Mock the reactiveDataService to return Flux with duplicate brands
        when(reactiveDataService.getAllBrands()).thenReturn(Flux.just(brand1, brand2, brand1, brand3, brand2));

        // Run the test
        final Flux<CarBrand> result = reactiveDataService.getAllBrands();

        // Convert Flux to List
        List<CarBrand> brandList = result.collectList().block();

        // Verify the results
        StepVerifier.create(result)
                .recordWith(ArrayList::new) // Record all elements in an ArrayList
                .expectNextCount(5) // Expecting 5 items in the Flux
                .consumeRecordedWith(brands -> {
                    // Convert the list to a Set to check for duplicates
                    Set<CarBrand> uniqueBrands = new HashSet<>(brands);
                    assertThat(uniqueBrands).isSameAs(brandList);
                });

    }

    @Test
    void testGetAllBrandsSse() {
        // Mock CloudDataService behavior
        when(cloudDataService.getAllBrandsSse()).thenReturn(Flux.empty());

        // Test the method
        Flux<ServerSentEvent<Map<String, String>>> result = reactiveDataService.getAllBrandsSse();

        // Verify interactions
        verify(cloudDataService, times(1)).getAllBrandsSse();

        // Verify the result
        StepVerifier.create(result)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void testFetchAndSendData() {
        // Arrange
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
        Flux<Car> fromIterableResult = Flux.fromIterable(new ArrayList<>());
        when(responseSpec.bodyToFlux(Mockito.<Class<Car>>any())).thenReturn(fromIterableResult);
        WebClient.RequestBodySpec requestBodySpec = mock(WebClient.RequestBodySpec.class);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        WebClient.RequestHeadersUriSpec<WebClient.RequestBodySpec> requestHeadersUriSpec = mock(
                WebClient.RequestHeadersUriSpec.class);
        when(requestHeadersUriSpec.uri(Mockito.<String>any(), isA(Object[].class))).thenReturn(requestBodySpec);
        Mockito.<WebClient.RequestHeadersUriSpec<?>>when(webClient.get()).thenReturn(requestHeadersUriSpec);

        // Act
        reactiveDataService.fetchAndSendData();

        // Assert
        verify(webClient).get();
        verify(requestBodySpec).retrieve();
        verify(responseSpec).bodyToFlux(Mockito.<Class<Car>>any());
        verify(requestHeadersUriSpec).uri(Mockito.<String>any(), isA(Object[].class));
    }

    /**
     * Method under test: {@link ReactiveDataServiceImpl#fetchAndSendData()}
     */
    @Test
    void testFetchAndSendData2() {
        // Arrange
        Flux<?> source = Flux.fromIterable(new ArrayList<>());
        when(cloudDataService.pushData(Mockito.<Car>any()))
                .thenReturn(new ChannelSendOperator<>(source, mock(Function.class)));

        ArrayList<Car> it = new ArrayList<>();
        Car buildResult = Car.builder()
                .brand("Brand")
                .carId(1)
                .color("Color")
                .mileage(10.0d)
                .model("Model")
                .price(10.0d)
                .quantity(1)
                .tax(10.0d)
                .year(1L)
                .build();
        it.add(buildResult);
        Flux<Car> fromIterableResult = Flux.fromIterable(it);
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
        when(responseSpec.bodyToFlux(Mockito.<Class<Car>>any())).thenReturn(fromIterableResult);
        WebClient.RequestBodySpec requestBodySpec = mock(WebClient.RequestBodySpec.class);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        WebClient.RequestHeadersUriSpec<WebClient.RequestBodySpec> requestHeadersUriSpec = mock(
                WebClient.RequestHeadersUriSpec.class);
        when(requestHeadersUriSpec.uri(Mockito.<String>any(), isA(Object[].class))).thenReturn(requestBodySpec);
        Mockito.<WebClient.RequestHeadersUriSpec<?>>when(webClient.get()).thenReturn(requestHeadersUriSpec);

        // Act
        reactiveDataService.fetchAndSendData();

        // Assert
        verify(cloudDataService).pushData(Mockito.<Car>any());
        verify(webClient).get();
        verify(requestBodySpec).retrieve();
        verify(responseSpec).bodyToFlux(Mockito.<Class<Car>>any());
        verify(requestHeadersUriSpec).uri(Mockito.<String>any(), isA(Object[].class));
    }

    @Test
    void test_handles_errors_during_data_retrieval() {

        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
        when(responseSpec.bodyToFlux(Mockito.<Class<Car>>any())).thenReturn(Flux.error(new WebClientException("Simulated error") {

        }));
        WebClient.RequestBodySpec requestBodySpec = mock(WebClient.RequestBodySpec.class);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        WebClient.RequestHeadersUriSpec<WebClient.RequestBodySpec> requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
        when(requestHeadersUriSpec.uri(Mockito.<String>any(), isA(Object[].class))).thenReturn(requestBodySpec);
        Mockito.<WebClient.RequestHeadersUriSpec<?>>when(webClient.get()).thenReturn(requestHeadersUriSpec);

        reactiveDataService.fetchAndSendData();

        verify(webClient).get();
        verify(requestBodySpec).retrieve();
        verify(responseSpec).bodyToFlux(Mockito.<Class<Car>>any());
        verify(requestHeadersUriSpec).uri(Mockito.<String>any(), isA(Object[].class));
    }


}