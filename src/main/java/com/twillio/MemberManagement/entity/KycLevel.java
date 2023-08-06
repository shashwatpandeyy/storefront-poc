package com.twillio.MemberManagement.entity;

public enum KycLevel {
    NOT_VERIFIED("Not Verified"),
    BASIC("Basic"),
    PRO("Pro");

    final String name;

    KycLevel(String name) {
        this.name = name;
    }
}
