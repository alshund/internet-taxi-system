package by.tr.web.service.impl;

import by.tr.web.domain.AuthenticationData;
import by.tr.web.domain.HashData;
import by.tr.web.service.HashService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class HashServiceImpl implements HashService {
    private final String algorithm = "SHA-1";
    private final int radix = 16;

    @Override
    public HashData formHashData(AuthenticationData authenticationData) {

        HashData hashData = new HashData();
        hashData.setSalt(generateSalt(authenticationData.getPassword().length()));
        hashData.setPasswordHash(createPasswordHash(authenticationData.getPassword(), hashData.getSalt()));
        return hashData;
    }

    @Override
    public String createPasswordHash(String password, String salt) {

        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update((password + salt).getBytes());
            return new BigInteger(digest.digest()).toString(radix);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    private String generateSalt(int passwordHashLength) {

        Random random = new SecureRandom();
        byte bytes[] = new byte[passwordHashLength];
        random.nextBytes(bytes);
        return new BigInteger(bytes).toString(radix);
    }
}
