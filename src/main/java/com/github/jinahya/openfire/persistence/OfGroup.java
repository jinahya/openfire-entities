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
import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@XmlRootElement
@Entity
public class OfGroup extends OfMapped {

  private static final long serialVersionUID = 6455075545276927084L;

  // -------------------------------------------------------------------------
  /**
   * The name of the target table of this entity. The value is
   * {@value #TABLE_NAME}.
   */
  public static final String TABLE_NAME = "ofGroup";

  // -------------------------------------------------------------------------
  /**
   * The name of target column to which {@value #ATTRIBUTE_NAME_GROUP_NAME}
   * attribute is bind. The value is {@value #COLUMN_NAME_GROUP_NAME}.
   */
  public static final String COLUMN_NAME_GROUP_NAME = "groupName";

  /**
   * The name of source attribute from which {@value #COLUMN_NAME_GROUP_NAME}
   * column is bind. The value is {@value #ATTRIBUTE_NAME_GROUP_NAME}.
   */
  public static final String ATTRIBUTE_NAME_GROUP_NAME = "groupName";

  // -------------------------------------------------------------------------
  /**
   * The name of target column to which {@value #ATTRIBUTE_NAME_DESCRIPTION}
   * attribute is bind. The value is {@value #COLUMN_NAME_DESCRIPTION}.
   */
  public static final String COLUMN_NAME_DESCRIPTION = "description";

  /**
   * The name of source attribute from which {@value #COLUMN_NAME_DESCRIPTION}
   * column is bind. The value is {@value #ATTRIBUTE_NAME_DESCRIPTION}.
   */
  public static final String ATTRIBUTE_NAME_DESCRIPTION = "description";

  // -------------------------------------------------------------------------
  /**
   * Creates a new instance.
   */
  public OfGroup() {
    super();
  }

  // -------------------------------------------------------------------------
  @Override
  public String toString() {
    return super.toString() + "{"
            + "groupName=" + groupName
            + ",description=" + description
            + "}";
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + Objects.hashCode(groupName);
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
    final OfGroup other = (OfGroup) obj;
    if (!Objects.equals(groupName, other.groupName)) {
      return false;
    }
    return true;
  }

  // --------------------------------------------------------------- groupName
  /**
   * Returns the current value of {@value #ATTRIBUTE_NAME_GROUP_NAME} attribute.
   *
   * @return the current value of {@value #ATTRIBUTE_NAME_GROUP_NAME} attribute.
   */
  public String getGroupName() {
    return groupName;
  }

  /**
   * Replaces the value of {@value #ATTRIBUTE_NAME_GROUP_NAME} attribute with
   * given.
   *
   * @param groupName new value for {@value #ATTRIBUTE_NAME_GROUP_NAME}
   * attribute.
   */
  public void setGroupName(final String groupName) {
    this.groupName = groupName;
  }

  /**
   * Replaces the value of {@value #ATTRIBUTE_NAME_GROUP_NAME} attribute with
   * given and returns this instance.
   *
   * @param groupName new value for {@value #ATTRIBUTE_NAME_GROUP_NAME}
   * attribute.
   * @return this instance.
   * @see #setGroupName(java.lang.String)
   */
  public OfGroup groupName(final String groupName) {
    setGroupName(groupName);
    return this;
  }

  // ------------------------------------------------------------- description
  /**
   * Returns the current value of {@value #ATTRIBUTE_NAME_DESCRIPTION}
   * attribute.
   *
   * @return the current value of {@value #ATTRIBUTE_NAME_DESCRIPTION}
   * attribute.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Replaces the value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute with
   * given.
   *
   * @param description new value for {@value #ATTRIBUTE_NAME_DESCRIPTION}
   * attribute.
   */
  public void setDescription(final String description) {
    this.description = description;
  }

  /**
   * Replaces the value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute with
   * given and returns this instance.
   *
   * @param description new value for {@value #ATTRIBUTE_NAME_DESCRIPTION}
   * attribute.
   * @return this instance.
   * @see #setDescription(java.lang.String)
   */
  public OfGroup description(final String description) {
    setDescription(description);
    return this;
  }

  // -------------------------------------------------------------------------
  @JsonbProperty()
  @XmlElement(required = true)
  @NotNull
  @Id
  @Column(name = COLUMN_NAME_GROUP_NAME, nullable = false, unique = true)
  @NamedAttribute(ATTRIBUTE_NAME_GROUP_NAME)
  private String groupName;

  @JsonbProperty(nillable = true)
  @XmlElement(nillable = true)
  @NamedAttribute(ATTRIBUTE_NAME_DESCRIPTION)
  private String description;
}
