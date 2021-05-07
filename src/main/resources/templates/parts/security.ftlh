<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
    >
<#if known>
    <#assign
        u = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        login = u.getLogin()
        isAdmin = u.isAdmin()
        isManager = u.isManager()
        user_id = u.getId()
    >
<#else>
    <#assign
        login = "unknown"
        isAdmin = false
        isManager = false
        user_id = -1
    >
</#if>