<#include "security.ftlh">
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">IAS</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/main">Главная</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/user/${user_id}">Профиль</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="#">Поиск</a>
                </li>
                <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/admin">Администрирование</a>
                </li>
                </#if>
                <#if isManager>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/events/managing/registration">Новое мероприятие</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/events/managing/my_events">Мои мероприятия</a>
                    </li>
                </#if>
                <#if isPlace>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/place/managing/registration">Новое место</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/place/managing/my_places">Мои места</a>
                    </li>
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