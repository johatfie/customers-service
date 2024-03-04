package com.widgets_are_us.customers_service.dto;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.widgets_are_us.customers_service.models.Customer;
import org.junit.jupiter.api.Test;

class CustomerDtoTest {

    @Test
    void customerDtoConstructor() {

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

        // when
        CustomerDto result = new CustomerDto(fakeCustomer);

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
        CustomerDto fakeCustomer =
                CustomerDto.builder()
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
        CustomerDto result = CustomerDto.fromJson(jsonDto);

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
