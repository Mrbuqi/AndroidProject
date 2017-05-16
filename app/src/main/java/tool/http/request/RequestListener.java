package tool.http.request;


import tool.http.global.HttpRequestID;

/**
 * 业务http请求监听
 * 
 * @author taojianli
 * 
 */
public interface RequestListener {

	/**
	 * 请求回调。
	 * 接口有不足应该保留用户输入参数。
	 * 
	 * @param statusCode
	 *            状态码
	 * @param requestId
	 *            区分请求号
	 * @param responseString
	 */
    @Deprecated
	public void requestStatusChanged(int statusCode, HttpRequestID requestId,
									 String responseString);
}
