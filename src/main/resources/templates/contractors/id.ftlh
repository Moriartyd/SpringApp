<#import "../parts/common.ftlh" as c>
<#import "../js/map.ftlh" as map>

<@c.page "${place.name}">
    <div class="container">
        <div class="row mb-3">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">В этом месте проводилось</h5>
                        <h6 class="card-text">
                            <a href="/place/${place.id}/events">${event_cnt}</a> мероприятий
                        </h6>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col align-self-center">
                <div class="position-relative top-0 start-50 translate-middle-x img-fluid" id="map" style="height: 400px"></div>
            </div>
        </div>
    </div>
    <@map.mainMap/>
</@c.page>