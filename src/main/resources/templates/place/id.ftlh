<#import "../parts/common.ftlh" as c>

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



    <script type="text/javascript">
        ymaps.ready(init);

        function init() {
            var myMap = new ymaps.Map("map", {
                center: [${place.latitude?replace(",", ".", 'i')}, ${place.longitude?replace(",", ".", 'i')}],
                zoom: 17
            }, {
                searchControlProvider: 'yandex#search'
            });

            // Создаем геообъект с типом геометрии "Точка".
            myGeoObject = new ymaps.GeoObject({
                // Определение геометрии" Point".
                geometry: {
                    type: "Point",
                    coordinates: [${place.latitude?replace(",", ".", 'i')}, ${place.longitude?replace(",", ".", 'i')}]
                },
                // Определение данных геообъекта.
                properties: {
                    hintContent: "${place.name}",
                    balloonContentHeader: "${place.name}",
                }
            }, {
                // Установка предустановки для метки с точкой и без содержимого.
                preset: "islands#redDotIcon",
                // Включение перетаскивания.
                draggable: true,
                // Переопределение макета для содержимого в нижней части воздушного шара.
                balloonContentFooterLayout: ymaps.templateLayoutFactory.createClass(
                    'population: {{ properties.population }}, coordinates: {{ geometry.coordinates }}'
                ),
                // Отключение задержки для закрытия всплывающей подсказки.
                hintCloseTimeout: null
            });
            myMap.geoObjects.add(myGeoObject);
        }
    </script>
</@c.page>