package com.ict.transaction;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Repository
public class TxDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	private DataSourceTransactionManager transactionManager; 
	
	public int getInsert(TxVO txVO) throws Exception{
		// 트랜잭션 이전
		// int result = 0 ;
		// result  += sqlSessionTemplate.insert("card.cardInsert", txVO);
		// result += sqlSessionTemplate.insert("card.ticketInsert", txVO);
		// return result;
		
		// 트랜잭션 처리 
		int result = 0 ;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			
			result  += sqlSessionTemplate.insert("card.cardInsert", txVO);
			result += sqlSessionTemplate.insert("card.ticketInsert", txVO);
			transactionManager.commit(status);
			System.out.println("결재 성공, 발권성공");
		} catch (Exception e) {
		    transactionManager.rollback(status);
		    System.out.println("오류발생, 결재취소, 발권취소");
		}
		return result;
	}
}











