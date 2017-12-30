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

import java.io.Serializable;
import java.util.Objects;

/**
 * The id class of {@link OfPrivate} class.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfPrivateId implements Serializable {

    private static final long serialVersionUID = -8306847301505311224L;

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
               + "user=" + user
               + ",name=" + name
               + ",namespace=" + namespace
               + "}";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(user);
        hash = 19 * hash + Objects.hashCode(name);
        hash = 19 * hash + Objects.hashCode(namespace);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OfPrivateId other = (OfPrivateId) obj;
        if (!Objects.equals(user, other.user)) {
            return false;
        }
        if (!Objects.equals(name, other.name)) {
            return false;
        }
        if (!Objects.equals(namespace, other.namespace)) {
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

    OfPrivateId user(final String user) {
        setUser(user);
        return this;
    }

    // -------------------------------------------------------------------- name
    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    OfPrivateId name(final String name) {
        setName(name);
        return this;
    }

    // --------------------------------------------------------------- namespace
    public String getNamespace() {
        return namespace;
    }

    void setNamespace(final String namespace) {
        this.namespace = namespace;
    }

    OfPrivateId namespace(final String namespace) {
        setNamespace(namespace);
        return this;
    }

    // -------------------------------------------------------------------------
    private String user;

    private String name;

    private String namespace;
}
