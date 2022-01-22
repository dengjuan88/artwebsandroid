package cn.artwebs.comm;


public enum DialogStyle {
	none("none", "", "", ""), forever("forever", "请稍等...", "数据加载中...", ""), waite(
			"waite", "请稍等...", "数据加载中...", "");

	String code;
	String title;
	String content;
	String ico;

	public static DialogStyle fromCode(String code)
	{
		for (DialogStyle config : DialogStyle.values())
		{
			if (config.code.equals(code))
			{
				return config;
			}
		}
		throw new IllegalArgumentException(code);
	}

	private DialogStyle(String code, String title, String content,String ico)
	{
		this.code = code;
		this.title = title;
		this.content = content;
		this.ico = ico;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getTitle()
	{
		return title;
	}

	public String getContent()
	{
		return content;
	}

	public String getIco()
	{
		return ico;
	}
}
