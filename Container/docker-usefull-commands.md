#### 启动docker
- `sudo systemctl restart docker.service`

- `docker pull` 命令可以从 Registry 下载镜像。
- `docker run` 命令则是先下载镜像（如果本地没有），然后再启动容器。

- 重启 docker daemon:
    - `systemctl daemon-reload`
    - `systemctl restart docker.service`

- `docker images` 查看本地容器镜像列表
- `docker history` 显示镜像构建历史
- `docker commit` 从容器构建新镜像
- `docker build` 从 Dockerfile 构建镜像
- `docker tag` 给镜像打标签
- `docker pull` 从 registry 下载镜像
- `docker push` 将镜像上传到 registry
- `docker rmi` 删除 Docker host 中的镜像
- `docker search` 搜索 Docker Hub 中的镜像
- `docker ps` 或 `docker container ls` 查看容器是否在运行
- `docker run -it <image>`: 启动并进入容器

- `docker run -it <container>` 以交互式方式进入容器
- `docler run -d <container>` 后台启动容器。
- `docker exec -it <container> bash` 交互式进入容器，查看问题。

- `docker start <container>` 启动容器(可以启动已经stop的容器)
- `docker stop <container>` 停止运行的容器。 本质上是向 docker host进程发送一个 `SIGTERM`信号， 如果想要快速停止容器，可以使用 `docker kill`命令。
- `docker restart <container>` 重启容器。
- `docker run -d --restart=always httpd` 无论容器因为什么原因退出(包括正常退出)，都立刻重启。
- `docker pause <container>` 暂停容器
- `docker unpause <container>` 恢复暂定的容器运行
- `docker rm <container> <container> ...` 删除容器。 已退出的容器依然会占用 host 的文件系统资源， 如果确认不会再重启此类容器，可以进行删除。 
- `docker rm -v $(docker ps -aq -f status=exited)` 删除所有已退出的容器。
- `docker logs <container>` 显示容器启动进程的控制台输出，用 `-f` 持续打印。 

### 删除容器和镜像
```sh
#!/bin/bash
# Delete all containers
docker rm $(docker ps -a -q)
# Delete all images
docker rmi $(docker images -q)


```




#### namespace



#### cgroup


#### iptables， bridge， net 

- https://www.ibm.com/developerworks/cn/linux/1310_xiawc_networkdevice/
- https://segmentfault.com/a/1190000009491002
- https://www.linuxprobe.com/chapter-08.html
- http://linux.vbird.org/linux_server/0250simple_firewall.php#nat_what