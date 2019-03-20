package com.codependent.mutualauth.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Destination implements Serializable {
  private static final long serialVersionUID = 3505573878335711861L;
  @JsonProperty("isdCountryCode")
  private String countryCode;
  @JsonProperty("phoneNumbers")
  private List<String> phoneNumbers = new ArrayList();

  public Destination() {
  }

  public String getCountryCode() {
    return this.countryCode;
  }

  public List<String> getPhoneNumbers() {
    return this.phoneNumbers;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public void setPhoneNumbers(List<String> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  public boolean equals(Object o) {
    if(o == this) {
      return true;
    } else if(!(o instanceof Destination)) {
      return false;
    } else {
      Destination other = (Destination)o;
      if(!other.canEqual(this)) {
        return false;
      } else {
        Object this$countryCode = this.getCountryCode();
        Object other$countryCode = other.getCountryCode();
        if(this$countryCode == null) {
          if(other$countryCode != null) {
            return false;
          }
        } else if(!this$countryCode.equals(other$countryCode)) {
          return false;
        }

        Object this$phoneNumbers = this.getPhoneNumbers();
        Object other$phoneNumbers = other.getPhoneNumbers();
        if(this$phoneNumbers == null) {
          if(other$phoneNumbers != null) {
            return false;
          }
        } else if(!this$phoneNumbers.equals(other$phoneNumbers)) {
          return false;
        }

        return true;
      }
    }
  }

  protected boolean canEqual(Object other) {
    return other instanceof Destination;
  }

  public int hashCode() {

    int result = 1;
    Object $countryCode = this.getCountryCode();
    result = result * 59 + ($countryCode == null?43:$countryCode.hashCode());
    Object $phoneNumbers = this.getPhoneNumbers();
    result = result * 59 + ($phoneNumbers == null?43:$phoneNumbers.hashCode());
    return result;
  }

  public String toString() {
    return "Destination(countryCode=" + this.getCountryCode() + ", phoneNumbers=" + this.getPhoneNumbers() + ")";
  }
}
