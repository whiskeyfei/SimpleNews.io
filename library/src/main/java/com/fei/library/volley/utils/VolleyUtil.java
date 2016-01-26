//package com.fei.library.volley.utils;
//
//import android.content.Context;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.ImageLoader;
//import com.android.volley.toolbox.Volley;
//
//public class VolleyUtil {
//	private volatile static RequestQueue mRequestQueue;
//	private volatile static ImageLoader mImageLoader;
//
//	public static void init(Context context) {
//		mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
//		mRequestQueue.start();
//	}
//
//	public static void initImageLoder(){
//		mImageLoader = new ImageLoader(mRequestQueue,new LruImageCache());
//	}
//
//	public static ImageLoader getImageLoader(){
//		if (mImageLoader != null){
//			return mImageLoader;
//		}else{
//			throw new IllegalStateException("no init VolleyUtils");
//		}
//	}
//
//	public static RequestQueue getRequestQueue(){
//		if (mRequestQueue != null) {
//			return mRequestQueue;
//		}else{
//			throw new IllegalStateException("no init VolleyUtils");
//		}
//	}
//
//	public static <T> void addToRequestQueue(Request<T> request, Object tag){
//		if (tag != null) {
//			request.setTag(tag);
//		}
//		getRequestQueue().add(request);
//	}
//
//	public static void cancelAllQueue(Object tag) {
//		getRequestQueue().cancelAll(tag);
//	}
//
//}
