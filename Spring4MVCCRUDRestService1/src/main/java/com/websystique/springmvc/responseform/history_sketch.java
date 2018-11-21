package com.websystique.springmvc.responseform;

import java.sql.Timestamp;
import java.util.List;

import com.websystique.springmvc.CStable.Historylist_detail;

public class history_sketch {
		private int state;
		private String error;
		private Object message;
		public int getState() {
			return state;
		}
		public void setState(int state) {
			this.state = state;
		}
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
		public Object getMessage() {
			return message;
		}
		public void setMessage(Object message) {
			this.message = message;
		}
		@Override
		public String toString() {
			return "history_sketch [state=" + state + ", error=" + error + ", message=" + message + "]";
		}
	
		 
}
