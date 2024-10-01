package com.dapg.handmade.marketplace.engine.iam.application.command;

import com.dapg.handmade.marketplace.engine.iam.domain.CraftType;

public record CreateNewArtisanCommand(String username, String password, String shopName,
        CraftType craftType, String countryCode, String phoneNumber) {
}
