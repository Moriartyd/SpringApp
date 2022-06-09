<#import "../parts/common.ftlh" as c>
<#import "../parts/cards.ftlh" as card>
<#import "../parts/list.ftlh" as lst>
<#if isContracts??>
    <#assign title = "Контракты ${user.login}">
    <#assign message = "Пользователь еще не подписал ни одного контракта">
<#elseif isCalcs??>
    <#assign title = "Расчеты ${user.login}">
    <#assign message = "Пользователь еще не создал ни одного расчета">
<#--<#elseif isOnEvent??>-->
<#--    <#assign title = "Пользователи на мероприятии ${event.name}">-->
<#--    <#assign message = "На это мероприятие пока никто не идет">-->
<#--<#elseif isFriendsOnEvent??>-->
<#--    <#assign title = "Ваши друзья на мероприятии ${event}">-->
<#--    <#assign message = "Никто из ваших друзей не идет на ээто мероприятие">-->
</#if>
<@c.page title>
    <#if contracts??>
        <@lst.list contracts true false user/>
    <#elseif calcs??>
        <@lst.list calcs false true user />
    <#else>
        ${message}
    </#if>
</@c.page>