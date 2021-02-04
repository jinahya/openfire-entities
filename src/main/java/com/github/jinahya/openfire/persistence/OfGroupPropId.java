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
 * The id class for {@link OfGroupProp}.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfGroupPropId extends OfPropId {

    private static final long serialVersionUID = 3884148746670598099L;

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + '{'
               + "group=" + groupName
               + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        final OfGroupPropId that = (OfGroupPropId) obj;
        return Objects.equals(groupName, that.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), groupName);
    }

    // ----------------------------------------------------------------------------------------------------------- group
    public String getGroupName() {
        return groupName;
    }

    void setGroupName(final String groupName) {
        this.groupName = groupName;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NamedAttribute(OfGroupProp.ATTRIBUTE_NAME_GROUP_NAME)
    private String groupName;
}
