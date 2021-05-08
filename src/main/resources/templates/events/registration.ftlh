<#import "../parts/common.ftlh" as c>

<@c.page "Регистрация нового мероприятия">
    <form action="/events/managing/registration" method="post">
        <div class="col-auto">
            <label for="name" class="col-form-label"> Название : </label>
        </div>
        <div class="col-auto">
            <input type="text" id="name" name="name" class="form-control"/>
        </div>

        <div class="col-auto">
            <label for="time" class="col-form-label"> Время проведения : </label>
        </div>
        <div class="col-auto">
            <input type="datetime-local" id="time" name="time" class="form-control"/>
        </div>

        <div class="col-auto">
            <label for="placeName" class="col-form-label"> Место : </label>
        </div>
        <div class="col-auto">
            <select name="place" class="form-select" aria-label="Default select example">
                <option selected>Выберите место</option>
                <option value="null">Моего места нет в списке</option>
                <#list places as place>
                <option value="${place.id}">${place.name}</option>
                </#list>
            </select>
        </div>

        <div class="col-auto">
            <label for="cost" class="col-form-label"> Цена за вход : </label>
        </div>
        <div class="col-auto">
            <input type="number" id="cost" name="cost" class="form-control"/>
        </div>

        <div class="col-auto">
            <label for="minAge" class="col-form-label"> Минимальный возраст : </label>
        </div>
        <div class="col-auto">
            <input type="number" id="minAge" name="minAge" class="form-control"/>
        </div>
        <br/>
        <input type="submit" id="but" value="Создать" class="btn btn-primary"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@c.page>