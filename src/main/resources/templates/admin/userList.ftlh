<#import "../parts/common.ftlh" as c>
    <@c.page "Список пользователей">
        <#list users as user>
            <div>
                <i>${user.id}</i>
                <b><a href="user/${user.id}">${user.login}</a></b>
            </div>
        </#list>
    </@c.page>