package com.car.CarRenting.config



import com.car.CarRenting.entity.account.User
import org.springframework.data.domain.AuditorAware
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

class ApplicationAuditAware : AuditorAware<Long> {

    override fun getCurrentAuditor(): Optional<Long> {
        val authentication: Authentication? = SecurityContextHolder.getContext().authentication

        if (authentication == null ||
            !authentication.isAuthenticated ||
            authentication is AnonymousAuthenticationToken
        ) {
            return Optional.empty()
        }

        val userPrincipal = authentication.principal as? User ?: return Optional.empty()
        return Optional.ofNullable(userPrincipal.id)
    }
}