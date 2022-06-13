<#import "parts/common.ftlh" as c>
<#--<#import "parts/cards.ftlh" as card>-->
<#import "parts/main.ftlh" as main>

<@c.page "Добро пожаловать">

    <input type="text" class="form-control mb-4" id="search" placeholder="Поиск">

    <div class="row">
        <div class="col" id="usercol" style="width: 20rem;">Пользователи</div>
        <div class="col" id="eventcol" style="width: 20rem;">Контракты</div>
        <div class="col" id="placecol" style="width: 20rem;">Подрядчики</div>
    </div>
    <@main.addJs/>
</@c.page>