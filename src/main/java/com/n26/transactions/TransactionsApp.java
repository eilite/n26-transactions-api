package com.n26.transactions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubspot.dropwizard.guice.GuiceBundle;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TransactionsApp extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionsApp.class);
	
	private GuiceBundle<Configuration> guiceBundle;
	private final TransactionModule transactionModule = new TransactionModule();

	public static void main(String[] args) throws Exception {
		LOGGER.info("Starting transactions api ...");
		new TransactionsApp().run(args);
	}

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
