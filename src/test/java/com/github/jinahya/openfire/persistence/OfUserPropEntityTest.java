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
import static com.github.jinahya.openfire.persistence.OfUserEntityTest.applyOfUsers;
import static java.lang.Math.pow;

/**
 * A class for testing {@link OfUser} as an Entity.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfUserPropEntityTest extends OfPropEntityTest<OfUserProp> {

    private static final Logger logger = getLogger(lookup().lookupClass());

    // -------------------------------------------------------------------------
    public static <R> R applyOfUserProps(
            final EntityManager manager, final OfUser user,
            final Function<List<OfUserProp>, R> function) {
        if (manager == null) {
            throw new NullPointerException("manager is null");
        }
        if (user != null && user.getUsername() == null) {
            throw new NullPointerException("user.username is null");
        }
        if (function == null) {
            throw new NullPointerException("funciton is null");
        }
        final CriteriaBuilder builder = manager.getCriteriaBuilder();
        final CriteriaQuery<OfUserProp> criteria
                = builder.createQuery(OfUserProp.class);
        final Root<OfUserProp> root = criteria.from(OfUserProp.class);
        if (user != null) {
            //criteria.where(builder.equal(root.get(OfUserProp_.user), user));
            criteria.where(builder.equal(
                    root.get(OfUserProp_.user).get(OfUser_.username),
                    user.getUsername()));
        }
        criteria.orderBy(builder.desc(root.get(OfUserProp_.name)));
        final TypedQuery<OfUserProp> typed = manager.createQuery(criteria);
        typed.setFirstResult(0);
        typed.setMaxResults((int) pow(2.0d, OfUserPropTest.EXPONENT));
        final List<OfUserProp> list = typed.getResultList();
        return function.apply(list);
    }

    // -------------------------------------------------------------------------
    /**
     * Creates a new instance.
     */
    public OfUserPropEntityTest() {
        super(OfUserProp.class);
    }

    // -------------------------------------------------------------------------
    private void test(final EntityManager entityManager, final OfUser ofUser) {
        final List<OfUserProp> ofUserProps = applyOfUserProps(
                entityManager, ofUser, identity());
        validate(ofUserProps);
        ofUserProps.forEach(ofUserProp -> {
            logger.debug("ofUserProp: {}", ofUserProp);
            if (ofUser != null) {
                assertNotNull(ofUserProp.getUser());
                assertEquals(ofUserProp.getUser(), ofUser);
            }
        });
    }

    @Test
    public void testWithUser() {
        acceptEntityManager(entityManager -> {
            final List<OfUser> ofUsers
                    = applyOfUsers(entityManager, identity());
            ofUsers.forEach(ofUser -> test(entityManager, ofUser));
        });
    }

    @Test
    public void testWithoutUser() {
        acceptEntityManager(entityManager -> test(entityManager, null));
    }
}
