<#macro page title>
<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<#--    Яндекс карты-->
    <script src="https://api-maps.yandex.ru/2.1/?apikey=0d8e6297-4ecd-48c7-badf-bbd9d8c03cfb&lang=ru_RU" type="text/javascript">
    </script>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <title>${title}</title>
</head>
<body>
<#include "navbar.ftlh">
<div class="container mt-3" id="mainContainer">
<#nested>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
</body>
</html>
</#macro>
