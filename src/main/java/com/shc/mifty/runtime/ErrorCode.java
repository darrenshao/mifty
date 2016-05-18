/*
 * Copyright 2016 The Mifty Project
 *
 * The Mifty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.shc.mifty.runtime;

public enum ErrorCode {
	/**
	 * All runtime error codes defined here,
	 * 0x00 00 0000 - 0xFF FF FFFF
	 * first 2 digits: used for server type
	 * following 2 digits: used for service/module/subsystem 
	 * last 4 digits: used for detailed errors
	 */
	//define your business runtime error codes here, 
	//or you could use the standard error codes defined in com.shc.crossing.runtime.ErrorCode
	//and do remember apply for module error code prefix first.

	//Error Codes Definition
	MIFTY_EXASERVICE_USER_NOT_EXIST(0x00000000,"User not exist."),
	MIFTY_EXASERVICE_USER_UNAUTHORIZED(0x00000000,"User not authorized.");
	//TO DO






	private int code;
	private String info;

	private ErrorCode(int code, String info){
		this.code = code;
		this.info = info;
	}

	public int getCode() {
		return code;
	}

	public String getInfo() {
		return info;
	}
}

