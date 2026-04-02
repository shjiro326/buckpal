package io.reflectoring.buckpal.adapter.out.persistence;

import org.springframework.stereotype.Component;

import io.reflectoring.buckpal.application.domain.model.Account.AccountId;
import io.reflectoring.buckpal.application.port.out.AccountLock;

@Component
class NoOpAccountLock implements AccountLock {

	@Override
	public void lockAccount(AccountId accountId) {
		// do nothing a
	}

	@Override
	public void releaseAccount(AccountId accountId) {
		// do nothing b
	}

}
