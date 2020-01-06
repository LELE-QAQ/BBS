package com.bbs.pojo;

public class Section {
    private Integer id;

    private String name;

    private String img;

    private String type;

    private String descs;

    private Integer parentid;

    private Integer orderby;

    private String createtime;

    private Integer createuserid;

    private String postgroup;

    private String replygroup;

    private String findgroup;

    private String shorthand;

    //帖子数量
    private Integer postCount;
    //回复数量
    private Integer replyCount;

    //父名称---关联父id使用
    private String fatherName;

    //帖子创建时间---查询该板块最后发帖时间
    private String postCreateTime;
    //帖子id---查询该板块最后发帖id
    private String postId;
    //帖子名称---查询该板块最后发帖名称
    private String postName;
    //用户id---查询该板块最后发帖用户id
    private String userId;
    //用户名称---查询该板块最后发帖用户名称
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs == null ? null : descs.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public Integer getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }

    public String getPostgroup() {
        return postgroup;
    }

    public void setPostgroup(String postgroup) {
        this.postgroup = postgroup == null ? null : postgroup.trim();
    }

    public String getReplygroup() {
        return replygroup;
    }

    public void setReplygroup(String replygroup) {
        this.replygroup = replygroup == null ? null : replygroup.trim();
    }

    public String getFindgroup() {
        return findgroup;
    }

    public void setFindgroup(String findgroup) {
        this.findgroup = findgroup == null ? null : findgroup.trim();
    }

    public String getShorthand() {
        return shorthand;
    }

    public void setShorthand(String shorthand) {
        this.shorthand = shorthand == null ? null : shorthand.trim();
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getPostCreateTime() {
        return postCreateTime;
    }

    public void setPostCreateTime(String postCreateTime) {
        this.postCreateTime = postCreateTime;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}