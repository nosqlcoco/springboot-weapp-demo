# springboot-weapp-demo
微信小程序服务端接口，支持普通Http请求、上传文件、长连接。

注意：如果你是本地运行，需要修改为你本地对应的主机和端口。

提供公网测试接口:http//121.40.187.136:8090/

运行环境：JDK8+

#### 一、测试小程序wx.request接口
```javascript

	wx.request({
		url: 'http://localhost:8090/weappservice/test',
		
	    data: {'name':'xiaoqiang'},
	    
	    method: 'GET',//or POST
	    
	    //return JSON format,like: {"content":"Your name is xiaoqiang"}
	    success: function(res){
			console.log(res.content);
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
        url: 'http://localhost:8090/weappservice/uploadfile',
        
        //文件临时路径
        filePath: tempFilePath,
        
        name: 'file',
        
        header: {},
        
        formData: {},
        
        success: function(res){
          console.log(res)
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
		<td>-1</td>
		<td>请选择上传文件!</td>
	</tr>
	<tr>
		<td>-2</td>
		<td>文件上传失败</td>
	</tr>
</table>

#### 三、测试小程序wx.connectSocket接口
```javascript

	//发起websocket连接
    wx.connectSocket({
    	url: 'ws://localhost:8090/weappservice/websocket?name=xiaoqiang',
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
    //发送消息,
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