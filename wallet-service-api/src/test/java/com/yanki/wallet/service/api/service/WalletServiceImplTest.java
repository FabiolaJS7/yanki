package com.yanki.wallet.service.api.service;

import com.yanki.wallet.service.api.bean.WalletRequest;
import com.yanki.wallet.service.api.bean.WalletResponse;
import com.yanki.wallet.service.api.constants.MovementTypeConstants;
import com.yanki.wallet.service.api.model.WalletModel;
import com.yanki.wallet.service.api.repository.DaoWalletFactory;
import com.yanki.wallet.service.api.repository.WalletRepository;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.TestObserver;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class WalletServiceImplTest {

    @Mock
    private WalletRepository walletRepository;
    @Mock
    private DaoWalletFactory daoWalletFactory;

    @InjectMocks
    private WalletServiceImpl walletService;

    @Disabled
    @Test
    void createOrUpdateWallet() {
        // Arrange
        WalletRequest walletRequest = new WalletRequest();
        walletRequest.setPhoneAsWalletId("qwqweqweqew");
        walletRequest.setTotalAmount(20.00);
        walletRequest.setProductIdAssociated("123123123123");
        walletRequest.setMovementType(MovementTypeConstants.CREATE);
        walletRequest.setCreatedAt(LocalDate.now());
        walletRequest.setUpdatedAt(LocalDate.now());

        WalletResponse expectedResponse = new WalletResponse();
        expectedResponse.setPhoneAsWalletId("qwqweqweqew");
        expectedResponse.setTotalAmount(20.00);
        expectedResponse.setProductIdAssociated("123123123123");

        WalletModel walletModel = new WalletModel();
        walletModel.setPhoneAsWalletId("qwqweqweqew");
        walletModel.setTotalAmount(20.00);
        walletModel.setCreatedAt(LocalDate.now());
        walletModel.setUpdatedAt(LocalDate.now());

        // Mocking DaoWalletFactory to return the mocked WalletRepository
        Mockito.when(daoWalletFactory.getWalletRepository()).thenReturn(walletRepository);

        // Mocking save operation in WalletRepository
        Mockito.when(walletRepository.save(Mockito.any(WalletModel.class)))
                .thenReturn(Mono.just(walletModel));

        // Act
        Single<WalletResponse> walletResponse = walletService.createOrUpdateWallet(Single.just(walletRequest));

        // Assert
        TestObserver<WalletResponse> testObserver = walletResponse.test();

        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValue(response ->
                response.getPhoneAsWalletId().equals(expectedResponse.getPhoneAsWalletId()));
        testObserver.assertValueCount(1);

    }
}