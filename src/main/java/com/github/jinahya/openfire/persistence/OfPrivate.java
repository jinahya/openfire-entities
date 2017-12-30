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
import static java.util.Optional.ofNullable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * An entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
@IdClass(OfPrivateId.class)
public class OfPrivate implements Serializable {

    private static final long serialVersionUID = -6315462731845917329L;

    // -------------------------------------------------------------------------
    public static final String TABLE_NAME = "ofPrivate";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_USERNAME
            = OfUser.COLUMN_NAME_USERNAME;

    public static final String ATTRIBUTE_NAME_USERNAME = "user";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_NAME = "name";

    public static final String ATTRIBUTE_NAME_NAME = "name";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_NAMESPACE = "namespace";

    public static final String ATTRIBUTE_NAME_NAMESPACE = "namespace";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_PRIVATE_DATA = "privateData";

    public static final String ATTRIBUTE_NAME_PRIVATE_DATA = "privateData";

    // -------------------------------------------------------------------------
    /**
     * Creates a new instance.
     */
    public OfPrivate() {
        super();
    }

    // -------------------------------------------------------------------- user
    public OfUser getUser() {
        return user;
    }

    public void setUser(final OfUser user) {
        this.user = user;
    }

    public OfPrivate user(final OfUser user) {
        setUser(user);
        return this;
    }

    @XmlAttribute
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

    public OfPrivate name(final String name) {
        setName(name);
        return this;
    }

    // --------------------------------------------------------------- namespace
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(final String namespace) {
        this.namespace = namespace;
    }

    public OfPrivate namespace(final String namespace) {
        setNamespace(namespace);
        return this;
    }

    // ------------------------------------------------------------- privateData
    public String getPrivateData() {
        return privateData;
    }

    public void setPrivateData(final String privateData) {
        this.privateData = privateData;
    }

    public OfPrivate privateData(final String privateData) {
        setPrivateData(privateData);
        return this;
    }

    // -------------------------------------------------------------------------
    @JsonbTransient
    @XmlTransient
    @NotNull
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
                name = COLUMN_NAME_USERNAME,
                nullable = false,
                referencedColumnName = OfUser.COLUMN_NAME_USERNAME,
                updatable = false)
    private OfUser user;

    @XmlElement(required = true)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_NAME)
    private String name;

    // -------------------------------------------------------------------------
    @XmlElement(required = true)
    @Id
    @Column(name = COLUMN_NAME_NAMESPACE)
    private String namespace;

    @XmlElement(required = true)
    @Column(name = COLUMN_NAME_PRIVATE_DATA, nullable = false)
    private String privateData;
}
