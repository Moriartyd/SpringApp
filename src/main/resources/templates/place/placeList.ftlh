<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page "Мои места">
    <#list places as place>
        <div class="container mb-3">
                <div class="row">
                    <div class="col">
                        <i>${place.id}</i>
                    </div>
                    <div class="col">
                        <a href="/place/managing/${place.id}">${place.name!'без имени'}</a>
                    </div>
                    <div class="col">
                        <form method="post" action="/place/managing/${place.id}/delete">
                            <input type="submit" value="Удалить" class="btn btn-primary">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                        </form>
                    </div>
                </div>
        </div>
    </#list>
</@c.page>