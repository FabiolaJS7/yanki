package com.yanki.service.service;

import com.yanki.service.bean.MovementRequest;
import com.yanki.service.bean.WalletRequest;
import com.yanki.service.bean.YankiUserRequest;
import com.yanki.service.constants.MovementTypeConstants;
import com.yanki.service.producer.KafkaProducer;
import com.yanki.service.util.JsonTransferUtil;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WalletServiceImpl implements WalletService {

    private static final String TOPIC_YANKI_WALLET = "yanki-wallet";

    @Autowired
    KafkaProducer kafkaProducer;

    @Override
    public void createWallet(Single<YankiUserRequest> yankiUserRequestSingle) {
        yankiUserRequestSingle
                .map(yankiUserRequest -> {
                    // Creando WalletRequest para creación de una nueva wallet
                    WalletRequest walletRequest = new WalletRequest();
                    walletRequest.setPhoneAsWalletId(yankiUserRequest.getPhoneNumber());
                    walletRequest.setMovementType(MovementTypeConstants.CREATE);
                    walletRequest.setTotalAmount(0.00);

                    kafkaProducer.sendMessage(TOPIC_YANKI_WALLET, JsonTransferUtil.objectToJson(walletRequest));
                    return "Wallet creation message sent";
                })
                .doOnSuccess(log::info)
                .doOnError(error -> log.error("Error creating wallet: {}", error.getMessage(), error))
                .subscribe();
    }

    @Override
    public void editWallet(Single<MovementRequest> movementRequest) {

        movementRequest
                .flatMap(rq -> {
                    log.info("Creating movement for wallet to DEPOSIT.");
                    // Crear el WalletRequest para la wallet destino =  DEPOSIT
                    WalletRequest walletRequestTo = new WalletRequest();
                    walletRequestTo.setPhoneAsWalletId(rq.getWalletIdTo());
                    walletRequestTo.setMovementType(MovementTypeConstants.DEPOSIT);
                    walletRequestTo.setTotalAmount(rq.getAmount());

                    // Enviar el mensaje al topic para al actualizar la wallet con el DEPOSITO
                    Single<String> sendTo = Single.fromCallable(() -> {
                        log.info("Sending to updated wallet with DEPOSIT");
                        kafkaProducer.sendMessage(TOPIC_YANKI_WALLET, JsonTransferUtil.objectToJson(walletRequestTo));
                        return "Deposit message sent";
                    });

                    log.info("Creating movement for wallet to WITHDRAW.");
                    // Crear el WalletRequest para la wallet de retiro = WITHDRAW
                    WalletRequest walletRequestFrom = new WalletRequest();
                    walletRequestFrom.setPhoneAsWalletId(rq.getWalletIdFrom());
                    walletRequestFrom.setMovementType(MovementTypeConstants.WITHDRAW);
                    walletRequestFrom.setTotalAmount(rq.getAmount());

                    // Enviar el mensaje al topic para el retiro
                    Single<String> sendFrom = Single.fromCallable(() -> {
                        log.info("Sending to updated wallet with WITHDRAW");
                        kafkaProducer.sendMessage(TOPIC_YANKI_WALLET, JsonTransferUtil.objectToJson(walletRequestFrom));
                        return "Withdraw message sent";
                    });

                    return Single.zip(sendTo, sendFrom, (depositResult, withdrawResult) -> "updated");
                })
                .doOnSuccess(result -> log.info("Wallet updated successfully: {}", result))
                .doOnError(error -> log.error("Error updating wallet: {}", error.getMessage(), error))
                .subscribe();
    }
}
