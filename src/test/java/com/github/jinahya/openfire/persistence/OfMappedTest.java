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

import com.github.jinahya.openfire.inject.ValidatorFactoryModule;
import com.github.jinahya.openfire.inject.ValidatorModule;
import com.google.inject.Inject;
import static java.lang.String.format;
import static java.lang.invoke.MethodHandles.lookup;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import static java.util.Objects.requireNonNull;
import java.util.Set;
import javax.json.bind.annotation.JsonbTransient;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.annotation.XmlTransient;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import static org.testng.FileAssert.fail;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * An abstract test class for subclasses of {@link OfMapped} class.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 * @param <T> subclass type parameter
 */
@Guice(modules = {ValidatorFactoryModule.class, ValidatorModule.class})
public abstract class OfMappedTest<T extends OfMapped> {

    private static final Logger logger = getLogger(lookup().lookupClass());

    // -------------------------------------------------------------------------
    protected static final String DOMAIN = System.getProperty("xmpp.domain");

    // -------------------------------------------------------------------------
    private static void checkNamedAttribute(final Class<?> currentClass) {
        for (final Field field : currentClass.getDeclaredFields()) {
            final NamedAttribute annotation
                    = field.getAnnotation(NamedAttribute.class);
            if (annotation == null) {
                final int modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers)) {
                    continue;
                }
                logger.warn("{} is not annotated with {}", field,
                            NamedAttribute.class);
                continue;
            }
            if (!field.getName().endsWith(annotation.value())) {
                final String message = format(
                        "%1$s is not named as annoated: %2$s", field,
                        annotation);
                logger.error(message);
                fail(message);
            }
        }
        final Class<?> superClass = currentClass.getSuperclass();
        if (superClass != null) {
            checkNamedAttribute(superClass);
        }
    }

    private static void checkXmlTransient(final Class<?> currentClass) {
        for (final Field field : currentClass.getDeclaredFields()) {
            final XmlTransient annotation
                    = field.getAnnotation(XmlTransient.class);
            if (annotation == null) {
                continue;
            }
            final JsonbTransient jsonbTransient
                    = field.getAnnotation(JsonbTransient.class);
            if (jsonbTransient == null) {
                final String message = format(
                        "%1$s is not annotated with %2$s", field,
                        JsonbTransient.class);
                logger.error(message);
                fail(message);
            }
        }
        for (final Method method : currentClass.getDeclaredMethods()) {
            final XmlTransient annotation
                    = method.getAnnotation(XmlTransient.class);
            if (annotation == null) {
                continue;
            }
            final JsonbTransient jsonbTransient
                    = method.getAnnotation(JsonbTransient.class);
            if (jsonbTransient == null) {
                final String message = format(
                        "%1$s is not annotated with %2$s", method,
                        JsonbTransient.class);
                logger.error(message);
                fail(message);
            }
        }
        final Class<?> superClass = currentClass.getSuperclass();
        if (superClass != null) {
            checkXmlTransient(superClass);
        }
    }

    // -------------------------------------------------------------------------
    /**
     * Creates a new instance.
     *
     * @param mapepdClass subclass.
     */
    public OfMappedTest(final Class<T> mapepdClass) {
        super();
        this.mappedClass = requireNonNull(mapepdClass, "mappedclass is null");
    }

    // -------------------------------------------------------------------------
    /**
     * Checks all fields of {@link #mappedClass}, including those of super
     * classes, for {@link NamedAttribute}.
     */
    @Test
    public void checkNamedAttribute() {
        checkNamedAttribute(mappedClass);
    }

    @Test
    public void checkXmlTransient() {
        checkXmlTransient(mappedClass);
    }

    protected void validate(final T bean) {
        if (bean == null) {
            throw new NullPointerException("bean is null");
        }
        final Set<ConstraintViolation<T>> violations = validator.validate(bean);
        violations.stream().findAny().ifPresent(violation -> {
            final String message = format("violation: %1$s", violation);
            logger.error(message);
            fail(message);
        });
    }

    protected void validate(final Collection<? extends T> beans) {
        if (beans == null) {
            throw new NullPointerException("beans is null");
        }
        beans.forEach(this::validate);
    }

    // -------------------------------------------------------------------------
    /**
     * The subclass.
     */
    protected final Class<T> mappedClass;

    @Inject
    protected Validator validator;
}
