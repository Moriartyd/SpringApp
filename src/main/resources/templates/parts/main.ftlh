<#macro addJs>
    <script>
        async function main() {
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

            var calcs;
            await fetch("/json/calcs")
                .then((response) => {
                    return response.json();
                })
                .then((data) => {
                    calcs = data;
                })
                .catch((err) => {
                    console.log(err);
                });

            var contractors;
            // await fetch("/json/contractors")
            //     .then((response) => {
            //         return response.json();
            //     })
            //     .then((data) => {
            //         contractors = data;
            //     })
            //     .catch((err) => {
            //         console.log(err);
            //     });

            fillColums(users, calcs, contractors);

            document.getElementById('search').onkeyup = function() {
                var usercol = document.getElementById("usercol");
                var calccol = document.getElementById("calccol");
                var contractorcol = document.getElementById("contractorcol");
                usercol.innerHTML = 'Пользователи';
                calccol.innerHTML = 'Контракты';
                contractorcol.innerHTML = 'Подрядчики';
                let searchText = this.value.toLowerCase();
                for (var i = 0; i < users.length && i < 10; i++) {
                    if (users[i].name.toLowerCase().search(searchText) !== -1 ||
                        users[i].surname.toLowerCase().search(searchText) !== -1 ||
                        users[i].login.toLowerCase().search(searchText) !== -1) {
                        usercol.appendChild(newRow().appendChild(userCard(users[i])));
                    }
                }
                for (var i = 0; i < calcs.length && i < 10; i++) {
                    if (calcs[i].name.toLowerCase().search(searchText) !== -1) {
                        calccol.appendChild(newRow().appendChild(calcCard(calcs[i])));
                    }
                }
                // for (var i = 0; i < contractors.length && i < 10; i++) {
                //     if (contractors[i].id.toLowerCase().search(searchText) !== -1 ||
                //         contractors[i].owner.login.toLowerCase().search(searchText) !== -1) {
                //         // contractorcol.appendChild(newRow().appendChild(contractorCard(contractors[i])));
                //     }
                // }
            }
            // fillColumsWithSearch(users, events, places, text);


            function fillColums(users, calcs, contractors) {
                var usercol = document.getElementById("usercol");
                var calccol = document.getElementById("calccol");
                var contractorcol = document.getElementById("contractorcol");

                for (var i = 0; i < users.length && i < 10; i++) {
                    usercol.appendChild(newRow().appendChild(userCard(users[i])));
                }
                for (var i = 0; i < calcs.length && i < 10; i++) {
                    calccol.appendChild(newRow().appendChild(calcCard(calcs[i])));
                }
                // for (var i = 0; i < contractors.length && i < 10; i++) {
                //     // contractorcol.appendChild(newRow().appendChild(contractorCard(contractors[i])));
                // }
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

            function calcCard(calc) {
                let card = document.createElement("div");
                card.className = "card mb-3";
                card.setAttribute("style", "width: 400px;")

                let cardbody = document.createElement("div");
                cardbody.className = "card-body";

                let title = document.createElement("h5");
                title.className = "card-title";
                let titleText = document.createTextNode(calc.id);
                title.appendChild(titleText);

                let text = document.createElement("h6");
                let text2 = document.createElement("h6");
                text.className = "card-text";
                text2.className = "card-text";
                let time = document.createTextNode(calc.cost);
                // let peoplecnt = document.createTextNode("Количество людей: " + event.registeredVisitors);
                text.appendChild(time);
                // text2.appendChild(peoplecnt);



                let a = document.createElement("a");
                a.className = "btn btn-primary";
                let elemText = document.createTextNode("Подронее");
                a.appendChild(elemText);
                a.setAttribute("href", "/calc/" + calc.id);

                cardbody.appendChild(title);
                cardbody.appendChild(text);
                cardbody.appendChild(text2);
                // cardbody.appendChild(pb);
                cardbody.appendChild(a);
                card.appendChild(cardbody);
                return card;
            }

            function contractorCard(contractor) {
                let card = document.createElement("div");
                card.className = "card mb-3";
                card.setAttribute("style", "width: 400px;")

                let cardbody = document.createElement("div");
                cardbody.className = "card-body";

                let title = document.createElement("h5");
                title.className = "card-title";
                let titleText = document.createTextNode(contractor.id);
                title.appendChild(titleText);

                let text = document.createElement("h6");
                text.className = "card-text";
                let time = document.createTextNode("Логин: ");
                text.appendChild(time);
                // let href = document.createElement("a");
                // href.setAttribute("href", "/user/" + place.owner.id);
                // href.appendChild(document.createTextNode(place.owner.login));
                // text.appendChild(href);
                let pb = document.createElement("div");
                pb.setAttribute("class", "progress mb-3");
                let pbd = document.createElement("div");
                pbd.setAttribute("class", "progress-bar");
                pbd.setAttribute("role", "progressbar");
                // console.log("Ev = " + event.name + "ev:" + (event.evaluators !== 0));
                pb.appendChild(pbd);

                if (contractor.ratingNum !== 0) {
                    pbd.setAttribute("style", "width: " + contractor.rating * 100 / (5 * contractor.ratingNum) + "%;");
                    pbd.setAttribute("aria-valuenow", contractor.rating * 100 / (5 * contractor.ratingNum));
                    pbd.setAttribute("aria-valuemin", "0");
                    pbd.setAttribute("aria-valuemax", "100");
                    pdbtext = document.createTextNode(contractor.rating / contractor.ratingNum + "/5 баллов");
                } else {
                    pbd.setAttribute("style", "width: " + contractor.rating * 100 / 5 + "%;");
                    pbd.setAttribute("aria-valuenow", contractor.rating * 100 / 5);
                    pbd.setAttribute("aria-valuemin", "0");
                    pbd.setAttribute("aria-valuemax", "100");
                    pdbtext = document.createTextNode(contractor.rating + "/5 баллов");
                }
                pbd.appendChild(pdbtext);

                let a = document.createElement("a");
                a.className = "btn btn-primary";
                let elemText = document.createTextNode("Посмотреть");
                a.appendChild(elemText);
                a.setAttribute("href", "/contractor/" + contractor.user.id);

                cardbody.appendChild(title);
                cardbody.appendChild(text);
                cardbody.appendChild(pb);
                cardbody.appendChild(a);
                card.appendChild(cardbody);
                return card;
            }
        }
        main();
    </script>
</#macro>