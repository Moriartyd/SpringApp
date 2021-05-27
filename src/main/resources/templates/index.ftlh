<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page "Вход и Регистрация">
<h1>Welcome!</h1>
    <#if user_id == -1>
    <a href="/login" class="btn btn-primary mb-3">Log In</a>
    <br/>
    <a href="/user/registration" class="btn btn-primary">Sign Up</a>
    </#if>
</@c.page>