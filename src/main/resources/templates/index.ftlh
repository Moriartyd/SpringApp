<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page "Вход и Регистрация">
<h2>Информационная система поиска мероприятий</h2>
    <form action="/login" method="post">
        <div class="mb-3 align-items-center">
            <div class="col-auto">
                <label for="username" class="col-form-label"> Логин : </label>
            </div>
            <div class="col-auto">
                <input type="text" id="username" name="username" class="form-control"/>
            </div>
            <div class="col-auto">
                <label for="password" class="col-form-label"> Пароль : </label>
            </div>
            <div class="col-auto">
                <input type="password" id="password" name="password" class="form-control"/>
            </div>
        </div>
        <div style="justify-content: center">
            <input type="submit" value="Войти" class="btn btn-primary" style="justify-content: center"/>
            <a href="/user/registration" class="btn btn-primary" style="margin-left: auto;margin-right: auto;">Зарегистрироваться</a>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@c.page>