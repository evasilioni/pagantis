package com.silionie.enums;

public enum TransferMessageCode {
    OK ("Transfer completed"),
    NOT_COMPLETED ("Transfer could not be completed"),
    TRANSFER_LIMIT("User has a limit of 1000€ per transfer"),
    ERROR_ON_BALANCE("User's balance account does not meet the requirements of transfer"),
    ERROR_ON_ACCOUNT("Account does not exist");

    private final String name;

    TransferMessageCode(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName){
        return name.equals(otherName);
    }

    public String toString(){
        return this.name;
    }
}
