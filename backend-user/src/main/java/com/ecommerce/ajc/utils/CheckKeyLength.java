package com.ecommerce.ajc.utils;

import java.util.Base64;

public class CheckKeyLength {
    public static void main(String[] args) {
        String base64Key = "6JoST51hCabkeAKV+LaWIe0dagbSyT/3HeyKIhANC6ITfwU/Ugx4q+mV/D/WCeIK6Bsz7sIJ4mjYB6u2nOqhag==";
        byte[] decoded = Base64.getDecoder().decode(base64Key);
        System.out.println("Longueur de la clé décodée (en octets) : " + decoded.length);
    }
}
