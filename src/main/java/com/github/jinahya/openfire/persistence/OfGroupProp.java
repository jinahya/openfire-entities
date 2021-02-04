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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

/**
 * Entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
@IdClass(OfGroupPropId.class)
@Table(name = OfGroupProp.TABLE_NAME)
public class OfGroupProp extends OfProp {

    private static final long serialVersionUID = 8633556437492074336L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "ofGroupProp";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_GROUP_NAME = OfGroup.COLUMN_NAME_GROUP_NAME;

    public static final String ATTRIBUTE_NAME_GROUP_NAME = "groupName";

    public static final String ATTRIBUTE_NAME_GROUP = "group";

    // -----------------------------------------------------------------------------------------------------------------
    public OfGroupProp() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + '{'
               + "group=" + group
               + '}';
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 67 * hash + Objects.hashCode(group);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        final OfGroupProp other = (OfGroupProp) obj;
        if (!Objects.equals(group, other.group)) {
            return false;
        }
        return true;
    }

    // ------------------------------------------------------------------------------------------------------- groupName
    String getGroupName() {
        return groupName;
    }

    void setGroupName(final String groupName) {
        this.groupName = groupName;
    }

    // ----------------------------------------------------------------------------------------------------------- group
    public OfGroup getGroup() {
        return group;
    }

    public void setGroup(final OfGroup group) {
        this.group = group;
        setGroupName(Optional.ofNullable(this.group).map(OfGroup::getGroupName).orElse(null));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @Column(name = COLUMN_NAME_GROUP_NAME, nullable = false, insertable = true, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_GROUP_NAME)
    private String groupName;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_GROUP_NAME, nullable = false, insertable = false, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_GROUP)
    private OfGroup group;
}
