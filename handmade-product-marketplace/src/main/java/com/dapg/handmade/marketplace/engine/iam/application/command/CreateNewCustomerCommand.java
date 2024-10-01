package com.dapg.handmade.marketplace.engine.iam.application.command;

public record CreateNewCustomerCommand(String username, String password, String fullName,
    String address) {
}
