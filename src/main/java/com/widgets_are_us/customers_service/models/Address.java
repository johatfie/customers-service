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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Log4j2
@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String zipcode;

    private String phoneNumber;

    @Transient
    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final ObjectMapper mapper = new ObjectMapper();

    public String toJson() {
        try {
            log.info("Mapping Address to json");
            return mapper.writeValueAsString(this);
        }
        catch(Exception e) {
            log.error(e.getClass().getName(), e.getMessage());
            return "";
        }
    }

    public static Address fromJson(String json) {
        try {
            log.info("Mapping Address from json");
            return mapper.readValue(json, Address.class);
        }
        catch(Exception e) {
            log.error(e.getClass().getName(), e.getMessage());
            return new Address();
        }
    }

}
