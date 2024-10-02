package com.dapg.handmade.marketplace.engine.iam.infrastructure;

import org.springframework.stereotype.Service;
import com.dapg.handmade.marketplace.engine.iam.domain.CreatedArtisanEvent;
import com.dapg.handmade.marketplace.engine.iam.domain.CreatedCustomerEvent;
import com.dapg.handmade.marketplace.engine.iam.domain.EventPublisher;

@Service
public class KafkaEventPublisher implements EventPublisher {

  @Override
  public void publish(CreatedArtisanEvent event) {
    // TODO Auto-generated method stub

  }

  @Override
  public void publish(CreatedCustomerEvent event) {
    // TODO Auto-generated method stub

  }


}
