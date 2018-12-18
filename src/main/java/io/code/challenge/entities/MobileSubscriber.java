package io.code.challenge.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MOBILE_SUBSCRIBERS")

public class MobileSubscriber  {

  @Id
  //@GeneratedValue(strategy= GenerationType.IDENTITY)	
  private Long id;

  @Column(name="MSISDN")
  private String msisdn;

  @Column(name="CUSTOMER_ID_OWNER")
  private Long customerIdOwner;

  @Column(name="CUSTOMER_ID_USER")
  private Long customerIdUser;


  //create new table and link
  @Column(name="SERVICE_TYPE")
  private String serviceType;


  @Column(name="SERVICE_START_DATE")
  private Long serviceStartDate;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public Long getCustomerIdOwner() {
    return customerIdOwner;
  }

  public void setCustomerIdOwner(Long customerIdOwner) {
    this.customerIdOwner = customerIdOwner;
  }


  public Long getCustomerIdUser() {
    return customerIdUser;
  }

  public void setCustomerIdUser(Long customerIdUser) {
    this.customerIdUser = customerIdUser;
  }

  public String getServiceType() {
    return serviceType;
  }

  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  public Long getServiceStartDate() {
    return serviceStartDate;
  }

  public void setServiceStartDate(Long serviceStartDate) {
    this.serviceStartDate = serviceStartDate;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MobileSubscriber {\n");
    
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

