<#import "../parts/common.ftlh" as c>
<#import "../parts/cards.ftlh" as card>
<#import "../parts/list.ftlh" as lst>
<#--<#if isSubs??>-->
<#--    <#assign title = "Подписки ${user.login}">-->
<#--    <#assign message = "Пользователь еще ни на кого не подписался">-->
<#--<#elseif isFollowers??>-->
<#--    <#assign title = "Подписчики ${user.login}">-->
<#--    <#assign message = "На этого пользователя еще никто не подписался">-->
<#--<#elseif isOnEvent??>-->
<#--    <#assign title = "Пользователи на мероприятии ${event.name}">-->
<#--    <#assign message = "На это мероприятие пока никто не идет">-->
<#--<#elseif isFriendsOnEvent??>-->
<#--    <#assign title = "Ваши друзья на мероприятии ${event}">-->
<#--    <#assign message = "Никто из ваших друзей не идет на ээто мероприятие">-->
<#--</#if>-->
<@c.page title>
    <#if users??>
        <@lst.list users true false false/>
    <#else>
        ${message}
    </#if>
</@c.page>