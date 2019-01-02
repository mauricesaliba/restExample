package io.code.challenge.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * MobileSubscriberDto
 */
@Validated


public class MobileSubscriberDto   {
  
	
  @JsonProperty("id")
  private Long id = null;

  
  @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$",message="Bad request / failed validation. not compliant with E164 format.")
  @JsonProperty("msisdn")
  private String msisdn = null;

  @JsonProperty("customer_id_owner")
  private Long customerIdOwner = null;

  @JsonProperty("customer_id_user")
  private Long customerIdUser = null;

  /**
   * An enum defining the type of service.
   */
  public enum ServiceTypeEnum {
    PREPAID("MOBILE_PREPAID"),
    
    POSTPAID("MOBILE_POSTPAID");

    private String value;

    ServiceTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ServiceTypeEnum fromValue(String text) {
      for (ServiceTypeEnum b : ServiceTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @NotNull(message="Bad request / failed validation.")
  @JsonProperty("service_type")
  private ServiceTypeEnum serviceType = null;

  @JsonProperty("service_start_date")
  private Long serviceStartDate = null;
  
  

  public MobileSubscriberDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Unique ID for this Mobile Number.
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Unique ID for this Mobile Number.")
  @NotNull
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MobileSubscriberDto msisdn(String msisdn) {
    this.msisdn = msisdn;
    return this;
  }

  /**
   * The mobile number in E164 format.
   * @return msisdn
  **/
  @ApiModelProperty(example = "35699123456", required = true, value = "The mobile number in E164 format.")
  @NotNull
  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public MobileSubscriberDto customerIdOwner(Long customerIdOwner) {
    this.customerIdOwner = customerIdOwner;
    return this;
  }

  /**
   * The ID referencing the owner of this mobile number.
   * @return customerIdOwner
  **/
  @ApiModelProperty(required = true, value = "The ID referencing the owner of this mobile number.")
  @NotNull
  public Long getCustomerIdOwner() {
    return customerIdOwner;
  }

  public void setCustomerIdOwner(Long customerIdOwner) {
    this.customerIdOwner = customerIdOwner;
  }

  public MobileSubscriberDto customerIdUser(Long customerIdUser) {
    this.customerIdUser = customerIdUser;
    return this;
  }

  /**
   * The ID referencing the user of this mobile number.
   * @return customerIdUser
  **/
  @ApiModelProperty(required = true, value = "The ID referencing the user of this mobile number.")
  @NotNull
  public Long getCustomerIdUser() {
    return customerIdUser;
  }

  public void setCustomerIdUser(Long customerIdUser) {
    this.customerIdUser = customerIdUser;
  }

  public MobileSubscriberDto serviceType(ServiceTypeEnum serviceType) {
    this.serviceType = serviceType;
    return this;
  }

  /**
   * An enum defining the type of service.
   * @return serviceType
  **/
  @ApiModelProperty(required = true, value = "An enum defining the type of service.")
  @NotNull
  public ServiceTypeEnum getServiceType() {
    return serviceType;
  }

  public void setServiceType(ServiceTypeEnum serviceType) {
    this.serviceType = serviceType;
  }

  public MobileSubscriberDto serviceStartDate(Long serviceStartDate) {
    this.serviceStartDate = serviceStartDate;
    return this;
  }

  /**
   * The time mobile number was created, encoded in Unix Epoch in Milliseconds (UTC).
   * @return serviceStartDate
  **/
  @ApiModelProperty(example = "1528208058557", required = true, value = "The time mobile number was created, encoded in Unix Epoch in Milliseconds (UTC).")
  @NotNull


  public Long getServiceStartDate() {
    return serviceStartDate;
  }

  public void setServiceStartDate(Long serviceStartDate) {
    this.serviceStartDate = serviceStartDate;
  }
  
  



  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MobileSubscriberDto mobileSubscriber = (MobileSubscriberDto) o;
    return Objects.equals(this.id, mobileSubscriber.id) &&
        Objects.equals(this.msisdn, mobileSubscriber.msisdn) &&
        Objects.equals(this.customerIdOwner, mobileSubscriber.customerIdOwner) &&
        Objects.equals(this.customerIdUser, mobileSubscriber.customerIdUser) &&
        Objects.equals(this.serviceType, mobileSubscriber.serviceType) &&
        Objects.equals(this.serviceStartDate, mobileSubscriber.serviceStartDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, msisdn, customerIdOwner, customerIdUser, serviceType, serviceStartDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MobileSubscriberDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    msisdn: ").append(toIndentedString(msisdn)).append("\n");
    sb.append("    customerIdOwner: ").append(toIndentedString(customerIdOwner)).append("\n");
    sb.append("    customerIdUser: ").append(toIndentedString(customerIdUser)).append("\n");
    sb.append("    serviceType: ").append(toIndentedString(serviceType)).append("\n");
    sb.append("    serviceStartDate: ").append(toIndentedString(serviceStartDate)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

