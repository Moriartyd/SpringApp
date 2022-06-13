<#include "security.ftlh">
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <#if known>
            <a class="navbar-brand" href="/main">Главная</a>
        <#else>
            <a class="navbar-brand" href="/">Главная</a>
        </#if>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
<#--                <li class="nav-item">-->
<#--                    <a class="nav-link" aria-current="page" href="/main">Главная</a>-->
<#--                </li>-->
                <#if known>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/user/${user_id}">Профиль</a>
                </li>
                </#if>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/admin/users">Пользователи</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/admin/contracts">Контракты</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/admin/calcs">Расчеты</a>
                    </li>
                </#if>
                <#if isOutstaff>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/contract/my_contract_rq">Запросы на контракты</a>
                    </li>
                </#if>
                <#if isAdmin == false && known>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/contract/my_contracts">Мои контракты</a>
                    </li>
                <#if isOutstaff == false>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/calc/my_calcs">Мои расчеты</a>
                    </li>
                </#if>
                </#if>
            </ul>
            <#if known>
            <form method="post" action="/logout">
                <input type="submit" value="Выйти" class="btn btn-primary">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </form>
            </#if>
        </div>
    </div>
</nav>