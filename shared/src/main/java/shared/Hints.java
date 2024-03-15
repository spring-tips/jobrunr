package shared;

import org.jobrunr.jobs.details.CachingJobDetailsGenerator;
import org.jobrunr.storage.sql.postgres.PostgresStorageProvider;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

import java.util.Set;

@SuppressWarnings("unused")
class Hints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        System.out.println("running the hints!");
        for (var cn : Set.of(CachingJobDetailsGenerator.class, PostgresStorageProvider.class))
            hints.reflection().registerType(cn, MemberCategory.values());
    }
}
