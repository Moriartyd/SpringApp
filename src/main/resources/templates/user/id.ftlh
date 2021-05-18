<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page "Пользователь ${user.login}">
    <h3>${user.name} ${user.surname} (${user.login})</h3>
    <#if user.email??>
        <h4>Email: ${user.email}</h4>
    </#if>
    <#if user.age%10 gt 5>
        <h4>Возраст: ${user.age} лет</h4>
    <#elseif user.age%10 == 1>
        <h4>Возраст: ${user.age} год</h4>
    <#else>
        <h4>Возраст: ${user.age} года</h4>
    </#if>
        <div class="container">
            <div class="row mb-3">
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">На меня подписаны</h5>
                            <h6 class="card-text"><a href="/user/${user.id}/followers">${followers_cnt}</a> пользователей</h6>
<#--                            <a href="#" class="btn btn-primary">Посмотреть</a>-->
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Я подписан на</h5>
                            <h6 class="card-text"><a href="/user/${user.id}/subs">${subscriptions_cnt}</a> пользователей</h6>
<#--                        <a href="#" class="btn btn-primary">Посмотреть</a>-->
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Я собираюсь посетить</h5>
                            <h6 class="card-text"><a href="/user/${user.id}/events">${event_cnt}</a> мероприятий</h6>
<#--                        <a href="#" class="btn btn-primary">Посмотреть</a>-->
                        </div>
                    </div>
                </div>
                <#if user.role == "MANAGER">
                    <div class="col">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Я организовал</h5>
                                <h6 class="card-text"><a href="/user/${user.id}/managed_events">${managed_events_cnt}</a> мероприятий</h6>
                                <#--                        <a href="#" class="btn btn-primary">Посмотреть</a>-->
                            </div>
                        </div>
                    </div>
                </#if>
            </div>
        </div>
    <#if user.id == user_id>
        <a href="/user/${user.id}/edit" class="btn btn-primary">Редактировать</a>
    <#else>
        <#if isSubscription>
            <form method="post" action="/user/${user.id}/unFollow">
                <input type="submit" class="btn btn-primary" value="Отписаться"/>
        <#else>
            <form method="post" action="/user/${user.id}/follow">
                <input type="submit" class="btn btn-primary" value="Подписаться"/>
        </#if>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </#if>
</@c.page>