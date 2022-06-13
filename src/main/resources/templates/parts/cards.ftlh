
<#macro calccard calc user>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Расчет №${calc.id}</h5>
            <h6 class="card-text">Стоимость: ${calc.cost}</h6>
            <h6 class="card-text">Создатель: <a href="/user/${user.id}">${user.login}</a></h6>
            <a href="/calc/${calc.id}" class="btn btn-primary">Посмотреть</a>
        </div>
    </div>
</#macro>

<#macro contractcard contract>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Контракт №${contract.id}</h5>
            <h6 class="card-text">Расчет: <a href="/calc/${contract.calculation.id}">№${contract.calculation.id}</a></h6>
            <h6 class="card-text">Создатель: <a href="/user/${contract.user.id}">${contract.user.login}</a></h6>
            <h6 class="card-text">Подрядчик: <a href="/user/${contract.contractor.user.id}">${contract.contractor.user.login}</a></h6>
            <#if contract.statusContractor == 1>
            <h6 class="card-text">Статус: Принят подрядчиком</h6>
            <#elseif contract.statusContractor == 0>
            <h6 class="card-text">Статус: В обработке</h6>
            <#else>
            <h6 class="card-text">Статус: Отклонен подрядчиком</h6>
            </#if>
        </div>
    </div>
</#macro>

<#macro contractorcard contractor>
    <#if contractor.rating_num != 0>
        <#assign rate = contractor.rating*100/5/contractor.rating_num>
    <#else>
        <#assign rate = contractor.rating*100/5>
    </#if>
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">${contractor.name}</h5>
            <p class="card-text">${contractor.time}<br/>Количество людей: ${contractor.registeredVisitors}</p>
            <div class="progress mb-3">
                <div class="progress-bar" role="progressbar" style="width: ${rate?string.computer}%;" aria-valuenow="${rate}" aria-valuemin="0" aria-valuemax="100">${rate/100*5}/5 баллов</div>
            </div>
            <a href="/events/${contractor.id}" class="btn btn-primary">Подробнее</a>
        </div>
    </div>
</#macro>