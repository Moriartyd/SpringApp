<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page "Подрядчики">
    <#list contractors as con>
        <div class="container mb-3">
                <div class="row">
                    <div class="col">
                        <i>${con.id}</i>
                    </div>
                    <div class="col">
                        <a href="/place/managing/${con.user.id}">${con.user.login!'без имени'}</a>
                    </div>
                    <div class="col">
                        <form method="post" action="/place/managing/${con.id}/delete">
                            <input type="submit" value="Удалить" class="btn btn-primary">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                        </form>
                    </div>
                </div>
        </div>
    </#list>
</@c.page>