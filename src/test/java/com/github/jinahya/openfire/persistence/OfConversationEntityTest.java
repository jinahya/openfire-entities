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

import static com.github.jinahya.openfire.persistence.OfMucRoomEntityTest.applyOfMucRooms;
import static java.lang.Math.pow;
import static java.lang.invoke.MethodHandles.lookup;
import java.util.List;
import static java.util.Optional.ofNullable;
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
 * Test class for testing {@link OfConversation} as an entity.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfConversationEntityTest
        extends OfMappedEntityTest<OfConversation> {

    private static final Logger logger = getLogger(lookup().lookupClass());

    // -------------------------------------------------------------------------
    public static <R> R applyOfConversations(
            final EntityManager manager, final OfMucRoom room,
            final Function<List<OfConversation>, R> function) {
        if (manager == null) {
            throw new NullPointerException("manager is null");
        }
        if (room != null && room.getName() == null) {
            throw new IllegalArgumentException("room.room is null");
        }
        if (function == null) {
            throw new NullPointerException("function is null");
        }
        final CriteriaBuilder builder = manager.getCriteriaBuilder();
        final CriteriaQuery<OfConversation> criteria
                = builder.createQuery(OfConversation.class);
        final Root<OfConversation> root = criteria.from(OfConversation.class);
        ofNullable(room)
                .map(OfMucRoom::getName)
                .map(v -> builder.equal(root.get(OfConversation_.room), v))
                .ifPresent(criteria::where);
        criteria.orderBy(
                builder.desc(root.get(OfConversation_.conversationId)));
        final TypedQuery<OfConversation> typed = manager.createQuery(criteria);
        typed.setFirstResult(0);
        typed.setMaxResults((int) pow(2.0d, OfConversationTest.EXPONENT));
        final List<OfConversation> resultList = typed.getResultList();
        return function.apply(resultList);
    }

    // -------------------------------------------------------------------------
    /**
     * Creates a new instance.
     */
    public OfConversationEntityTest() {
        super(OfConversation.class);
    }

    // -------------------------------------------------------------------------
    private void test(final EntityManager manager, final OfMucRoom room) {
        final List<OfConversation> conversations
                = applyOfConversations(manager, room, identity());
        validate(conversations);
        for (final OfConversation conversation : conversations) {
            logger.debug("ofConversation: {}", conversation);
            assertNotNull(conversation);
            if (room != null) {
                assertNotNull(conversation.getRoom());
                assertEquals(conversation.getRoom(), room.getName());
            }
        }
    }

    @Test
    public void testWithRoom() {
        acceptEntityManager(entityManager -> {
            final List<OfMucRoom> ofMucRooms
                    = applyOfMucRooms(entityManager, null, identity());
            ofMucRooms.forEach(ofMucRoom -> test(entityManager, ofMucRoom));
        });
    }

    @Test
    public void testWithtoutRoom() {
        acceptEntityManager(manager -> test(manager, null));
    }
}
