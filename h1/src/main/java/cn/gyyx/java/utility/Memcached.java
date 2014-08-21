package cn.gyyx.java.utility;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;


public class Memcached {
	
	private static Memcached memInstance=null;
	
	private static MemcachedClient client=null;
	
	
	public static Memcached getInstance(){
		if(client == null){

			try {
				client = new XMemcachedClient("192.168.6.195",10001);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(memInstance == null){
			memInstance = new Memcached();
		}
		return memInstance;
	}
	
	public Object get(String key){
		try {
			return client.get(key);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void set(String key,int time,Object obj){
//		try{
//			client.delete(key);
//		}catch (TimeoutException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MemcachedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace(); 
//		}
	
			
			try {
				client.set(key, time, obj);
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MemcachedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	
	
}
