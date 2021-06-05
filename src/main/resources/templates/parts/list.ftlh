<#import "cards.ftlh" as card>

<#--    TODO: ПЕРЕДЕЛАТЬ    -->
<#macro list someList isUser isPlace isEvent>
    <#assign i = -1>
    <#list someList as obj>
        <#assign i++>
        <#if i == 0>
            <div class="row">
        </#if>
        <div class="col-sm-3 mb-4">
            <#if isUser>
                <@card.usercard obj/>
            <#elseif isPlace>
                <@card.placecard obj/>
            <#elseif isEvent>
                <@card.eventcard obj/>
            </#if>
        </div>
        <#if i == 2>
            </div>
        </#if>
        <#if i == 2>
            <#assign i = -1>
        </#if>
    </#list>
</#macro>