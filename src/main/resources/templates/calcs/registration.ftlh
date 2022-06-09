<#import "../parts/common.ftlh" as c>

<@c.page "Регистрация нового мероприятия">
    <form action="/calc/registration" method="post">
<#--        <div class="col-auto">-->
<#--            <label for="name" class="col-form-label"> Название : </label>-->
<#--        </div>-->
<#--        <div class="col-auto">-->
<#--            <input type="text" id="name" name="name" class="form-control"/>-->
<#--        </div>-->

        <div class="col-auto">
            <label for="length" class="col-form-label"> Длина (м) : </label>
        </div>
        <div class="col-auto">
            <input type="number" id="length" name="length" class="form-control"/>
        </div>

        <div class="col-auto">
            <label for="width" class="col-form-label"> Ширина (м) : </label>
        </div>
        <div class="col-auto">
            <input type="number" id="width" name="width" class="form-control"/>
        </div>

        <div class="col-auto">
            <label for="height" class="col-form-label"> Высота (м) : </label>
        </div>
        <div class="col-auto">
            <input type="number" id="height" name="height" class="form-control"/>
        </div>

        <div class="col-auto">
            <label for="floors" class="col-form-label"> Количество этажей : </label>
        </div>
        <div class="col-auto">
            <input type="number" id="floors" name="floors" class="form-control"/>
        </div>

        <div class="col-auto">
            <label for="techCarrierWall" class="col-form-label">Технология, использующаяся при строительстве несущей стены : </label>
        </div>
        <div class="col-auto">
            <select name="techCarrierWall" class="form-select" aria-label="Default select example">
                <option selected value="61">Выберите</option>
                <#--                <option value="null">Моего места нет в списке</option>-->
                <#list techCarrierWalls as techCarrierWall>
                    <option value="${techCarrierWall.id}">${techCarrierWall.word}</option>
                </#list>
            </select>
        </div>

        <div class="col-auto">
            <label for="techExterior" class="col-form-label">Технология, использующаяся при наружной отделке дома : </label>
        </div>
        <div class="col-auto">
            <select name="techExterior" class="form-select" aria-label="Default select example">
                <option selected value="61">Выберите</option>
                <#--                <option value="null">Моего места нет в списке</option>-->
                <#list techExteriors as techExterior>
                    <option value="${techExterior.id}">${techExterior.word}</option>
                </#list>
            </select>
        </div>

        <div class="col-auto">
            <label for="techFoundation" class="col-form-label">Технология, использующаяся при закладке фундамента : </label>
        </div>
        <div class="col-auto">
            <select name="techFoundation" class="form-select" aria-label="Default select example">
                <option selected value="61">Выберите</option>
                <#--                <option value="null">Моего места нет в списке</option>-->
                <#list techFoundations as techFoundation>
                    <option value="${techFoundation.id}">${techFoundation.word}</option>
                </#list>
            </select>
        </div>

        <div class="col-auto">
            <label for="techOverlaps" class="col-form-label">Технология, использующаяся при строительстве перекрытий : </label>
        </div>
        <div class="col-auto">
            <select name="techOverlaps" class="form-select" aria-label="Default select example">
                <option selected value="61">Выберите</option>
                <#--                <option value="null">Моего места нет в списке</option>-->
                <#list techOverlapss as techOverlaps>
                    <option value="${techOverlaps.id}">${techOverlaps.word}</option>
                </#list>
            </select>
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
            <label for="techRoof" class="col-form-label">Технология, использующаяся при строительстве крыши : </label>
        </div>
        <div class="col-auto">
            <select name="techRoof" class="form-select" aria-label="Default select example">
                <option selected value="61">Выберите</option>
                <#--                <option value="null">Моего места нет в списке</option>-->
                <#list techRoofs as techRoof>
                    <option value="${techRoof.id}">${techRoof.word}</option>
                </#list>
            </select>
        </div>

        <div class="col-auto">
            <label for="techLadder" class="col-form-label">Технология, использующаяся при строительстве лестниц : </label>
        </div>
        <div class="col-auto">
            <select name="techLadder" class="form-select" aria-label="Default select example">
                <option selected value="61">Выберите</option>
                <#--                <option value="null">Моего места нет в списке</option>-->
                <#list techLadders as techLadder>
                    <option value="${techLadder.id}">${techLadder.word}</option>
                </#list>
            </select>
        </div>

        <div class="col-auto">
            <label for="techInterior" class="col-form-label">Технология, использующаяся при внутренней отделке дома : </label>
        </div>
        <div class="col-auto">
            <select name="techInterior" class="form-select" aria-label="Default select example">
                <option selected value="61">Выберите</option>
                <#--                <option value="null">Моего места нет в списке</option>-->
                <#list techInteriors as techInterior>
                    <option value="${techInterior.id}">${techInterior.word}</option>
                </#list>
            </select>
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
<#--        <div class="col-auto">-->
<#--            <label for="keywords" class="col-form-label"> Ключевые слова : </label>-->
<#--        </div>-->
<#--        <div class="col-auto">-->
<#--            <#list types as type>-->
<#--                <input type="checkbox" class="btn-check" id="${type}" name="${type.name()}" autocomplete="off">-->
<#--                <label class="btn btn-outline-primary" for="${type}">${type.toString()}</label>-->
<#--            </#list>-->
<#--        </div>-->
        <br/>
        <input type="submit" id="but" value="Создать" class="btn btn-primary"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
<#--    <script>-->
<#--        let boxes = document.getElementsByClassName("btn-check");-->
<#--        for (let i = 0; i < boxes.length; i++)-->
<#--            boxes[i].onchange = handler;-->
<#--        function handler(e) {-->
<#--            let qnt = 0;-->
<#--            for (let i = 0; i < boxes.length; i++) {-->
<#--                if (boxes[i].checked) {-->
<#--                    qnt++;-->
<#--                }-->
<#--            }-->
<#--            if (qnt > 3) {-->
<#--                alert("Выберите не больше трех вариантов");-->
<#--                this.checked = false;-->
<#--            }-->
<#--        }-->
<#--    </script>-->
</@c.page>