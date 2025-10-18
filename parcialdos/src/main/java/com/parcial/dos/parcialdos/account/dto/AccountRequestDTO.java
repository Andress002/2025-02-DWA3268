package com.parcial.dos.parcialdos.account.dto;

public class AccountRequestDTO {
    private String numeroCuenta;
    private String dueño;
    private Double balanceActual;

    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public String getDueño() { return dueño; }
    public void setDueño(String dueño) { this.dueño = dueño; }

    public Double getBalanceActual() { return balanceActual; }
    public void setBalanceActual(Double balanceActual) { this.balanceActual = balanceActual; }
}
