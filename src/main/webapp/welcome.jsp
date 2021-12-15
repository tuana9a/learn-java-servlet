<html>
    <head>
        <title>Welcome</title>
    </head>
    <body>
        <h1><%out.print("POST: " + request.getAttribute("welcome"));%></h1>
        <p><strong>if you see this then your POST request is success</strong></p>
    </body>
</html>
