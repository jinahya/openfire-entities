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

import static com.github.jinahya.openfire.persistence.OfMucServiceEntityTest.applyOfMucServices;
import static java.lang.Math.pow;
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

/**
 * Test class for {@link OfMucRoom} as an entity.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfMucRoomEntityTest extends OfMappedEntityTest<OfMucRoom> {

    private static final Logger logger = getLogger(lookup().lookupClass());

    // -------------------------------------------------------------------------
    static <R> R applyOfMucRooms(final EntityManager manager,
                                 final OfMucService service,
                                 final Function<List<OfMucRoom>, R> function) {
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
        final CriteriaQuery<OfMucRoom> criteria
                = builder.createQuery(OfMucRoom.class);
        final Root<OfMucRoom> root = criteria.from(OfMucRoom.class);
        if (service != null) {
            criteria.where(builder.equal(
                    root.get(OfMucRoom_.service).get(OfMucService_.serviceId),
                    service.getServiceId()));
        }
        criteria.orderBy(builder.desc(root.get(OfMucRoom_.creationDate)));
        final TypedQuery<OfMucRoom> typed = manager.createQuery(criteria);
        typed.setFirstResult(0);
        typed.setMaxResults((int) pow(2.0d, OfMucRoomTest.EXPONENT));
        return function.apply(typed.getResultList());
    }

    // -------------------------------------------------------------------------
    /**
     * Creates a new instance.
     */
    public OfMucRoomEntityTest() {
        super(OfMucRoom.class);
    }

    // -------------------------------------------------------------------------
    private void test(final EntityManager manager, final OfMucService service) {
        final List<OfMucRoom> ofMucRooms
                = applyOfMucRooms(manager, service, identity());
        validate(ofMucRooms);
        for (final OfMucRoom room : ofMucRooms) {
            logger.debug("ofMucRoom: {}", room);
            if (service != null) {
                assertNotNull(room.getService());
                assertEquals(room.getService().getServiceId(),
                             service.getServiceId());
                assertEquals(room.getService().getSubdomain(),
                             service.getSubdomain());
            }
        }
    }

    @Test
    public void testWithService() {
        acceptEntityManager(entityManager -> {
            final List<OfMucService> ofMucServices
                    = applyOfMucServices(entityManager, identity());
            for (final OfMucService ofMucService : ofMucServices) {
                test(entityManager, ofMucService);
            }
        });
    }

    @Test
    public void testWithoutService() {
        acceptEntityManager(manager -> test(manager, null));
    }
}
