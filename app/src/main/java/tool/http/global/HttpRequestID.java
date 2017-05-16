package tool.http.global;


import heibai.tv.global.Global;

/**
 * http接口枚举使用接口来组织。 在此处进行版本标识。
 * 
 * @author
 * @version [版本号, 2013-10-19]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface HttpRequestID {

	/** 中国气象台开放平台 */
	// public static String WEATHER_CHINA_URL =
	// "http://m.weather.com.cn/data/cityinfo/";
	public static String WEATHER_CHINA_URL = "http://weather.api.114la.com/";
	public static String SERVER_CTB_URL = "http://lk.chetuobang.com/snstraffic/";

	public int getId();

	public String getUrl();

	public static enum V1 implements HttpRequestID {
		TOUPDATE;
		@Override
		public int getId() {
			// TODO Auto-generated method stub
			return this.ordinal();
		}

		@Override
		public String getUrl() {
			String url = Global.SERVER_URL;
			switch (this) {
			case TOUPDATE:
				url += "update.xml";
				break;
			default:
				break;
			}
			return url;
		}
	}

	public static enum VIHICLE implements HttpRequestID {// user/getVerifyCode
		MYCARBRAND1;

		@Override
		public int getId() {
			// TODO Auto-generated method stub
			return this.ordinal();
		}

		@Override
		public String getUrl() {
			String url = Global.SERVER_URL_NEW;
			switch (this) {
			case MYCARBRAND1:
				url += "car/api/CarBrandApiImpl!getBrandLevel1";
				break;

				
			default:
				break;
			}
			return url;
		}
	}

	public static enum CTB implements HttpRequestID {
		/** 车托邦实时路况 */
		REALTIME_ROUTE_TRAFFIC,
		/** 热门关键字，目前只提供长沙的数据 */
		HOTKEYS,
		/** 查询电子狗 */
		EDOG;

		@Override
		public int getId() {
			return this.ordinal();
		}

		@Override
		public String getUrl() {
			String url = SERVER_CTB_URL;
			switch (this) {
			case REALTIME_ROUTE_TRAFFIC:
				url += "getroadcondition";// ?format=json&keywords=URLEncode.encode("长沙",“utf-8”)
				break;
			case EDOG:
				url += "getedog";// ?format=json&lon=113.02017151355312&lat=28.193030581147337&range=1000&app_id=1
				break;
			case HOTKEYS:
				url += "gethotkeys";// ?format=json
				break;
			

			default:
				break;
			}
			return url;
		}
	}
}