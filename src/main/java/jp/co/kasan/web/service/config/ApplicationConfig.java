/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.service.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * JAX-RSの設定。自動生成されるクラスです。
 * @author rued97
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}

	/**
	 * Do not modify addRestResourceClasses() method.
	 * It is automatically populated with
	 * all resources defined in the project.
	 * If required, comment out calling this method in getClasses().
	 */
	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(jp.co.kasan.web.service.api.AuthenticationWebAPI.class);
		resources.add(jp.co.kasan.web.service.api.MemberAccountsWebAPI.class);
		resources.add(jp.co.kasan.web.service.api.MembersWebAPI.class);
	}
	
}
