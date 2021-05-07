<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page "Вход и Регистрация">
<h1>Welcome!</h1>
    <#if user_id == -1>
    <a href="/login">Log In</a>
    <br/>
    <a href="/user/registration">Sign Up</a>
    </#if>
</@c.page>