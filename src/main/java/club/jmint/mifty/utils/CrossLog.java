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
package club.jmint.mifty.utils;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;



/**
 * @author shc
 *
 */
public class CrossLog {
	static {
		PropertyConfigurator.configure("conf/log4j.properties"); 
	}
	
	public final static Logger logger = LoggerFactory.getLogger(CrossLog.class);
	
	public final static StringWriter sw = new StringWriter();
	public final static  PrintWriter pw = new PrintWriter(sw);

	public static void printStackTrace(Exception e){
        e.printStackTrace(pw);
        logger.error(sw.toString());
	}
}
