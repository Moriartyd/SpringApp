<#import "cards.ftlh" as card>

<#--    TODO: ПЕРЕДЕЛАТЬ    -->
<#macro list someList isContracts isCalcs user>
    <#assign i = -1>
    <#list someList as obj>
        <#assign i++>
        <#if i == 0>
            <div class="row">
        </#if>
        <div class="col-sm-3 mb-4">
            <#if isContracts>
                <@card.contractcard obj/>
            <#elseif isCalcs>
                <@card.calccard obj user/>
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