package com.ttgis.education.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Student
{

	private String studentId;// 学员表ID

	private String studentSeq;// 学员编号

	private String studentName;// 学员姓名

	private String studentCategory;// 学员人员类别

	private String studentRank;// 学员级别

	private Integer studentType;// 学员状态   1代表在职   2代表游离

	private String studentCompany;// 学员所在单位(现在放的是学员等级)..............

	private String studentCompanyid;// 学员所属单位编号（有用 组织架构id）.............

	private String studentUpcompanyid;// 学员上级单位编号（暂时没用）...........(list存部门)

	private String studentDepartment;// 学员所在部门...........

	private Integer studentCoin;// 学习币   0

	private BigDecimal studentYeartime;// 学员本年度学时.........

	private String studentTelephone;// 学员手机号码

	private String studentEmail;// 学员电子邮箱

	private String studentPassword;// 学员密码

	private Date studentAt;// 添加时间

	private Integer studentDel;// 删除标记

	private String departmentId;// 部门ID....................

	private String studentoneId;// 人员信息1ID

	private String studenttwoId;// 人员信息2ID

	private String studenthreeId;// 人员信息3ID

	private BigDecimal studentBxyxtime;// 必修课应修学时......

	private BigDecimal studentBxxxtime;// 必修课学习进度...........

	private BigDecimal studentYxxxtime;// 已选选修课学时............

	private BigDecimal studentXxxxtime;// 选修课学习进度...........

	private String studentScore;
	private String studentNoscore;
	
	private Integer page;
	private Integer size;
	private Integer cj;

	private Studentone studentone;
	private Studenttwo studenttwo;
	private Studenthree studenthree;
	
	// ---------------
	private String organizationId;
	private String studentDepartments;
	private String studentCompanyids;
	//--------筛选菜单--------
	private String tOne;
	private String tTwo;
	private String tThree;
	private String tFour;
	//-----------------
	private String[] checkboxids;
	//-----------------
	private String ys;
	//-----------------
	private String jop;//在职和游离的标记
	//-----------------
	private String courseId;//课程推送
	
	private Organization organization;
	
	private Department department;
	



	public String getStudentCompanyids() {
		return studentCompanyids;
	}

	public void setStudentCompanyids(String studentCompanyids) {
		this.studentCompanyids = studentCompanyids;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getJop() {
		return jop;
	}

	public void setJop(String jop) {
		this.jop = jop;
	}

	public String getYs() {
		return ys;
	}

	public void setYs(String ys) {
		this.ys = ys;
	}

	public String[] getCheckboxids() {
		return checkboxids;
	}

	public void setCheckboxids(String[] checkboxids) {
		this.checkboxids = checkboxids;
	}

	public String gettOne() {
		return tOne;
	}

	public void settOne(String tOne) {
		this.tOne = tOne;
	}

	public String gettTwo() {
		return tTwo;
	}

	public void settTwo(String tTwo) {
		this.tTwo = tTwo;
	}

	public String gettThree() {
		return tThree;
	}

	public void settThree(String tThree) {
		this.tThree = tThree;
	}

	public String gettFour() {
		return tFour;
	}

	public void settFour(String tFour) {
		this.tFour = tFour;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public void setCj(Integer cj) {
		this.cj = cj;
	}

	public String getStudentDepartments()
	{
		return studentDepartments;
	}

	public void setStudentDepartments(String studentDepartments)
	{
		this.studentDepartments = studentDepartments;
	}

	public String getOrganizationId()
	{
		return organizationId;
	}

	public void setOrganizationId(String organizationId)
	{
		this.organizationId = organizationId;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public int getCj()
	{
		return cj;
	}

	public void setCj(int cj)
	{
		this.cj = cj;
	}

	public Studentone getStudentone()
	{
		return studentone;
	}

	public void setStudentone(Studentone studentone)
	{
		this.studentone = studentone;
	}

	public Studenttwo getStudenttwo()
	{
		return studenttwo;
	}

	public void setStudenttwo(Studenttwo studenttwo)
	{
		this.studenttwo = studenttwo;
	}

	public Studenthree getStudenthree()
	{
		return studenthree;
	}

	public void setStudenthree(Studenthree studenthree)
	{
		this.studenthree = studenthree;
	}

	public String getStudentId()
	{
		return studentId;
	}

	public void setStudentId(String studentId)
	{
		this.studentId = studentId;
	}

	public String getStudentSeq()
	{
		return studentSeq;
	}

	public void setStudentSeq(String studentSeq)
	{
		this.studentSeq = studentSeq;
	}

	public String getStudentName()
	{
		return studentName;
	}

	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}

	public String getStudentCategory()
	{
		return studentCategory;
	}

	public void setStudentCategory(String studentCategory)
	{
		this.studentCategory = studentCategory;
	}

	public String getStudentRank()
	{
		return studentRank;
	}

	public void setStudentRank(String studentRank)
	{
		this.studentRank = studentRank;
	}

	public Integer getStudentType()
	{
		return studentType;
	}

	public void setStudentType(Integer studentType)
	{
		this.studentType = studentType;
	}

	public String getStudentCompany()
	{
		return studentCompany;
	}

	public void setStudentCompany(String studentCompany)
	{
		this.studentCompany = studentCompany;
	}

	public String getStudentCompanyid()
	{
		return studentCompanyid;
	}

	public void setStudentCompanyid(String studentCompanyid)
	{
		this.studentCompanyid = studentCompanyid;
	}

	public String getStudentUpcompanyid()
	{
		return studentUpcompanyid;
	}

	public void setStudentUpcompanyid(String studentUpcompanyid)
	{
		this.studentUpcompanyid = studentUpcompanyid;
	}

	public String getStudentDepartment()
	{
		return studentDepartment;
	}

	public void setStudentDepartment(String studentDepartment)
	{
		this.studentDepartment = studentDepartment;
	}

	public Integer getStudentCoin()
	{
		return studentCoin;
	}

	public void setStudentCoin(Integer studentCoin)
	{
		this.studentCoin = studentCoin;
	}

	public BigDecimal getStudentYeartime()
	{
		return studentYeartime;
	}

	public void setStudentYeartime(BigDecimal studentYeartime)
	{
		this.studentYeartime = studentYeartime;
	}

	public String getStudentTelephone()
	{
		return studentTelephone;
	}

	public void setStudentTelephone(String studentTelephone)
	{
		this.studentTelephone = studentTelephone;
	}

	public String getStudentEmail()
	{
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail)
	{
		this.studentEmail = studentEmail;
	}

	public String getStudentPassword()
	{
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword)
	{
		this.studentPassword = studentPassword;
	}

	public Date getStudentAt()
	{
		return studentAt;
	}

	public void setStudentAt(Date studentAt)
	{
		this.studentAt = studentAt;
	}

	public Integer getStudentDel()
	{
		return studentDel;
	}

	public void setStudentDel(Integer studentDel)
	{
		this.studentDel = studentDel;
	}

	public String getDepartmentId()
	{
		return departmentId;
	}

	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;
	}

	public String getStudentoneId()
	{
		return studentoneId;
	}

	public void setStudentoneId(String studentoneId)
	{
		this.studentoneId = studentoneId;
	}

	public String getStudenttwoId()
	{
		return studenttwoId;
	}

	public void setStudenttwoId(String studenttwoId)
	{
		this.studenttwoId = studenttwoId;
	}

	public String getStudenthreeId()
	{
		return studenthreeId;
	}

	public void setStudenthreeId(String studenthreeId)
	{
		this.studenthreeId = studenthreeId;
	}

	public BigDecimal getStudentBxyxtime()
	{
		return studentBxyxtime;
	}

	public void setStudentBxyxtime(BigDecimal studentBxyxtime)
	{
		this.studentBxyxtime = studentBxyxtime;
	}

	public BigDecimal getStudentBxxxtime()
	{
		return studentBxxxtime;
	}

	public void setStudentBxxxtime(BigDecimal studentBxxxtime)
	{
		this.studentBxxxtime = studentBxxxtime;
	}

	public BigDecimal getStudentYxxxtime()
	{
		return studentYxxxtime;
	}

	public void setStudentYxxxtime(BigDecimal studentYxxxtime)
	{
		this.studentYxxxtime = studentYxxxtime;
	}

	public BigDecimal getStudentXxxxtime()
	{
		return studentXxxxtime;
	}

	public void setStudentXxxxtime(BigDecimal studentXxxxtime)
	{
		this.studentXxxxtime = studentXxxxtime;
	}

	public String getStudentScore()
	{
		return studentScore;
	}

	public void setStudentScore(String studentScore)
	{
		this.studentScore = studentScore;
	}

	public String getStudentNoscore()
	{
		return studentNoscore;
	}

	public void setStudentNoscore(String studentNoscore)
	{
		this.studentNoscore = studentNoscore;
	}

}