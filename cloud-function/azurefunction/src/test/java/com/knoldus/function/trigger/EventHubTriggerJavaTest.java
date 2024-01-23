package com.knoldus.function.trigger;

import com.knoldus.function.model.Car;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.OutputBinding;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class EventHubTriggerJavaTest {

    @Mock
    private ExecutionContext context;
    @Mock
    private OutputBinding<List<Car>> outputBinding;

    @InjectMocks
    private EventHubTriggerJava eventHubTriggerJava;

    @Before
    public void setUp() {

        MockitoAnnotations.openMocks(this);
        eventHubTriggerJava = new EventHubTriggerJava();
    }

    @Test
    public void testRun() {
        // Prepare test data
        List<Car> testCarDetails = new ArrayList<>();
        testCarDetails.add(new Car(1,"Toyota","Sedan", 1996L, "white", 20.0,2000.0,2,5.0 )); // Add more cars as needed

        // Mock context and logger
        ExecutionContext context = mock(ExecutionContext.class);
        Logger mockLogger = mock(Logger.class);
        when(context.getLogger()).thenReturn(mockLogger);

        // Call the method under test
        eventHubTriggerJava.run(testCarDetails, outputBinding, context);

        // Assertions and verifications
        ArgumentCaptor<List<Car>> argumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(outputBinding).setValue(argumentCaptor.capture());
        List<Car> capturedCars = argumentCaptor.getValue();

        assertNotNull(capturedCars);
        assertFalse(capturedCars.isEmpty());

    }
}
