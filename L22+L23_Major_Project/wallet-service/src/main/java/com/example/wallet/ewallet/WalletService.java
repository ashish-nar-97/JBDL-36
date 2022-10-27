package com.example.wallet.ewallet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = CommonConstants.USER_CREATION_TOPIC, groupId = "group123")
    public void createWallet(String message) throws ParseException {
        JSONObject data = (JSONObject) new JSONParser().parse(message);
//        JSONObject jsonObject = new JSONObject();
//        JSONObject data = objectMapper.convertValue(message, JSONObject.class);

        String phoneNumber = (String) data.get(CommonConstants.USER_CREATION_TOPIC_PHONE_NUMBER);
        Long userId = (Long) data.get(CommonConstants.USER_CREATION_TOPIC_USERID);
        String identifierValue = (String) data.get(CommonConstants.USER_CREATION_TOPIC_IDENTIFIER_VALUE);
        String userIdentifier = (String) data.get(CommonConstants.USER_CREATION_TOPIC_IDENTIFIER_KEY);

        Wallet wallet = Wallet.builder()
                .phoneNumber(phoneNumber)
                .userId(userId)
                .userIdentifier(UserIdentifier.valueOf(userIdentifier))
                .identifierValue(identifierValue)
                .balance(10.0)
                .build();

        walletRepository.save(wallet);

    }

    @KafkaListener(topics = CommonConstants.TRANSACTION_CREATION_TOPIC, groupId = "group123")
    public void updatedWalletForTransaction(String message) throws ParseException, JsonProcessingException {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(message);
        String sender = (String) jsonObject.get("sender");
        String receiver = (String) jsonObject.get("receiver");
        Double amount = (Double) jsonObject.get("amount");
        String transactionId = (String) jsonObject.get("transactionId");
        Wallet senderWallet = walletRepository.findByPhoneNumber(sender);
        Wallet receiverWallet = walletRepository.findByPhoneNumber(receiver);
        JSONObject object = new JSONObject();
        object.put("transactionId", transactionId);
        object.put("sender", sender);
        object.put("receiver", receiver);
        // TODO: Do we need amount ?
        object.put("amount", amount);

        if(senderWallet == null || receiverWallet == null || senderWallet.getBalance() < amount){
            // fail this transaction.
            object.put("walletUpdateStatus", WalletUpdateStatus.FAILED);
            kafkaTemplate.send(CommonConstants.TRANSACTION_UPDATE_TOPIC, objectMapper.writeValueAsString(object));
            return;
        }

        walletRepository.updateWallet(receiver, amount);
        walletRepository.updateWallet(sender, 0-amount);
        object.put("walletUpdateStatus", WalletUpdateStatus.SUCCESS);

        // TODO: produce an event for updating txn.
        kafkaTemplate.send(CommonConstants.WALLET_UPDATED_TOPIC, objectMapper.writeValueAsString(object));

    }

}
