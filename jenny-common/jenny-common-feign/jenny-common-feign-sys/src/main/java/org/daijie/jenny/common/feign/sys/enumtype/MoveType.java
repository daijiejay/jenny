package org.daijie.jenny.common.feign.sys.enumtype;

import org.daijie.core.factory.TypeFactory;

public enum MoveType implements TypeFactory  {
	
	PREV("前面"),
	
	NEXT("后面"),
	
	INNER("里面");
	
	private String describle;
	
	MoveType(String describle) {
		this.describle = describle;
	}

	@Override
	public String describle() {
		return this.describle;
	}

	
}
