<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page "Подрядчики">
    <div class="container mb-3">
        <div class="row">
            <div class="col">
                <i>ID</i>
            </div>
            <div class="col">
                <i>Логин</i>
            </div>
            <div class="col">
                <i>Рейтинг</i>
            </div>
            <div class="col">
                <i>Контракт</i>
            </div>
        </div>
    <#list contractors as con>
        <div class="row">
                    <div class="col">
                        <i>${con.id}</i>
                    </div>
                    <div class="col">
                        <a href="/contractor/${con.user.id}">${con.user.login!'без имени'}</a>
                    </div>
                    <div class="col">
                        <i>${con.rating/con.ratingNum}</i>
                    </div>
                    <div class="col">
                        <#if statusMap[con.user.login]??>
                        <#if statusMap[con.user.login] == 1>
                            <i>Принят</i>
                        <#elseif statusMap[con.user.login] == -1>
                            <i>Отклонен</i>
                        <#else>
                            <i>Запрос создан</i>
                        </#if>
                        <#else>
                        <form method="post" action="/contractor/${con.id}/request/calc/">
                            <input type="submit" value="Создать запрос на контракт" class="btn btn-primary">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <input type="hidden" name="calcId" value="${calc.id}">
                        </form>
                        </#if>

                    </div>
                </div>
    </#list>
    </div>
</@c.page>