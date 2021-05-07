<#import "parts/common.ftlh" as c>

<@c.page "Добро пожаловать">
<body>
    <div>
        <form method="post" action="/logout">
            <input type="submit" value="Выйти" class="btn btn-primary">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
<#list users as user>
    <div>
        <i>${user.id}</i>
        <b><a href="user/${user.id}">${user.login}</a></b>
<#--        <if ${usr.role} == "ADMIN"><a href="/admin/user/${user.id}"> Изменить </a></if>-->
    </div>
</#list>
</@c.page>