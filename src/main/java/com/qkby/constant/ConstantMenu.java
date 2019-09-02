package com.qkby.constant;

public class ConstantMenu {
	// 人员是否被锁定 -------0 正常
	public static final Integer LOCK_ZERO = 0;
	// 人员是否被锁定 -------1 锁定
	public static final Integer LOCK_ONE = 1;
	// 判断白班人员
	public static final Integer DAY_ONE = 1;
	// 判断夜班人员
	public static final Integer NIGHT_TWO = 2;
	// 逻辑删除
	public static final Integer DS_ONE = 1;
	// 执行失败
	public static final Integer EXECUTE_FAIL = 0;
	
	//人员角色
	public static final Integer OPS_SUP = 1;// 超级管理员
	public static final Integer OPS_MEN = 2;// 系统管理员
	public static final Integer OPS_AUD= 3;// 信息安全审计员
	public static final Integer OPS_CEK = 4;// 审核人员
	public static final Integer OPS_SIX = 5;// 运维人员
	public static final Integer OPS_OPR = 6;// 普通人员
	public static final Integer OPS_ANA = 7;// 数据分析人员
	public static final Integer OPS_PRO = 8;// 项目管理员

	// 关闭申告状态
	public static final Integer EVENT_CLOSE_APPLY = 21;
	//撤销
	public static final Integer EVENT_REPEAL_APPLY = 29;
	// 待受理
	public static final Integer EVENT_TWENTY = 20;
	// 主表待审核
	public static final Integer EVENT_CHECK_PENDING = 30;
	// 审核被驳回到受理
	public static final Integer EVENT_CHECK_REJECT = 31;
	// 待处理    
	public static final Integer EVENT_DEAL_PENDING = 50;
	
	// 主表待确定 
	public static final Integer EVENT_SURE_PENDING = 60;
	// check表确定
	public static final Integer EVENT_CHECKSURE_PENDING = 67;//待确定
	public static final Integer EVENT_CHECKSURE_FINISH = 65;//通过
	public static final Integer EVENT_DEALSURE_REJECT = 61;////确定驳回 变成
	
	
	// 待评价
	public static final Integer EVENT_ANALYSIS_PENDING=70;
	// 评价完成
	public static final Integer EVENT_ANALYSIS_FINISH=77;
	
	// 处理已接单
	public static final Integer DEAL_ONE = 1;
	public static final Integer DEAL_ZERO = 0;
	// 事件等级
	public static final Integer EVENT_ONE = 1;
	public static final Integer EVENT_TWO = 2;
	public static final Integer EVENT_THREE = 3;
	public static final Integer EVENT_FOUR = 4;

	// 优先级
	public static final Integer EVENT_PRIORITY_HIGH = 1;
	public static final Integer EVENT_PRIORITY_MIDDLE = 2;
	public static final Integer EVENT_PRIORITY_LOW = 3;

	// 处理结果
	public static final Integer EVENT_DEAL_NORMAL = 2;// 解决
	public static final Integer EVENT_DEAL_REINFORCE = 3;// 转派
	public static final Integer EVENT_DEAL_REDEPLOY = 4;//增援
	
	
	public static final Integer SURE_ONE = 1;// 已接单

	public static final Integer EVENT_FIRST = 1;// 申告
	public static final Integer EVENT_SECOND = 2;// 受理
	public static final Integer EVENT_THIRD = 3;// 审核
	public static final Integer EVENT_FOURTH = 4;// 处理
	public static final Integer EVENT_FIFTH = 5;// 确定
	public static final Integer EVENT_SIXTH = 6;// 评价
	
	//等级统计和状态统计初始化值为0
	public static final Integer LEVEL_STATUS_INNIT = 0;
	
	public static final Integer USER_POST_NINE = 9;
	
	//项目状态
		public static final Integer PROJ_DRAFT = 90;//草稿
		public static final Integer PROJ_NORMAL = 91;//正常
		public static final Integer PROJ_FREEZE = 96;//冻结
		public static final Integer PROJ_FINISH = 97;//完成
		
		//任务状态
		public static final Integer TASK_NORMAL = 80;//进行中
		public static final Integer TASK_FINISH = 85;//完成
		
		//项目管理：三个地方的任务列表状态
		public static final String TASK_STATE = "taskState";//我的任务
		public static final String PROJ_TASK_STATE = "projTaskState";//项目列表中的任务列表操作
		public static final String PROJ_STATE = "projState";//编辑页面中的任务列表
		public static final String MINE_TASK = "mineTask";//我的任务中的任务处理
		public static final String PROJ_MINE_TASK = "projMineTask";//我的项目列表中的任务处理
		public static final String PROJ_TASK = "projTask";//项目编辑中的任务处理
		public static final String PROJ_CONTROL = "projControl";//跳转编辑项目的点击来源，是项目监控页，还是我的项目页
		public static final String PROJ_CONTROL_TASK = "projControlTask";//跳转任务处理的点击来源，是项目监控页，还是项目编辑中的任务列表页
		
		//个人办公
		public static final Integer PLAN_ONE_STATE = 1;// 计划状态    进行中
		public static final Integer PLAN_TWO_STATE = 2;// 计划状态    未开始
		public static final Integer PLAN_THREE_STATE = 3;// 计划状态    完成
		public static final Integer DS_ON = 1;	//逻辑删除     已删除
		public static final Integer DS_ZERO = 0;  //逻辑删除    未删除
		public static final Integer DS_TWO = 2; 
		
		//工作汇报
		public static final Integer DRAFT_ONE = 1;
		public static final Integer DRAFT_TWO = 2;
		
		//工作计划推送
		public static final Integer EXAMINE_ONE = 1;//中间表查看标记已查看
		public static final Integer EXAMINE_TWO = 2;//未查看
		
		//合同管理逻辑删除
		public static final Integer CONTRACT_ONE = 1;//未删除
		public static final Integer CONTRACT_TWO = 2;//已删除
		
		//合同履约情况
		public static final Integer HONOUR_AN_AGREEMENT = 1;//履约中
		public static final Integer IN_INNEGOTIATION  =2;//协商中
		public static final Integer ACCOMPLISH = 3;//完成
	
}
