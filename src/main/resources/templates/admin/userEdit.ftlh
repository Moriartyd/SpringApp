<#import "../parts/common.ftlh" as c>

<@c.page "Редактирование пользователя ${user.login}">
    <form method="post" action="/admin/user/${user.id}">
        <input type="text" name="login" value="${user.login}">
        <#list roles as role>
            <div>
                <label><input class="checkbox" type="checkbox" name="${role}" ${user.role?contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Изменить">
    </form>
    <script>
        let boxes = document.getElementsByClassName("checkbox");
        for (let i = 0; i < boxes.length; i++)
            boxes[i].onchange = handler;
        function handler(e) {
            for (let i = 0; i < boxes.length; i++)
                if (boxes[i].checked && boxes[i] !== this)
                    boxes[i].checked = false;
        }
    </script>
</@c.page>