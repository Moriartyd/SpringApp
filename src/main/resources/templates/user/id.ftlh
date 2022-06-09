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
    <#if user.role == "OUTSTAFF">
<#--        TODO: Навыки подрядчика-->
    </#if>
        <div class="container">
            <div class="row mb-3">
                <#if user.role == "USER">
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Мои расчеты</h5>
                            <h6 class="card-text"><a href="/user/${user.id}/calcs">${calcs_cnt}</a> расчеты</h6>
                            <a href="#" class="btn btn-primary">Посмотреть</a>
                        </div>
                    </div>
                </div>
                </#if>
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Мои контракты</h5>
                            <h6 class="card-text"><a href="/user/${user.id}/contracts">${contracts_cnt}</a> контрактов</h6>
                        <a href="#" class="btn btn-primary">Посмотреть</a>
                        </div>
                    </div>
                </div>
            </div>
<#--            <div class="row mb-3">-->
<#--                <div class="col">-->
<#--                    <div class="card">-->
<#--                        <div class="card-body">-->
<#--                            <h5 class="card-title">Я собираюсь посетить</h5>-->
<#--                            <h6 class="card-text"><a href="/user/${user.id}/events">${event_cnt}</a> мероприятий</h6>-->
<#--&lt;#&ndash;                        <a href="#" class="btn btn-primary">Посмотреть</a>&ndash;&gt;-->
<#--                        </div>-->
<#--                    </div>-->
<#--                </div>-->
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
<#--            </div>-->
        </div>
    <#if user.id == user_id>
        <a href="/user/${user.id}/edit" class="btn btn-primary">Редактировать</a>
    </#if>
</@c.page>