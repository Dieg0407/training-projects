package com.dapg.handmade.marketplace.engine.iam.domain;

public record PhoneNumber(String countryCode, String number) {

  public String toString() {
    return countryCode + number;
  }

}
