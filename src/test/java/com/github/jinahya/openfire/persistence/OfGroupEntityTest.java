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
import java.util.List;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.testng.annotations.Test;
import static com.github.jinahya.openfire.persistence.OfGroupEntityTest.applyOfGroups;
import static java.lang.Math.pow;
import static java.util.function.Function.identity;

/**
 * A class for testing {@link OfGroup} as an Entity.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfGroupEntityTest extends OfMappedEntityTest<OfGroup> {

    private static final Logger logger = getLogger(lookup().lookupClass());

    // -------------------------------------------------------------------------
    public static <R> R applyOfGroups(
            final EntityManager manager,
            final Function<List<OfGroup>, R> function) {
        if (manager == null) {
            throw new NullPointerException("manager is null");
        }
        if (function == null) {
            throw new NullPointerException("function is null");
        }
        final CriteriaBuilder builder = manager.getCriteriaBuilder();
        final CriteriaQuery<OfGroup> criteria
                = builder.createQuery(OfGroup.class);
        final Root<OfGroup> root = criteria.from(OfGroup.class);
        criteria.orderBy(builder.desc(root.get(OfGroup_.groupName)));
        final TypedQuery<OfGroup> typed = manager.createQuery(criteria);
        typed.setFirstResult(0);
        typed.setMaxResults((int) pow(2, OfGroupTest.EXPONENT));
        final List<OfGroup> list = typed.getResultList();
        return function.apply(list);
    }

    // -------------------------------------------------------------------------
    /**
     * Creates a new instance.
     */
    public OfGroupEntityTest() {
        super(OfGroup.class);
    }

    // -------------------------------------------------------------------------
    private void test(final EntityManager entityManager) {
        final List<OfGroup> ofGroups = applyOfGroups(entityManager, identity());
        validate(ofGroups);
        ofGroups.forEach(ofGroup -> {
            logger.debug("ofGroup: {}", ofGroup);
        });
    }

    @Test
    public void test() {
        acceptEntityManager(this::test);
    }
}
