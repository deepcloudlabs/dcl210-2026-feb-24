package com.example.security;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

import javax.crypto.DecapsulateException;
import javax.crypto.KEM;
import javax.crypto.SecretKey;

public class Exercise01 {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, DecapsulateException {
		// PQC
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("ML-KEM");
		KeyPair kp = kpg.generateKeyPair();

		System.out.println("Algorithm: " + kp.getPublic().getAlgorithm());
		System.out.println("Public Key Format: " + kp.getPublic().getFormat());
		
		KEM kemSender = KEM.getInstance("ML-KEM");
        KEM.Encapsulator encapsulator = kemSender.newEncapsulator(kp.getPublic());
        KEM.Encapsulated encapsulated = encapsulator.encapsulate();

        SecretKey sharedSecretSender = encapsulated.key();
        byte[] ciphertext = encapsulated.encapsulation();
        
        System.out.println("\n--- Encapsulation (Sender) ---");
        System.out.println("Ciphertext: " + HexFormat.of().formatHex(ciphertext));
        System.out.println("Shared Secret (Sender): " + HexFormat.of().formatHex(sharedSecretSender.getEncoded()));
        
        KEM kemReceiver = KEM.getInstance("ML-KEM");
        KEM.Decapsulator decapsulator = kemReceiver.newDecapsulator(kp.getPrivate());
        SecretKey sharedSecretReceiver = decapsulator.decapsulate(ciphertext);

        System.out.println("\n--- Decapsulation (Receiver) ---");
        System.out.println("Shared Secret (Receiver): " + HexFormat.of().formatHex(sharedSecretReceiver.getEncoded()));       
        
        if (MessageDigest.isEqual(sharedSecretSender.getEncoded(), sharedSecretReceiver.getEncoded())) {
            System.out.println("\nSUCCESS: Quantum-resistant shared secret established.");
        }        
	}

}
