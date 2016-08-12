package com.n26.transactions;

import com.hubspot.dropwizard.guice.GuiceBundle;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TransactionsApp extends Application<Configuration> {
    private GuiceBundle<Configuration> guiceBundle;
    private TransactionModule transactionModule;

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {

    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        guiceBundle = GuiceBundle.<Configuration> newBuilder().addModule(transactionModule)
                .enableAutoConfig(getClass().getPackage().getName()).setConfigClass(Configuration.class).build();
        bootstrap.addBundle(guiceBundle);
    }

}
