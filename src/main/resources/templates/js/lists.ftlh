<#macro users>
    <script>
        async function main(){

            var users;
            await fetch("json/users")
                .then((response) => {
                    return response.json();
                })
                .then((data) => {
                    users = data;
                })
                .catch((err) => {
                    console.log(err);
                });
            var body = document.getElementById("maindiv");

            for (var i = 0; i < users.length / 3 && i < 10; i++) {
                var row = newRow();
                for (var j = 0; j < 3; j++) {
                    if (users[i * 3 + j]) {
                        var col = document.createElement("div");
                        col.className = "col";
                        col.appendChild(userCard(users[i * 3 + j]))
                        row.appendChild(col);
                    }
                }
                body.appendChild(row);
            }

            function newRow(){
                var row = document.createElement("div");
                row.setAttribute("class", "row");
                return row;
            }

            function userCard(user) {
                let card = document.createElement("div");
                card.className = "card mb-3";

                let cardbody = document.createElement("div");
                cardbody.className = "card-body";

                let title = document.createElement("h5");
                title.className = "card-title";
                let titleText = document.createTextNode(user.login);
                title.appendChild(titleText);

                let text = document.createElement("h6");
                text.className = "card-text";
                let cardtext = document.createTextNode(user.name + ' ' + user.surname);
                text.appendChild(cardtext);

                let a = document.createElement("a");
                a.className = "btn btn-primary";
                let elemText = document.createTextNode("Перейти");
                a.appendChild(elemText);
                a.setAttribute("href", "/user/" + user.id);

                cardbody.appendChild(title);
                cardbody.appendChild(text);
                cardbody.appendChild(a);
                card.appendChild(cardbody);
                return card;
            }
        }
        main();
    </script>
</#macro>

<#macro events>
    <script>
        async function main() {

            var events;
            await fetch("/json/events")
                .then((response) => {
                    return response.json();
                })
                .then((data) => {
                    events = data;
                })
                .catch((err) => {
                    console.log(err);
                });

            var body = document.getElementById("maindiv");

            for (var i = 0; i < events.length / 3 && i < 10; i++) {
                var row = newRow();
                for (var j = 0; j < 3; j++) {
                    if (events[i * 3 + j]) {
                        var col = newCol();
                        col.appendChild(eventCard(events[i * 3 + j]))
                        row.appendChild(col);
                    }
                }
                body.appendChild(row);
            }

            function newCol(){
                var col = document.createElement("div");
                col.setAttribute("class", "col");
                return col;
            }

            function newRow(){
                var row = document.createElement("div");
                row.setAttribute("class", "row");
                return row;
            }

            function eventCard(event) {
                let card = document.createElement("div");
                card.className = "card mb-3";
                card.setAttribute("style", "width: 400px;")

                let cardbody = document.createElement("div");
                cardbody.className = "card-body";

                let title = document.createElement("h5");
                title.className = "card-title";
                let titleText = document.createTextNode(event.name);
                title.appendChild(titleText);

                let text = document.createElement("h6");
                let text2 = document.createElement("h6");
                text.className = "card-text";
                text2.className = "card-text";
                let time = document.createTextNode(event.time);
                let peoplecnt = document.createTextNode("Количество людей: " + event.registeredVisitors);
                text.appendChild(time);
                text2.appendChild(peoplecnt);

                let pb = document.createElement("div");
                pb.setAttribute("class", "progress mb-3");
                let pbd = document.createElement("div");
                pbd.setAttribute("class", "progress-bar");
                pbd.setAttribute("role", "progressbar");
                pbd.setAttribute("style", "width: " + event.rating * 100/(5 * event.evaluators) + "%;");
                pbd.setAttribute("aria-valuenow", event.rating * 100 /(5 * event.evaluators));
                pbd.setAttribute("aria-valuemin", "0");
                pbd.setAttribute("aria-valuemax", "100");
                pdbtext = document.createTextNode(event.rating / event.evaluators + "/5 баллов");
                pbd.appendChild(pdbtext);
                pb.appendChild(pbd);

                let a = document.createElement("a");
                a.className = "btn btn-primary";
                let elemText = document.createTextNode("Подронее");
                a.appendChild(elemText);
                a.setAttribute("href", "/events/" + event.id);

                cardbody.appendChild(title);
                cardbody.appendChild(text);
                cardbody.appendChild(text2);
                cardbody.appendChild(pb);
                cardbody.appendChild(a);
                card.appendChild(cardbody);
                return card;
            }
        }
        main();
    </script>
</#macro>

<#macro places>
    <script>
        async function main(){

            var places;
            await fetch("/json/places")
                .then((response) => {
                    return response.json();
                })
                .then((data) => {
                    places = data;
                })
                .catch((err) => {
                    console.log(err);
                });

            var body = document.getElementById("maindiv");

            for (var i = 0; i < places.length / 3 && i < 10; i++) {
                var row = newRow();
                for (var j = 0; j < 3; j++) {
                    if (places[i * 3 + j]) {
                        var col = newCol();
                        col.appendChild(placecard(places[i * 3 + j]))
                        row.appendChild(col);
                    }
                }
                body.appendChild(row);
            }

            function newCol(){
                var col = document.createElement("div");
                col.setAttribute("class", "col");
                return col;
            }

            function newRow(){
                var row = document.createElement("div");
                row.setAttribute("class", "row");
                return row;
            }

            function placecard(place) {
                let card = document.createElement("div");
                card.className = "card mb-3";
                card.setAttribute("style", "width: 400px;")

                let cardbody = document.createElement("div");
                cardbody.className = "card-body";

                let title = document.createElement("h5");
                title.className = "card-title";
                let titleText = document.createTextNode(place.name);
                title.appendChild(titleText);

                let text = document.createElement("h6");
                text.className = "card-text";
                let time = document.createTextNode("Владелец: ");
                text.appendChild(time);
                let href = document.createElement("a");
                href.setAttribute("href", "/user/" + place.owner.id);
                href.appendChild(document.createTextNode(place.owner.login));
                text.appendChild(href);

                // let pb = document.createElement("div");
                // pb.setAttribute("class", "progress mb-3");
                // let pbd = document.createElement("div");
                // pbd.setAttribute("class", "progress-bar");
                // pbd.setAttribute("role", "progressbar");
                // pbd.setAttribute("style", "width: " + place.rating*100/5 + "%;");
                // pbd.setAttribute("aria-valuenow", place.rating * 100 / 5);
                // pbd.setAttribute("aria-valuemin", "0");
                // pbd.setAttribute("aria-valuemax", "100");
                // pdbtext = document.createTextNode(place.rating + "/5 баллов");
                // pbd.appendChild(pdbtext);
                // pb.appendChild(pbd);

                let a = document.createElement("a");
                a.className = "btn btn-primary";
                let elemText = document.createTextNode("Посмотреть");
                a.appendChild(elemText);
                a.setAttribute("href", "/place/" + place.id);

                cardbody.appendChild(title);
                cardbody.appendChild(text);
                // cardbody.appendChild(pb);
                cardbody.appendChild(a);
                card.appendChild(cardbody);
                return card;
            }
        }
        main();
    </script>
</#macro>