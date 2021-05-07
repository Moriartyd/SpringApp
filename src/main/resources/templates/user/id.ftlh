<#import "../parts/common.ftlh" as c>

<@c.page "Пользователь ${user.login}">
<div>
    <h1>${user.id}</h1>
    <h2>${user.login}</h2>
</div>
</@c.page>