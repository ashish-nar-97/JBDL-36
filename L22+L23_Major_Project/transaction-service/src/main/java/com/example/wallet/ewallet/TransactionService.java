package com.example.wallet.ewallet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class TransactionService implements UserDetailsService {


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // somehow this method have to return UserDetails.

        JSONObject requestedUser = getUserFromUserService(username);
        List<LinkedHashMap<String, String>> requestedAuthorities = (List<LinkedHashMap<String, String>>) requestedUser.get("authorities");

        List<SimpleGrantedAuthority> authorities = requestedAuthorities.stream()
                .map(x -> x.get("authority"))
                .map(x -> new SimpleGrantedAuthority(x))
                .collect(Collectors.toList());
        return new User((String) requestedUser.get("username"), (String) requestedUser.get("password"), authorities);
    }

    public String initiateTransaction(String sender, String receiver, String purpose, Double amount) throws JsonProcessingException {

        Transaction transaction = Transaction.builder()
                .sender(sender)
                .receiver(receiver)
                .amount(amount)
                .transactionId(UUID.randomUUID().toString())
                .purpose(purpose)
                .transactionStatus(TransactionStatus.PENDING)
                .build();

        transactionRepository.save(transaction);

        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sender", sender);
        jsonObject.put("receiver", receiver);
        jsonObject.put("amount", amount);
        jsonObject.put("transactionId", transaction.getTransactionId());

        kafkaTemplate.send(CommonConstants.TRANSACTION_CREATION_TOPIC, objectMapper.writeValueAsString(jsonObject));

        return transaction.getTransactionId();
    }

    @KafkaListener(topics = CommonConstants.WALLET_UPDATED_TOPIC, groupId = "group123")
    public void updateTxn(String message) throws ParseException, JsonProcessingException {
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(message);
        Double amount = (Double) jsonObject.get("amount");
        String transactionId = (String) jsonObject.get("transactionId");
        String sender = (String) jsonObject.get("sender");
        String receiver = (String) jsonObject.get("receiver");
        WalletUpdateStatus walletUpdateStatus = WalletUpdateStatus.valueOf((String) jsonObject.get("walletUpdateStatus"));
        JSONObject senderObj = getUserFromUserService(sender);
        String senderEmail = (String) senderObj.get("email");
        String receiverEmail = null;

        if (walletUpdateStatus == WalletUpdateStatus.SUCCESS) {
            JSONObject receiverObj = getUserFromUserService(receiver);
            receiverEmail = (String) receiverObj.get("email");
            transactionRepository.updateTransaction(transactionId, TransactionStatus.SUCCESS);
        } else {
            transactionRepository.updateTransaction(transactionId, TransactionStatus.FAILED);
        }
        String senderMessage = "Hi, Your transaction with id : " + transactionId + " got " + walletUpdateStatus;
        JSONObject senderEmailObj = new JSONObject();
        senderEmailObj.put("email", senderEmail);
        senderEmailObj.put("senderMessage", senderMessage);

        kafkaTemplate.send(CommonConstants.TRANSACTION_COMPLETED_TOPIC, objectMapper.writeValueAsString(senderEmailObj));

        if (WalletUpdateStatus.SUCCESS == walletUpdateStatus) {
            String receiverMessage = "Hi, you have received Rs. " + amount + " from "
                    + sender + " in your wallet linked with phone number " + receiver;
            JSONObject receiverEmailObj = new JSONObject();
            receiverEmailObj.put("email", receiverEmail);
            receiverEmailObj.put("message", receiverMessage);

            kafkaTemplate.send(CommonConstants.TRANSACTION_COMPLETED_TOPIC, objectMapper.writeValueAsString(receiverEmailObj));
        }
    }

    private JSONObject getUserFromUserService(String username) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth("txn_service", "txn123");
        HttpEntity request = new HttpEntity(httpHeaders);

        return restTemplate
                .exchange("http://localhost:6001/admin/user/" + username, HttpMethod.GET, request, JSONObject.class).getBody();
    }
}
