package com.widgets_are_us.customers_service.models;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.widgets_are_us.customers_service.dto.AddressDto;
import org.junit.jupiter.api.Test;

class AddressTest {

    @Test
    void addressConstructor() {

        // given
        Long fakeAddressId = 1L;
        String fakeAddress1 = "fakeAddress1";
        String fakeAddress2 = "fakeAddress2";
        String fakeCity = "fakeCity";
        String fakeState = "fakeState";
        String fakeZipcode = "fakeZipcode";
        String fakePhoneNumber = "fakePhoneNumber";
        AddressDto fakeAddressDto =
                AddressDto.builder()
                        .id(fakeAddressId)
                        .address1(fakeAddress1)
                        .address2(fakeAddress2)
                        .city(fakeCity)
                        .state(fakeState)
                        .zipcode(fakeZipcode)
                        .phoneNumber(fakePhoneNumber)
                        .build();

        // when
        Address result = new Address(fakeAddressDto);

        // then
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(fakeAddressId, result.getId()),
                () -> assertEquals(fakeAddress1, result.getAddress1()),
                () -> assertEquals(fakeAddress2, result.getAddress2()),
                () -> assertEquals(fakeCity, result.getCity()),
                () -> assertEquals(fakeState, result.getState()),
                () -> assertEquals(fakeZipcode, result.getZipcode()),
                () -> assertEquals(fakePhoneNumber, result.getPhoneNumber()));
    }

    @Test
    void toJson() {

        // given
        Long fakeAddressId = 1L;
        String fakeAddress1 = "fakeAddress1";
        String fakeAddress2 = "fakeAddress2";
        String fakeCity = "fakeCity";
        String fakeState = "fakeState";
        String fakeZipcode = "fakeZipcode";
        String fakePhoneNumber = "fakePhoneNumber";
        String fakeAddressJson =
                """
                    {"id":1,\
                    "address1":"fakeAddress1",\
                    "address2":"fakeAddress2",\
                    "city":"fakeCity",\
                    "state":"fakeState",\
                    "zipcode":"fakeZipcode",\
                    "phoneNumber":"fakePhoneNumber"}\
                    """;
        Address fakeAddress =
                Address.builder()
                        .id(fakeAddressId)
                        .address1(fakeAddress1)
                        .address2(fakeAddress2)
                        .city(fakeCity)
                        .state(fakeState)
                        .zipcode(fakeZipcode)
                        .phoneNumber(fakePhoneNumber)
                        .build();

        // when
        String result = fakeAddress.toJson();

        // then
        assertAll(() -> assertNotNull(result), () -> assertEquals(fakeAddressJson, result));
    }

    @Test
    void fromJson() {

        // given
        Long fakeAddressId = 1L;
        String fakeAddress1 = "fakeAddress1";
        String fakeAddress2 = "fakeAddress2";
        String fakeCity = "fakeCity";
        String fakeState = "fakeState";
        String fakeZipcode = "fakeZipcode";
        String fakePhoneNumber = "fakePhoneNumber";
        String fakeAddressJson =
                """
                    {"id":1,\
                    "address1":"fakeAddress1",\
                    "address2":"fakeAddress2",\
                    "city":"fakeCity",\
                    "state":"fakeState",\
                    "zipcode":"fakeZipcode",\
                    "phoneNumber":"fakePhoneNumber"}\
                    """;

        // when
        Address result = Address.fromJson(fakeAddressJson);

        // then
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(fakeAddressId, result.getId()),
                () -> assertEquals(fakeAddress1, result.getAddress1()),
                () -> assertEquals(fakeAddress2, result.getAddress2()),
                () -> assertEquals(fakeCity, result.getCity()),
                () -> assertEquals(fakeState, result.getState()),
                () -> assertEquals(fakeZipcode, result.getZipcode()),
                () -> assertEquals(fakePhoneNumber, result.getPhoneNumber()));
    }
}
