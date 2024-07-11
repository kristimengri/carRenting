package com.car.CarRenting.entity.account

import com.car.CarRenting.entity.access.Role
import com.car.CarRenting.entity.car.Car
import com.car.CarRenting.entity.common.UserBaseEntity
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.security.Principal


@Entity
@Table(name = "_users")
class User : UserDetails, Principal, UserBaseEntity() {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "_users_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: MutableList<Role> = mutableListOf()


    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonManagedReference
    var cars: MutableList<Car> = mutableListOf()

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, optional = true)
    @JsonManagedReference
    var customer: Customer? = null

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, optional = true)
    @JsonManagedReference
    var carOwner: CarOwner? = null

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, optional = true)
    @JsonManagedReference
    var admin: Admin? = null



    override fun getAuthorities(): Collection<GrantedAuthority> {
        return roles.map { SimpleGrantedAuthority(it.name?.name) }
    }

    @Transient
    override fun getPassword(): String {
         return password ?: ""
    }


    override fun getUsername(): String {
        return email!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return !accountLocked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getName(): String {
        return email!!
    }

    fun fullname(): String {
        return firstname!! + " " + lastname!!
    }

}