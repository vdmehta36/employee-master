/**
 * 
 */
package com.employee.common.exception.message;

import java.util.Map;

import com.employee.common.exception.dto.IMessageDTO;
import com.google.common.collect.Maps;

/**
 *
 */
public class ErrorMessage {
	 private Map<String,IMessageDTO> errors = Maps.newHashMap();
	    
	    public ErrorMessage() {
		}
	    public ErrorMessage( Map<String, IMessageDTO> errors) {
			this.errors = errors;
		}
	    
	    public ErrorMessage(IMessageDTO error, String errorProperty) {
			addError(error, errorProperty);
		}
		private void addError(IMessageDTO error, String errorProperty) {
			this.errors.put(errorProperty, error);
		}
	    
		@Override
		public String toString() {
			return "errors=" + errors;
		}
		public Map<String, IMessageDTO> getErrors() {
			return errors;
		}
		public void setErrors(Map<String, IMessageDTO> errors) {
			this.errors = errors;
		}
}
