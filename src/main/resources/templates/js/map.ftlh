<#macro mainMap>
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
</#macro>

<#macro getCoords>
    <script type="text/javascript">
        ymaps.ready(init);

        var myMap, myPlacemark;

        function init() {
            var cord = $("#coords").text(); // адрес
            myMap = new ymaps.Map("map", {
                center: [55.76, 37.64],
                zoom: 7
            });

            var myGeocoder = ymaps.geocode(cord); // пытаюсь передать переменную
            myGeocoder.then(
                function (res) {
                    myMap.geoObjects.add(res.geoObjects);

                    var adres = result.geoObjects
                        .get(0)
                        .properties.get("metaDataProperty")
                        .getAll(); // записываю координаты в переменную

                    myPlacemark = new ymaps.Placemark([adres], {
                        // пытаюсь передать координаты и поставить метку
                        hintContent: "Москва!",
                        balloonContent: "Столица России"
                    });

                    myMap.geoObjects.add(myPlacemark);
                },
                function (err) {
                    // обработка ошибки
                }
            );
        }

    </script>
</#macro>

<#macro getBySelection>
    <script type="text/javascript">
        ymaps.ready(init);

        function init() {
            var myPlacemark,
                myMap = new ymaps.Map('map', {
                    center: [55.753994, 37.622093],
                    zoom: 9
                }, {
                    searchControlProvider: 'yandex#search'
                });

            // Слушаем клик на карте.
            myMap.events.add('click', function (e) {
                var coords = e.get('coords');

                // Если метка уже создана – просто передвигаем ее.
                if (myPlacemark) {
                    myPlacemark.geometry.setCoordinates(coords);
                }
                // Если нет – создаем.
                else {
                    myPlacemark = createPlacemark(coords);
                    myMap.geoObjects.add(myPlacemark);
                    // Слушаем событие окончания перетаскивания на метке.
                    myPlacemark.events.add('dragend', function () {
                        getAddress(myPlacemark.geometry.getCoordinates());
                    });
                }
                getAddress(coords);
                setCoords(coords);
            });

            function setCoords(coords) {
                console.log(coords);
                let lo = document.getElementById("long").value = coords[1];
                let la = document.getElementById("lat").value = coords[0];
            }

            // Создание метки.
            function createPlacemark(coords) {
                return new ymaps.Placemark(coords, {
                    iconCaption: 'поиск...'
                }, {
                    preset: 'islands#violetDotIconWithCaption',
                    draggable: true
                });
            }

            // Определяем адрес по координатам (обратное геокодирование).
            function getAddress(coords) {
                myPlacemark.properties.set('iconCaption', 'поиск...');
                ymaps.geocode(coords).then(function (res) {
                    var firstGeoObject = res.geoObjects.get(0);

                    myPlacemark.properties
                        .set({
                            // Формируем строку с данными об объекте.
                            iconCaption: [
                                // Название населенного пункта или вышестоящее административно-территориальное образование.
                                firstGeoObject.getLocalities().length ? firstGeoObject.getLocalities() : firstGeoObject.getAdministrativeAreas(),
                                // Получаем путь до топонима, если метод вернул null, запрашиваем наименование здания.
                                firstGeoObject.getThoroughfare() || firstGeoObject.getPremise()
                            ].filter(Boolean).join(', '),
                            // В качестве контента балуна задаем строку с адресом объекта.
                            balloonContent: firstGeoObject.getAddressLine()
                        });
                });
            }
        }

    </script>
</#macro>