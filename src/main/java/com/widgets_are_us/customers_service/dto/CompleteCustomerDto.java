package com.widgets_are_us.customers_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.widgets_are_us.customers_service.models.Address;
import com.widgets_are_us.customers_service.models.CompleteCustomer;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Schema(description = "Complete Customer DTO Information")
@Log4j2
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CompleteCustomerDto {

    public CompleteCustomerDto(CompleteCustomer completeCustomer) {
        this.customerDto = new CustomerDto(completeCustomer.getCustomer());
        this.defaultAddress = new AddressDto(completeCustomer.getDefaultAddress());
        this.addressList =
                completeCustomer.getAddressList().stream()
                        .map(
                                (Address address) -> {
                                    return new AddressDto(address);
                                })
                        .toList();
    }

    @Schema(description = "The customer")
    private CustomerDto customerDto;

    @Schema(description = "The default address of the customer")
    private AddressDto defaultAddress;

    @Schema(description = "List of the addresses associated with the customer")
    @Builder.Default
    private List<AddressDto> addressList = new ArrayList<>();

    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final ObjectMapper mapper = new ObjectMapper();

    public String toJson() {
        try {
            log.info("Mapping complete customer to json");
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            log.error(e.getClass().getName(), e.getMessage());
            return "";
        }
    }
}
