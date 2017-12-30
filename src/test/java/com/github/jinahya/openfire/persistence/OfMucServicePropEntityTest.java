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

import static com.github.jinahya.openfire.persistence.OfMappedEntityTest.acceptEntityManager;
import static java.lang.invoke.MethodHandles.lookup;
import java.util.List;
import java.util.function.Function;
import static java.util.function.Function.identity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;
import static com.github.jinahya.openfire.persistence.OfMucServiceEntityTest.applyOfMucServices;

/**
 * A class for testing {@link OfMucService} as an Entity.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfMucServicePropEntityTest
        extends OfPropEntityTest<OfMucServiceProp> {

    private static final Logger logger = getLogger(lookup().lookupClass());

    // -------------------------------------------------------------------------
    public static <R> R applyOfMucServiceProps(
            final EntityManager manager, final OfMucService service,
            final Function<List<OfMucServiceProp>, R> function) {
        if (manager == null) {
            throw new NullPointerException("manager is null");
        }
        if (service != null && service.getServiceId() == null) {
            throw new IllegalArgumentException("service.serviceId is null");
        }
        if (function == null) {
            throw new NullPointerException("function is null");
        }
        final CriteriaBuilder builder = manager.getCriteriaBuilder();
        final CriteriaQuery<OfMucServiceProp> criteria
                = builder.createQuery(OfMucServiceProp.class);
        final Root<OfMucServiceProp> root
                = criteria.from(OfMucServiceProp.class);
        if (service != null) {
            criteria.where(
                    builder.equal(
                            root.get(OfMucServiceProp_.service)
                                    .get(OfMucService_.serviceId),
                            service.getServiceId())
            );
        }
        criteria.orderBy(builder.desc(root.get(OfMucServiceProp_.name)));
        final TypedQuery<OfMucServiceProp> typed
                = manager.createQuery(criteria);
        typed.setFirstResult(0);
        typed.setMaxResults(16);
        final List<OfMucServiceProp> list = typed.getResultList();
        return function.apply(list);
    }

    // -------------------------------------------------------------------------
    /**
     * Creates a new instance.
     */
    public OfMucServicePropEntityTest() {
        super(OfMucServiceProp.class);
    }

    // -------------------------------------------------------------------------
    private void test(final EntityManager entityManager,
                      final OfMucService ofMucService) {
        final List<OfMucServiceProp> ofMucServiceProps = applyOfMucServiceProps(
                entityManager, ofMucService, identity());
        validate(ofMucServiceProps);
        ofMucServiceProps.forEach(ofMucServiceProp -> {
            if (ofMucService != null) {
                assertNotNull(ofMucServiceProp.getService());
                assertEquals(ofMucServiceProp.getService(), ofMucServiceProp);
            }
        });
    }

    @Test
    public void testWithService() {
        acceptEntityManager(entityManager -> {
            final List<OfMucService> ofMucServices
                    = applyOfMucServices(entityManager, identity());
            ofMucServices.forEach(
                    ofMucService -> test(entityManager, ofMucService));
        });
    }

    @Test
    public void testWithoutService() {
        acceptEntityManager(entityManager -> test(entityManager, null));
    }
}
