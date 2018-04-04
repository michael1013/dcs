package cn.gnetop.dcs.console.system.linster;

import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;

public class DcsContextLoaderListener extends ContextLoaderListener {
	private ContextLoader contextLoader = null;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		this.contextLoader = new ContextLoader();
		ApplicationContext applicationContext = this.contextLoader.initWebApplicationContext(event.getServletContext());
		ContextHolder.setCtx(applicationContext);
		System.out.println("============================================================");
		System.out.println("|                                                          |");
		System.out.println("|                                                          |");
		System.out.println("|                  dcs console load success                |");
		System.out.println("|                                                          |");
		System.out.println("|                                                          |");
		System.out.println("============================================================");
	}
}
