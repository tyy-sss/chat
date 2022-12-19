package chat.comment;

//消息类型
public interface MessageType {
    //登录
    String MESSAGE_LOGIN="0";//请求登录
    String MESSAGE_LOGIN_SUCCEED ="1";//登录成功
    String MESSAGE_LOGIN_FAIL ="2";//登录失败
    //退出登录
    String MESSAGE_CLOSE = "3";//退出登录
    String MESSAGE_CLOSE_SUCCEED ="4";//退出登录成功
    //发送邮箱
    String MESSAGE_EmailSend="5";//请求发送邮箱
    String MESSAGE_EmailSend_SUCCEED="6";//发送邮箱成功
    String MESSAGE_EmailSend_FAIL="7";//发送邮箱失败
    String MESSAGE_EmailSend1="8";
    //注册,申请账号
    String MESSAGE_REGISTER="9";//请求注册
    String MESSAGE_REGISTER_SUCCEED="10";//注册成功
    //登录
    String MESSAGE_ID="11";//判断ID是不是正确的
    String MESSAGE_ID_SUCCEED="12";//是ID
    String MESSAGE_ID_FAIL="13";//不是ID
    //查询有没有这个id，添加好友
    String MESSAGE_ID1="14";//请求查询，个人资料
    String MESSAGE_ID1_SUCCEED="15";//是ID
    String MESSAGE_ID2="16";
    String MESSAGE_ID2_SUCCEED="17";//好友
    String MESSAGE_ID2_GROUP_SUCCEED="18";//群聊
    String MESSAGE_ID2_FAIL="19";
    //忘记密码，修改密码
    String MESSAGE_PASSWORD="20";//重写密码
    String MESSAGE_PASSWORD_SUCCEED="21";//重写密码正确
    String MESSAGE_PASSWORD_FAIL="22";//重写密码错误
    String MESSAGE_CHANGE_PASSWORD="23";//忘记密码
    //修改个人信息
    String MESSAGE_CHANGE="24";//修改
    String MESSAGE_CHANGE_SUCCEED="25";//修改成功
    String MESSAGE_CHANGE_FAIl="26";//修改失败
    //添加好友
    String MESSAGE_ADD_FRIEND="27";//添加好友申请
    //添加群聊
    String MESSAGE_ADD_GROUP= "28";
    //对方请求添加好友
    String MESSAGE_REQUEST_SUCCEED="29";//对方同意添加好友
    String MESSAGE_REQUEST_GROUP_SUCCEED="30";
    String MESSAGE_REQUEST_FAIL="31";//对方不同意添加好友
    String MESSAGE_REQUEST_GROUP_FAIL="32";
    //查看好友申请
    String MESSAGE_SEEK="33";
    String MESSAGE_SEEK_SUCCEED="34";//有好友申请
    String MESSAGE_SEEK_FAIL="35";//没有好友申请
    //查看群聊申请
    String MESSAGE_SEEK_GROUP="36";
    String MESSAGE_SEEK_GROUP_SUCCEED="37";
    String MESSAGE_SEEK_GROUP_FAIL="38";
    //加载所有的好友
    String MESSAGE_FIND_FRIEND="39";//查看所有的好友
    String MESSAGE_FIND_FRIEND_SUCCEED="40";//查看所有的好友
    //获取所有聊天记录
    String MESSAGE_FIND_INFORMATION="41";//获取所有的消息
    String MESSAGE_FIND_INFORMATION_SUCCEED="42";
    String MESSAGE_SEND_INFORMATION="43";//发送消息
    String MESSAGE_RECEIVE_INFORMATION="44";//接收消息
    //判断验证码的时效性
    String MESSAGE_ON_TIME="45";
    String MESSAGE_ON_TIME_SUCCEED="46";//在时间内
    //删除好友
    String MESSAGE_DELETE_FRIEND="47";//删除好友
    String MESSAGE_DELETE_FRIEND_SUCCEED="48";//删除好友成功
    //创造群聊成功
    String MESSAGE_CREAT_GROUP="49";
    String MESSAGE_CREAT_GROUP_SUCCEED="50";
    //查找群聊
    String MESSAGE_FIND_GROUP="51";
    String MESSAGE_FIND_GROUP_SUCCEED="52";
    //判断是群聊还是好友
    String MESSAGE_IS_FRIEND="53";
    String MESSAGE_IS_FRIEND_SUCCEED="54";//是好友
    String MESSAGE_IS_FRIEND_FAIL="55";//不是好友
    //查看所有的群消息
    String MESSAGE_FIND_GROUP_INFORMATION="56";
    String MESSAGE_FIND_GROUP_INFORMATION_SUCCEED="57";
    //查看所有的群成员
    String MESSAGE_FIND_GROUP_MEMBER="58";
    String MESSAGE_FIND_GROUP_MEMBER_SUCCEED="59";
    //退出群聊
    String MESSAGE_OUT_GROUP="60";
    //删除群聊
    String MESSAGE_DELETE_GROUP="61";
    //判断好友是否在线
    String MESSAGE_FIND_ONLINE="62";
    String MESSAGE_FIND_ONLINE_SUCCEED="63";
    //修改群消息
    String MESSAGE_CHANGE_GROUP="64";
    String MESSAGE_CHANGE_GROUP_SUCCEED="65";
    //群聊删人
    String MESSAGE_DELETE_GROUP_PEOPLE="66";
    String MESSAGE_DELETE_GROUP_PEOPLE_SUCCEED="67";
    //群聊设置管理员
    String MESSAGE_SET_GROUP_MANAGE="68";
    String MESSAGE_SET_GROUP_MANAGE_SUCCEED="69";
    String MESSAGE_DELETE_GROUP_MANAGE="70";
    String MESSAGE_DELETE_GROUP_MANAGE_SUCCEED="71";
    String MESSAGE_CHANGE_GROUP_POWER="72";
    String MESSAGE_CHANGE_GROUP_POWER_SUCCEED="73";
    //创建群聊是拉人
    String MESSAGE_CREAT_GROUP_PEOPLE="74";
    //发送文件
    String MESSAGE_SEND_DOCUMENT="75";
    //查看所有的常用语
    String MESSAGE_CHECK_COMMENT="76";
    String MESSAGE_CHECK_COMMENT_SUCCEED="77";
    String MESSAGE_ADD_COMMENT="78";
    String MESSAGE_ADD_COMMENT_SUCCEED="79";
    //从服务端里下载文件
    String MESSAGE_DOWN_DOCUMENT="80";
    String MESSAGE_DOWN_DOCUMENT_SUCCEED="81";
    //模糊查找群和好友
    String MESSAGE_SEEK_LIKE="82";
    String MESSAGE_SEEK_LIKE_SUCCEED="83";
    //得到一个群的消息
    String MESSAGE_SEEK_GROUP_NEWS="84";
    String MESSAGE_SEEK_GROUP_NEWS_SUCCEED="85";
}
