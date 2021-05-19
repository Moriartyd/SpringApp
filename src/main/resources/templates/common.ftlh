<#import "parts/common.ftlh" as c>
<#import "js/lists.ftlh" as js>

<@c.page title>
    <div id = "maindiv">
    </div>
    <#if title = "Пользователи">
        <@js.users/>
    <#elseif title = "Места">
        <@js.places/>
    <#else>
        <@js.events/>
    </#if>
</@c.page>