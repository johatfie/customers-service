package com.widgets_are_us.customers_service.exceptions;

public class CustomerNotFoundException extends ResourceNotFoundException {

  public CustomerNotFoundException(String message) {
    super(message);
  }
}
