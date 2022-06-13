<#import "../parts/common.ftlh" as c>
<#import "../parts/rating.ftlh" as rating>
<#include "../parts/security.ftlh">
<@c.page "Контракт №${contract.id}">
        <ol class="list-group list-group-numbered">
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">№</div>
                        ${contract.id}
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
