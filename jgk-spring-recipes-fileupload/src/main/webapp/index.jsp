<html>
<head>
<title>File Upload</title>
</head>
<body>
<h2>jgk-spring-recipes - File Upload</h2>
and more here

<a href="http://static.springsource.org/spring/docs/2.0.x/reference/mvc.html#mvc-multipart">http://static.springsource.org/spring/docs/2.0.x/reference/mvc.html#mvc-multipart</a>
<hr/>
<h1>Please upload a file</h1>
        <form method="post" action="fileupload/upload.form" enctype="multipart/form-data">
            <input type="file" name="file"/>
            <input type="submit"/>
        </form>
</body>
</html>
