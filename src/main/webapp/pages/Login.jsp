<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<html>

<head>
	
	<jsp:include page="/templates/Resource.jsp" />

	<style type="text/css">
		a:link    { text-decoration: none; color: #FFFFFF }
		a:visited { text-decoration: none; color: #FFFFFF }
		a:hover   { text-decoration: underline; color: #FFF000 }
	</style>

</head>

<div id="layeroutDiv" style="position:absolute; left:0px; top:0px; width:100%; height:100%; z-index:1;">

<form:form action="loginCheck" method="post" modelAttribute="login">
<table border="0" cellspacing="0" cellpadding="0" style="width:100%; height:100%;">
	<tr>
		<td colspan="2" class="layoutTop">
			<jsp:include page="/templates/LayoutTop.jsp" />
		</td>
	</tr>
	<tr>
		<td class="layoutLeft">
			<jsp:include page="/templates/LayoutLeft.jsp" />
		</td>
		<td class="layoutMain">

			<div id="loginBgDiv" style="position:absolute; left:300px; top:190px; z-index:1;">
			<table border="0" cellspacing="6" cellpadding="0" class="loginTable">
				<tr>
					<td>
						<table border="0" cellspacing="0" cellpadding="0" class="loginTable">
							<tr>
								<td class="loginBg">&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</div>
			
			<div id="loginDiv" style="position:absolute; left:375px; top:280px; z-index:2;">
			<table border="0" cellspacing="0" cellpadding="0" class="loginInputTable">
				<tr>
					<td align="center" class="loginInputTd1">帳&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;號</td>
					<td class="loginInputTd2"><form:input path="id" size="25" cssClass="loginInput" /></td>
				</tr>
				<tr>
					<td align="center" class="loginInputTd1">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;碼</td>
					<td class="loginInputTd2"><form:input path="pwd" size="25" cssClass="loginInput" /></td>
				</tr>
				<tr>
					<td colspan="2" height="10"></td>
				</tr>
				<tr>
					<td colspan="2" align="right" class="loginInputTd3">
						<input type="submit" value="登入" class="loginBtn" />
						&nbsp;/&nbsp;
						<input type="button" value="註冊" class="loginBtn" onclick="redirectPage('/addMember');" />
					</td>
				</tr>
			</table>
			</div>

		</td>
	</tr>
</table>
</form:form>

</div>

</html>
