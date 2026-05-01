package dev.sivalabs.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModularityTest {
    static ApplicationModules modules = ApplicationModules.of(BookStoreModulithApplication.class);

    @Test
    void verifiesModularStructure() {
        modules.verify();
        new Documenter(modules).writeDocumentation();
    }
}
