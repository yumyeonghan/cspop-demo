<%--
  Created by IntelliJ IDEA.
  User: hamhyeonjun
  Date: 2023/02/27
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="kyonggi.cspop.domain.login.dto.UserSessionDto" %>
<%
    session = request.getSession(false);
    UserSessionDto cspop = (UserSessionDto) session.getAttribute("CSPOP");
    String userId = "NotLogin";
    String userName = "NotLogin";
    if (cspop != null) {
        userId = cspop.getStudentId();
        userName = cspop.getStudentName();
    }
%>
<script>
    let userId = "<%=userId%>";
    let userName = "<%=userName%>";
</script>