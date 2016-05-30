package com.ttgis.education.entity;

import java.util.Date;
/**
 * 新闻
 * <p>Title:News </p>
 *@author WYP
 *2015-8-3
 */
public class News {

	private String newsId;//新闻编号

    private String newsName;//新闻名称

    private String newsType;//新闻状�?   （可�?    不可用）

    private String newsCatalog;//新闻上级目录

    private String newsProducer;//新闻制作�?    
    private Integer newsRank;//新闻管理级别

    private String newsImage;//新闻图片

    private String newsSort;//新闻类别

    private String newsTime;//新闻发布时间

    private Date newsAt;//新闻记录添加时间

    private Integer newsDel;//新闻删除标记

    private String newsContent;//新闻内容

    private String organizationId;//上级单位id
    
	private Integer newsTop;

	private String newsToptime;
    //--------------------------------------------
    private String zhuanNewsTime;//新闻发布时间(String)
    
    private int page;
	private int size;
	
	private int cj ; //层级
	
	private int type ; //登录权限类型
	
	private Organization organization;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCj() {
		return cj;
	}

	public void setCj(int cj) {
		this.cj = cj;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Integer getNewsTop() {
		return newsTop;
	}

	public void setNewsTop(Integer newsTop) {
		this.newsTop = newsTop;
	}

	public String getNewsToptime() {
		return newsToptime;
	}

	public void setNewsToptime(String newsToptime) {
		this.newsToptime = newsToptime;
	}

	public String getZhuanNewsTime() {
		return zhuanNewsTime;
	}

	public void setZhuanNewsTime(String zhuanNewsTime) {
		this.zhuanNewsTime = zhuanNewsTime;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}



	public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getNewsCatalog() {
        return newsCatalog;
    }

    public void setNewsCatalog(String newsCatalog) {
        this.newsCatalog = newsCatalog;
    }

    public String getNewsProducer() {
        return newsProducer;
    }

    public void setNewsProducer(String newsProducer) {
        this.newsProducer = newsProducer;
    }

    public Integer getNewsRank() {
        return newsRank;
    }

    public void setNewsRank(Integer newsRank) {
        this.newsRank = newsRank;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsSort() {
        return newsSort;
    }

    public void setNewsSort(String newsSort) {
        this.newsSort = newsSort;
    }

   

    public String getNewsTime() {
		return newsTime;
	}

	public void setNewsTime(String newsTime) {
		this.newsTime = newsTime;
	}

	public Date getNewsAt() {
        return newsAt;
    }

    public void setNewsAt(Date newsAt) {
        this.newsAt = newsAt;
    }

    public Integer getNewsDel() {
        return newsDel;
    }

    public void setNewsDel(Integer newsDel) {
        this.newsDel = newsDel;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
}