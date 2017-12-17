# InterestingHibernate

有趣的Hibernate，有趣？呵呵……我也很无奈……

这个工程是总结Hibernate的一些知识点，温故而知新。其实在使用一些框架时，最烦人的就是一些配置，本工程中，配置文件写的很细，也有一些注释说明，常用的一些配置都有，在使用Hibernate框架时，完全可以把本工程的配置文件复制粘贴过去，修改修改就能用了。

## 开发环境说明
	eclipse：Luna Service Release 1 (4.4.1) (简称：eclipse luna-SR1_4.4.1)
	JDK：1.8.0_92
	tomcat：apache-tomcat-7.0.69

	Hibernate5

## 总体大纲（只列出了部分重要的技术点）
	chapter01
		核心配置文件配置
		映射配置
		测试代码编写
		Transaction
		Query
		Criteria
		
	chapter02
		持久化对象三种状态
		一级缓存
		持久化对象具有修改数据库能力
		一级缓存常用操作
		一对多
			映射文件配置
			保存操作测试
			单向关联保存操作
			双向维护分析与问题解决
			对象导航
			级联删除
			
	chapter03
		PO类注解
		注解一对多
		注解多对多
		注解一对一
		HQL
		QBC
		本地SQL
		
	chapter04
		HQL多表操作
		事务隔离级别设置
		管理session-与线程绑定session
		一级缓存优化
		检索策略-延迟加载与类级检索
		检索策略-延迟对象初始化
		抓取策略的注解配置和测试

## 打个广告：其他技术的整理
JavaSE：[InterestingJavaSE](https://github.com/gongchuanjing/InterestingJavaSE.git)

JavaEE：[InterestingJavaEE](https://github.com/gongchuanjing/InterestingJavaEE.git)

Struts2：[InterestingStruts2](https://github.com/gongchuanjing/InterestingStruts2.git)

Spring：[InterestingSpring](https://github.com/gongchuanjing/InterestingSpring.git)

SSH采用XML方式整合：[InterestingSSH_XML](https://github.com/gongchuanjing/InterestingSSH_XML.git)

SSH采用注解方式整合：[InterestingSSH_annotation](https://github.com/gongchuanjing/InterestingSSH_annotation.git)
