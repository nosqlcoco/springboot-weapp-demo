# springboot-weapp-demo
微信小程序服务端接口，支持普通Http请求、上传文件、长连接。后台服务使用springboot框架搭建，mongodb做数据库，redis做缓存。

运行环境：JDK8+

<font color="red">注意：如果你是本地运行，需要修改为你本地对应的主机和端口。</font>
长连接需使用ws协议

####更新日志:
- 2016-11-24
	- 小程序code换取session_key和openid
	- 小程序登录用户敏感数据解密
	
- 2016-11-22
	- 配置Https
	
- 2016-11-18
	- 重写小程序http测试和上传文件接口
	- 统一接口返回返回状态码和格式
	
- 2016-11-20
	- 添加Redis缓存
	- 添加微信登录状态维护和用户数据解密接口

#### 一、测试小程序wx.request接口
```javascript

wx.request({
	url: 'http://localhost:9090/weappservice/api/v1/user/get/{id}',
	
    data: {appId: 'JWEJIJ345QHWJKENVKF', apiName: 'GET_USER'},
    
    method: 'GET',
    
    //return JSON format,like: {"id":"{id}"}
    success: function(res){
		console.log(res.data);
    },
    fail: function(res){
    
    },
    complete: function(res){
    
    }
});
```

#### 二、测试小程序wx.uploadFile接口,单张上传
```javascript

wx.uploadFile({
    url: 'http://localhost:9090/weappservice/api/v1/upload/image',
    
    //文件临时路径
    filePath: tempFilePath,
    
    name: 'file',
    
    header: {},
    
    formData: {appId: 'JWEJIJ345QHWJKENVKF', apiName: 'UPLOAD_IMAGE'},
    
    success: function(res){
      console.log(res.data)
    },
    
    fail: function(res){
    
    },
    
    complete: function(res){
    
    }
})；
```

<table>
	<tr>
		<th>状态码(errcode)</th>
		<th>说明(msg)</th>
	</tr>
	<tr>
		<td>0</td>
		<td>图片路径</td>
	</tr>
	<tr>
		<td>40010</td>
		<td>请选择上传文件!</td>
	</tr>
	<tr>
		<td>40011</td>
		<td>文件上传失败</td>
	</tr>
</table>

#### 三、测试小程序websocket相关接口
```javascript

//发起websocket连接
wx.connectSocket({
	url: 'ws://localhost:9090/weappservice/websocket?name=xiaoqiang',
  	//这里写了参数，但是参数没有发送出去，大家可以试试，已经邮件反馈微信团队了，等待回复。所以把参数拼接在url后面。
  	data: {
  		'name1': 'xiaoqiang1'
  	}
}),

//监听打开事件
wx.onSocketOpen(function(res) {
  	console.log('WebSocket连接已打开！');
}),

//接收消息，接收的消息是json字符串，需要JSON.parse转成JSON对象
wx.onSocketMessage(function(res){
	var data = JSON.parse(res.data);
	console.log(data);
}),

//发送消息,消息对象属性key(user和content)不能自定义。
wx.sendSocketMessage({
    data: JSON.stringify({
      user: 'xiaoqaing',
      content: 'Hi, My name is xiaoqiang'
    }),
    success: function(res){
    	console.log('消息发送成功！')
    }
})
```

我的微信小程序DEMO：[weixin_smallexe](https://github.com/cocoli/weixin_smallexe)
