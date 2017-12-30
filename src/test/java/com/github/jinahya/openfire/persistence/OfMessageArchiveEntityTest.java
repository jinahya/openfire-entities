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

import static com.github.jinahya.openfire.persistence.OfConversationEntityTest.applyOfConversations;
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
 * Test class for testing {@link OfMessageArchive} as an entity.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfMessageArchiveEntityTest
        extends OfMappedEntityTest<OfMessageArchive> {

    private static final Logger logger = getLogger(lookup().lookupClass());

    // -------------------------------------------------------------------------
    public static <R> R applyOfMessageArchives(
            final EntityManager manager, final OfConversation conversation,
            final Function<List<OfMessageArchive>, R> function) {
        if (manager == null) {
            throw new NullPointerException("manager is null");
        }
        if (conversation != null && conversation.getConversationId() == null) {
            throw new IllegalArgumentException(
                    "conversation.conversationId is null");
        }
        if (function == null) {
            throw new NullPointerException("function is nul");
        }
        final CriteriaBuilder builder = manager.getCriteriaBuilder();
        final CriteriaQuery<OfMessageArchive> criteria
                = builder.createQuery(OfMessageArchive.class);
        final Root<OfMessageArchive> root
                = criteria.from(OfMessageArchive.class);
        if (conversation != null) {
            criteria.where(builder.equal(
                    root.get(OfMessageArchive_.conversation)
                            .get(OfConversation_.conversationId),
                    conversation.getConversationId()));
        }
        criteria.orderBy(builder.desc(root.get(OfMessageArchive_.sentDate)));
        final TypedQuery<OfMessageArchive> typed = manager.createQuery(criteria);
        typed.setFirstResult(0);
        typed.setMaxResults((int) pow(2, OfMessageArchiveTest.EXPONENT));
        final List<OfMessageArchive> resultList = typed.getResultList();
        return function.apply(resultList);
    }

    // -------------------------------------------------------------------------
    /**
     * Creates a new instance.
     */
    public OfMessageArchiveEntityTest() {
        super(OfMessageArchive.class);
    }

    private void test(final EntityManager manager,
                      final OfConversation conversation) {
        final List<OfMessageArchive> messageArchives
                = applyOfMessageArchives(manager, conversation, identity());
        validate(messageArchives);
        for (final OfMessageArchive messageArchive : messageArchives) {
            logger.debug("ofMessageArchive: {}", messageArchive);
            assertNotNull(messageArchive);
            assertNotNull(messageArchive.getConversation());
            if (conversation != null) {
                assertEquals(
                        messageArchive.getConversation().getConversationId(),
                        conversation.getConversationId());
            }
        }
    }

    @Test
    public void testWithConversation() {
        acceptEntityManager(manager -> {
            final List<OfConversation> conversations
                    = applyOfConversations(manager, null, identity());
            for (final OfConversation conversation : conversations) {
                test(manager, conversation);
            }
        });
    }

    @Test
    public void testWithoutConversation() {
        acceptEntityManager(manager -> test(manager, null));
    }
}
