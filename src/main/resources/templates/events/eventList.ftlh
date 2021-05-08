<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page "Мои мероприятия">
    <#list eventList as event>
            <div class="container mb-3">
                <#if event.active??>
                <div class="row">
                    <div class="col">
                        <i>${event.id}</i>
                    </div>
                    <div class="col">
                        <a href="/events/managing/event/${event.id}">${event.name}</a>
                    </div>
                    <div class="col">
                        ${event.attendedVisitors}
                    </div>
                    <div class="col">
                        <form method="post" action="/events/managing/event/${event.id}/delete">
                            <input type="submit" value="Архивировать" class="btn btn-primary">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                        </form>
                    </div>
                </div>
                </#if>
            </div>
    </#list>
</@c.page>