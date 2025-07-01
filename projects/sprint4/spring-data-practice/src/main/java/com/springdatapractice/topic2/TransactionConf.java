package com.springdatapractice.topic2;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionConf {


    public void transact(){

        var transactionConfig = new DefaultTransactionDefinition();
        transactionConfig.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionConfig.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        transactionConfig.setTimeout(3);
        transactionConfig.setReadOnly(false);


    }




}
