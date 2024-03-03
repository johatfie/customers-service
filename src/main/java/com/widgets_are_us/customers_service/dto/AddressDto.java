package com.widgets_are_us.customers_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.widgets_are_us.customers_service.models.Address;
import io.swagger.v3.oas.annotations.media.Schema;
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

@Schema(description = "Address DTO Information")
@Log4j2
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

  public AddressDto(Address address) {
    this.id = address.getId();
    this.address1 = address.getAddress1();
    this.address2 = address.getAddress2();
    this.city = address.getCity();
    this.state = address.getState();
    this.zipcode = address.getZipcode();
    this.phoneNumber = address.getPhoneNumber();
  }

  @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Address Id", example = "123")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Schema(description = "Street Address", example = "123 Main Street")
  private String address1;

  @Schema(description = "Address second line", example = "Suite 100")
  private String address2;

  @Schema(description = "City", example = "Indianapolis")
  private String city;

  @Schema(description = "State", example = "Indiana")
  private String state;

  @Schema(description = "Zipcode", example = "90210")
  private String zipcode;

  @Schema(description = "Telephone number", example = "3175551512")
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
    } catch (Exception e) {
      log.error(e.getClass().getName(), e.getMessage());
      return "";
    }
  }

  public static AddressDto fromJson(String json) {
    try {
      log.info("Mapping Address from json");
      return mapper.readValue(json, AddressDto.class);
    } catch (Exception e) {
      log.error(e.getClass().getName(), e.getMessage());
      return new AddressDto();
    }
  }
}
