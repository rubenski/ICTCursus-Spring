<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Browse test</title>

    <script type="text/javascript">
        function select_image() {
            var CKEditorFuncNum = ${editorFuncNum};
            window.parent.opener.CKEDITOR.tools.callFunction(CKEditorFuncNum, '/static/img/logo/2.jpg', '');
            self.close();
        }
    </script>

</head>
<body>

<a href="javascript:select_image();">Insert this image</a>

</body>
</html>

