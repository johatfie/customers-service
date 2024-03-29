package com.widgets_are_us.customers_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CompleteCustomer {

    private Customer customer;

    private Address defaultAddress;

    private List<Address> addressList;

    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final ObjectMapper mapper = new ObjectMapper();

    public String toJson() {
        try {
            log.info("Mapping complete customer to json");
            return mapper.writeValueAsString(this);
        }
        catch(Exception e) {
            log.error(e.getClass().getName(), e.getMessage());
            return "";
        }
    }

}
