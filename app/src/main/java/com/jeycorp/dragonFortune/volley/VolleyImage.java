package com.jeycorp.dragonFortune.volley;

import com.android.volley.toolbox.ImageLoader;

public class VolleyImage {
	
	private static final int MAX_IMAGE_CACHE_ENTIRES  = 500;
	private static ImageLoader imageLoader;
	
	private VolleyImage() {}
	
	public static synchronized ImageLoader getImageLoader() {
		if(imageLoader == null) {
			imageLoader = new ImageLoader(VolleyQueue.getRequestQueue(), new com.jeycorp.dragonFortune.volley.BitmapLruCache(MAX_IMAGE_CACHE_ENTIRES));
		}
		return imageLoader;
	}
}
