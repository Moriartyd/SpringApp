<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">
<@c.page "Мероприятие ${event.name}">
    <#if isManager>
        <form method="post" action="/events/managing/${event.id}">
    <div class="mb-3 align-items-center">
        <div class="col-auto">
            <label for="name" class="col-form-label"> Название : </label>
        </div>
        <div class="col-auto">
            <input type="text" id="name" name="name" class="form-control" value="${event.name}"/>
        </div>

        <div class="col-auto">
            <label for="time" class="col-form-label"> Время проведения : </label>
        </div>
        <div class="col-auto">
            <input type="date" id="time" name="time" class="form-control" value="${event.time}"/>
        </div>

<#--        <div class="col-auto">-->
<#--            <label for="placeName" class="col-form-label"> Место : </label>-->
<#--        </div>-->
<#--        <div class="col-auto">-->
<#--            <input type="text" id="placeName" name="place" class="form-control" value="${event.place.name}"/>-->
<#--        </div>-->

        <div class="col-auto">
            <label for="cost" class="col-form-label"> Цена за вход : </label>
        </div>
        <div class="col-auto">
            <input type="number" id="cost" name="cost" class="form-control" value="${event.cost}"/>
        </div>

        <div class="col-auto">
            <label for="minAge" class="col-form-label"> Минимальный возраст : </label>
        </div>
        <div class="col-auto">
            <input type="number" id="minAge" name="minAge" class="form-control" value="${event.minAge}"/>
        </div>
    </div>
            <input type="submit" id="but" value="Изменить" class="btn btn-primary"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
        <#else>
            <ol class="list-group list-group-numbered">
                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold">Название</div>
                        ${event.name}
                    </div>
                </li>
                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold">Время проведения</div>
                        ${event.time}
                    </div>
                </li>
<#--                <li class="list-group-item d-flex justify-content-between align-items-start">-->
<#--                    <div class="ms-2 me-auto">-->
<#--                        <div class="fw-bold">Место</div>-->
<#--                        ${event.place.name}-->
<#--                    </div>-->
<#--                </li>-->
                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold">Цена за вход</div>
                        ${event.cost}
                    </div>
                </li>
                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold">Минимальный возраст</div>
                        ${event.cost}
                    </div>
                </li>
                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold">Средний чек</div>
                        ${event.middlePrice}
                    </div>
                </li>
            </ol>
    </#if>
</@c.page>