<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Home - Pick 'Em</title>
</head>
<body>
    Welcome ${displayName}!
    <br />
    <sec:ifNotLoggedIn>
        <g:link controller="login">Login</g:link>
    </sec:ifNotLoggedIn>
    <sec:ifLoggedIn>
        <sec:ifNotGranted roles="ROLE_ADMIN">
            <g:render template="userActions" />
        </sec:ifNotGranted>
        <sec:ifAllGranted roles="ROLE_ADMIN">
            <g:render template="adminActions" />
        </sec:ifAllGranted>
        <br />
        <g:form controller="logout">
            <g:submitButton name="Logout">Logout</g:submitButton>
        </g:form>
    </sec:ifLoggedIn>
</body>
</html>