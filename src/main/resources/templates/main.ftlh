<#import "parts/common.ftlh" as c>
<#import "parts/cards.ftlh" as card>

<@c.page "Добро пожаловать">
    <body>
    <#assign i = -1>
    <#list events as event>
        <#assign rate = event.rating*100/5>
        <#assign i++>
        <#if i == 0>
        <div class="row">
        </#if>
        <div class="col-sm-3 mb-4">
            <@card.eventcard event/>
        </div>
        <#if i == 2>
        </div>
        </#if>
        <#if i == 2>
            <#assign i = -1>
        </#if>
    </#list>
</@c.page>