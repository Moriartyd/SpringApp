<#import "parts/common.ftlh" as c>

<@c.page "Вход">
  <#if message??>
      ${message}
  </#if>
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
  <div><input type="submit" value="Войти" class="btn btn-primary"/></div>
  <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>
</@c.page>