package com.amanuel.RESTProject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users")
@ApiModel(description = "About the User")
@JsonIgnoreProperties(value = {"birthDate"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ApiModelProperty(notes = "Name must be between 2 to 20 characters")
    @NotBlank(message = "Name must not be blank")
    @Size(min = 2, max = 20)
    private String name;

    @ApiModelProperty(notes = "Date must be in the past")
    @NotNull(message = "Date must not be null")
    @Past(message = "Birthday must be in the past.")
    @JsonIgnore
    @XmlTransient
    private Date birthDate;

    public User() {
    }

    User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    void setId(Integer id) {
        this.id = id;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return this.name + " " + this.id + " " + this.birthDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate) + id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User) | obj == null)
            return false;
        User user = (User) obj;
        return user.id.equals(this.id) && user.birthDate.equals(this.birthDate)
                && user.name.equals(this.name);

    }
}
