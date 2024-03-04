package com.widgets_are_us.customers_service.controllers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.widgets_are_us.customers_service.dto.CompleteCustomerDto;
import com.widgets_are_us.customers_service.dto.CustomerDto;
import com.widgets_are_us.customers_service.exceptions.CustomerNotFoundException;
import com.widgets_are_us.customers_service.models.Address;
import com.widgets_are_us.customers_service.models.Customer;
import com.widgets_are_us.customers_service.models.CustomerAddress;
import com.widgets_are_us.customers_service.repositories.AddressRepository;
import com.widgets_are_us.customers_service.repositories.CustomerAddressRepository;
import com.widgets_are_us.customers_service.repositories.CustomerRepository;
import com.widgets_are_us.customers_service.services.CustomerService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock AddressRepository mockAddressRepository;
    @Mock CustomerAddressRepository mockCustomerAddressRepository;
    @Mock CustomerRepository mockCustomerRepository;
    @Mock CustomerService mockCustomerService;

    @InjectMocks CustomerController customerController;

    @Test
    void findCustomerById_succeeds_whenCustomerExists() {

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
        when(mockCustomerRepository.findById(fakeCustomerId))
                .thenReturn(Optional.ofNullable(fakeCustomer));
        CustomerDto result = customerController.findCustomerById(fakeCustomerId);

        // then
        assertAll(
                () -> assertNotNull(result),
                () -> verify(mockCustomerRepository).findById(fakeCustomerId),
                () -> assertEquals(fakeCustomerId, result.getId()),
                () -> assertEquals(fakeFirstName, result.getFirstName()),
                () -> assertEquals(fakeLastName, result.getLastName()),
                () -> assertEquals(fakeBusinessName, result.getBusinessName()),
                () -> assertEquals(fakePhoneNumber, result.getPhoneNumber()),
                () -> assertEquals(fakeEmail, result.getEmail()));
    }

    @Test
    void findCustomerById_throwCustomerNotFoundException_whenCustomerDoesNotExist() {

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
        when(mockCustomerRepository.findById(fakeCustomerId))
                .thenThrow(new CustomerNotFoundException("fake exception"));
        Exception expectedException =
                assertThrows(
                        CustomerNotFoundException.class,
                        () -> customerController.findCustomerById(fakeCustomerId));

        // then
        assertAll(
                () -> assertNotNull(expectedException),
                () -> verify(mockCustomerRepository).findById(fakeCustomerId),
                () -> assertEquals("fake exception", expectedException.getMessage()));
    }

    @Test
    void findByFirstNameAndLastName() {

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
        when(mockCustomerRepository.findByFirstNameAndLastName(fakeFirstName, fakeLastName))
                .thenReturn(List.of(fakeCustomer));
        List<CustomerDto> result =
                customerController.findByFirstNameAndLastName(fakeFirstName, fakeLastName);

        // then
        assertAll(
                () -> assertNotNull(result),
                () -> assertFalse(result.isEmpty()),
                () -> assertEquals(1, result.size()),
                () ->
                        verify(mockCustomerRepository)
                                .findByFirstNameAndLastName(fakeFirstName, fakeLastName),
                () -> assertEquals(fakeCustomerId, result.get(0).getId()),
                () -> assertEquals(fakeFirstName, result.get(0).getFirstName()),
                () -> assertEquals(fakeLastName, result.get(0).getLastName()),
                () -> assertEquals(fakeBusinessName, result.get(0).getBusinessName()),
                () -> assertEquals(fakePhoneNumber, result.get(0).getPhoneNumber()),
                () -> assertEquals(fakeEmail, result.get(0).getEmail()));
    }

    @Test
    void findByBusinessName() {

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
        when(mockCustomerRepository.findByBusinessName(fakeBusinessName))
                .thenReturn(List.of(fakeCustomer));
        List<CustomerDto> result = customerController.findByBusinessName(fakeBusinessName);

        // then
        assertAll(
                () -> assertNotNull(result),
                () -> assertFalse(result.isEmpty()),
                () -> assertEquals(1, result.size()),
                () -> verify(mockCustomerRepository).findByBusinessName(fakeBusinessName),
                () -> assertEquals(fakeCustomerId, result.get(0).getId()),
                () -> assertEquals(fakeFirstName, result.get(0).getFirstName()),
                () -> assertEquals(fakeLastName, result.get(0).getLastName()),
                () -> assertEquals(fakeBusinessName, result.get(0).getBusinessName()),
                () -> assertEquals(fakePhoneNumber, result.get(0).getPhoneNumber()),
                () -> assertEquals(fakeEmail, result.get(0).getEmail()));
    }

    @Test
    void findByEmail() {

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
        when(mockCustomerRepository.findByEmail(fakeEmail)).thenReturn(List.of(fakeCustomer));
        List<CustomerDto> result = customerController.findByEmail(fakeEmail);

        // then
        assertAll(
                () -> assertNotNull(result),
                () -> assertFalse(result.isEmpty()),
                () -> assertEquals(1, result.size()),
                () -> verify(mockCustomerRepository).findByEmail(fakeEmail),
                () -> assertEquals(fakeCustomerId, result.get(0).getId()),
                () -> assertEquals(fakeFirstName, result.get(0).getFirstName()),
                () -> assertEquals(fakeLastName, result.get(0).getLastName()),
                () -> assertEquals(fakeBusinessName, result.get(0).getBusinessName()),
                () -> assertEquals(fakePhoneNumber, result.get(0).getPhoneNumber()),
                () -> assertEquals(fakeEmail, result.get(0).getEmail()));
    }

    @Test
    void findByPhoneNumber() {

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
        when(mockCustomerRepository.findByPhoneNumber(fakePhoneNumber))
                .thenReturn(List.of(fakeCustomer));
        List<CustomerDto> result = customerController.findByPhoneNumber(fakePhoneNumber);

        // then
        assertAll(
                () -> assertNotNull(result),
                () -> assertFalse(result.isEmpty()),
                () -> assertEquals(1, result.size()),
                () -> verify(mockCustomerRepository).findByPhoneNumber(fakePhoneNumber),
                () -> assertEquals(fakeCustomerId, result.get(0).getId()),
                () -> assertEquals(fakeFirstName, result.get(0).getFirstName()),
                () -> assertEquals(fakeLastName, result.get(0).getLastName()),
                () -> assertEquals(fakeBusinessName, result.get(0).getBusinessName()),
                () -> assertEquals(fakePhoneNumber, result.get(0).getPhoneNumber()),
                () -> assertEquals(fakeEmail, result.get(0).getEmail()));
    }

    @Test
    void deleteCustomerById() {

        // given
        Long fakeCustomerId = 1L;

        // when
        customerController.deleteCustomerById(fakeCustomerId);

        // then
        verify(mockCustomerRepository).deleteCustomerById(fakeCustomerId);
    }

    @Test
    void createCustomer() {

        // given
        Long fakeCustomerId = 1L;
        String fakeFirstName = "fakeFirstName";
        String fakeLastName = "fakeLastName";
        String fakeBusinessName = "fakeBusinessName";
        String fakePhoneNumber = "fakePhoneNumber";
        String fakeEmail = "fakeEmail";
        String fakeCustomerString =
                "Customer(id=1, firstName=fakeFirstName, lastName=fakeLastName,"
                        + " businessName=fakeBusinessName, phoneNumber=fakePhoneNumber,"
                        + " email=fakeEmail)";
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
        when(mockCustomerService.createCustomer(fakeCustomerString)).thenReturn(fakeCustomer);
        CustomerDto result = customerController.createCustomer(fakeCustomerString);

        // then
        assertAll(
                () -> assertNotNull(result),
                () -> verify(mockCustomerService).createCustomer(fakeCustomerString),
                () -> assertEquals(fakeCustomerId, result.getId()),
                () -> assertEquals(fakeFirstName, result.getFirstName()),
                () -> assertEquals(fakeLastName, result.getLastName()),
                () -> assertEquals(fakeBusinessName, result.getBusinessName()),
                () -> assertEquals(fakePhoneNumber, result.getPhoneNumber()),
                () -> assertEquals(fakeEmail, result.getEmail()));
    }

    @Test
    void replaceCustomer() {

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
        when(mockCustomerService.replaceCustomer(fakeCustomerId, fakeCustomerDto))
                .thenReturn(fakeCustomer);
        CustomerDto result = customerController.replaceCustomer(fakeCustomerId, fakeCustomerDto);

        // then
        assertAll(
                () -> assertNotNull(result),
                () -> verify(mockCustomerService).replaceCustomer(fakeCustomerId, fakeCustomerDto),
                () -> assertEquals(fakeCustomerId, result.getId()),
                () -> assertEquals(fakeFirstName, result.getFirstName()),
                () -> assertEquals(fakeLastName, result.getLastName()),
                () -> assertEquals(fakeBusinessName, result.getBusinessName()),
                () -> assertEquals(fakePhoneNumber, result.getPhoneNumber()),
                () -> assertEquals(fakeEmail, result.getEmail()));
    }

    @Test
    void getCompleteCustomer() {

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

        CustomerAddress fakeCustomerAddress =
                CustomerAddress.builder()
                        .id(2L)
                        .customerId(fakeCustomerId)
                        .addressId(fakeAddressId)
                        .defaultAddress(true)
                        .build();

        // when
        when(mockCustomerRepository.findById(fakeCustomerId))
                .thenReturn(Optional.ofNullable(fakeCustomer));
        when(mockCustomerAddressRepository.findByCustomerIdWhereDefaultAddressIsTrue(
                        fakeCustomerId))
                .thenReturn(Optional.ofNullable(fakeCustomerAddress));
        when(mockCustomerAddressRepository.findByCustomerId(fakeCustomerId))
                .thenReturn(List.of(fakeCustomerAddress));
        when(mockAddressRepository.findAddressById(fakeAddressId))
                .thenReturn(Optional.ofNullable(fakeAddress));
        when(mockAddressRepository.findAllById(List.of(fakeAddressId)))
                .thenReturn(List.of(fakeAddress));

        CompleteCustomerDto result = customerController.getCompleteCustomer(fakeCustomerId);

        // then
        assertAll(
                () -> assertNotNull(result),
                () -> verify(mockCustomerRepository).findById(fakeCustomerId),
                () ->
                        verify(mockCustomerAddressRepository)
                                .findByCustomerIdWhereDefaultAddressIsTrue(fakeCustomerId),
                () -> verify(mockAddressRepository).findAddressById(fakeCustomerId),
                () -> verify(mockAddressRepository).findAllById(anyList()),
                () -> assertEquals(fakeCustomerId, result.getCustomerDto().getId()),
                () -> assertEquals(fakeFirstName, result.getCustomerDto().getFirstName()),
                () -> assertEquals(fakeLastName, result.getCustomerDto().getLastName()),
                () -> assertEquals(fakeBusinessName, result.getCustomerDto().getBusinessName()),
                () -> assertEquals(fakePhoneNumber, result.getCustomerDto().getPhoneNumber()),
                () -> assertEquals(fakeEmail, result.getCustomerDto().getEmail()),
                () -> assertNotNull(result.getDefaultAddress()),
                () -> assertEquals(fakeAddressId, result.getDefaultAddress().getId()),
                () -> assertEquals(fakeAddress1, result.getDefaultAddress().getAddress1()),
                () -> assertEquals(fakeAddress2, result.getDefaultAddress().getAddress2()),
                () -> assertEquals(fakeCity, result.getDefaultAddress().getCity()),
                () -> assertEquals(fakeState, result.getDefaultAddress().getState()),
                () -> assertEquals(fakeZipcode, result.getDefaultAddress().getZipcode()),
                () ->
                        assertEquals(
                                fakeAddressPhoneNumber,
                                result.getDefaultAddress().getPhoneNumber()),
                () -> assertNotNull(result.getAddressList()),
                () -> assertFalse(result.getAddressList().isEmpty()),
                () -> assertEquals(1, result.getAddressList().size()),
                () -> assertNotNull(result.getAddressList().get(0)),
                () -> assertEquals(fakeAddressId, result.getAddressList().get(0).getId()),
                () -> assertEquals(fakeAddressId, result.getAddressList().get(0).getId()),
                () -> assertEquals(fakeAddress1, result.getAddressList().get(0).getAddress1()),
                () -> assertEquals(fakeAddress2, result.getAddressList().get(0).getAddress2()),
                () -> assertEquals(fakeCity, result.getAddressList().get(0).getCity()),
                () -> assertEquals(fakeState, result.getAddressList().get(0).getState()),
                () -> assertEquals(fakeZipcode, result.getAddressList().get(0).getZipcode()),
                () ->
                        assertEquals(
                                fakeAddressPhoneNumber,
                                result.getAddressList().get(0).getPhoneNumber()));
    }
}
