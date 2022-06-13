<#import "../parts/common.ftlh" as c>
<#import "../parts/rating.ftlh" as rating>
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
        <h3>Компетенции:</h3>
        <li class="list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto">
                <div class="fw-bold">Стена несущая</div>
                ${techCarrierWall}
            </div>
        </li>
        <li class="list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto">
                <div class="fw-bold">Отделка наружняя</div>
                ${techExterior}
            </div>
        </li>
        <li class="list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto">
                <div class="fw-bold">Фундамент</div>
                ${techFoundation}
            </div>
        </li>
        <li class="list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto">
                <div class="fw-bold">Перекрытия</div>
                ${techOverlaps}
            </div>
        </li>
        <li class="list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto">
                <div class="fw-bold">Крыша</div>
                ${techRoof}
            </div>
        </li>
        <li class="list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto">
                <div class="fw-bold">Внутренняя отделка</div>
                ${techInterior}
            </div>
        </li>
        <li class="list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto">
                <div class="fw-bold">Лестница</div>
                ${techLadder}
            </div>
        </li>
<#--        <li class="list-group-item d-flex justify-content-between align-items-start">-->
<#--            <div class="ms-2 me-auto">-->
<#--                <div class="fw-bold">Окна</div>-->
<#--                ${techWindow}-->
<#--            </div>-->
<#--        </li>-->
<#--        <li class="list-group-item d-flex justify-content-between align-items-start">-->
<#--            <div class="ms-2 me-auto">-->
<#--                <div class="fw-bold">Внутр. перегородки</div>-->
<#--                ${techPartitions}-->
<#--            </div>-->
<#--        </li>-->
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
            <#elseif user.role == "OUTSTAFF">
                <br/>
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
                <#if rated == 0>
                    <form method="post" action="/contractor/${con.id}/set_rating" id="form-id">
                        <@rating.get/>
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" hidden="true" id="click">
                    </form>
                <#else>
                    <@rating.show/>
                </#if>

            </#if>
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
<#--            </div>-->
        </div>
    <#if user.id == user_id>
        <a href="/user/${user.id}/edit" class="btn btn-primary">Редактировать</a>
    </#if>
</@c.page>