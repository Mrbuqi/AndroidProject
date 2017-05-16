package tool.http;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import tool.http.upload.CountMultipartEntity.ProgressListener;

/**
 * http请求统一接口。
 * 当前只对字符串类型的返回内容进行接口封装。
 */
public interface HttpInterface {
    public ReturnData doGetRequest(String url, Map<String, String> params);
    
    public ReturnData doPostRequest(String url, Map<String, String> params);
    
    public ReturnData doPostUploadFileRequest(String url, Map<String, String> params, ProgressListener prosLisr, File[] files, String[] fileNames);
    
    public ReturnData doPostUploadStreamRequest(String url, Map<String, String> params, ProgressListener prosLisr, InputStream[] iss, String[] fileNames);

    public ReturnData doDownloadRequest(String url, Map<String, String> params, int dataType);
    public ReturnData doGetDownloadRequest(String url, Map<String, String> params, int dataType);
    
    public void cancel();
}
