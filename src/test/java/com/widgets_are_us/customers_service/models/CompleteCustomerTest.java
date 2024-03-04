package com.widgets_are_us.customers_service.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class CompleteCustomerTest {

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

        Long fakeAddressId = 1L;
        String fakeAddress1 = "fakeAddress1";
        String fakeAddress2 = "fakeAddress2";
        String fakeCity = "fakeCity";
        String fakeState = "fakeState";
        String fakeZipcode = "fakeZipcode";
        String fakeAddressPhoneNumber = "fakeAddressPhoneNumber";
        Address fakeAddress =
                Address.builder()
                        .id(fakeAddressId)
                        .address1(fakeAddress1)
                        .address2(fakeAddress2)
                        .city(fakeCity)
                        .state(fakeState)
                        .zipcode(fakeZipcode)
                        .phoneNumber(fakeAddressPhoneNumber)
                        .build();

        CompleteCustomer completeCustomer =
                CompleteCustomer.builder()
                        .customer(fakeCustomer)
                        .defaultAddress(fakeAddress)
                        .addressList(List.of(fakeAddress))
                        .build();

        String expected =
                """
                  {"customer":{\
                  "id":1,\
                  "firstName":"fakeFirstName",\
                  "lastName":"fakeLastName",\
                  "businessName":"fakeBusinessName",\
                  "phoneNumber":"fakePhoneNumber",\
                  "email":"fakeEmail"},\
                  "defaultAddress":{\
                  "id":1,\
                  "address1":"fakeAddress1",\
                  "address2":"fakeAddress2",\
                  "city":"fakeCity",\
                  "state":"fakeState",\
                  "zipcode":"fakeZipcode",\
                  "phoneNumber":"fakeAddressPhoneNumber"},\
                  "addressList":[{\
                  "id":1,\
                  "address1":"fakeAddress1",\
                  "address2":"fakeAddress2",\
                  "city":"fakeCity",\
                  "state":"fakeState",\
                  "zipcode":"fakeZipcode",\
                  "phoneNumber":"fakeAddressPhoneNumber"}]}\
                  """;

        // when
        String result = completeCustomer.toJson();

        // then
        assertEquals(expected, result);
    }
}
