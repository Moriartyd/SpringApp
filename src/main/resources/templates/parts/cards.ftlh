
<#macro usercard user>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">${user.login}</h5>
            <h6 class="card-text">${user.name} ${user.surname}</h6>
            <a href="/user/${user.id}" class="btn btn-primary">Посмотреть</a>
        </div>
    </div>
</#macro>

<#macro placecard place>
    <#assign rate = place.rating*100/5>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">${place.name}</h5>
            <h6 class="card-text">Владелец: ${place.owner.login}</h6>
            <div class="progress mb-3">
                <div class="progress-bar" role="progressbar" style="width: ${rate}%;" aria-valuenow="${rate}" aria-valuemin="0" aria-valuemax="100">${rate/100*5}/5 баллов</div>
            </div>
            <a href="/place/${place.id}" class="btn btn-primary">Посмотреть</a>
        </div>
    </div>
</#macro>

<#macro eventcard event>
    <#assign rate = event.rating*100/5>
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">${event.name}</h5>
            <p class="card-text">${event.time}<br/>Количество людей: ${event.registeredVisitors}</p>
            <div class="progress mb-3">
                <div class="progress-bar" role="progressbar" style="width: ${rate}%;" aria-valuenow="${rate}" aria-valuemin="0" aria-valuemax="100">${rate/100*5}/5 баллов</div>
            </div>
            <a href="/events/${event.id}" class="btn btn-primary">Подробнее</a>
        </div>
    </div>
</#macro>