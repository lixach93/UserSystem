<#assign known = Session.SPRING_SECURITY_CONTEXT??/>
<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    username = user.getUsername()
    isAdmin = user.getAuthorities()?seq_contains('ROLE_ADMIN')
    isUser = user.getAuthorities()?seq_contains('ROLE_USER')
    isAuthenticated = true />
<#else>
    <#assign
    username = "unknown"
    isAdmin = false
    isUser = false
    isAuthenticated = false/>
</#if>
