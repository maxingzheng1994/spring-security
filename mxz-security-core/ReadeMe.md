validate.code
��֤ģ��
����  ValidateController   /code/{type} 
    ����ValidateCodeProcessorHolder   findValidateCodeProcessor
    �����и������͵�����������֤��    ValidateCodeProcessor (������֤�봴������֤)

�罻ģ��
SociaConfig   
ע�����ݿⰡ
	������loginpage  �����	 BrowserSecurityController ��ȡ���ʵ�url  �ж��Ƿ�����.html��β
	�ǵ���ת��login���� 
	��½��ס�� ���� ����  �ֿ� persistentTokenRepository(JdbcTokenRepositoryImpl) ʱ�䣬 ����service
	��usernamepasswordauthenticationfilter ��½�ɹ������providerManager ��ȡ�������RememberMeAuthenticationProvider
	���������Ǹ�֧��provider.supports(toTest)  isAssignableFrom  ��֤�Ƿ�����

�ֻ���½
	���������������ĳ��λ�ã�=������֤�˺����������ǰ  �����Զ����SmsCodeAuthenticationToken
	����������֤��½����  ����provider������providerManager  �ͻ����������· provider.supports(SmsCodeAuthenticationToken)  
	ִ�ж�Ӧprovider�е�authenticate  ��½�ɹ�

΢�ŵ�½
	
session �洢