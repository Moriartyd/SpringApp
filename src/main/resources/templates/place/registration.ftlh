<#import "../parts/common.ftlh" as c>
<#import "../js/map.ftlh" as map>

<@c.page "Регистрация нового места">
    <form action="/place/managing/registration" method="post">
        <div class="col-auto">
            <label for="name" class="col-form-label"> Имя : </label>
        </div>
        <div class="col-auto">
            <input type="text" id="name" name="name" class="form-control"/>
        </div>
        <br/>
        <div class="position-relative top-0 start-50 translate-middle-x img-fluid" id="map" style="height: 400px"></div>
        <br/>
        <input type="submit" id="but" value="Создать" class="btn btn-primary"/>
        <input type="hidden" id="long" name="longitude"/>
        <input type="hidden" id="lat" name="latitude"/>
        <input type="hidden" id="rate" name="rating" value="5"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
    <@map.getBySelection/>
</@c.page>