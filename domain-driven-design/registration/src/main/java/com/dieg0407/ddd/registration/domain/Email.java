package com.dieg0407.ddd.registration.domain;

import org.springframework.util.Assert;

import java.util.regex.Pattern;

public class Email {
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    private String validEmail;

    Email() {

    }

    public Email(String rawEmail) {
        Assert.notNull(rawEmail, "The 'email' can't be null");
        if (!emailIsFormatedCorrectly(rawEmail)) {
            throw new IllegalArgumentException("The provided email '%s' is not valid".formatted(rawEmail));
        }
        this.validEmail = rawEmail;
    }

    private boolean emailIsFormatedCorrectly(String rawEmail) {
        // TLDR: Using regex for email validate tends to not be recommended
        // but given that this is a demo project I'll just use it to speed things up
        // in prod a more robust solution would need to be used.

        final var pattern = Pattern.compile(EMAIL_REGEX);
        final var matcher = pattern.matcher(rawEmail);

        return matcher.matches();
    }

    public String getValidEmail() {
        return validEmail;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((validEmail == null) ? 0 : validEmail.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Email other = (Email) obj;
        if (validEmail == null) {
            if (other.validEmail != null)
                return false;
        } else if (!validEmail.equals(other.validEmail))
            return false;
        return true;
    }

}
