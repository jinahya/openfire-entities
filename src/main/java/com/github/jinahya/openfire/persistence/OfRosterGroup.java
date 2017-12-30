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
import static java.util.Optional.ofNullable;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * An entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@IdClass(OfRosterGroupId.class)
@Entity
public class OfRosterGroup extends OfMapped {

    private static final long serialVersionUID = 6710931163459638967L;

    // -------------------------------------------------------------------------
    private static final Logger logger
            = getLogger(lookup().lookupClass().getName());

    // -------------------------------------------------------------------------
    public static final String TABLE_NAME = "OfRosterGroups";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_ROSTER_ID
            = OfRoster.COLUMN_NAME_ROSTER_ID;

    public static final String ATTRIBUTE_NAME_ROSTER = "roster";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_RANK = "rank";

    public static final String ATTRIBUTE_NAME_RANK = "rank";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_GROUP_NAME = "groupName";

    public static final String ATTRIBUTE_NAME_GROUP_NAME = "groupName";

    public static final int SIZE_MAX_GROUP_NAME = 255;

    public static final int SIZE_MIN_GROUP_NAME = 0;

    // -------------------------------------------------------------------------
    /**
     * Creates a new instance.
     */
    public OfRosterGroup() {
        super();
    }

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
               + "roster=" + roster
               + ",rank=" + rank
               + ",groupName=" + groupName
               + "}";
    }

    // -------------------------------------------------------------- idInstance
    public OfRosterGroupId getIdInstance() {
        return new OfRosterGroupId()
                .roster(getRosterRosterId())
                .rank(getRank());
    }

    // ------------------------------------------------------------------ roster
    public OfRoster getRoster() {
        return roster;
    }

    public void setRoster(final OfRoster roster) {
        this.roster = roster;
    }

    public OfRosterGroup roster(final OfRoster roster) {
        setRoster(roster);
        return this;
    }

    public Long getRosterRosterId() {
        return ofNullable(getRoster()).map(OfRoster::getRosterId).orElse(null);
    }

    // -------------------------------------------------------------------- rank
    public Long getRank() {
        return rank;
    }

    public void setRank(final Long rank) {
        this.rank = rank;
    }

    public OfRosterGroup rank(final Long rank) {
        setRank(rank);
        return this;
    }

    // --------------------------------------------------------------- groupName
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(final String groupName) {
        this.groupName = groupName;
    }

    public OfRosterGroup groupName(final String groupName) {
        setGroupName(groupName);
        return this;
    }

    // -------------------------------------------------------------------------
    @NotNull
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
                name = COLUMN_NAME_ROSTER_ID,
                nullable = false,
                referencedColumnName = OfRoster.COLUMN_NAME_ROSTER_ID,
                updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_ROSTER)
    private OfRoster roster;

    @NotNull
    @Id
    @Column(name = COLUMN_NAME_RANK, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_RANK)
    private Long rank;

    @Size(max = SIZE_MAX_GROUP_NAME, min = SIZE_MAX_GROUP_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_RANK, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_GROUP_NAME)
    private String groupName;
}
