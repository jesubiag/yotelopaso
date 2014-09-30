package com.example.utils;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.GoogleApi;

public class Google2ApiInfo {
	
	public ApiInfo apiInfo;
	public final String apiName = "GoogleApi";
	public final Class<? extends Api> scribeApi = GoogleApi.class;
	public final String apiKey = "398023219009.apps.googleusercontent.com";
	public final String apiSecret = "WjD6Dq0S5_19dw1KlBreZakL";
	public final String exampleGetRequest = "https://www.googleapis.com/drive/v2/about?key=";
	
	public Google2ApiInfo() {
		super();
		apiInfo = new ApiInfo(apiName, scribeApi, apiKey, apiSecret, exampleGetRequest);
	}

}
