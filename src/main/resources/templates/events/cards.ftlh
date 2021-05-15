<#import "../parts/common.ftlh" as c>
<#import "../parts/cards.ftlh" as card>
<#import "../parts/list.ftlh" as lst>
<#if isForUser??>
    <#assign title = "Мероприятия ${user.login}">
    <#assign message = "Пользователь еще не зарегистрировался ни на одно мероприятие">
<#elseif isByUser??>
    <#assign title = "Мероприятия от ${user.login}">
    <#assign message = "Пользователь еще не создал ни одного мероприятия">
<#elseif isOnPlace??>
    <#assign title = "Мероприятия в ${place.name}">
    <#assign message = "В этом месте пока не проводятся мероприятия">
</#if>
<@c.page title>
    <#if events??>
        <@lst.list events false false true/>
    <#else>
        <h2>${message}</h2>
    </#if>
</@c.page>