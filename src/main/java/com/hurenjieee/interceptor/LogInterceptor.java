package com.hurenjieee.interceptor;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

public class LogInterceptor implements Interceptor {
	private static Logger log = Logger.getLogger(LogInterceptor.class);
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		invocation.addPreResultListener(new PreResultListener() {

			@Override
			public void beforeResult(ActionInvocation invocation, String resultCode) {
				// TODO Auto-generated method stub
				StringBuffer sb = new StringBuffer();
				Map<String, Object> sessionMap = invocation.getInvocationContext().getSession();
				String name = (String) sessionMap.get("name");
				if (name != null) {
					sb.append("["+name+"]");
				} else {
					sb.append("[anonymous]");
				}
				sb.append("visited["+invocation.getAction().getClass().getSimpleName() + "." + invocation.getInvocationContext().getName() + "]with{");
				Map<String, Object> map = invocation.getInvocationContext().getParameters();
				Set<String> keys = map.keySet();
				for (String key : keys) {
					sb.append(key + "==>[" + ((Object[]) map.get(key))[0]+"]");
					}
				sb.append("}result==>["+invocation.getResultCode()+"]");
				log.info(sb);
			}
		});
		return invocation.invoke();
	}

}
