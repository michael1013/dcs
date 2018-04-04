package cn.gnetop.dcs.web.servlet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import cn.gnetop.dcs.server.service.logservice.LogService;
import cn.gnetop.dcs.server.service.userservice.UserService;
import cn.gnetop.dcs.system.engine.IService;
import cn.gnetop.dcs.system.entity.Result;
import cn.gnetop.dcs.system.helper.DcsComment;
import cn.gnetop.dcs.system.helper.ResultCodeManager;
import cn.gnetop.pde.foundation.ArrayUtils;

public class DocServlet extends HttpServlet {

	private static final long serialVersionUID = -4709565906386022759L;

	public void service(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
		req.setAttribute("logActionList", this.getActionList(LogService.class));
		req.setAttribute("userActionList", this.getActionList(UserService.class));
		req.setAttribute("resultList", this.getResultMap());
		req.getRequestDispatcher("/WEB-INF/view/doc.jsp").forward(req, rsp);
	}

	private List<Action> getActionList(Class<? extends IService> serviceClass) {
		List<Action> actionList = new ArrayList<>();
		List<Class<? extends IService>> classList = new ArrayList<>();
		classList.add(serviceClass);
		for (Class<? extends IService> clz : classList) {
			for (Method method : clz.getDeclaredMethods()) {
				String simpleName = clz.getSimpleName().toLowerCase();
				String service = simpleName.substring(0, simpleName.indexOf("service"));
				String name = "cn.gnetop.dcs.server.service." + simpleName + ".req."
						+ StringUtils.capitalize(method.getName()) + "Req";
				Action action = new Action();
				action.setService(service);
				action.setAction(method.getName());
				try {
					Class<?> c = Class.forName(name);
					DcsComment d = c.getAnnotation(DcsComment.class);
					if (null != d) {
						String commentC = d.comment();
						action.setComment(commentC);
						List<Column> columnList = new ArrayList<>();
						action.setColumnList(columnList);
						for (Field f : c.getDeclaredFields()) {
							if (ArrayUtils.isEmpty(f.getAnnotations())) {
								continue;
							}
							Column column = new Column();
							DcsComment com = f.getAnnotation(DcsComment.class);
							column.setAction(f.getName());
							column.setComment(com.comment());
							column.setValue(com.value());
							column.setDescription(com.description());
							columnList.add(column);
						}
//						Column superColumn = new Column();
//						Field superField = Request.class.getDeclaredField("token");
//						DcsComment com = superField.getAnnotation(DcsComment.class);
//						superColumn.setAction(superField.getName());
//						superColumn.setComment(com.comment());
//						superColumn.setValue(com.value());
//						superColumn.setDescription(com.description());
//						columnList.add(superColumn);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				actionList.add(action);
			}
		}
		return actionList;
	}

	private List<Result> getResultMap() {
		Map<String, Result> map = ResultCodeManager.getResultMap();
		List<Result> resultList = new ArrayList<>();
		for (Map.Entry<String, Result> entry : map.entrySet()) {
			resultList.add(entry.getValue());
		}
		return resultList;
	}
}
