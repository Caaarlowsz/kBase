package cc.fatenetwork.kbase.utils.chat;

import net.minecraft.server.v1_8_R3.ChatClickable;

public enum ClickAction{
	OPEN_URL(ChatClickable.EnumClickAction.OPEN_URL),
	OPEN_FILE(ChatClickable.EnumClickAction.OPEN_FILE),
	RUN_COMMAND(ChatClickable.EnumClickAction.RUN_COMMAND),
	SUGGEST_COMMAND(ChatClickable.EnumClickAction.SUGGEST_COMMAND);

	private final ChatClickable.EnumClickAction clickAction;

	ClickAction(ChatClickable.EnumClickAction action){
		this.clickAction = action;
	}

	public ChatClickable.EnumClickAction getNMS(){
		return this.clickAction;
	}
}