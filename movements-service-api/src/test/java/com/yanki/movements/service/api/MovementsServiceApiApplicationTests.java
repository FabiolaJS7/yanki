package com.yanki.movements.service.api;

import com.yanki.movements.service.api.bean.MovementRequest;
import com.yanki.movements.service.api.bean.MovementResponse;
import com.yanki.movements.service.api.service.MovementServiceImpl;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.TestObserver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@SpringBootTest
@Profile("local")
class MovementsServiceApiApplicationTests {

	@Autowired
	MovementServiceImpl movementServiceImpl;

	@Test
	void contextLoads() {
		MovementRequest mr = new MovementRequest();
		mr.setAmount(90.00);
		mr.setMovementType("DEPOSIT");
		//mr.setMovementType("WITHDRAW");
		mr.setWalletIdTo("123123123123");
		mr.setWalletIdFrom("123123123123");
		mr.setCreatedAt(LocalDate.now());
		mr.setUpdatedAt(LocalDate.now());

		Single<MovementResponse> movementResponse = movementServiceImpl.createMovement(Single.just(mr));
		TestObserver<MovementResponse> testObserver = movementResponse.test();

		testObserver.onComplete();

	}

}
