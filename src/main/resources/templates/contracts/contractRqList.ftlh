<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page "Мои запросы на контракты">
    <#list conList as contract>
        <#if contract.statusContractor == 0>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Контракт №${contract.id}</h5>
                <h6 class="card-text">Расчет: <a href="/calc/${contract.calculation.id}">№${contract.calculation.id}</a></h6>
                <h6 class="card-text">Создатель: <a href="/user/${contract.user.id}">${contract.user.login}</a></h6>
<#--                <h6 class="card-text">Подрядчик: <a href="/user/${contract.contractor.user.id}">${contract.contractor.user.login}</a></h6>-->
<#--                <#if contract.statusContractor == 1>-->
<#--                    <h6 class="card-text">Статус: Принят подрядчиком</h6>-->
<#--                <#elseif contract.statusContractor == 0>-->
<#--                    <h6 class="card-text">Статус: В обработке</h6>-->
<#--                <#else>-->
<#--                    <h6 class="card-text">Статус: Отклонен подрядчиком</h6>-->
<#--                </#if>-->
                <div>
                <form method="post" action="/contract/${contract.id}/accept">
                    <input type="submit" value="Принять" class="btn btn-primary"/>
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                </form>
                <form method="post" action="/contract/${contract.id}/reject">
                    <input type="submit" value="Отклонить" class="btn btn-primary"/>
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                </form>
                </div>
<#--                <a href="/contract/${contract.calculationId}/accept" class="btn btn-primary">Принять</a>-->
<#--                <a href="/contract/${contract.calculationId}/reject" class="btn btn-primary">Отклонить</a>-->
            </div>
        </div>
        </#if>
    </#list>
</@c.page>