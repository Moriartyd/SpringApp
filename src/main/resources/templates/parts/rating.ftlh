<#macro get>
    <div class="rating-area">
        <input type="radio" id="star-5" name="rating" value="5" onclick="rate()">
        <label for="star-5" title="Оценка «5»"></label>
        <input type="radio" id="star-4" name="rating" value="4" onclick="rate()">
        <label for="star-4" title="Оценка «4»"></label>
        <input type="radio" id="star-3" name="rating" value="3" onclick="rate()">
        <label for="star-3" title="Оценка «3»"></label>
        <input type="radio" id="star-2" name="rating" value="2" onclick="rate()">
        <label for="star-2" title="Оценка «2»"></label>
        <input type="radio" id="star-1" name="rating" value="1" onclick="rate()">
        <label for="star-1" title="Оценка «1»"></label>
    </div>
    <style type="text/css">
        .rating-area {
            overflow: hidden;
            width: 265px;
            margin: 0 auto;
        }
        .rating-area:not(:checked) > input {
            display: none;
        }
        .rating-area:not(:checked) > label {
            float: right;
            width: 42px;
            padding: 0;
            cursor: pointer;
            font-size: 32px;
            line-height: 32px;
            color: lightgrey;
            text-shadow: 1px 1px #bbb;
        }
        .rating-area:not(:checked) > label:before {
            content: '★';
        }
        .rating-area > input:checked ~ label {
            color: gold;
            text-shadow: 1px 1px #c60;
        }
        .rating-area:not(:checked) > label:hover,
        .rating-area:not(:checked) > label:hover ~ label {
            color: gold;
        }
        .rating-area > input:checked + label:hover,
        .rating-area > input:checked + label:hover ~ label,
        .rating-area > input:checked ~ label:hover,
        .rating-area > input:checked ~ label:hover ~ label,
        .rating-area > label:hover ~ input:checked ~ label {
            color: gold;
            text-shadow: 1px 1px goldenrod;
        }
        .rate-area > label:active {
            position: relative;
        }
    </style>
    <script>
        async function rate() {
            var form = document.getElementById("form-id");
            form.submit();
        }
    </script>
</#macro>

<#macro show>

    <div class="rating-mini">
        <span class="active"></span>
        <#if rated gt 1>
            <span class="active"></span>
        <#else>
            <span></span>
        </#if>
        <#if rated gt 2>
            <span class="active"></span>
        <#else>
            <span></span>
        </#if><#if rated gt 3>
            <span class="active"></span>
        <#else>
            <span></span>
        </#if>
        <#if rated gt 4>
            <span class="active"></span>
        <#else>
            <span></span>
        </#if>
    </div>

    <style type="text/css">
    .rating-mini {
    display: inline-block;
    font-size: 0;
    }
    .rating-mini span {
    padding: 0;
    font-size: 32px;
    line-height: 1;
    color: lightgrey;
    }
    .rating-mini > span:before {
    content: '★';
    }
    .rating-mini > span.active {
    color: gold;
    }
    </style>

</#macro>