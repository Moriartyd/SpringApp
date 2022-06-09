<#import "../parts/common.ftlh" as c>
<#import "../parts/rating.ftlh" as rating>
<#include "../parts/security.ftlh">
<@c.page "Расчет №${calc.id}">
    <#if canEdit == true>
<#--        <form method="post" action="/calc/edit/${event.id}">-->
<#--    <div class="mb-3 align-items-center">-->
<#--        <div class="col-auto">-->
<#--            <label for="name" class="col-form-label"> Название : </label>-->
<#--        </div>-->
<#--        <div class="col-auto">-->
<#--            <input type="text" id="name" name="name" class="form-control" value="${event.name}"/>-->
<#--        </div>-->

<#--        <div class="col-auto">-->
<#--            <label for="time" class="col-form-label"> Время проведения : </label>-->
<#--        </div>-->
<#--        <div class="col-auto">-->
<#--            <input type="datetime-local" id="time" name="time" class="form-control" value="${time}"/>-->
<#--        </div>-->

<#--        <div class="col-auto">-->
<#--            <label for="placeName" class="col-form-label"> Место : </label>-->
<#--        </div>-->
<#--        <div class="col-auto">-->
<#--            <select id="placeName" name="place" class="form-select" aria-label="Default select example">-->
<#--                <option>Выберите место</option>-->
<#--                <option value="null">Моего места нет в списке</option>-->
<#--                <#list places as place>-->
<#--                    <#if place.name == event.place.name>-->
<#--                        <option selected value="${place.id}">${place.name}</option>-->
<#--                    <#else>-->
<#--                        <option value="${place.id}">${place.name}</option>-->
<#--                    </#if>-->
<#--                </#list>-->
<#--            </select>-->
<#--        </div>-->

<#--        <div class="col-auto">-->
<#--            <label for="cost" class="col-form-label"> Цена за вход : </label>-->
<#--        </div>-->
<#--        <div class="col-auto">-->
<#--            <input type="number" id="cost" name="cost" class="form-control" value="${event.cost}"/>-->
<#--        </div>-->

<#--        <div class="col-auto">-->
<#--            <label for="minAge" class="col-form-label"> Минимальный возраст : </label>-->
<#--        </div>-->
<#--        <div class="col-auto">-->
<#--            <input type="number" id="minAge" name="minAge" class="form-control" value="${event.minAge}"/>-->
<#--        </div>-->
<#--        <br/>-->
<#--        <div class="col-auto">-->
<#--            <#list keywords as keyword>-->
<#--                <input type="checkbox" class="btn-check" id="${keyword}" name="${keyword.name()}" autocomplete="off" ${event.keywords?seq_contains(keyword)?string("checked", "")}>-->
<#--                <label class="btn btn-outline-primary" for="${keyword}">${keyword.toString()}</label>-->
<#--            </#list>-->
<#--        </div>-->
<#--    </div>-->
<#--            <input type="submit" id="but" value="Изменить" class="btn btn-primary"/>-->
<#--            <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--        </form>-->
        <a href="/calc/${calc.id}/con" class="btn btn-primary">Подходящие подрядчики</a>
        <br></br>
    </#if>
        <ol class="list-group list-group-numbered">
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">№</div>
                        ${calc.id}
                </div>
            </li>
        <#--Габариты-->
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">Длина (м)</div>
                    ${calc.length}
                </div>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">Ширина (м)</div>
                    ${calc.width}
                </div>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">Высота (м)</div>
                    ${calc.height}
                </div>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold"> Количество этажей</div>
                    ${calc.floors}
                </div>
            </li>
        <#--Технологии-->
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">Стена несущая</div>
                    ${calc.techCarrierWall.word}
                </div>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">Отделка наружняя</div>
                    ${calc.techExterior.word}
                </div>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">Фундамент</div>
                    ${calc.techFoundation.word}
                </div>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">Перекрытия</div>
                    ${calc.techOverlaps.word}
                </div>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">Крыша</div>
                    ${calc.techRoof.word}
                </div>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">Внутренняя отделка</div>
                    ${calc.techInterior.word}
                </div>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">Лестница</div>
                    ${calc.techLadder.word}
                </div>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">Окна</div>
                    ${calc.techWindow}
                </div>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">Внутр. перегородки</div>
                    ${calc.techPartitions}
                </div>
            </li>
        </ol>
        <br/>
        <br/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
<#--    </#if>-->
                <script>
                    let boxes = document.getElementsByClassName("btn-check");
                    for (let i = 0; i < boxes.length; i++)
                        boxes[i].onchange = handler;
                    function handler(e) {
                        let qnt = 0;
                        for (let i = 0; i < boxes.length; i++) {
                            if (boxes[i].checked) {
                                qnt++;
                            }
                        }
                        if (qnt > 3) {
                            alert("Выберите не больше трех вариантов");
                            this.checked = false;
                        }
                    }
                </script>
</@c.page>
