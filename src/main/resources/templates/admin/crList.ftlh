<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">
    <@c.page "Список расчетов">
        <div class="container mb-3">
            <div class="row">
                <div class="col">
                    <i>ID</i>
                </div>
                <div class="col">
<#--                    <i>Редактировать</i>-->
                </div>
                <div class="col">
<#--                    <i>Удалить</i>-->
                </div>
            </div>
        <#list crs as cr>
                <div class="row">
                    <div class="col">
                        <a href="/cr/${cr.id}">${cr.id}</a>
                    </div>
<#--                    <div class="col">-->
<#--                        <a href="/admin/calc/${cr.id}" class="btn btn-primary">Редактировать</a>-->
<#--                    </div>-->
                    <div class="col">
                        <form method="post" action="/admin/cr/${cr.id}/delete">
                            <input type="submit" value="Удалить" class="btn btn-primary">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                        </form>
                    </div>
                </div>
        </#list>
        </div>
    </@c.page>