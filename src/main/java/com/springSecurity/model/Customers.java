package com.springSecurity.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long id;

    private String name;

    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    /*
    Above declaration always make sure that we are only accepting this field
    from UI to BE but not from BE to UI
    */
    private String pwd;

    private String role;

    @Column(name = "create_dt")
    @JsonIgnore
    private Date createDt;

    @OneToMany(mappedBy = "customers", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Authority> authorities;


    /* In this scenario use case for FetchType
    * FetchType.EAGER => When ever spring will load a parent record which is customer
    *                    please also load all the children records i.e entries from authorities
    *                    for the particular customer.
    * FetchType.LAZY => In this case during loading of parent record the children record only be loaded
    *                   when someone try to invoke the get authorities method present inside the entity
    *                   class.
    */
}
