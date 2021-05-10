<#import "parts/common.ftlh" as c>

<@c.page "Добро пожаловать">
    <body>
        <div>
            <form method="post" action="/logout">
                <input type="submit" value="Выйти" class="btn btn-primary">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </form>
        </div>
    <#assign i = -1>
    <#list events as event>
        <#assign rate = event.rating*100/5>
        <#assign i++>
        <#if i == 0>
        <div class="row">
        </#if>
        <div class="col-sm-3 mb-4">
            <div class="card" style="width: 18rem;">
        <#--        <img src="..." class="card-img-top" alt="..."> -->
                <div class="card-body">
                    <h5 class="card-title">${event.name}</h5>
                    <p class="card-text">${event.time}<br/>${event.rating}</p>
                    <div class="progress mb-3">
                        <div class="progress-bar" role="progressbar" style="width: ${rate}%;" aria-valuenow="${rate}" aria-valuemin="0" aria-valuemax="100">${rate/100*5}/5 баллов</div>
                    </div>
                    <a href="/events/${event.id}" class="btn btn-primary">Подробнее</a>
                </div>
            </div>
        </div>
        <#if i == 2>
        </div>
        </#if>
        <#if i == 2>
            <#assign i = -1>
        </#if>
<#--         <div>-->
<#--             <i>${user.id}</i>-->
<#--             <b><a href="user/${user.id}">${user.login}</a></b>-->
<#--     &lt;#&ndash;        <if ${usr.role} == "ADMIN"><a href="/admin/user/${user.id}"> Изменить </a></if>&ndash;&gt;-->
<#--    </div>-->
    </#list>
</@c.page>