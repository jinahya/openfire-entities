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

import java.util.Objects;

/**
 * The id class of {@link OfUserProp}.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfUserPropId extends OfPropId<OfUserPropId> {

    private static final long serialVersionUID = 3020858191177597147L;

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + '{'
               + "user=" + user
               + '}';
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 97 * hash + Objects.hashCode(user);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        final OfUserPropId other = (OfUserPropId) obj;
        if (!Objects.equals(user, other.user)) {
            return false;
        }
        return true;
    }

    // -------------------------------------------------------------------- user
    public String getUser() {
        return user;
    }

    void setUser(final String user) {
        this.user = user;
    }

    OfUserPropId user(final String user) {
        setUser(user);
        return this;
    }

    // -------------------------------------------------------------------------
    private String user;
}
