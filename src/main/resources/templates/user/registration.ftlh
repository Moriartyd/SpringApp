<#import "../parts/common.ftlh" as c>

<@c.page "Регистрация нового пользователя">
<form action="/user/registration" method="post">
    <div class="mb-3 align-items-center">
        <div class="col-auto">
            <label for="username" class="col-form-label"> Логин : </label>
        </div>
        <div class="col-auto">
            <input type="text" id="username" name="login" class="form-control"/>
        </div>
        <div class="col-auto">
            <label for="name" class="col-form-label"> Имя : </label>
        </div>
        <div class="col-auto">
            <input type="text" id="name" name="name" class="form-control"/>
        </div>
        <div class="col-auto">
            <label for="surname" class="col-form-label"> Фамилия : </label>
        </div>
        <div class="col-auto">
            <input type="text" id="surname" name="surname" class="form-control"/>
        </div>
        <div class="col-auto">
            <label for="email" class="col-form-label"> Email : </label>
        </div>
        <div class="col-auto">
            <input type="email" id="email" name="email" class="form-control"/>
        </div>
        <div class="col-auto">
            <label for="age" class="col-form-label"> Возраст : </label>
        </div>
        <div class="col-auto">
            <input type="number" id="age" name="age" class="form-control"/>
        </div>
        <div class="col-auto">
            <label for="password" class="col-form-label"> Пароль : </label>
        </div>
        <div class="col-auto">
            <input type="password" id="password" name="password" class="form-control"/>
        </div>
    </div>
    <input type="submit" id="but" value="Зарегистрироваться" class="btn btn-primary"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>
    <script>
        let button = document.getElementById('but')
        const handleClick = () => {
            alert('Подтвердите почту')
        }
            button.addEventListener('click', handleClick)
    </script>
<#if message??><h1>${message}</h1></#if>
</@c.page>