package com.now.cloudphoto.onedrive.services;

import java.util.Stack;

public class OneDriveBackStack {
	private final int MAX_BACKSTACK_SIZE = 10;
	private static Stack<String> backStack;
	
	public OneDriveBackStack(){			
		if(backStack == null){
			backStack = new Stack<String>();
		}
	}
	
	public void push(String url){		
		if(backStack.size()  >= MAX_BACKSTACK_SIZE){
			backStack.removeElementAt(backStack.size()-1);
		}
		backStack.push(url);
	}
	
	public String getCurrentURL(){
		return backStack.peek();
	}
	
	public String getPreviousURL(){
		if(backStack.size()>=2){
			backStack.pop();
			return backStack.peek();
		}
		return "";
	}
	
	public boolean isEmpty(){
		if(backStack == null){
			return true;
		}
		
		if(backStack.size() <= 1){
			backStack.clear();
			return true;
		}
		return false;
	}
}
