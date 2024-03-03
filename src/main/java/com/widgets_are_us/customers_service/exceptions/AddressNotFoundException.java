package com.widgets_are_us.customers_service.exceptions;

public class AddressNotFoundException extends ResourceNotFoundException {

    public AddressNotFoundException(String message) {
        super(message);
    }
}
