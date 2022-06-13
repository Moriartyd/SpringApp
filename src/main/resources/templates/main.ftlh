<#import "parts/common.ftlh" as c>
<#--<#import "parts/cards.ftlh" as card>-->
<#import "parts/main.ftlh" as main>

<@c.page "Добро пожаловать">

    <input type="text" class="form-control mb-4" id="search" placeholder="Поиск">

    <div class="row">
        <div class="col" id="usercol" style="width: 20rem;">Пользователи</div>
        <div class="col" id="calccol" style="width: 20rem;">Расчеты</div>
<#--        <div class="col" id="contractorcol" style="width: 20rem;">Подрядчики</div>-->
    </div>
    <@main.addJs/>
</@c.page>