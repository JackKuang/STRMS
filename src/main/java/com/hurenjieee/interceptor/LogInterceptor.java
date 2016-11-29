package com.hurenjieee.interceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

public class LogInterceptor implements Interceptor {

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
				DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sb.append(dateFormat.format(new Date()) + "|");
				Map<String, Object> sessionMap = invocation.getInvocationContext().getSession();
				String name = (String) sessionMap.get("name");
				if (name != null) {
					sb.append(name + "|");
				} else {
					sb.append("anonymous" + "|");
				}
				sb.append(invocation.getAction().getClass().getSimpleName() + "|" + invocation.getInvocationContext().getName() + "|");
				Map<String, Object> map = invocation.getInvocationContext().getParameters();
				Set<String> keys = map.keySet();
				for (String key : keys) {
					sb.append(key + "=" + ((Object[]) map.get(key))[0]+ "&");
					}
				sb.append("|"+invocation.getResultCode()+"|");
				System.out.println(sb);
					
			}
		});
		return invocation.invoke();
	}

}
