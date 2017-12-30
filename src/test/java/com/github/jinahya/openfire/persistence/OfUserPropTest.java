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
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * A class for testing {@link OfUser} as an Entity.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfUserPropTest extends OfPropTest<OfUserProp> {

    private static final Logger logger = getLogger(lookup().lookupClass());

    // -------------------------------------------------------------------------
    static final double EXPONENT = OfUserTest.EXPONENT + 1.0d;

    // -------------------------------------------------------------------------
    /**
     * Creates a new instance.
     */
    public OfUserPropTest() {
        super(OfUserProp.class);
    }

    // -------------------------------------------------------------------------
}
