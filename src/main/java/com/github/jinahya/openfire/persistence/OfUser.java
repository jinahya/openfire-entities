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
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
@Table(name = OfUser.TABLE_NAME)
public class OfUser extends OfMapped {

    private static final long serialVersionUID = -5654310275875222680L;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the target table. The value is {@value}.
     */
    public static final String TABLE_NAME = "ofUser";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the column to which {@value #ATTRIBUTE_NAME_USERNAME} attribute is bound. The value is {@value}.
     */
    public static final String COLUMN_NAME_USERNAME = "username";

    /**
     * The name of the attribute from which {@value #COLUMN_NAME_USERNAME} column is bound. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_USERNAME = "username";

    // -----------------------------------------------------------------------------------------------------------------
//    public static final String COLUMN_NAME_STORED_KEY = "storedKey";
//
//    public static final String ATTRIBUTE_NAME_STORED_KEY = "storedKey";

    // -----------------------------------------------------------------------------------------------------------------
//    public static final String COLUMN_NAME_SERVIER_KEY = "serverKey";
//
//    public static final String ATTRIBUTE_NAME_SERVIER_KEY = "serverKey";

    // -----------------------------------------------------------------------------------------------------------------
//    public static final String COLUMN_NAME_SALT = "salt";
//
//    public static final String ATTRIBUTE_NAME_SALT = "salt";

    // -----------------------------------------------------------------------------------------------------------------
//    public static final String COLUMN_NAME_ITERATIONS = "iterations";
//
//    public static final String ATTRIBUTE_NAME_ITERATIONS = "iterations";

    // -----------------------------------------------------------------------------------------------------------------
//    public static final String COLUMN_NAME_PLAIN_PASSWORD = "plainPassword";
//
//    public static final String ATTRIBUTE_NAME_PLAIN_PASSWORD = "plainPassword";

    // -----------------------------------------------------------------------------------------------------------------
//    public static final String COLUMN_NAME_ENCRYPTED_PASSWORD = "encryptedPassword";
//
//    public static final String ATTRIBUTE_NAME_ENCRYPTED_PASSWORD = "encryptedPassword";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_NAME = "name";

    public static final String ATTRIBUTE_NAME_NAME = "name";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_EMAIL = "email";

    public static final String ATTRIBUTE_NAME_EMAIL = "email";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_CREATION_DATE = "creationDate";

    public static final String ATTRIBUTE_NAME_CREATION_DATE = "creationDate";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_MODIFICATION_DATE = "modificationDate";

    public static final String ATTRIBUTE_NAME_MODIFICATION_DATE = "modificationDate";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public OfUser() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + '{'
               + "username=" + username
//               + ",storedKey=" + storedKey
//               + ",serverKey=" + serverKey
//               + ",salt=" + salt
//               + ",iterations=" + iterations
//               + ",plainPassword=" + plainPassword
//               + ",encryptedPassword=" + encryptedPassword
               + ",name=" + name
               + ",email=" + email
               + ",creationDate=" + creationDate
               + ",modificationDate=" + modificationDate
               + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final OfUser that = (OfUser) obj;
        return Objects.equals(username, that.username)
               && Objects.equals(name, that.name)
               && Objects.equals(email, that.email)
               && Objects.equals(creationDate, that.creationDate)
               && Objects.equals(modificationDate, that.modificationDate)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                username,
                name,
                email,
                creationDate,
                modificationDate
        );
    }

    // -------------------------------------------------------------------------------------------------------- username
    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public OfUser username(final String username) {
        setUsername(username);
        return this;
    }

    // ------------------------------------------------------------------------------------------------------------ name
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public OfUser name(final String name) {
        setName(name);
        return this;
    }

    // ----------------------------------------------------------------------------------------------------------- email
    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    // ---------------------------------------------------------------------------------------------------- creationDate
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    // ------------------------------------------------------------------------------------------------ modificationDate
    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(final LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @NotNull
    @Column(name = COLUMN_NAME_USERNAME, unique = true, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_USERNAME)
    private String username;

//    @Column(name = COLUMN_NAME_STORED_KEY)
//    @NamedAttribute(ATTRIBUTE_NAME_STORED_KEY)
//    private String storedKey;
//
//    @Column(name = COLUMN_NAME_SERVIER_KEY)
//    @NamedAttribute(ATTRIBUTE_NAME_SERVIER_KEY)
//    private String serverKey;
//
//    @Column(name = COLUMN_NAME_SALT)
//    @NamedAttribute(ATTRIBUTE_NAME_SALT)
//    private String salt;
//
//    @Column(name = COLUMN_NAME_ITERATIONS)
//    @NamedAttribute(ATTRIBUTE_NAME_ITERATIONS)
//    private int iterations;
//
//    @Column(name = COLUMN_NAME_PLAIN_PASSWORD)
//    @NamedAttribute(ATTRIBUTE_NAME_PLAIN_PASSWORD)
//    private String plainPassword;
//
//    @Column(name = COLUMN_NAME_ENCRYPTED_PASSWORD)
//    @NamedAttribute(ATTRIBUTE_NAME_ENCRYPTED_PASSWORD)
//    private String encryptedPassword;

    @Column(name = COLUMN_NAME_NAME)
    @NamedAttribute(ATTRIBUTE_NAME_NAME)
    private String name;

    @Column(name = COLUMN_NAME_EMAIL)
    @NamedAttribute(ATTRIBUTE_NAME_EMAIL)
    private String email;

    @NotNull
    @Convert(converter = LocalDateTime015AttributeConverter.class)
    @Column(name = COLUMN_NAME_CREATION_DATE, nullable = false,
            updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_CREATION_DATE)
    private LocalDateTime creationDate;

    @NotNull
    @Convert(converter = LocalDateTime015AttributeConverter.class)
    @Column(name = COLUMN_NAME_MODIFICATION_DATE, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_MODIFICATION_DATE)
    private LocalDateTime modificationDate;
}
