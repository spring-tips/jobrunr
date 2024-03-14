package com.example.api;

import org.jobrunr.jobs.details.CachingJobDetailsGenerator;
import org.jobrunr.storage.sql.postgres.PostgresStorageProvider;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.batch.integration.partition.MessageChannelPartitionHandler;
import org.springframework.batch.integration.partition.StepExecutionRequest;

import java.util.Set;

public class Hints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        System.out.println("registering hints!");
        hints.serialization().registerType(StepExecutionRequest.class);
        Set.of(
            //batch
            MessageChannelPartitionHandler.class,
            // jobrunr
            CachingJobDetailsGenerator.class,
            PostgresStorageProvider.class
        )
        .forEach(cn ->
                hints.reflection().registerType(cn, MemberCategory.values())
        );

    }
}
