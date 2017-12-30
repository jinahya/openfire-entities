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
import static com.github.jinahya.openfire.persistence.OfGroupEntityTest.applyOfGroups;
import static java.lang.Math.pow;

/**
 * A class for testing {@link OfGroupProp} as an Entity.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfGroupPropEntityTest extends OfPropEntityTest<OfGroupProp> {

    private static final Logger logger = getLogger(lookup().lookupClass());

    // -------------------------------------------------------------------------
    public static <R> R applyOfGroupProps(
            final EntityManager manager, final OfGroup group,
            final Function<List<OfGroupProp>, R> function) {
        if (manager == null) {
            throw new NullPointerException("manager is null");
        }
        if (group != null && group.getGroupName() == null) {
            throw new IllegalArgumentException("group.groupName is null");
        }
        if (function == null) {
            throw new NullPointerException("function is null");
        }
        final CriteriaBuilder builder = manager.getCriteriaBuilder();
        final CriteriaQuery<OfGroupProp> criteria
                = builder.createQuery(OfGroupProp.class);
        final Root<OfGroupProp> root = criteria.from(OfGroupProp.class);
        if (group != null) {
            criteria.where(builder.equal(root.get(OfGroupProp_.group), group));
        }
        criteria.orderBy(builder.desc(root.get(OfGroupProp_.name)));
        final TypedQuery<OfGroupProp> typed = manager.createQuery(criteria);
        typed.setFirstResult(0);
        typed.setMaxResults((int) pow(2.0d, OfGroupPropTest.EXPONENT));
        final List<OfGroupProp> list = typed.getResultList();
        return function.apply(list);
    }

    // -------------------------------------------------------------------------
    /**
     * Creates a new instance.
     */
    public OfGroupPropEntityTest() {
        super(OfGroupProp.class);
    }

    // -------------------------------------------------------------------------
    private void test(final EntityManager entityManager,
                      final OfGroup ofGroup) {
        final List<OfGroupProp> ofGroupProps = applyOfGroupProps(
                entityManager, ofGroup, identity());
        validate(ofGroupProps);
        ofGroupProps.forEach(ofGroupProp -> {
            logger.debug("ofGroupProp: {}", ofGroupProp);
            validate(ofGroupProp);
            if (ofGroup != null) {
                assertNotNull(ofGroupProp.getGroup());
                assertEquals(ofGroupProp.getGroup(), ofGroup);
            }
        });
    }

    @Test
    public void testWithGroup() {
        acceptEntityManager(entityManager -> {
            final List<OfGroup> ofGroups
                    = applyOfGroups(entityManager, identity());
            ofGroups.forEach(ofGroup -> test(entityManager, ofGroup));
        });
    }

    @Test
    public void testWithoutGroup() {
        acceptEntityManager(entityManager -> test(entityManager, null));
    }
}
