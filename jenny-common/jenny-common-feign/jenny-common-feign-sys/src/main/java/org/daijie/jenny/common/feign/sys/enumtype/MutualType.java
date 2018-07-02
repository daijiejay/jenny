package org.daijie.jenny.common.feign.sys.enumtype;

import org.daijie.core.factory.TypeFactory;

public enum MutualType implements TypeFactory {

	CONFIRM("确认"),
	
	FORM("表单");
	
	private String describle;
	
	MutualType(String describle) {
		this.describle = describle;
	}

	@Override
	public String describle() {
		return this.describle;
	}
}
