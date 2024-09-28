package com.dapg.handmade.marketplace.engine.iam.domain;

public interface EventPublisher {
  void publish(CreatedArtisanEvent event);

  void publish(CreatedCustomerEvent event);
}
