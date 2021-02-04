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
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * The entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
public class OfGroup extends OfMapped {

    private static final long serialVersionUID = 3560435021625260990L;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the target table of this entity. The value is {@value}.
     */
    public static final String TABLE_NAME = "ofGroup";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of target column to which {@value #ATTRIBUTE_NAME_GROUP_NAME} attribute is bind. The value is {@value}.
     */
    public static final String COLUMN_NAME_GROUP_NAME = "groupName";

    /**
     * The name of source attribute from which {@value #COLUMN_NAME_GROUP_NAME} column is bind. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_GROUP_NAME = "groupName";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of database column to which {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute is bind. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_DESCRIPTION = "description";

    /**
     * The name of entity attribute from which {@value #COLUMN_NAME_DESCRIPTION} column is bind. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DESCRIPTION = "description";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public OfGroup() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + '{'
               + "groupName=" + groupName
               + ",description=" + description
               + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final OfGroup ofGroup = (OfGroup) obj;
        return Objects.equals(groupName, ofGroup.groupName)
               && Objects.equals(description, ofGroup.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, description);
    }

    // ------------------------------------------------------------------------------------------------------- groupName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_GROUP_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_GROUP_NAME} attribute.
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_GROUP_NAME} attribute with specified value.
     *
     * @param groupName new value for {@value #ATTRIBUTE_NAME_GROUP_NAME} attribute.
     */
    public void setGroupName(final String groupName) {
        this.groupName = groupName;
    }

    // ----------------------------------------------------------------------------------------------------- description

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute with specified value.
     *
     * @param description new value for {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_GROUP_NAME, nullable = false, unique = true)
    @NamedAttribute(ATTRIBUTE_NAME_GROUP_NAME)
    private String groupName;

    @NotNull
    @Column(name = COLUMN_NAME_DESCRIPTION, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_DESCRIPTION)
    private String description;
}
