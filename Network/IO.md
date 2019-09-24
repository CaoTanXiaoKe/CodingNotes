### 改变文件描述符限制

```shell
# 查看文件描述符软限制
ulimit -n
ulimit -Sn
# 查看文件描述符硬限制
ulimit -Hn

# root 用户即能改文件描述符硬限制，又能改文件描述符软限制(软限制 <= 硬限制)
# 普通用户只能改文件描述符软限制；改变方法有三种：
# 1. 直接用 ulimit -n <number> 
#          ulimit -Sn <number>
#          ulimit -Hn <number>   # 只有root用户能够改硬限制。
#          做修改。只对向前shell起作用。重新登录后就失效了。
# 2. 通过 Pluggable Authentication Modules (PAM) module修改
#    <1> 编辑 /etc/security/limits.conf 添加上下面几行：
#            *       hard    nofile  20000
#            *       soft    nofile  15000
#            root    hard    nofile  20000
#            root    soft    nofile  15000
#     <2> 编辑 /etc/pam.d/login 或者/etc/pam.d/common-session 添加上下面一行：
#            session required pam_limits.so
```

#### [参考资料](https://linoxide.com/linux-how-to/03-methods-change-number-open-file-limit-linux/)