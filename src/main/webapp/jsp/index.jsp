<html>
    <head>
        <title>Jsp</title>
    </head>
    <body>
        <form action="/api/jsp" method="post">
            <input type="text" name="username" />
            <input type="submit" value="submit" />
        </form>
        <script>
            fetch("/api/jsp")
                .then((resp) => resp.text())
                .then((text) => document.body.append(text));
        </script>
    </body>
</html>
