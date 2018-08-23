# spring-security

validate.code
验证模块
访问  ValidateController   /code/{type} 
    调用ValidateCodeProcessorHolder   findValidateCodeProcessor
    里面有根据类型调用了两个验证器    ValidateCodeProcessor (关于验证码创建，验证)

社交模块
SociaConfig   
注册数据库啊
	配置了loginpage  会访问	 BrowserSecurityController 获取访问的url  判断是否是以.html结尾
	是的跳转到login界面 
	登陆记住我 功能 配置  仓库 persistentTokenRepository(JdbcTokenRepositoryImpl) 时间， 操作service
	在usernamepasswordauthenticationfilter 登陆成功左后会从providerManager 中取出配入的RememberMeAuthenticationProvider
	遍历，看那个支持provider.supports(toTest)  isAssignableFrom  验证是否启程

手机登陆
	加入过滤器到链上某个位置，=加在验证账号密码过滤器前  构建自定义的SmsCodeAuthenticationToken
	参照其他验证登陆方法  加入provider方法到providerManager  就会走上面的老路 provider.supports(SmsCodeAuthenticationToken)  
	执行对应provider中的authenticate  登陆成功

微信登陆
	
session 存储
