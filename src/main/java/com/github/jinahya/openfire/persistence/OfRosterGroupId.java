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
 * The id class of {@link OfRosterGroup} class.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfRosterGroupId implements Serializable {

    private static final long serialVersionUID = 2759427671840109183L;

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
               + "roster=" + roster
               + ",rank=" + rank
               + "}";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(roster);
        hash = 67 * hash + Objects.hashCode(rank);
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
        final OfRosterGroupId other = (OfRosterGroupId) obj;
        if (!Objects.equals(roster, other.roster)) {
            return false;
        }
        if (!Objects.equals(rank, other.rank)) {
            return false;
        }
        return true;
    }

    // -------------------------------------------------------------------------
    public Long getRoster() {
        return roster;
    }

    void setRoster(Long roster) {
        this.roster = roster;
    }

    OfRosterGroupId roster(final Long roster) {
        setRoster(roster);
        return this;
    }

    // -------------------------------------------------------------------------
    public Long getRank() {
        return rank;
    }

    void setRank(final Long rank) {
        this.rank = rank;
    }

    OfRosterGroupId rank(final Long rank) {
        setRank(rank);
        return this;
    }

    // -------------------------------------------------------------------------
    private Long roster;

    private Long rank;
}
