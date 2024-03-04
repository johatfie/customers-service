package com.widgets_are_us.customers_service.models;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.widgets_are_us.customers_service.dto.CustomerDto;
import org.junit.jupiter.api.Test;

class CustomerTest {

    @Test
    void customerConstructor() {

        // given
        Long fakeCustomerId = 1L;
        String fakeFirstName = "fakeFirstName";
        String fakeLastName = "fakeLastName";
        String fakeBusinessName = "fakeBusinessName";
        String fakePhoneNumber = "fakePhoneNumber";
        String fakeEmail = "fakeEmail";
        CustomerDto fakeCustomerDto =
                CustomerDto.builder()
                        .id(fakeCustomerId)
                        .firstName(fakeFirstName)
                        .lastName(fakeLastName)
                        .businessName(fakeBusinessName)
                        .phoneNumber(fakePhoneNumber)
                        .email(fakeEmail)
                        .build();

        // when
        Customer result = new Customer(fakeCustomerDto);

        // then
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(fakeCustomerId, result.getId()),
                () -> assertEquals(fakeFirstName, result.getFirstName()),
                () -> assertEquals(fakeLastName, result.getLastName()),
                () -> assertEquals(fakeBusinessName, result.getBusinessName()),
                () -> assertEquals(fakePhoneNumber, result.getPhoneNumber()),
                () -> assertEquals(fakeEmail, result.getEmail()));
    }

    @Test
    void toJson() {

        // given
        Long fakeCustomerId = 1L;
        String fakeFirstName = "fakeFirstName";
        String fakeLastName = "fakeLastName";
        String fakeBusinessName = "fakeBusinessName";
        String fakePhoneNumber = "fakePhoneNumber";
        String fakeEmail = "fakeEmail";
        Customer fakeCustomer =
                Customer.builder()
                        .id(fakeCustomerId)
                        .firstName(fakeFirstName)
                        .lastName(fakeLastName)
                        .businessName(fakeBusinessName)
                        .phoneNumber(fakePhoneNumber)
                        .email(fakeEmail)
                        .build();
        String jsonDto =
                """
        {"id":1,\
        "firstName":"fakeFirstName",\
        "lastName":"fakeLastName",\
        "businessName":"fakeBusinessName",\
        "phoneNumber":"fakePhoneNumber",\
        "email":"fakeEmail"}\
        """;

        // when
        String result = fakeCustomer.toJson();

        // then
        assertAll(() -> assertNotNull(result), () -> assertEquals(jsonDto, result));
    }

    @Test
    void fromJson() {

        // given
        Long fakeCustomerId = 1L;
        String fakeFirstName = "fakeFirstName";
        String fakeLastName = "fakeLastName";
        String fakeBusinessName = "fakeBusinessName";
        String fakePhoneNumber = "fakePhoneNumber";
        String fakeEmail = "fakeEmail";
        String jsonDto =
                """
        {"id":1,\
        "firstName":"fakeFirstName",\
        "lastName":"fakeLastName",\
        "businessName":"fakeBusinessName",\
        "phoneNumber":"fakePhoneNumber",\
        "email":"fakeEmail"}\
        """;

        // when
        Customer result = Customer.fromJson(jsonDto);

        // then
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(fakeCustomerId, result.getId()),
                () -> assertEquals(fakeFirstName, result.getFirstName()),
                () -> assertEquals(fakeLastName, result.getLastName()),
                () -> assertEquals(fakeBusinessName, result.getBusinessName()),
                () -> assertEquals(fakePhoneNumber, result.getPhoneNumber()),
                () -> assertEquals(fakeEmail, result.getEmail()));
    }
}
