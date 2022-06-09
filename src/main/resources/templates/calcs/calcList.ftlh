<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page "Мои расчеты">
    <#list calcList as calc>
            <div class="container mb-3">
                <#if calc.active??>
                <div class="row">
                    <div class="col">
                        <i>${calc.id}</i>
                    </div>
                    <div class="col">
                        <a href="/calc/${calc.id}">№${calc.id}</a>
                    </div>
<#--                    <div class="col">-->
<#--                        ${calc.registeredVisitors}-->
<#--                    </div>-->
                    <div class="col">
                        <form method="post" action="/events/managing/event/${calc.id}/delete">
                            <input type="submit" value="Архивировать" class="btn btn-primary">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                        </form>
                    </div>
                </div>
                </#if>
            </div>
    </#list>
</@c.page>