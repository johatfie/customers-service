package com.widgets_are_us.customers_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.widgets_are_us.customers_service.dto.CustomerDto;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Schema(description = "Customer Model Information")
@Log4j2
@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    public Customer(CustomerDto customerDto) {
        this.id = customerDto.getId();
        this.firstName = customerDto.getFirstName();
        this.lastName = customerDto.getLastName();
        this.businessName = customerDto.getBusinessName();
        this.phoneNumber = customerDto.getPhoneNumber();
        this.email = customerDto.getEmail();
    }

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Customer Id", example = "123")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "First name of customer", example = "Clevon")
    private String firstName;

    @Schema(description = "Last name of customer", example = "Smith")
    private String lastName;

    @Schema(description = "Name of the customer's business", example = "Acme Widgets")
    private String businessName;

    @Schema(description = "Telephone number of customer", example = "3175551212")
    private String phoneNumber;

    @Schema(description = "Email address of customer", example = "acme.widgets@fakedomain.com")
    private String email;

    @Transient
    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final ObjectMapper mapper = new ObjectMapper();

    public String toJson() {
        try {
            log.info("Mapping customer to json");
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            log.error(e.getClass().getName(), e.getMessage());
            return "";
        }
    }

    public static Customer fromJson(String json) {
        try {
            log.info("Mapping customer from json");
            return mapper.readValue(json, Customer.class);
        } catch (Exception e) {
            log.error(e.getClass().getName(), e.getMessage());
            return null;
        }
    }
}
