package com.example.api;

import org.jobrunr.storage.sql.postgres.PostgresStorageProvider;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

import java.util.Set;

public class Hints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {

        System.out.println("registering hints!");

        Set.of(org.jobrunr.jobs.details.CachingJobDetailsGenerator.class,
                PostgresStorageProvider.class).forEach(cn ->
                hints.reflection().registerType(cn, MemberCategory.values()));

    }
}
