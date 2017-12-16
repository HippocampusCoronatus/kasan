/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.db;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.RollbackException;

/**
 *
 * @author rued97
 */
@Interceptor
@Dependent
@Transactional
@Priority(Interceptor.Priority.APPLICATION)
public class TransactionalInterceptor {

	@Inject
	private EntityManager EM;
	
	/**
	 * メソッドに対してトランザクション処理を行います。
	 * @param ic
	 * @return 結果
	 * @throws Exception 
	 */
	@AroundInvoke
	public Object invoke(InvocationContext ic) throws Exception {
		EntityTransaction tran = this.EM.getTransaction();

		if(tran.isActive()) {
			throw new IllegalStateException("既にトランザクションが開始されています。");
		}
		try {
			tran.begin();
			Object o = ic.proceed();
			if(tran.getRollbackOnly()) {
				tran.rollback();
				throw new RollbackException("RollbackOnlyでした。");
			}
			tran.commit();
			return o;
		} catch(Exception e) {
			throw e;
		} finally {
			if(tran.isActive()) {
				try {
					tran.rollback();
				} catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}
