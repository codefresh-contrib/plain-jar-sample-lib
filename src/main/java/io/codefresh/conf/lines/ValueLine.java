/*
 * Copyright 2011 Kapelonis Kostis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.codefresh.conf.lines;

import io.codefresh.conf.tree.ConfNode;
import io.codefresh.conf.tree.ValueNode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author kkapelonis
 */
public class ValueLine implements LineReader{
	
	@Override
	public boolean matches (String line) {
		return line.trim().matches("[^\\s]+[\\s]+[^\\s]+");
	}

	@Override
	public ConfNode process (ConfNode where, String line) {
		line = line.trim();
		
		Pattern pattern = Pattern.compile("([^\\s]+)[\\s]+([^\\s]+)");
		Matcher matcher = pattern.matcher(line);
		matcher.matches();
		String key = matcher.group(1);
		String value = matcher.group(2);
		
		ValueNode node = new ValueNode(where);
		node.setName(key);
		node.setValue(value);
		where.getChildren().put(key,node); //Overwrite previous node if present
		return where;
	
	}
	
	
	

}
