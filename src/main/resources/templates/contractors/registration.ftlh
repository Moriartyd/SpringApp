<#import "../parts/common.ftlh" as c>
<#import "../js/map.ftlh" as map>

<@c.page "Регистрация подрядчика">
    <form action="/contractor/registration/${user.id}" method="post" xmlns="http://www.w3.org/1999/html"
          xmlns="http://www.w3.org/1999/html">
        <h3>Выберите ключевые навыки</h3>
        <div class="col-auto">
            <label for="techCarrierWall" class="col-form-label">Технология, использующаяся при строительстве несущей стены : </label>
        </div>
        <div class="col-auto">
            <#list techCarrierWalls as techCarrierWall>
                <input type="checkbox" class="btn-check" id="${techCarrierWall}" name="${techCarrierWall.word}" autocomplete="off">
                <label class="btn btn-outline-primary" for="${techCarrierWall}">${techCarrierWall.word}</label>
            </#list>
        </div>

        <div class="col-auto">
            <label for="techExterior" class="col-form-label">Технология, использующаяся при наружной отделке дома : </label>
        </div>
        <div class="col-auto">
            <#list techExteriors as techExterior>
                <input type="checkbox" class="btn-check" id="${techExterior}" name="${techExterior.word}" autocomplete="off">
                <label class="btn btn-outline-primary" for="${techExterior}">${techExterior.word}</label>
            </#list>
        </div>

        <div class="col-auto">
            <label for="techFoundation" class="col-form-label">Технология, использующаяся при закладке фундамента : </label>
        </div>
        <div class="col-auto">
            <#list techFoundations as techFoundation>
                <input type="checkbox" class="btn-check" id="${techFoundation}" name="${techFoundation.word}" autocomplete="off">
                <label class="btn btn-outline-primary" for="${techFoundation}">${techFoundation.word}</label>
            </#list>
        </div>

        <div class="col-auto">
            <label for="techOverlaps" class="col-form-label">Технология, использующаяся при строительстве перекрытий : </label>
        </div>
        <div class="col-auto">
            <#list techOverlapss as techOverlaps>
                <input type="checkbox" class="btn-check" id="${techOverlaps}" name="${techOverlaps.word}" autocomplete="off">
                <label class="btn btn-outline-primary" for="${techOverlaps}">${techOverlaps.word}</label>
            </#list>
        </div>

        <div class="col-auto">
            <label for="techRoof" class="col-form-label">Технология, использующаяся при строительстве крыши : </label>
        </div>
        <div class="col-auto">
            <#list techRoofs as techRoof>
                <input type="checkbox" class="btn-check" id="${techRoof}" name="${techRoof.word}" autocomplete="off">
                <label class="btn btn-outline-primary" for="${techRoof}">${techRoof.word}</label>
            </#list>
        </div>

        <div class="col-auto">
            <label for="techLadder" class="col-form-label">Технология, использующаяся при строительстве лестниц : </label>
        </div>
        <div class="col-auto">
            <#list techLadders as techLadder>
                <input type="checkbox" class="btn-check" id="${techLadder}" name="${techLadder.word}" autocomplete="off">
                <label class="btn btn-outline-primary" for="${techLadder}">${techLadder.word}</label>
            </#list>
        </div>

        <div class="col-auto">
            <label for="techInterior" class="col-form-label">Технология, использующаяся при внутренней отделке дома : </label>
        </div>
        <div class="col-auto">
            <#list techInteriors as techInterior>
                <input type="checkbox" class="btn-check" id="${techInterior}" name="${techInterior.word}" autocomplete="off">
                <label class="btn btn-outline-primary" for="${techInterior}">${techInterior.word}</label>
            </#list>
        </div>

        <div class="col-auto">
            <label for="techPartitions" class="col-form-label">Установка внутренних перегородок : </label>
        </div>
        <div class="col-auto">
            <input class="form-check-input" type="radio" name="techPartitions" id="techPartitions" value="1">
            <label class="form-check-label" for="techPartitions">Да</label>
            <input class="form-check-input" type="radio" name="techPartitions" id="techPartitions" value="0">
            <label class="form-check-label" for="techPartitions">Нет</label>
        </div>

        <div class="col-auto">
            <label for="techWindow" class="col-form-label">Установка окон : </label>
        </div>
        <div class="col-auto">
            <input class="form-check-input" type="radio" name="techWindow" id="techWindow" value="1">
            <label class="form-check-label" for="techWindow">Да</label>
            <input class="form-check-input" type="radio" name="techWindow" id="techWindow" value="0">
            <label class="form-check-label" for="techWindow">Нет</label>
        </div>

        <br/>

        <h3>Выберите желаемые параметры постройки</h3>
        <div class="container">
            <div class="row row-cols-4">
                <div class="col">
                    <label for="minLength" class="col-form-label"> Длина (м) минимальная: </label>
                </div>
                <div class="col">
                    <input type="number" id="minLength" name="minLength" class="form-control"/>
                </div>
                <div class="col">
                    <label for="maxLength" class="col-form-label"> Длина (м) максимальная: </label>
                </div>
                <div class="col">
                    <input type="number" id="maxLength" name="maxLength" class="form-control"/>
                </div>
            </div>

            <div class="row row-cols-4">
            <br/>
            </div>

            <div class="row row-cols-4">
                <div class="col">
                    <label for="minWidth" class="col-form-label"> Ширина (м) : </label>
                </div>
                <div class="col">
                    <input type="number" id="minWidth" name="minWidth" class="form-control"/>
                </div>
                <div class="col">
                    <label for="maxWidth" class="col-form-label"> Ширина (м) : </label>
                </div>
                <div class="col">
                    <input type="number" id="maxWidth" name="maxWidth" class="form-control"/>
                </div>
            </div>

            <div class="row row-cols-4">
                <br/>
            </div>

            <div class="row row-cols-4">
                <div class="col">
                    <label for="minHeight" class="col-form-label"> Высота (м) минимальная: </label>
                </div>
                <div class="col">
                    <input type="number" id="minHeight" name="minHeight" class="form-control"/>
                </div>
                <div class="col">
                    <label for="maxHeight" class="col-form-label"> Высота (м) : </label>
                </div>
                <div class="col">
                    <input type="number" id="maxHeight" name="maxHeight" class="form-control"/>
                </div>
            </div>

            <div class="row row-cols-4">
                <br/>
            </div>

            <div class="row row-cols-4">
                <div class="col">
                    <label for="minFloors" class="col-form-label"> Количество этажей : </label>
                </div>
                <div class="col">
                    <input type="number" id="minFloors" name="minFloors" class="form-control"/>
                </div>
                <div class="col">
                    <label for="maxFloors" class="col-form-label"> Количество этажей : </label>
                </div>
                <div class="col">
                    <input type="number" id="maxFloors" name="maxFloors" class="form-control"/>
                </div>
            </div>

            <div class="row row-cols-4">
                <br/>
            </div>

            <div class="row row-cols-4">
                <div class="col">
                    <label for="minCost" class="col-form-label"> Стоимость материалов минимальная : </label>
                </div>
                <div class="col">
                    <input type="number" id="minCost" name="minCost" class="form-control"/>
                </div>
                <div class="col">
                    <label for="maxCost" class="col-form-label">  Стоимость материалов максимальная : </label>
                </div>
                <div class="col">
                    <input type="number" id="maxCost" name="maxCost" class="form-control"/>
                </div>
            </div>
        </div>

        <input type="hidden" id="rate" name="rating" value="5"/>
        <input type="hidden" id="rateNum" name="ratingNum" value="1"/>
<#--        <input type="hidden" id="user" name="user" value="${user.id}"/>-->
        <br/>
        <input type="submit" id="but" value="Создать" class="btn btn-primary"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
    <@map.getBySelection/>
</@c.page>