<html>
    <head>
        <title>post to jsp</title>
    </head>
    <body>
        <form action="/learn/jsp" method="post">
            <input type="text" name="username" />
            <input type="submit" value="submit" />
        </form>
        <script>
            fetch("/learn/jsp")
                .then((resp) => resp.text())
                .then((text) => document.body.append(text));
        </script>
    </body>
</html>
