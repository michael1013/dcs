<%@page import="cn.gnetop.dcs.server.service.logservice.LogService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
	<head>
		<style>
		* {
			margin: 0;
			padding: 0;
			font-family: 微软雅黑;
		}
		.json-r {
			font-size: 13px;
			padding: 5px;
			border-box: box-sizing;
			border: 1px solid #ccc;
			-webkit-box-shadow: 0 0 0 3px #eee;
			box-shadow: 0 0 0 3px #eee;
			border-radius: 5px;
			margin-top: 5px;
		}
		.json-k {
			color: #1d75b3;
		}
		.json-v {
			color: #b35e14;
		}
		.json-c {
			color: #75787b;
		}
		.auth-announce {
			font-size: 14px;
		}
		.auth-announce>p{
			line-height: 20px;
			text-indent: 20px;			
		}
		.line-p {
			margin:20px auto;
			border-top:1px solid #ccc;
		}
		.darkred {
			color: #a00;
		}
		.cerulean {
			color: #06f;
		}
		.indigotin {
			color: #03c;
		}
		table tr th {
			background-color: #aaa;
		}
		.result-code-li {
			list-style: none;
		}
		.result-code-li li{
			border-bottom: 1px solid #ccc;
			margin:0px;
			padding: 5px;
		}
		.li-col-1 {
			display: inline-block;
			width: 80px;
		}
		.li-col-2 {
			display: inline-block;
			width: 120px;
		}
		.li-col-3 {
			display: inline-block;
			
			width: 350px;
		}
		</style>
	</head>
	<body>
		<div style="width: 100%;background-color: #">
		
		</div>
		<div style="width: 70%;margin:0 auto;border: 1px solid #ccc;padding: 10px;border-box:box-sizing;margin-top:20px;">
			<div style="width: 30%;height: auto; float: left;font-size: 14px;">
				<c:forEach items="${actionList}" var="action">
					<p style="margin: 3px;">/json/<c:out value="${action.service}" />/<c:out value="${action.action}"></c:out></p>
				</c:forEach>
			</div>
			<div style="width: 65%;height: auto; float: right;">
				<div>
					<h3>AUTHENTICATION</h3>
					<div class="auth-announce">
						<h4>请求头参数列表:</h4>
						<p style="">请将如下参数添加至请求头中</p>
						<div class="json-r" style="padding: 10px;border-box:box-sizing;">
							<p><span class="json-k">version</span>: 版本号, 默认为 <span class="json-v">1.0</span></p>
							<p><span class="json-k">componentCode</span>: 客户端部件编号, <span class="java-v">需向服务端索取分配指定编号</span></p>
							<p><span class="json-k">timeStamp</span>: 时间戳, 格式为 <span class="json-v">YYYYMMDDHHmmss</span></p>
							<p><span class="json-k">authenticateCode</span>: 认证编号由 <span class="json-k">version|componentCode|timestamp</span> 组成, 使用 <span class="json-v">DEC+base64</span> 加密算法加密, 加密密钥: 由服务端分配, 请向服务端工作人员索取</p>
						</div>
						<p style="margin:5px auto;">DES+Base64 加密算法</p>
						<details ontoggle="javascript:void(0);" class="json-r" style="padding: 10px;border-box:box-sizing;overflow-y:hidden;">
							<summary style="cursor:pointer;">查看完整代码</summary>
							<span class="darkred">import</span> java.security.SecureRandom;<br><br>
							<span class="darkred">import</span> javax.crypto.Cipher;<br>
							<span class="darkred">import</span> javax.crypto.SecretKey;<br>
							<span class="darkred">import</span> javax.crypto.SecretKeyFactory;<br>
							<span class="darkred">import</span> javax.crypto.spec.DESKeySpec;<br><br>
							<span class="darkred">import</span> org.apache.commons.codec.binary.Base64;<br><br>
							<span class="darkred">public class</span> DESBaseUtils {<br><br>
							<div style="margin-left: 20px;">
								<span class="darkred">public static</span> String encrypt(String data, String key) <span class="darkred">throws</span> Exception {<br>
									<div style="margin-left: 20px;">
										<span class="darkred">byte</span>[] keyBytes = key.getBytes(<span class="cerulean">"utf-8"</span>);<br>
										<span class="darkred">byte</span>[] dataBytes = data.getBytes(<span class="cerulean">"utf-8"</span>);<br>
										SecureRandom sr = new SecureRandom();<br>
										DESKeySpec dks = new DESKeySpec(keyBytes);<br>
										SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(<span class="cerulean">"DES"</span>);<br>
										SecretKey securekey = keyFactory.generateSecret(dks);<br>
										Cipher cipher = Cipher.getInstance(<span class="cerulean">"DES"</span>);<br>
										cipher.init(Cipher.<span class="indigotin">ENCRYPT_MODE</span>, securekey, sr);<br>
										<span class="darkred">byte</span>[] resultBytes = cipher.doFinal(dataBytes);<br>
										<span class="darkred">return new</span> String(Base64.encodeBase64(resultBytes));<br>
									</div>
									}<br><br>
									<span class="darkred">public static</span> String decrypt(String data, String key) throws Exception {<br>
									<div style="margin-left: 20px;">
											<span class="darkred">byte</span>[] keyBytes = key.getBytes(<span class="cerulean">"utf-8"</span>);<br>
											<span class="darkred">byte</span>[] dataBytes = Base64.decodeBase64(data.getBytes(<span class="cerulean">"utf-8"</span>));<br>
											SecureRandom sr = new SecureRandom();<br>
											DESKeySpec dks = new DESKeySpec(keyBytes);<br>
											SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(<span class="cerulean">"DES"</span>);<br>
											SecretKey securekey = keyFactory.generateSecret(dks);<br>
											Cipher cipher = Cipher.getInstance(<span class="cerulean">"DES"</span>);<br>
											cipher.init(Cipher.<span class="indigotin">DECRYPT_MODE</span>, securekey, sr);<br>
											<span class="darkred">byte</span>[] resultBytes = cipher.doFinal(dataBytes);<br>
											<span class="darkred">return new</span> String(resultBytes, <span class="cerulean">"utf-8"</span>);<br>
									</div>	
									}<br><br>
									<span class="darkred">public static void</span> main(String[] args) <span class="darkred">throws</span> Exception {<br>
									<div style="margin-left: 20px;">
											String key = <span class="cerulean">"encryptKey"</span>;<br>
											String data = <span class="cerulean">"1.0|comp|2017010101020202"</span>;<br>
											String code = encrypt(data, key);<br>
											System.<span class="indigotin">out</span>.println(code);<br>
											System.<span class="indigotin">out</span>.println(decrypt(code, key));<br>
									</div>
									}<br>
								</div>
							}<br>
						</details>
					</div>
				</div>
				<p class="line-p"></p>
				<div>
					<h3>REQUEST</h3>
				</div>
				<c:forEach items="${actionList}" var="action">
					<div style="margin: 20px auto;">
						<h4><c:out value="${action.action}"></c:out>(<c:out value="${action.comment}"></c:out>)</h4>
						<div class="json-r">
							<p>{</p>
							<c:forEach items="${action.columnList}" var="column" varStatus="st">
								<p style="text-indent: 18px;">
									<span class="json-k">"<c:out value="${column.action}"></c:out>"</span>
									:
									<span class="json-v">"<c:out value="${column.value}"></c:out>"</span>
									<c:if test="${st.index != (fn:length(action.columnList)-1)}">
									,
									</c:if>
									<span class="json-c">//	<c:out value="${column.comment}"></c:out></span>
								</p>
							</c:forEach>
							<p>}</p>
						</div>
					</div>
				</c:forEach>
				<p class="line-p"></p>
				<div>
					<h3>RESPONSE</h3>
				</div>
				<div style="margin: 20px auto;">
					<div class="json-r">
						<p>{</p>
						<p style="text-indent: 18px;">
							<span class="json-k">"result"</span>
							:{
						</p>
						<p style="text-indent: 36px;">
							<span class="json-k">"resultCode"</span>
							:
							<span class="json-v">"000000"</span>,
						</p>
						<p style="text-indent: 36px;">
							<span class="json-k">"resultMsg"</span>
							:
							<span class="json-v">"成功"</span>
						</p>
						<p style="text-indent: 18px;">}</p>
						<p>}</p>
					</div>
					<div>
						<ul class="result-code-li" style="font-size: 13px;margin-top: 20px;">
							<li style="background-color: #">
								<span class="li-col-1">结果码</span><span class="li-col-2">结果信息</span><span class="li-col-3">备注</span>
							</li>
							<li>
								<span class="li-col-1">000000</span><span class="li-col-2">成功</span><span class="li-col-3"></span>
							</li>
							<li>
								<span class="li-col-1">403999</span><span class="li-col-2">连接被拒绝</span><span class="li-col-3">请求头鉴权失败</span>
							</li>
							<li>
								<span class="li-col-1">539990</span><span class="li-col-2">请求参数格式错误</span><span class="li-col-3">请求参数格式错误, 或请求链接有误</span>
							</li>
						</table>
					</div>
				</div>
			</div>
			<div style="clear:both;"></div>
		</div>
	</body>
</html>