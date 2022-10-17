package com.example.wallet.ewallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WalletRepository walletRepository;

    @KafkaListener(topics = CommonConstants.USER_CREATION_TOPIC, groupId = "group123")
    public void createWallet(String message){
        JSONObject jsonObject = new JSONObject();
        JSONObject data = objectMapper.convertValue(message, JSONObject.class);

        String phoneNumber = (String) data.get(CommonConstants.USER_CREATION_TOPIC_PHONE_NUMBER);
        Long userId = (Long) data.get(CommonConstants.USER_CREATION_TOPIC_USERID);
        String identifierValue = (String) data.get(CommonConstants.USER_CREATION_TOPIC_IDENTIFIER_VALUE);
        String userIdentifier = (String) data.get(CommonConstants.USER_CREATION_TOPIC_IDENTIFIER_KEY);

        Wallet wallet = Wallet.builder()
                .phoneNumber(phoneNumber)
                .userId(userId)
                .userIdentifier(UserIdentifier.valueOf(userIdentifier))
                .identifierValue(identifierValue)
                .build();

        walletRepository.save(wallet);

    }

}
