package com.widgets_are_us.customers_service.controllers;

import com.widgets_are_us.customers_service.dto.AddressDto;
import com.widgets_are_us.customers_service.exceptions.AddressNotFoundException;
import com.widgets_are_us.customers_service.models.Address;
import com.widgets_are_us.customers_service.repositories.AddressRepository;
import com.widgets_are_us.customers_service.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Address", description = "Address APIs")
@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/v1/address")
public class AddressController {

    private final AddressRepository addressRepository;
    private final AddressService addressService;

    @Operation(
            summary = "Retrieve an address by Id",
            description = "Get an address by specifying its id")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                content = {
                    @Content(
                            schema = @Schema(implementation = AddressDto.class),
                            mediaType = "application/json")
                }),
        @ApiResponse(
                responseCode = "404",
                description = "The address was not found",
                content = {@Content(schema = @Schema())}),
        @ApiResponse(
                responseCode = "500",
                content = {@Content(schema = @Schema())})
    })
    @ResponseBody
    @GetMapping(value = "/{id}")
    public Address findAddressById(@PathVariable(value = "id") Long id) {
        return addressRepository
                .findAddressById(id)
                .orElseThrow(
                        () ->
                                new AddressNotFoundException(
                                        AddressService.ADDRESS_NOT_FOUND_FOR_THIS_ID.formatted(
                                                id)));
    }

    @Operation(
            summary = "Delete an Address by Id",
            description = "Delete a Address by specifying its id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = @Content(schema = @Schema())),
        @ApiResponse(
                responseCode = "404",
                description = "The address was not found",
                content = {@Content(schema = @Schema())}),
        @ApiResponse(
                responseCode = "500",
                content = {@Content(schema = @Schema())})
    })
    @ResponseBody
    @DeleteMapping(value = "/{id}")
    public void deleteAddressById(@PathVariable(value = "id") Long id) {
        addressRepository.deleteAddressById(id);
    }

    @Operation(
            summary = "Create a new Address",
            description = "Create a Address by specifying its id.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                content = {
                    @Content(
                            schema = @Schema(implementation = AddressDto.class),
                            mediaType = "application/json")
                }),
        @ApiResponse(
                responseCode = "500",
                content = {@Content(schema = @Schema())})
    })
    @ResponseBody
    @PostMapping(value = "/new", consumes = "application/json")
    public Address createAddress(@RequestParam("address") String address) {
        return addressService.createAddress(address);
    }

    @Operation(summary = "Update an Address", description = "Replace an Address object")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                content = {
                    @Content(
                            schema = @Schema(implementation = AddressDto.class),
                            mediaType = "application/json")
                }),
        @ApiResponse(
                responseCode = "404",
                description = "The address was not found",
                content = {@Content(schema = @Schema())}),
        @ApiResponse(
                responseCode = "500",
                content = {@Content(schema = @Schema())})
    })
    @ResponseBody
    @PutMapping(value = "/{id}")
    public Address updateAddress(
            @PathVariable(value = "id") Long id, @RequestBody AddressDto addressDto) {
        return addressService.replaceAddress(id, new Address(addressDto));
    }
}
