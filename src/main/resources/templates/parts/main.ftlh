<#macro addJs p>
    <script>
        async function main() {
        let body = document.getElementById("mainContainer");
        let row = document.createElement("div");
        row.className = "row";
        let col = document.createElement("div");
        col.className = "col";

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

            // var events;
            // await fetch("echo/json/events")
            //     .then((response) => {
            //         return response.json();
            //     })
            //     .then((data) => {
            //         events = data;
            //     })
            //     .catch((err) => {
            //         console.log(err);
            //     });
            for (var i = 0; i < users.length; i++) {
                col.appendChild(userCard(users[i]));
            }
        row.appendChild(col);
        body.appendChild(row);

        function userCard(user) {
            let card = document.createElement("div");
            card.className = "card";

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
            let elemText = document.createTextNode("Привет мир");
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