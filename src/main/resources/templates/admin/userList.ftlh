<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">
    <@c.page "Список пользователей">
        <#list users as user>
            <#if user.id != user_id>
            <div class="container mb-3">
                <div class="row">
                    <div class="col">
                        <i>${user.id}</i>
                    </div>
                    <div class="col">
                        <a href="admin/user/${user.id}">${user.login}</a>
                    </div>
                    <div class="col">
                        ${user.role}
                    </div>
                    <div class="col">
                        <form method="post" action="/admin/user/${user.id}/delete">
                            <input type="submit" value="Удалить" class="btn btn-primary">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                        </form>
                    </div>
                </div>
            </div>
            </#if>
        </#list>
    </@c.page>