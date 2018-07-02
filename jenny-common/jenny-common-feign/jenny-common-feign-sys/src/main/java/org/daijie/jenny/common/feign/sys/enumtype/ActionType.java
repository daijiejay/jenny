package org.daijie.jenny.common.feign.sys.enumtype;

import org.daijie.core.factory.TypeFactory;

public enum ActionType implements TypeFactory {

	OPERATE("列表单行操作"),
	
	TOOLBAR("列表工具栏");
	
	private String describle;
	
	ActionType(String describle) {
		this.describle = describle;
	}

	@Override
	public String describle() {
		return this.describle;
	}
}
