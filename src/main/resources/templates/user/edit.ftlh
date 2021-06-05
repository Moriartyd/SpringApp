<#import "../parts/common.ftlh" as c>

<@c.page "Редактирование">
    <form action="/user/${user.id}/edit" method="post">
        <div class="mb-3 align-items-center">
            <div class="col-auto">
                <label for="username" class="col-form-label"> Логин : </label>
            </div>
            <div class="col-auto">
                <input type="text" id="username" name="login" value="${user.login}" class="form-control" disabled/>
            </div>
            <div class="col-auto">
                <label for="name" class="col-form-label"> Имя : </label>
            </div>
            <div class="col-auto">
                <input type="text" id="name" name="name" class="form-control" value="${user.name}"/>
            </div>
            <div class="col-auto">
                <label for="surname" class="col-form-label"> Фамилия : </label>
            </div>
            <div class="col-auto">
                <input type="text" id="surname" name="surname" class="form-control" value="${user.surname}"/>
            </div>
            <div class="col-auto">
                <label for="email" class="col-form-label"> Email : </label>
            </div>
            <div class="col-auto">
                <input type="email" id="email" name="email" class="form-control" value="${user.email!"example@mail.ru"}"/>
            </div>
            <div class="col-auto">
                <label for="age" class="col-form-label"> Возраст : </label>
            </div>
            <div class="col-auto">
                <input type="number" id="age" name="age" class="form-control" value="${user.age}"/>
            </div>
            <div class="col-auto">
                <label for="password" class="col-form-label"> Пароль : </label>
            </div>
            <div class="col-auto">
                <input type="password" id="password" name="password" class="form-control"/>
            </div>
            <br/>
            <div class="col-auto">
                <#list keywords as keyword>
                    <input type="checkbox" class="btn-check" id="${keyword}" name="${keyword.name()}" autocomplete="off" ${user.keywords?seq_contains(keyword)?string("checked", "")}>
                    <label class="btn btn-outline-primary" for="${keyword}">${keyword.toString()}</label>
                </#list>
            </div>
        </div>
        <input type="submit" id="but" value="Изменить" class="btn btn-primary"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
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