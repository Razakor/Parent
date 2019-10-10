<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
    <body>
    <form method="post">
        <input type="text" name="firstStop" placeholder="Введіть назву зупинки" />
        <input type="text" name="secondStop" placeholder="Введіть назву місця призначення">
        <button type="submit">Знайти</button>
    </form>
    <#list schedule as trolleybus>
    <p>${trolleybus}
        <#else>
            Nothing
        </#list>
    </body>
</html>