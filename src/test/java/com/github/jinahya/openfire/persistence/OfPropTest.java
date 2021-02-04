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

/**
 * An abstract test class for testing subclasses of {@link OfProp}.
 *
 * @param <T> subclass type parameter
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
abstract class OfPropTest<T extends OfProp> extends OfMappedTest<T> {

    /**
     * Creates a new instance.
     *
     * @param propClass the subclass of {@link OfProp} to test.
     */
    protected OfPropTest(final Class<T> propClass) {
        super(propClass);
    }
}
