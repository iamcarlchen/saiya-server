# saiya-server

发布步骤：
* 本地clean -> install 
* cd 到target目录
* 上传资源包 ： scp -r ./mokelay-server-1.0.22.jar root@xxxx:/opt/package
* 登录服务器 kill mokelay进程
* 参考/opt目录下的start.sh 启动
