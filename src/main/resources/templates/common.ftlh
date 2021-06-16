<#import "parts/common.ftlh" as c>
<#import "js/lists.ftlh" as js>
    <#import "parts/list.ftlh" as list>

<@c.page title>
    <div id = "maindiv">
    </div>
    <#if title = "Пользователи">
        <@js.users/>
    <#elseif title = "Места">
        <@js.places/>
    <#else>
        <h1> Коллаборативная фильтрация</h1>
        <@list.list ibList false false true/>
        <h1> Контент-ориентированный анализ</h1>
        <@list.list cbList false false true/>
        <h1> Агрегатор</h1>
        <@list.list aList false false true/>
<#--        <@js.events/>-->
    </#if>
</@c.page>