package hr.vsite.messageapprestapi.security.annotations


import org.springframework.security.access.prepost.PreAuthorize

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@MustBeDocumented
@PreAuthorize("hasAnyAuthority('OP_AUCTION_ADMIN_WRITE')")
annotation class PreAuthorizeApplicationAdminWrite
