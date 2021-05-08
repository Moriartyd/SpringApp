<#import "../parts/common.ftlh" as c>

<@c.page "Регистрация нового места">
    <form action="/place/managing/registration" method="post">
        <div class="col-auto">
            <label for="name" class="col-form-label"> Имя : </label>
        </div>
        <div class="col-auto">
            <input type="text" id="name" name="name" class="form-control"/>
        </div>
        <br/>
        <input type="submit" id="but" value="Создать" class="btn btn-primary"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@c.page>