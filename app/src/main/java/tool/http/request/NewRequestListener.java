package tool.http.request;

import java.util.Map;

import tool.http.global.HttpRequestID;


/**
 * 业务http请求监听
 * 
 * @author taojianli
 * 
 */
public interface NewRequestListener {

	/**
	 * 请求回调。
	 * 
	 * @param statusCode
	 *            状态码
	 * @param requestId
	 *            区分请求号
	 * @param responseString
	 */
	public void requestStatusChanged(int statusCode, HttpRequestID requestId,
									 String responseString, Map<String, String> requestParams);

}
