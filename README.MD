# 公众号自动推送早安问候以及天气预报

![](https://caoh2-images-1308318435.cos.ap-beijing.myqcloud.com/img/Snipaste_2022-08-23_13-59-51.png)

##  1. 注册微信测试账号，编辑推送模板

- 使用微信扫码登录此网站https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login就能得到一个微信公众号测试号

- 推送消息需要用到的信息

  ![](https://caoh2-images-1308318435.cos.ap-beijing.myqcloud.com/img/Snipaste_2022-08-23_14-03-34.png)

-  用户扫码关注得到用户的id 

  ![](https://caoh2-images-1308318435.cos.ap-beijing.myqcloud.com/img/Snipaste_2022-08-23_14-05-37.png)

-  编辑消息模板 

![](https://caoh2-images-1308318435.cos.ap-beijing.myqcloud.com/img/Snipaste_2022-08-23_14-06-36.png)

### 模板示例

    {{date.DATA}} 
    
    今日天气：{{weather.DATA}} 
    
    当前温度：{{temp.DATA}}
    
    最低温度:{{low-temp.DATA}}℃。 
    
    最高温度:{{high-temp.DATA}}℃。 
    
    风力：{{wind_class.DATA}}
    
    {{caihongpi.DATA}}
    
    神 回 复：
    小董：{{title.DATA}}
    小曹：{{content.DATA}}
## 2. 使用spring boot 做后台开发，并且与第三方对接

>  第三方接口——[控制台 | 百度地图开放平台 (baidu.com)](https://lbsyun.baidu.com/apiconsole/center#/home) 

- 在百度地图开放平台注册账号，并且到控制台中的应用创建一个应用，获取`应用AK`

![](https://caoh2-images-1308318435.cos.ap-beijing.myqcloud.com/img/Snipaste_2022-08-23_14-10-30.png)

- **设置ip白名单为`0.0.0.0/0`**



>  使用第三方接口——[天行数据TianAPI - 开发者API数据平台](https://www.tianapi.com/) 

- 注册账号选择需要的接口
- 每个接口都有实例代码，直接使用即可

![](https://caoh2-images-1308318435.cos.ap-beijing.myqcloud.com/img/Snipaste_2022-08-23_14-13-12.png)

### 项目配置

- 将申请好的各种信息填入配置文件即可
- cityCode可在cityCode.md文件中获取

```properties
#wechat
appID=
appSecret=
userID=
templateID=
#AK
baiduAK=
cityCode=
#APIKEY
apiKey=
```

## 3. 部署

![](https://caoh2-images-1308318435.cos.ap-beijing.myqcloud.com/img/Snipaste_2022-08-23_14-17-42.png)

> 上传jar包到服务器并且运行（服务器需自行购买，腾讯云阿里云新用户可以试用）

```sh
#运行命令/后台运行
nohup java -jar [包名].jar&
```

