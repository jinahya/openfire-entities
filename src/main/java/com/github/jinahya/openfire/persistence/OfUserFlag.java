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

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import static com.github.jinahya.openfire.persistence.Utilities.copyOf;
import static java.util.Optional.ofNullable;

/**
 * Entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
@IdClass(OfUserFlagId.class)
public class OfUserFlag implements Serializable {

    private static final long serialVersionUID = 3723852856041333841L;

    // -------------------------------------------------------------------------
    public static final String TABLE_NAME = "ofUserFlag";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_USERNAME
            = OfUser.COLUMN_NAME_USERNAME;

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_NAME = "name";

    // -------------------------------------------------------------------------    
    public static final String COLUMN_NAME_START_TIME = "startTime";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_END_TIME = "endTime";

    // -------------------------------------------------------------- idInstance
    public OfUserFlagId getIdInstance() {
        return new OfUserFlagId().user(getUserUsername()).name(getName());
    }

    // -------------------------------------------------------------------- user
    public OfUser getUser() {
        return user;
    }

    public void setUser(final OfUser user) {
        this.user = user;
    }

    public OfUserFlag user(final OfUser user) {
        setUser(user);
        return this;
    }

    public String getUserUsername() {
        return ofNullable(getUser()).map(OfUser::getUsername).orElse(null);
    }

    // -------------------------------------------------------------------- name
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public OfUserFlag name(final String name) {
        setName(name);
        return this;
    }

    // --------------------------------------------------------------- startTime
    public Date getStartTime() {
        return copyOf(startTime);
    }

    public void setStartTime(final Date startTime) {
        this.startTime = copyOf(startTime);
    }

    public OfUserFlag startTime(final Date startTime) {
        setStartTime(startTime);
        return this;
    }

    // ----------------------------------------------------------------- endTime
    public Date getEndTime() {
        return copyOf(endTime);
    }

    public void setEndTime(final Date endTime) {
        this.endTime = copyOf(endTime);
    }

    public OfUserFlag endTime(final Date endTime) {
        setEndTime(endTime);
        return this;
    }

    // -------------------------------------------------------------------------
    @NotNull
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
                name = COLUMN_NAME_USERNAME,
                nullable = false,
                referencedColumnName = OfUser.COLUMN_NAME_USERNAME,
                updatable = false)
    private OfUser user;

    @NotNull
    @Id
    @Column(name = COLUMN_NAME_NAME)
    private String name;

    // -------------------------------------------------------------------------
    //@Temporal(TemporalType.TIMESTAMP)
    @Convert(converter = Date015AttributeConverter.class)
    @Column(name = COLUMN_NAME_START_TIME)
    private Date startTime;

    //@Temporal(TemporalType.TIMESTAMP)
    @Convert(converter = Date015AttributeConverter.class)
    @Column(name = COLUMN_NAME_END_TIME)
    private Date endTime;
}
