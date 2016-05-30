package com.ttgis.education.utils;

import org.springframework.context.ApplicationContext;

public class Const
{
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_USER_RIGHTS = "sessionUserRights";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String NO_INTERCEPTOR_PATH = "/((login)|(board)|(uploadHomework)|(homeWorkJump)|(myCourseNoteById)|(myCourseNoteList)|(coursenoteSava)|(myTrainHistory)|(twoclass)|(myTraincourse)|(coursestudyWebAll)|(oneTranningCourse)|(insertStudentInfo)|(allTranningCourse)|(bbs)|(images)|(image)|(shangchuan)|(css)|(js)|(ResourceWebTree)|(ResourceWeblist)|(selectResourcesOne)|(register)|(loginForm)|(Notice/NoticeAll)|(mainout)|(noticeInfo)|(UploadFile)|(noticeInfoAll)|(training)|(StudentcheckLogin)|(main)|(partySchoolTraining)|(SchoolnoticeInfo)|(SchoolnoticeInfoAll)|(Schooltraining)|(courseInfo)|(courseWebAll)|(upplansort)|(testView)|(mycourse)|(courseVideo)|(videotimeWebAxjx)|(mystudent)|(partySchool)|(reply)|(webEstimate)|(StudentCheckLogin)|(allTest)|(testSubmit)|(traincourseTest)|(traincourseCoursTest)|(allTestQuery)|(praise)|(doreply)|(replytome)|(writebbs)|(mybbs)|(savebbs)|(courseWebSc)|(deleteCollect)|(collectOnes)|(loginInterface)|(StudentLoginInterface)|(courseScorm)|(newGitLogin)|(mainInstation))/*.*"; // 不对匹配该值的访问路径拦截（正则）

	public static ApplicationContext WEB_APP_CONTEXT = null; // 该值会在web容器启动时由WebAppContextListener初始化checkEnterprise
}
