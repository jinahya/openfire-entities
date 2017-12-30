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

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 * Test class for {@link OfConversation}.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfConversationTest extends OfMappedTest<OfConversation> {

    static final double EXPONENT = OfMucRoomTest.EXPONENT + 1.0d;

    // -------------------------------------------------------------------------
    @Test
    public static void testRoom() {
        final String ofMucServiceSubdomain = "subdomain";
        final OfMucService service
                = new OfMucService().subdomain(ofMucServiceSubdomain);
        final String ofMucRoomName = "name";
        final OfMucRoom ofMucRoom
                = new OfMucRoom().name(ofMucRoomName).service(service);
        final String domain = "domain";
        final String expected
                = ofMucRoomName + "@" + ofMucServiceSubdomain + "." + domain;
        final String actual = OfConversation.room(ofMucRoom, domain);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public static void testRoomExpectNpeWhenRoomIsNull() {
        OfConversation.room(null, "domain");
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public static void testRoomExpectNpeWhenRoomNameIsNull() {
        OfConversation.room(
                new OfMucRoom()
                        .name(null)
                        .service(new OfMucService().subdomain("subdomain")),
                "domain");
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public static void testRoomExpectNpeWhenRoomServiceIsNull() {
        OfConversation.room(
                new OfMucRoom().name("name").service(null),
                "domain");
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public static void testRoomExpectNpeWhenRoomServiceSubdomainIsNull() {
        OfConversation.room(
                new OfMucRoom()
                        .name("name")
                        .service(new OfMucService().subdomain(null)),
                "domain");
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public static void testRoomExpectNpeWhenDomainIsNull() {
        OfConversation.room(
                new OfMucRoom()
                        .name("name")
                        .service(new OfMucService().subdomain("subdomain")),
                null);
    }

    // -------------------------------------------------------------------------
    public OfConversationTest() {
        super(OfConversation.class);
    }
}
