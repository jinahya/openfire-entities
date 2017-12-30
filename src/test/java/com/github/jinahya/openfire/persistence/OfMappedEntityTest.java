/*
 * Copyright 2017 Jin Kwon &lt;onacit at gmail.com&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jinahya.openfire.persistence;

import static java.lang.invoke.MethodHandles.lookup;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static javax.persistence.Persistence.createEntityManagerFactory;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * An abstract class for testing subclasses of {@link OfMapped} as entities.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 * @param <T> subclass type parameter
 */
abstract class OfMappedEntityTest<T extends OfMapped> extends OfMappedTest<T> {

    // -------------------------------------------------------------------------
    private static final Logger logger = getLogger(lookup().lookupClass());

    // -------------------------------------------------------------------------
    public static final String PERSISTENCE_UNIT_NAME = "openfirePU";

    // -------------------------------------------------------------------------
    public static EntityManagerFactory ENTITY_MANAGER_FACTORY = null;

    @BeforeSuite
    static void initializeEntityManagerFactory(final ITestContext context) {
        if (context == null) {
            throw new NullPointerException("context is null");
        }
        if (ENTITY_MANAGER_FACTORY != null) {
            throw new IllegalStateException("already initialized");
        }
        synchronized (OfMappedEntityTest.class) {
            if (ENTITY_MANAGER_FACTORY != null) {
                throw new IllegalStateException("already initialized");
            }
            ENTITY_MANAGER_FACTORY
                    = createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        logger.debug("entity manager factory initialized: {}",
                     ENTITY_MANAGER_FACTORY);
    }

    @AfterSuite
    private static void deinitializeEntityManagerFactory() {
        ENTITY_MANAGER_FACTORY = null;
    }

    @Deprecated
    protected static EntityManager entityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    protected static <R> R applyEntityManager(
            final Function<EntityManager, R> function) {
        if (function == null) {
            throw new NullPointerException("function is null");
        }
        final EntityManager entityManager
                = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return function.apply(entityManager);
        } finally {
            entityManager.close();
        }
    }

    protected static <U, R> R applyEntityManager(
            final BiFunction<EntityManager, U, R> function, final U u) {
        if (function == null) {
            throw new NullPointerException("function is null");
        }
        return applyEntityManager(v -> function.apply(v, u));
    }

    protected static void acceptEntityManager(
            final Consumer<EntityManager> consumer) {
        if (consumer == null) {
            throw new NullPointerException("consumer is null");
        }
        applyEntityManager(v -> {
            consumer.accept(v);
            return null;
        });
    }

    protected static <U> void acceptEntityManager(
            final BiConsumer<EntityManager, U> consumer, final U u) {
        if (consumer == null) {
            throw new NullPointerException("consumer is null");
        }
        acceptEntityManager(v -> consumer.accept(v, u));
    }

    // -------------------------------------------------------------------------
    public OfMappedEntityTest(final Class<T> entityClass) {
        super(entityClass);
    }

    // -------------------------------------------------------------------------
}
