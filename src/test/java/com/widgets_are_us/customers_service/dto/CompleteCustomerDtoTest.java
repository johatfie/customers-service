package com.widgets_are_us.customers_service.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class CompleteCustomerDtoTest {

    @Test
    void toJson() {

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

        Long fakeAddressId = 1L;
        String fakeAddress1 = "fakeAddress1";
        String fakeAddress2 = "fakeAddress2";
        String fakeCity = "fakeCity";
        String fakeState = "fakeState";
        String fakeZipcode = "fakeZipcode";
        String fakeAddressPhoneNumber = "fakeAddressPhoneNumber";
        AddressDto fakeAddressDto =
                AddressDto.builder()
                        .id(fakeAddressId)
                        .address1(fakeAddress1)
                        .address2(fakeAddress2)
                        .city(fakeCity)
                        .state(fakeState)
                        .zipcode(fakeZipcode)
                        .phoneNumber(fakeAddressPhoneNumber)
                        .build();

        CompleteCustomerDto completeCustomerDto =
                CompleteCustomerDto.builder()
                        .customerDto(fakeCustomerDto)
                        .defaultAddress(fakeAddressDto)
                        .addressList(List.of(fakeAddressDto))
                        .build();

        String expected =
                """
                  {"customerDto":{\
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
        String result = completeCustomerDto.toJson();

        // then
        assertEquals(expected, result);
    }
}
