package com.widgets_are_us.customers_service.controllers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.widgets_are_us.customers_service.dto.CustomerDto;
import com.widgets_are_us.customers_service.exceptions.CustomerNotFoundException;
import com.widgets_are_us.customers_service.models.Customer;
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
}
