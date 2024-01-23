package com.nashtech.order.exception;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderServiceErrorHandlerTest {
    /**
     * Method under test:
     * {@link OrderServiceErrorHandler#handleIllegalStateException(IllegalStateException, WebRequest)}
     */
    @Test
    public void testHandleIllegalStateException() {
        // Arrange
        OrderServiceErrorHandler orderServiceErrorHandler = new OrderServiceErrorHandler();
        IllegalStateException ex = new IllegalStateException("foo");

        // Act
        ResponseEntity<Object> actualHandleIllegalStateExceptionResult = orderServiceErrorHandler
                .handleIllegalStateException(ex, new ServletWebRequest(new MockHttpServletRequest()));

        // Assert
        assertEquals("foo", ((ErrorMessage) actualHandleIllegalStateExceptionResult.getBody()).getMessage());

        Date current = new Date();
        Date expectedDate = ((ErrorMessage) actualHandleIllegalStateExceptionResult.getBody()).getTimestamp();
        assertDate(current,expectedDate);

        assertEquals(500, actualHandleIllegalStateExceptionResult.getStatusCodeValue());
        assertTrue(actualHandleIllegalStateExceptionResult.hasBody());
        assertTrue(actualHandleIllegalStateExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link OrderServiceErrorHandler#handleOtherExceptions(Exception, WebRequest)}
     */
    @Test
    public void testHandleOtherExceptions() {
        // Arrange
        OrderServiceErrorHandler orderServiceErrorHandler = new OrderServiceErrorHandler();
        Exception ex = new Exception("foo");

        // Act
        ResponseEntity<Object> actualHandleOtherExceptionsResult = orderServiceErrorHandler.handleOtherExceptions(ex,
                new ServletWebRequest(new MockHttpServletRequest()));

        // Assert
        assertEquals("foo", ((ErrorMessage) actualHandleOtherExceptionsResult.getBody()).getMessage());

        Date current = new Date();
        Date expectedDate = ((ErrorMessage) actualHandleOtherExceptionsResult.getBody()).getTimestamp();
        assertDate(current,expectedDate);

        assertEquals(500, actualHandleOtherExceptionsResult.getStatusCodeValue());
        assertTrue(actualHandleOtherExceptionsResult.hasBody());
        assertTrue(actualHandleOtherExceptionsResult.getHeaders().isEmpty());
    }

    public void assertDate(Date currentDate, Date expectedDate) {
        Calendar currentCal = new GregorianCalendar();
        currentCal.setTime(currentDate);

        Calendar expectedCal = new GregorianCalendar();
        currentCal.setTime(expectedDate);

        assertEquals("month", expectedCal.get(Calendar.MONTH), currentCal.get(Calendar.MONTH));
        assertEquals("day", expectedCal.get(Calendar.DATE), currentCal.get(Calendar.DATE));
        assertEquals("year", expectedCal.get(Calendar.YEAR), currentCal.get(Calendar.YEAR));
    }
}
